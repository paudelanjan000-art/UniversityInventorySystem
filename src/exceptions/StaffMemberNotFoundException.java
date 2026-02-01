package exceptions;
public class StaffMemberNotFoundException extends InventoryException {

    private static final long serialVersionUID = 1L;

    public StaffMemberNotFoundException(String message) {
        super(message);
    }
}
