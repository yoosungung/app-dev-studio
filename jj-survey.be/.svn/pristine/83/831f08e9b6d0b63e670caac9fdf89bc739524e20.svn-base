package kr.ac.jj.survey.infrastructure.framework.core.support.codedata;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMapList;

public class CodeDataSourceAccessor {
    private List<CodeDataSource> codeDataSourceList;

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

    public BaseMapList getList(String path, Locale locale) {
        if (StringUtils.isEmpty(path)) {
            throw new BaseException("CodeData path is not defined!");
        }

        for (int i = 0, ii = this.codeDataSourceList.size(); i < ii; i++) {
            CodeDataSource codeDataSource = this.codeDataSourceList.get(i);

            if (codeDataSource.isMatched(path)) {
                BaseMapList codeDataList = codeDataSource.getList(path, locale);

                if (codeDataList != null) {
                    return codeDataList;
                }
            }
        }

        throw new BaseException("CodeData \"" + path + "\" is not available.");
    }

    public void refreshAll() {
        for (CodeDataSource codeDataSource : this.codeDataSourceList) {
            codeDataSource.refresh();
        }
    }
}
