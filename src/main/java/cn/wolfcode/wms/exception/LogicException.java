package cn.wolfcode.wms.exception;

public class LogicException extends RuntimeException {
    public LogicException() {
        super();
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(Throwable cause) {
        super(cause);
    }
}
