package managers;
import exceptions.AssignmentLimitExceededException;
import exceptions.EquipmentNotAvailableException;
import exceptions.StaffMemberNotFoundException;
import models.Equipment;
import models.StaffMember;

public class InventoryManager {

    // Assign equipment to staff
    public void assignEquipment(StaffMember staff, Equipment equipment)
            throws EquipmentNotAvailableException, AssignmentLimitExceededException {

        if (staff == null) {
            throw new AssignmentLimitExceededException("Staff member not found.");
        }

        if (!equipment.isAvailable()) {
            throw new EquipmentNotAvailableException("Equipment is not available.");
        }

        if (staff.getAssignedEquipmentCount() >= 5) {
            throw new AssignmentLimitExceededException("Staff already has maximum equipment assigned.");
        }

        staff.addAssignedEquipment(equipment);
        equipment.setAvailable(false);
    }

    // Return equipment from staff
    public void returnEquipment(StaffMember staff, String assetId)
            throws StaffMemberNotFoundException {

        if (staff == null) {
            throw new StaffMemberNotFoundException("Staff member not found.");
        }

        boolean removed = staff.removeAssignedEquipment(assetId);

        if (!removed) {
            throw new StaffMemberNotFoundException("Equipment not found with staff.");
        }
    }

    // Maintenance fee calculation using switch
    public double calculateMaintenanceFee(Equipment equipment, int daysOverdue) {

        double feePerDay;

        switch (equipment.getCategory()) {
            case "Computers":
                feePerDay = 5.0;
                break;
            case "Lab":
                feePerDay = 10.0;
                break;
            case "Furniture":
                feePerDay = 2.0;
                break;
            default:
                feePerDay = 3.0;
        }

        return feePerDay * daysOverdue;
    }

    // -------- SEARCH METHODS (OVERLOADING) --------

    // Search by name
    public void searchEquipment(String name) {
        System.out.println("Searching equipment by name: " + name);
    }

    // Search by category and availability
    public void searchEquipment(String category, boolean availableOnly) {
        System.out.println("Searching equipment in category: " + category +
                ", Available only: " + availableOnly);
    }

    // Search by warranty range
    public void searchEquipment(int minWarranty, int maxWarranty) {
        System.out.println("Searching equipment with warranty between " +
                minWarranty + " and " + maxWarranty + " months");
    }

    // Validation with nested if-else
    public boolean validateAssignment(StaffMember staff, Equipment equipment) {

        if (staff != null) {
            if (equipment != null) {
                if (equipment.isAvailable()) {
                    if (staff.getAssignedEquipmentCount() < 5) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
