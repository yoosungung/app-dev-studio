package kr.ac.jj.shared.infrastructure.framework.core.support.codedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMapList;

public class CodeDataSourceAccessor {

    private List<CodeDataSource> codeDataSourceList;
    private Map<String, BaseMapList> booleanTypeCodeDataSource;
    private CodeDataBooleanTypeList codeDataBooleanTypeList;

    public CodeDataSourceAccessor(CodeDataSource... codeDataSources) {
        this.codeDataSourceList = new ArrayList<CodeDataSource>();

        if (codeDataSources == null) {
            return;
        }

        for (int i = 0; i < codeDataSources.length; i++) {
            this.codeDataSourceList.add(codeDataSources[i]);
            codeDataSources[i].refresh();
        }
    }

    public void addCodeDataSource(CodeDataSource... codeDataSources) {
        if (codeDataSources == null) {
            return;
        }

        if (this.codeDataSourceList == null) {
            this.codeDataSourceList = new ArrayList<CodeDataSource>();
        }

        for (int i = 0; i < codeDataSources.length; i++) {
            this.codeDataSourceList.add(codeDataSources[i]);
            codeDataSources[i].refresh();
        }
    }

    public void addBooleanType(String trueCode, String falseCode) {
        if (this.codeDataBooleanTypeList == null) {
            this.codeDataBooleanTypeList = new CodeDataBooleanTypeList();
        }

        this.codeDataBooleanTypeList.add(new CodeDataBooleanType(trueCode, falseCode));
    }

    public BaseMapList getList(String path, Locale locale) {
        return this.getList(path, locale, false);
    }

    public BaseMapList getList(String path, Locale locale, boolean convertToBoolean) {
        if (StringUtils.isEmpty(path)) {
            throw new BaseException("CodeData path is not defined!");
        }

        for (int i = 0, ii = this.codeDataSourceList.size(); i < ii; i++) {
            CodeDataSource codeDataSource = this.codeDataSourceList.get(i);

            if (!codeDataSource.isMatched(path)) {
                continue;
            }

            if (!convertToBoolean || !codeDataSource.isBooleanTypeAvailable()) {
                BaseMapList orgCodeDataList = codeDataSource.getList(path, locale);

                if (orgCodeDataList != null) {
                    return orgCodeDataList;
                }
            } else {
                if (this.booleanTypeCodeDataSource == null) {
                    this.booleanTypeCodeDataSource = new HashMap<String, BaseMapList>();
                }

                String key = path + "::" + locale;

                BaseMapList booleanCodeDataList = this.booleanTypeCodeDataSource.get(key);

                if (booleanCodeDataList != null) {
                    return booleanCodeDataList;
                }

                BaseMapList orgCodeDataList = codeDataSource.getList(path, locale);

                if (orgCodeDataList != null) {
                    BaseMapList codeDataList;

                    if (this.isBooleanType(orgCodeDataList)) {
                        codeDataList = this.convertToBoolean(orgCodeDataList);
                    } else {
                        codeDataList = orgCodeDataList;
                    }

                    this.booleanTypeCodeDataSource.put(key, codeDataList);

                    return codeDataList;
                }
            }
        }

        throw new BaseException("CodeData \"" + path + "\" is not available.");
    }

    public boolean isBooleanType(BaseMapList codeDataList) {
        if (this.codeDataBooleanTypeList == null) {
            return false;
        }

        if (codeDataList.size() != 2) {
            return false;
        }

        String code1 = codeDataList.get(0, "code") + "";
        String code2 = codeDataList.get(1, "code") + "";

        return this.codeDataBooleanTypeList.isBooleanType(code1, code2);
    }

    public BaseMapList convertToBoolean(BaseMapList codeDataList) {
        if (this.codeDataBooleanTypeList == null) {
            return null;
        }

        BaseMapList booleanCodeDataList = new BaseMapList();

        for (BaseMap map : codeDataList) {
            BaseMap booleanMap = new BaseMap();
            booleanMap.putAll(map);

            if (booleanMap.get("code") != null) {
                booleanMap.put("code", this.codeDataBooleanTypeList.isTrueCode(booleanMap.get("code") + ""));
            }

            booleanCodeDataList.add(booleanMap);
        }

        return booleanCodeDataList;
    }

    public String getBooleanCode(BaseMapList codeDataList, Boolean codeValue) {
        if (this.codeDataBooleanTypeList == null) {
            return null;
        }

        if (codeValue == null) {
            return null;
        }

        for (BaseMap codeData : codeDataList) {
            String code = codeData.get("code") + "";

            if (codeValue) {
                if (this.codeDataBooleanTypeList.isTrueCode(code)) {
                    return code;
                }
            } else {
                if (this.codeDataBooleanTypeList.isFalseCode(code)) {
                    return code;
                }
            }
        }

        return null;
    }

    public void refreshAll() {
        if (this.booleanTypeCodeDataSource != null) {
            this.booleanTypeCodeDataSource.clear();
        }

        for (CodeDataSource codeDataSource : this.codeDataSourceList) {
            codeDataSource.refresh();
        }
    }

}
