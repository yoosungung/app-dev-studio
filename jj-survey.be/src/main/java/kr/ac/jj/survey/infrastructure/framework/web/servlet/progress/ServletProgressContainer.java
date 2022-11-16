package kr.ac.jj.survey.infrastructure.framework.web.servlet.progress;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.infrastructure.framework.core.support.progress.ServiceProgress;
import kr.ac.jj.survey.infrastructure.framework.web.context.session.SessionContextUtil;

public class ServletProgressContainer {
    public static ServiceProgress init(String key) {
        ServiceProgress progress = get(key);

        if (progress != null) {
            progress.init();
        }

        return progress;
    }

    public static ServiceProgress get(String key) {
        clear(key);

        if (StringUtils.isEmpty(key)) {
            return null;
        }

        Map<String, ServiceProgress> container = getSessionContainer();

        ServiceProgress progress = container.get(key);

        if (progress == null) {
            progress = new ServiceProgress();
            container.put(key, progress);
        }

        return progress;
    }

    public static void remove(String key) {
        clear(key);

        if (StringUtils.isEmpty(key)) {
            return;
        }

        Map<String, ServiceProgress> container = getSessionContainer();

        container.remove(key);
    }

    private static void clear(String key) {
        Map<String, ServiceProgress> container = getSessionContainer();

        Object[] keys;

        try {
            keys = container.keySet().toArray();
        } catch (NegativeArraySizeException e) {
            container.clear();
            return;
        }

        for (int i = 0, ii = keys.length; i < ii; i++) {
            ServiceProgress progress = container.get(keys[i]);

            if (progress == null || (!keys[i].equals(key) && !progress.isValid())) {
                container.remove(keys[i]);
                progress = null;
            }
        }
    }

    private static Map<String, ServiceProgress> getSessionContainer() {
        Map<String, ServiceProgress> container = SessionContextUtil
                .getAttribute(ServletProgressContainer.class.getName());

        if (container == null) {
            container = new HashMap<String, ServiceProgress>();
            SessionContextUtil.setAttribute(ServletProgressContainer.class.getName(), container);
        }

        return container;
    }
}
