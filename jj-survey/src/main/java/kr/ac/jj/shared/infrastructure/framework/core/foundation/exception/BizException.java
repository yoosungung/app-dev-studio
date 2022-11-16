package kr.ac.jj.shared.infrastructure.framework.core.foundation.exception;

public class BizException extends BaseException {

    private static final long serialVersionUID = 577016911774547784L;

    /**
     * 기본 생성자.
     */
    public BizException() {
        super();
    }

    /**
     * 상세메시지를 전달받는 생성자.
     *
     * @param message
     */
    public BizException(String message) {
        super(message);
    }

    /**
     * 상세메시지 및 <code>Throwable</code> 객체를 전달받는 생성자.
     *
     * @param message
     * @param cause
     */
    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <code>Throwable</code> 객체를 전달받는 생성자.
     *
     * @param cause
     */
    public BizException(Throwable cause) {
        super(cause);
    }

}
