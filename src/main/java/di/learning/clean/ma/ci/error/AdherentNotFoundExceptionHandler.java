package di.learning.clean.ma.ci.error;

public class AdherentNotFoundExceptionHandler extends Exception{
    public AdherentNotFoundExceptionHandler() {
        super();
    }

    public AdherentNotFoundExceptionHandler(String message) {
        super(message);
    }

    public AdherentNotFoundExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public AdherentNotFoundExceptionHandler(Throwable cause) {
        super(cause);
    }

    protected AdherentNotFoundExceptionHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
