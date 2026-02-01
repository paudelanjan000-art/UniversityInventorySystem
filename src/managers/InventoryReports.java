package managers;
import models.Equipment;
import models.InventoryItem;
import models.StaffMember;

public class InventoryReports {

    private InventoryItem[] items;      // all inventory items (polymorphism)
    private StaffMember[] staffMembers; // to show assignments report

    public InventoryReports(InventoryItem[] items, StaffMember[] staffMembers) {
        this.items = items;
        this.staffMembers = staffMembers;
    }

    // 1) for loop: display all items with status
    public void generateInventoryReport() {
        System.out.println("=== Inventory Report ===");

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println(items[i] + " | Type: " + items[i].getItemType());
            }
        }
    }

    // 2) while loop: show equipment items with expired warranties
    // (0 months warranty = expired)
    public void findExpiredWarranties() {
        System.out.println("=== Expired Warranties Report ===");

        int i = 0;
        while (i < items.length) {
            if (items[i] instanceof Equipment) {
                Equipment eq = (Equipment) items[i];
                if (eq.getWarrantyMonths() == 0) {
                    System.out.println(eq);
                }
            }
            i++;
        }
    }

    // 3) foreach loop: group assignments by "department"
    // NOTE: Your StaffMember class doesn’t have department, so we’ll assume:
    // department = email domain (example: it@uni.edu -> "uni.edu")
    public void displayAssignmentsByDepartment() {
        System.out.println("=== Assignments By Department (email domain) ===");

        for (StaffMember staff : staffMembers) {
            if (staff == null) continue;

            String dept = "unknown";
            if (staff.getEmail() != null && staff.getEmail().contains("@")) {
                dept = staff.getEmail().substring(staff.getEmail().indexOf("@") + 1);
            }

            System.out.println("\nDepartment: " + dept);
            System.out.println("Staff: " + staff.getName() + " (ID: " + staff.getStaffId() + ")");
            System.out.println("Assigned Count: " + staff.getAssignedEquipmentCount());
        }
    }

    // 4) nested loops: calculate utilisation rate (simple stats)
    // We'll define utilisation as:
    // (number of assigned equipment / total equipment) * 100
    public void calculateUtilisationRate() {
        int totalEquipment = 0;
        int assignedEquipment = 0;

        // count total equipment items in inventory
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof Equipment) {
                totalEquipment++;
            }
        }

        // count assigned equipment using nested loops
        for (int i = 0; i < staffMembers.length; i++) {
            if (staffMembers[i] == null) continue;

            // We don't have a getter to list equipment array,
            // so we use count only (simple and acceptable)
            assignedEquipment += staffMembers[i].getAssignedEquipmentCount();
        }

        double rate = 0;
        if (totalEquipment > 0) {
            rate = ((double) assignedEquipment / totalEquipment) * 100;
        }

        System.out.println("=== Utilisation Rate ===");
        System.out.println("Total Equipment: " + totalEquipment);
        System.out.println("Assigned Equipment: " + assignedEquipment);
        System.out.printf("Utilisation Rate: %.2f%%\n", rate);
    }

    // 5) do-while loop: generate maintenance schedule
    // We'll generate next 5 "maintenance days"
    public void generateMaintenanceSchedule() {
        System.out.println("=== Maintenance Schedule (Next 5 items) ===");

        int index = 0;
        int count = 0;

        do {
            if (index >= items.length) break;

            if (items[index] instanceof Equipment) {
                Equipment eq = (Equipment) items[index];
                System.out.println("Maintenance Slot " + (count + 1) + ": " + eq.getName()
                        + " (Asset: " + eq.getAssetId() + ")");
                count++;
            }

            index++;
        } while (count < 5);
    }
}
