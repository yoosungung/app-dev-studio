package kr.ac.jj.shared.infrastructure.framework.core.support.message;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.jj.shared.config.props.SharedConfigProperties;

@Component
public class MessageCoreUtil {

    private static SharedConfigProperties configProperties;

    private MessageCoreUtil(@Autowired SharedConfigProperties configProperties) {
        MessageCoreUtil.configProperties = configProperties;
    }

    public static String getDefaultMessage(String code, String defaultMessage) {
        return configProperties.getMessage().getInvalidText().getPrefix() //
                + (StringUtils.isEmpty(defaultMessage) ? code : defaultMessage) //
                + configProperties.getMessage().getInvalidText().getPrefix();
    }

}
