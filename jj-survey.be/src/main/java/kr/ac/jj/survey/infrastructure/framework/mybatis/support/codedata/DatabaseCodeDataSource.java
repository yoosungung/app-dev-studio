package kr.ac.jj.survey.infrastructure.framework.mybatis.support.codedata;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import kr.ac.jj.survey.infrastructure.framework.core.support.codedata.CodeDataSource;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMapList;

public class DatabaseCodeDataSource implements CodeDataSource {
    private final SqlSession sqlSession;

    private String sqlMapperId;
    private String groupColumn;
    private String localeColumn;
    private String codeColumn;
    private String nameColumn;

    private Map<String, BaseMapList> codeDataSource;

    public DatabaseCodeDataSource(SqlSession sqlSession) {
        this.sqlSession = sqlSession;

        this.groupColumn = "GROUP";
        this.localeColumn = "LOCALE";
        this.codeColumn = "CODE";
        this.nameColumn = "NAME";
    }

    public void setSqlMapperId(String sqlMapperId) {
        this.sqlMapperId = sqlMapperId;
    }

    public void setGroupColumn(String groupColumn) {
        this.groupColumn = groupColumn;
    }

    public void setLocaleColumn(String localeColumn) {
        this.localeColumn = localeColumn;
    }

    public void setCodeColumn(String codeColumn) {
        this.codeColumn = codeColumn;
    }

    public void setNameColumn(String nameColumn) {
        this.nameColumn = nameColumn;
    }

    private Map<String, BaseMapList> getCodeDataSource() {
        Map<String, BaseMapList> codeDataSource = new HashMap<String, BaseMapList>();

        this.sqlSession.select(this.sqlMapperId, new ResultHandler<BaseMap>() {
            @Override
            public void handleResult(ResultContext<? extends BaseMap> resultContext) {
                BaseMap resultObject = resultContext.getResultObject();

                String group = (String) resultObject.get(groupColumn);
                String locale = (String) resultObject.get(localeColumn);
                String key = "[" + group + "]::" + locale;

                BaseMapList codeDataList;

                if (codeDataSource.containsKey(key)) {
                    codeDataList = codeDataSource.get(key);
                } else {
                    codeDataList = new BaseMapList();
                    codeDataSource.put(key, codeDataList);
                }

                BaseMap codeData = new BaseMap();
                codeData.put("code", resultObject.get(codeColumn));
                codeData.put("name", resultObject.get(nameColumn));
                codeDataList.add(codeData);
            }
        });

        return codeDataSource;
    }

    @Override
    public void refresh() {
        this.codeDataSource = this.getCodeDataSource();
    }

    @Override
    public boolean isMatched(String path) {
        return StringUtils.isNotEmpty(path) && path.startsWith("[") && path.endsWith("]");
    }

    @Override
    public BaseMapList getList(String path, Locale locale) {
        if (this.codeDataSource.containsKey(path + "::" + locale)) {
            return this.codeDataSource.get(path + "::" + locale);
        }

        Locale defaultLocale = Locale.KOREA;

        if (this.codeDataSource.containsKey(path + "::" + defaultLocale)) {
            return this.codeDataSource.get(path + "::" + defaultLocale);
        }

        return null;
    }
}
