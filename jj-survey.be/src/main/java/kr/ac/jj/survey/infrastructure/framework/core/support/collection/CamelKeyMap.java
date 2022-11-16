package kr.ac.jj.survey.infrastructure.framework.core.support.collection;

import kr.ac.jj.survey.infrastructure.framework.core.support.lang.StringUtil;

public class CamelKeyMap extends BaseMap {
    private static final long serialVersionUID = -7109973190696489850L;

    @Override
    public Object put(String key, Object value) {
        return super.put(StringUtil.toCamelCase(key), value);
    }
}
