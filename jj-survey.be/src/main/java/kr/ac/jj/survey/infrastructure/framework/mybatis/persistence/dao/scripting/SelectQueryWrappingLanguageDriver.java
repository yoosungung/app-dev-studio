package kr.ac.jj.survey.infrastructure.framework.mybatis.persistence.dao.scripting;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.session.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import kr.ac.jj.survey.infrastructure.framework.mybatis.persistence.dao.helper.SqlMapHelper;

public class SelectQueryWrappingLanguageDriver extends CommentAppendLanguageDriver {
    @Override
    public SqlSource createSqlSource(Configuration configuration, XNode script, Class<?> parameterType) {
        Node node = script.getNode();

        if (!"select".equals(node.getNodeName())) {
            return super.createSqlSource(configuration, script, parameterType);
        }

        Document document = node.getOwnerDocument();

        node.insertBefore(document.createCDATASection("${" + SqlMapHelper.WRAPPER_KEY + ".selectPrefix}"),
                node.getFirstChild());
        node.appendChild(document.createCDATASection("${" + SqlMapHelper.WRAPPER_KEY + ".selectSuffix}"));

        return super.createSqlSource(configuration, script, parameterType);
    }

    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        String wrappedScript = "${" + SqlMapHelper.WRAPPER_KEY + ".selectPrefix}" + script + "${"
                + SqlMapHelper.WRAPPER_KEY + ".selectSuffix}";

        return super.createSqlSource(configuration, wrappedScript, parameterType);
    }
}
