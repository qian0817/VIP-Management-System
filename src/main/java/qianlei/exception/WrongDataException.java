package qianlei.exception;

/**
 * service的数据错误
 *
 * @author qianlei
 */
public class WrongDataException extends RuntimeException {
    public WrongDataException(String message) {
        super(message);
    }

}
