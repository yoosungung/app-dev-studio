package kr.ac.jj.shared.infrastructure.framework.core.support.codedata;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.MessageSourceAccessor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.file.FileUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.message.MessageCoreUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.xml.XmlUtil;

public class FileCodeDataSource implements CodeDataSource {

    private String charsetName;
    private MessageSourceAccessor messageSourceAccessor;
    private List<String> directoryList;
    private Map<String, BaseMapList> codeDataSource;

    public FileCodeDataSource() {
        this.charsetName = "utf-8";
    }

    public void setCharsetName(String charsetName) {
        this.charsetName = charsetName;
    }

    public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    public void addDirectories(String... directories) {
        if (directories == null) {
            return;
        }

        if (this.directoryList == null) {
            this.directoryList = new ArrayList<String>();
        }

        this.directoryList.addAll(Arrays.asList(directories));
    }

    public void setDirectory(String directory) {
        this.addDirectories(directory);
    }

    public void setDirectories(String... directories) {
        this.addDirectories(directories);
    }

    private Map<String, BaseMapList> getCodeDataSource() {
        Map<String, BaseMapList> codeDataSource = new HashMap<String, BaseMapList>();

        if (this.directoryList == null) {
            return codeDataSource;
        }

        for (int i = 0, ii = this.directoryList.size(); i < ii; i++) {
            this.addCodeDataSource(codeDataSource, FileUtil.getResourceFile(this.directoryList.get(i)));
        }

        return codeDataSource;
    }

    private void addCodeDataSource(Map<String, BaseMapList> codeDataSource, File file) {
        if (FileUtil.isFile(file)) {
            Document xmlDoc = XmlUtil.parse(file, this.charsetName);
            Element rootElem = xmlDoc.getDocumentElement();

            String namespace = rootElem.getAttribute("namespace");
            NodeList groupList = rootElem.getElementsByTagName("group");

            for (int i = 0, ii = groupList.getLength(); i < ii; i++) {
                Node group = groupList.item(i);
                String id = "/" + namespace + "/" + group.getAttributes().getNamedItem("id").getNodeValue();
                NodeList itemList = group.getChildNodes();

                if (codeDataSource.containsKey(id)) {
                    throw new BaseException("CodeData ID \"" + id + "\" is duplicated in resource ["
                            + FileUtil.getCanonicalPath(file) + "].");
                }

                BaseMapList codeDataList = new BaseMapList();

                codeDataSource.put(id, codeDataList);

                for (int j = 0, jj = itemList.getLength(); j < jj; j++) {
                    if (itemList.item(j).getNodeType() == Node.ELEMENT_NODE) {
                        Node item = itemList.item(j);
                        NamedNodeMap attrs = item.getAttributes();
                        BaseMap codeData = new BaseMap();

                        for (int k = 0, kk = attrs.getLength(); k < kk; k++) {
                            Node attr = attrs.item(k);
                            codeData.put(attr.getNodeName(), attr.getNodeValue());
                        }

                        codeDataList.add(codeData);
                    }
                }
            }

            return;
        }

        File[] files;

        files = file.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                return file.isFile();
            }

        });

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                this.addCodeDataSource(codeDataSource, files[i]);
            }
        }

        files = file.listFiles(new FileFilter() {

            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }

        });

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                this.addCodeDataSource(codeDataSource, files[i]);
            }
        }
    }

    @Override
    public void refresh() {
        this.codeDataSource = this.getCodeDataSource();
    }

    @Override
    public boolean isMatched(String path) {
        return StringUtils.isNotEmpty(path) && path.startsWith("/");
    }

    @Override
    public BaseMapList getList(String path, Locale locale) {
        String key = path + "::" + locale;

        if (this.codeDataSource.containsKey(key)) {
            return this.codeDataSource.get(key);
        }

        BaseMapList orgCodeDataList = this.codeDataSource.get(path);

        if (orgCodeDataList == null) {
            return null;
        }

        Locale defaultLocale = Locale.KOREA;
        BaseMapList codeDataList = new BaseMapList();

        for (int i = 0; i < orgCodeDataList.size(); i++) {
            BaseMap codeData = new BaseMap();

            codeData.put("code", orgCodeDataList.get(i, "code"));

            if (StringUtils.isNotEmpty((String) orgCodeDataList.get(i, "name-" + locale))) {
                codeData.put("name", orgCodeDataList.get(i, "name-" + locale));
            } else if (this.messageSourceAccessor != null
                    && StringUtils.isNotEmpty((String) orgCodeDataList.get(i, "message"))) {
                String defaultMessage = MessageCoreUtil.getDefaultMessage((String) orgCodeDataList.get(i, "message"),
                        (String) orgCodeDataList.get(i, "name"));
                codeData.put("name", this.messageSourceAccessor.getMessage((String) orgCodeDataList.get(i, "message"),
                        defaultMessage, locale));
            } else if (StringUtils.isNotEmpty((String) orgCodeDataList.get(i, "name"))) {
                codeData.put("name", orgCodeDataList.get(i, "name"));
            } else if (StringUtils.isNotEmpty((String) orgCodeDataList.get(i, "name-" + defaultLocale))) {
                codeData.put("name", orgCodeDataList.get(i, "name-" + defaultLocale));
            }

            codeDataList.add(codeData);
        }

        this.codeDataSource.put(key, codeDataList);

        return codeDataList;
    }

}
