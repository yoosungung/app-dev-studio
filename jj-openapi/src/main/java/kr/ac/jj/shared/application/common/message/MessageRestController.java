package kr.ac.jj.shared.application.common.message;

import java.util.Locale;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.shared.infrastructure.framework.core.support.message.ReloadableAllBasenamesMessageSource;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;

/**
 * 메시지 RestController
 */
@RestController
@RequestMapping(path = "/common/message/Message", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class MessageRestController {

    private @Autowired ReloadableAllBasenamesMessageSource messageSource;

    /**
     * 전체 메시지 조회
     *
     * @return
     */
    @RequestMapping(path = "/readAllMessage.do")
    public Properties readAllMessage() {
        Locale locale = RequestContextUtil.getLocale();

        Properties mergedProperties = messageSource.getMergedPropertiesProperties(locale);

        return mergedProperties;
    }

}
