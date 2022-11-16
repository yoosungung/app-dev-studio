package kr.ac.jj.survey.infrastructure.framework.mybatis.persistence.dao.scripting;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class CommentAppendLanguageDriver extends XMLLanguageDriver {
    @Override
    public SqlSource createSqlSource(Configuration configuration, XNode script, Class<?> parameterType) {
        Node node = script.getNode();
        NamedNodeMap attributes = node.getAttributes();

        if (attributes != null) {
            Node idNode = attributes.getNamedItem("id");

            if (idNode != null) {
                Document document = node.getOwnerDocument();
                String mapper = document.getDocumentElement().getAttribute("namespace");
                node.insertBefore(document.createCDATASection("/* " + mapper + "." + idNode.getNodeValue() + " */"),
                        node.getFirstChild());
            }
        }

        return super.createSqlSource(configuration, script, parameterType);
    }
}
