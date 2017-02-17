package exception;

public class WrongBoardSizeException extends RuntimeException {

    public WrongBoardSizeException() {

    }

    public WrongBoardSizeException(String message) {
        super(message);
    }
}
