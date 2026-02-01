package models;
public class Equipment extends InventoryItem {

    private String assetId;
    private String brand;
    private String category;
    private int warrantyMonths;

    public Equipment(String id, String name, boolean isAvailable,
                     String assetId, String brand, String category, int warrantyMonths) {
        super(id, name, isAvailable);
        this.assetId = assetId;
        this.brand = brand;
        this.category = category;
        this.warrantyMonths = warrantyMonths;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getItemType() {
        return "Equipment";
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", isAvailable=" + isAvailable() +
                ", assetId='" + assetId + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", warrantyMonths=" + warrantyMonths +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Equipment)) return false;
        Equipment other = (Equipment) obj;
        return assetId != null && assetId.equals(other.assetId);
    }
}
