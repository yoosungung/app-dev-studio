package kr.ac.jj.survey.infrastructure.framework.mybatis.support.codedata;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.survey.infrastructure.framework.core.support.codedata.CodeDataSource;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.CamelKeyMap;

public class QueryCodeDataSource implements CodeDataSource {
    private final SqlSession sqlSession;

    private String namespacePrefix;
    private Map<String, String> namespaceMap;

    public QueryCodeDataSource(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void addSqlMapperNamespacePrefix(String namespacePrefix) {
        this.namespacePrefix = namespacePrefix;
    }

    public void addSqlMapperNamespace(String alias, String namespace) {
        if (StringUtils.isEmpty(alias) || StringUtils.isEmpty(namespace)) {
            return;
        }

        if (this.namespaceMap == null) {
            this.namespaceMap = new HashMap<String, String>();
        }

        this.namespaceMap.put(alias, namespace);
    }

    @Override
    public void refresh() {
    }

    @Override
    public boolean isMatched(String path) {
        return StringUtils.isNotEmpty(path) && !path.startsWith("[") && !path.endsWith("]") && !path.startsWith("/");
    }

    @Override
    public BaseMapList getList(String path, Locale locale) {
        if (StringUtils.isEmpty(path)) {
            throw new BaseException("QueryCodeDataSource path is not defined!");
        }

        String[] paths = path.split(",");
        String queryPath = paths[0];

        if (queryPath.indexOf(".") == -1) {
            throw new BaseException("QueryCodeDataSource path \"" + queryPath + "\" is not valid!");
        }

        String namespaceAlias = queryPath.substring(0, queryPath.lastIndexOf("."));

        if ((this.namespaceMap == null || !this.namespaceMap.containsKey(namespaceAlias))
                && StringUtils.isEmpty(this.namespacePrefix)) {
            throw new BaseException("QueryCodeDataSource namespace \"" + namespaceAlias + "\" is not available!");
        }

        String queryId = queryPath.substring(queryPath.lastIndexOf(".") + 1);
        String sqlMapperId;

        if (this.namespaceMap != null && this.namespaceMap.containsKey(namespaceAlias)) {
            sqlMapperId = this.namespaceMap.get(namespaceAlias);
        } else {
            sqlMapperId = this.namespacePrefix + namespaceAlias;
        }

        BaseMap param = new BaseMap();

        for (int i = 1; i < paths.length; i++) {
            param.put("param" + i, paths[i]);
        }

        BaseMapList resultList = new BaseMapList();

        this.sqlSession.select(sqlMapperId + "." + queryId, param, new ResultHandler<CamelKeyMap>() {
            @Override
            public void handleResult(ResultContext<? extends CamelKeyMap> resultContext) {
                resultList.add(resultContext.getResultObject());
            }
        });

        return resultList;
    }
}
