package kr.ac.jj.survey.infrastructure.framework.web.context.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;

import kr.ac.jj.survey.infrastructure.framework.core.support.message.MessageCoreUtil;
import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;

public class MessageUtil {
    private static final Logger log = LoggerFactory.getLogger(MessageUtil.class);

    private static MessageSourceAccessor messageSourceAccessor;

    public synchronized void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        MessageUtil.messageSourceAccessor = messageSourceAccessor;
    }

    public static boolean exists(String code) {
        if (messageSourceAccessor == null) {
            return false;
        }

        try {
            return messageSourceAccessor.getMessage(code, RequestContextUtil.getLocale()) != null;
        } catch (NoSuchMessageException e) {
            if (code != null && !code.startsWith("any.codeData.firstName.")) {
                log.error(e.getMessage(), e);
            }
            return false;
        }
    }

    public static String getMessage(String code) {
        if (messageSourceAccessor == null) {
            return null;
        }

        return messageSourceAccessor.getMessage(code, null, MessageCoreUtil.getDefaultMessage(code, null),
                RequestContextUtil.getLocale());
    }

    public static String getMessage(String code, String defaultMessage) {
        if (messageSourceAccessor == null) {
            return null;
        }

        return messageSourceAccessor.getMessage(code, null, MessageCoreUtil.getDefaultMessage(code, defaultMessage),
                RequestContextUtil.getLocale());
    }

    public static String getMessage(String code, Object[] args) {
        if (messageSourceAccessor == null) {
            return null;
        }

        return messageSourceAccessor.getMessage(code, args, MessageCoreUtil.getDefaultMessage(code, null),
                RequestContextUtil.getLocale());
    }

    public static String getMessage(String code, Object[] args, String defaultMessage) {
        if (messageSourceAccessor == null) {
            return null;
        }

        return messageSourceAccessor.getMessage(code, args, MessageCoreUtil.getDefaultMessage(code, defaultMessage),
                RequestContextUtil.getLocale());
    }

    public static String getMessage(String code, String defaultMessage, Object[] args) {
        if (messageSourceAccessor == null) {
            return null;
        }

        return messageSourceAccessor.getMessage(code, args, MessageCoreUtil.getDefaultMessage(code, defaultMessage),
                RequestContextUtil.getLocale());
    }
}
