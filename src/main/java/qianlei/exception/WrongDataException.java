package qianlei.exception;

/**
 * 输入的数据错误
 *
 * @author qianlei
 */
public class WrongDataException extends Exception {
    public WrongDataException(String message) {
        super(message);
    }
}
