package Model.Exception;

public class OutOfTimeException extends Exception{
    private static final long serialVersionUID = 1L;

    public OutOfTimeException(String msg) {
        super(msg);
    }
}
