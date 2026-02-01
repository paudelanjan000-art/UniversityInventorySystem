package exceptions;
public class EquipmentNotAvailableException extends InventoryException {

    private static final long serialVersionUID = 1L;

    public EquipmentNotAvailableException(String message) {
        super(message);
    }
}
