package kr.ac.jj.survey.infrastructure.framework.core.persistence.dao.util;

import java.util.Map;

import org.springframework.context.ApplicationContext;

import kr.ac.jj.survey.infrastructure.framework.core.persistence.dao.handler.DataUpdatedCheckHandler;
import kr.ac.jj.survey.infrastructure.framework.core.support.context.ApplicationContextUtil;

public class DataUpdatedCheckUtil {
    public static void setDisabled(boolean disabled) {
        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
        Map<String, DataUpdatedCheckHandler> handlerMap = applicationContext
                .getBeansOfType(DataUpdatedCheckHandler.class);

        for (DataUpdatedCheckHandler handler : handlerMap.values()) {
            handler.setDisabled(disabled);
        }
    }

    public static void handleUpdated() {
        ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
        Map<String, DataUpdatedCheckHandler> handlerMap = applicationContext
                .getBeansOfType(DataUpdatedCheckHandler.class);

        for (DataUpdatedCheckHandler handler : handlerMap.values()) {
            handler.handleUpdated();
        }
    }
}
