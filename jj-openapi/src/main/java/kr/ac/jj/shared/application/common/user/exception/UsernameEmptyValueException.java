package kr.ac.jj.shared.application.common.user.exception;

import org.springframework.security.core.AuthenticationException;

public class UsernameEmptyValueException extends AuthenticationException {

    private static final long serialVersionUID = 625782564882006573L;

    public UsernameEmptyValueException(String msg) {
        super(msg);
    }

}
