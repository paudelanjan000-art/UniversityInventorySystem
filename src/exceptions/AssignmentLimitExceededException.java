package exceptions;
public class AssignmentLimitExceededException extends InventoryException {

    private static final long serialVersionUID = 1L;

    public AssignmentLimitExceededException(String message) {
        super(message);
    }
}
