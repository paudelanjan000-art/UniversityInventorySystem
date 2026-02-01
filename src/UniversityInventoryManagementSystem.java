import java.util.Scanner;

import exceptions.EquipmentNotAvailableException;
import exceptions.InventoryException;
import exceptions.StaffMemberNotFoundException;
import managers.InventoryManager;
import managers.InventoryReports;
import models.Equipment;
import models.InventoryItem;
import models.StaffMember;

public class UniversityInventoryManagementSystem {

    private static InventoryItem[] inventory = new InventoryItem[50];
    private static StaffMember[] staffMembers = new StaffMember[20];

    private static int inventoryCount = 0;
    private static int staffCount = 0;

    private static InventoryManager manager = new InventoryManager();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = readInt(sc);

            try {
                switch (choice) {
                    case 1:
                        addNewEquipment(sc);
                        break;
                    case 2:
                        registerStaffMember(sc);
                        break;
                    case 3:
                        assignEquipmentToStaff(sc);
                        break;
                    case 4:
                        returnEquipmentFromStaff(sc);
                        break;
                    case 5:
                        searchInventory(sc);
                        break;
                    case 6:
                        generateReports();
                        break;
                    case 7:
                        System.out.println("Exiting system. Goodbye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option. Choose 1–7.");
                }
            } catch (InventoryException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=== University Inventory Management System ===");
        System.out.println("1. Add new equipment");
        System.out.println("2. Register a new staff member");
        System.out.println("3. Assign equipment to staff");
        System.out.println("4. Return equipment");
        System.out.println("5. Search inventory");
        System.out.println("6. Generate reports");
        System.out.println("7. Exit system");
        System.out.print("Choose an option: ");
    }

    private static void addNewEquipment(Scanner sc) {
        System.out.print("Enter item id: ");
        String id = sc.nextLine();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Available (true/false): ");
        boolean available = readBoolean(sc);

        System.out.print("Enter assetId: ");
        String assetId = sc.nextLine();

        System.out.print("Enter brand: ");
        String brand = sc.nextLine();

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Enter warranty months: ");
        int warranty = readInt(sc);

        inventory[inventoryCount++] =
                new Equipment(id, name, available, assetId, brand, category, warranty);

        System.out.println("✅ Equipment added.");
    }

    private static void registerStaffMember(Scanner sc) {
        System.out.print("Enter staff ID: ");
        int id = readInt(sc);

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        staffMembers[staffCount++] = new StaffMember(id, name, email);
        System.out.println("✅ Staff member registered.");
    }

    private static void assignEquipmentToStaff(Scanner sc)
            throws InventoryException {

        StaffMember staff = findStaff(sc);
        Equipment eq = findEquipment(sc);

        manager.assignEquipment(staff, eq);
        System.out.println("✅ Equipment assigned.");
    }

    private static void returnEquipmentFromStaff(Scanner sc)
            throws InventoryException {

        StaffMember staff = findStaff(sc);

        System.out.print("Enter assetId to return: ");
        String assetId = sc.nextLine();

        Equipment eq = getEquipmentByAssetId(assetId);
        if (eq != null) eq.setAvailable(true);

        manager.returnEquipment(staff, assetId);
        System.out.println("✅ Equipment returned.");
    }

    private static void searchInventory(Scanner sc) {
        System.out.println("Search by:");
        System.out.println("1. Name");
        System.out.println("2. Category + availability");
        System.out.println("3. Warranty range");

        int opt = readInt(sc);

        switch (opt) {
            case 1:
                System.out.print("Name: ");
                manager.searchEquipment(sc.nextLine());
                break;
            case 2:
                System.out.print("Category: ");
                String cat = sc.nextLine();
                System.out.print("Available only (true/false): ");
                boolean avail = readBoolean(sc);
                manager.searchEquipment(cat, avail);
                break;
            case 3:
                System.out.print("Min warranty: ");
                int min = readInt(sc);
                System.out.print("Max warranty: ");
                int max = readInt(sc);
                manager.searchEquipment(min, max);
                break;
        }
    }

    private static void generateReports() {
        InventoryReports reports =
                new InventoryReports(inventory, staffMembers);

        reports.generateInventoryReport();
        reports.findExpiredWarranties();
        reports.displayAssignmentsByDepartment();
        reports.calculateUtilisationRate();
        reports.generateMaintenanceSchedule();
    }

    private static StaffMember findStaff(Scanner sc) throws StaffMemberNotFoundException {
        System.out.print("Enter staff ID: ");
        int id = readInt(sc);

        for (int i = 0; i < staffCount; i++) {
            if (staffMembers[i].getStaffId() == id) return staffMembers[i];
        }
        throw new StaffMemberNotFoundException("Staff not found.");
    }

    private static Equipment findEquipment(Scanner sc) throws EquipmentNotAvailableException {
        System.out.print("Enter equipment assetId: ");
        String assetId = sc.nextLine();

        Equipment eq = getEquipmentByAssetId(assetId);
        if (eq == null) throw new EquipmentNotAvailableException("Equipment not found.");
        return eq;
    }

    private static Equipment getEquipmentByAssetId(String assetId) {
        for (int i = 0; i < inventoryCount; i++) {
            if (inventory[i] instanceof Equipment) {
                Equipment eq = (Equipment) inventory[i];
                if (assetId.equals(eq.getAssetId())) return eq;
            }
        }
        return null;
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Enter a number: ");
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }

    private static boolean readBoolean(Scanner sc) {
        while (!sc.hasNextBoolean()) {
            sc.nextLine();
            System.out.print("Enter true or false: ");
        }
        boolean v = sc.nextBoolean();
        sc.nextLine();
        return v;
    }
}
