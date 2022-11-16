package kr.ac.jj.survey.infrastructure.framework.web.support.codedata;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import kr.ac.jj.survey.infrastructure.framework.core.support.codedata.CodeDataSourceAccessor;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.survey.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;

public class CodeDataUtil {
    private static CodeDataSourceAccessor codeDataSourceAccessor;

    private static CodeDataSourceAccessor getCodeDataSourceAccessor() {
        if (codeDataSourceAccessor == null && ApplicationContextUtil.getApplicationContext() != null) {
            codeDataSourceAccessor = ApplicationContextUtil.getBean(CodeDataSourceAccessor.class);
        }

        return codeDataSourceAccessor;
    }

    public static Map<String, Object> getCodeDataMap(List<RequestCode> requestCodeList) {
        return getCodeDataMap(requestCodeList, RequestContextUtil.getLocale());
    }

    public static Map<String, Object> getCodeDataMap(List<RequestCode> requestCodeList, Locale locale) {
        Map<String, Object> codeDataMap = new LinkedHashMap<String, Object>();

        for (int i = 0; i < requestCodeList.size(); i++) {
            String key = requestCodeList.get(i).getKey();

            try {
                BaseMapList codeDataList = getCodeDataSourceAccessor().getList(key, locale);
                BaseMap codeInfoMap = new BaseMap();
                codeInfoMap.put("list", codeDataList);
                codeDataMap.put(key, codeInfoMap);
            } catch (RuntimeException e) {
                Map<String, Object> error = new HashMap<String, Object>();
                error.put("error", ExceptionUtils.getRootCauseMessage(e));
                codeDataMap.put(key, error);
            }
        }

        return codeDataMap;
    }

    public static BaseMapList getList(String codeDataPath) {
        return getList(codeDataPath, RequestContextUtil.getLocale());
    }

    public static BaseMapList getList(String codeDataPath, Locale locale) {
        return getCodeDataSourceAccessor().getList(codeDataPath, locale);
    }

    public static String[] getCodes(String codeDataPath) {
        return getCodes(codeDataPath, RequestContextUtil.getLocale());
    }

    public static String[] getCodes(String codeDataPath, Locale locale) {
        BaseMapList codeDataList = getList(codeDataPath, locale);

        String[] result = new String[codeDataList.size()];

        for (int i = 0, ii = codeDataList.size(); i < ii; i++) {
            result[i] = (String) codeDataList.get(i, "code");
        }

        return result;
    }

    public static String getCodeName(String codeDataPath, String codeValue) {
        return getCodeName(codeDataPath, codeValue, null, RequestContextUtil.getLocale());
    }

    public static String getCodeName(String codeDataPath, String codeValue, String defaultText) {
        return getCodeName(codeDataPath, codeValue, defaultText, RequestContextUtil.getLocale());
    }

    public static String getCodeName(String codeDataPath, String codeValue, Locale locale) {
        return getCodeName(codeDataPath, codeValue, null, locale);
    }

    public static String getCodeName(String codeDataPath, String codeValue, String defaultText, Locale locale) {
        if (StringUtils.isEmpty(codeDataPath) || StringUtils.isEmpty(codeValue)) {
            return null;
        }

        BaseMapList codeDataList = getCodeDataSourceAccessor().getList(codeDataPath, locale);

        for (BaseMap codeData : codeDataList) {
            if (codeValue.equals(codeData.get("code"))) {
                return (String) codeData.get("name");
            }
        }

        return defaultText;
    }
}
