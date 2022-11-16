package kr.ac.jj.survey.infrastructure.framework.core.support.message;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.jj.survey.config.props.ConfigProperties;

@Component
public class MessageCoreUtil {
    private static ConfigProperties configProperties;

    private MessageCoreUtil(@Autowired ConfigProperties configProperties) {
        MessageCoreUtil.configProperties = configProperties;
    }

    public static String getDefaultMessage(String code, String defaultMessage) {
        return configProperties.getMessage().getInvalidText().getPrefix() //
                + (StringUtils.isEmpty(defaultMessage) ? code : defaultMessage) //
                + configProperties.getMessage().getInvalidText().getPrefix();
    }
}
