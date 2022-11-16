package kr.ac.jj.shared.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kr.ac.jj.shared.infrastructure.framework.web.multipart.exception.FileUploadExcludeExtensionsException;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.exception.FileUploadIncludeExtensionsException;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.exception.FileUploadMaxFileSizeException;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.exception.FileUploadTotalFileSizeException;
import kr.ac.jj.shared.infrastructure.framework.web.servlet.error.ServletErrorConfig;

@Configuration
public class SharedExceptionConfig extends ServletErrorConfig {

    @PostConstruct
    public void init() {
        this.setServletErrorInfo(UsernameNotFoundException.class, this.getVisableBizErrorInfo());
        this.setServletErrorInfo(BadCredentialsException.class, this.getVisableBizErrorInfo());
        this.setServletErrorInfo(DisabledException.class, this.getVisableBizErrorInfo());
        this.setServletErrorInfo(AccountExpiredException.class, this.getVisableBizErrorInfo());
        this.setServletErrorInfo(CredentialsExpiredException.class, this.getVisableBizErrorInfo());
        this.setServletErrorInfo(LockedException.class, this.getVisableBizErrorInfo());
        this.setServletErrorInfo(InternalAuthenticationServiceException.class, this.getVisableBizErrorInfo());
        this.setServletErrorInfo(FileUploadIncludeExtensionsException.class, this.getVisableBizErrorInfo());
        this.setServletErrorInfo(FileUploadExcludeExtensionsException.class, this.getVisableBizErrorInfo());
        this.setServletErrorInfo(FileUploadMaxFileSizeException.class, this.getVisableBizErrorInfo());
        this.setServletErrorInfo(FileUploadTotalFileSizeException.class, this.getVisableBizErrorInfo());
    }

}
