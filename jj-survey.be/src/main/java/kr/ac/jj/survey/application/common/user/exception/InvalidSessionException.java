package kr.ac.jj.survey.application.common.user.exception;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;

public class InvalidSessionException extends BaseException {
    private static final long serialVersionUID = -706232344227417575L;

    /**
     * 기본 생성자.
     */
    public InvalidSessionException() {
        super();
    }

    /**
     * 상세메시지를 전달받는 생성자.
     *
     * @param message
     */
    public InvalidSessionException(String message) {
        super(message);
    }

    /**
     * 상세메시지 및 <code>Throwable</code> 객체를 전달받는 생성자.
     *
     * @param message
     * @param cause
     */
    public InvalidSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * <code>Throwable</code> 객체를 전달받는 생성자.
     *
     * @param cause
     */
    public InvalidSessionException(Throwable cause) {
        super(cause);
    }
}
