package di.learning.clean.ma.ci.error;

public class AssignmentNotFoundExceptionHandler extends Exception{
    public AssignmentNotFoundExceptionHandler() {
        super();
    }

    public AssignmentNotFoundExceptionHandler(String message) {
        super(message);
    }

    public AssignmentNotFoundExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public AssignmentNotFoundExceptionHandler(Throwable cause) {
        super(cause);
    }

    protected AssignmentNotFoundExceptionHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
