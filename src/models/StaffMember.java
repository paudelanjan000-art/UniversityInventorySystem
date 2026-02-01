package models;
public class StaffMember {

    private int staffId;
    private String name;
    private String email;

    private Equipment[] assignedEquipment; // max 5

    public StaffMember(int staffId, String name, String email) {
        this.staffId = staffId;
        this.name = name;
        this.email = email;
        this.assignedEquipment = new Equipment[5];
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // adds equipment to the assigned equipment array
    public boolean addAssignedEquipment(Equipment equipment) {
        if (equipment == null) return false;

        for (int i = 0; i < assignedEquipment.length; i++) {
            if (assignedEquipment[i] == null) {
                assignedEquipment[i] = equipment;
                return true;
            }
        }
        return false; // array full (max 5)
    }

    // removes equipment from the assigned equipment array
    public boolean removeAssignedEquipment(String assetId) {
        if (assetId == null) return false;

        for (int i = 0; i < assignedEquipment.length; i++) {
            if (assignedEquipment[i] != null &&
                    assetId.equals(assignedEquipment[i].getAssetId())) {
                assignedEquipment[i] = null;
                return true;
            }
        }
        return false;
    }

    // returns the number of currently assigned equipment items
    public int getAssignedEquipmentCount() {
        int count = 0;
        for (Equipment e : assignedEquipment) {
            if (e != null) count++;
        }
        return count;
    }
}
