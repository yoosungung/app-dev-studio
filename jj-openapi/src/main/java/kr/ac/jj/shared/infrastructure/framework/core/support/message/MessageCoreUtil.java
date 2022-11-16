package kr.ac.jj.shared.infrastructure.framework.core.support.message;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;

public class MessageCoreUtil {

    public static String getDefaultMessage(String code, String defaultMessage) {
        ReloadableAllBasenamesMessageSource messageSource = ApplicationContextUtil
                .getConfigBean(ReloadableAllBasenamesMessageSource.class);

        String invalidPrefix = StringUtils.defaultString(messageSource.getInvalidPrefix(), "");
        String invalidSuffix = StringUtils.defaultString(messageSource.getInvalidSuffix(), "");

        return invalidPrefix + (StringUtils.isEmpty(defaultMessage) ? code : defaultMessage) + invalidSuffix;
    }

}
