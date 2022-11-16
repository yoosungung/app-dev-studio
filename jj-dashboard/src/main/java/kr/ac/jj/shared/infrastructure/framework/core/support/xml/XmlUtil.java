package kr.ac.jj.shared.infrastructure.framework.core.support.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.file.FileUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.util.IOUtil;

/**
 * XML 관련 유틸리티
 */
public class XmlUtil {

    private XmlUtil() {
    }

    /**
     * 특정 XML 파일을 파싱하여 DOM 문서 객체 반환
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) XmlUtil.parse(file);
     * </pre>
     *
     * @param file
     * @return Document
     */
    public static Document parse(File file) {
        return parse(file, "utf-8");
    }

    public static Document parse(File file, String charsetName) {
        return file == null ? null : parse(FileUtil.readAll(file, charsetName));
    }

    /**
     * 특정 XML 문자열을 파싱하여 DOM 문서 객체 반환
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) XmlUtil.parse(string);
     * </pre>
     *
     * @param String
     * @return Document
     */
    public static Document parse(String string) {
        if (string == null) {
            return null;
        }

        StringReader sr = null;

        try {
            sr = new StringReader(string);
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(sr));
        } catch (ParserConfigurationException e) {
            throw new BaseException(e);
        } catch (IOException e) {
            throw new BaseException(e);
        } catch (SAXException e) {
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(sr);
        }
    }

    /**
     * 자식 노드 값 반환
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) XmlUtil.getChildNodesValue(node);
     * </pre>
     *
     * @param node
     * @return String
     */
    public static String getChildNodesValue(Node node) {
        if (node == null) {
            return null;
        }

        NodeList nodeList = node.getChildNodes();
        StringBuilder sb = new StringBuilder();

        for (int i = 0, ii = nodeList.getLength(); i < ii; i++) {
            sb.append(nodeList.item(i).getNodeValue());
        }

        return sb.toString();
    }

    /**
     * CDATA 문자열 Escape 처리하여 반환
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * <xmp>1) XmlUtil.toCDataContents("<![CDATA[ 값1 ]]>");</xmp>
     * </pre>
     *
     * @param text 출력할 문자
     * @return String
     */
    public static String toCDataContents(String text) {
        if (text == null) {
            return "";
        }

        return text.replaceAll("]]>", "]]]]><![CDATA[>");
    }

    /**
     * 특정 노드 데이터를 AnyData로 반환
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) XmlUtil.nodeToSingleData(node);
     * </pre>
     *
     * @param node
     * @return AnyData
     */
    public static BaseMap nodeToMap(Node node) {
        if (node == null) {
            return null;
        }

        BaseMap result = new BaseMap();
        NodeList childNodeList = node.getChildNodes();

        for (int i = 0, ii = childNodeList.getLength(); i < ii; i++) {
            Node childNode = childNodeList.item(i);
            if (childNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (childNode.getChildNodes().getLength() == 0) {
                continue;
            }
            result.put(childNode.getNodeName(), getChildNodesValue(childNode));
        }

        return result;
    }

    /**
     * 특정 노드 리스트를 BaseMapList로 반환
     *
     * <p>
     * 사용예 :
     * </p>
     *
     * <pre>
     * 1) XmlUtil.nodeListToMultiData(nodeList);
     * </pre>
     *
     * @param nodeList
     * @return BaseMapList
     */
    public static BaseMapList nodeMapList(NodeList nodeList) {
        if (nodeList == null) {
            return null;
        }

        BaseMapList result = new BaseMapList();

        for (int i = 0, ii = nodeList.getLength(); i < ii; i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            result.add(nodeToMap(nodeList.item(i)));
        }

        return result;
    }

}
