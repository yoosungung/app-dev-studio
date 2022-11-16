package kr.ac.jj.survey.infrastructure.framework.web.multipart.policy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.infrastructure.framework.core.support.lang.StringUtil;
import kr.ac.jj.survey.infrastructure.framework.web.multipart.nameformatter.DefaultFilenameFormatter;
import kr.ac.jj.survey.infrastructure.framework.web.multipart.nameformatter.UploadedFilenameFormatter;
import kr.ac.jj.survey.infrastructure.framework.web.multipart.repository.FileRepositoryUtil;

public class FilePolicy {
    private String repositoryName;
    private File repositoryDirectory;
    private long maxUploadSize = -1;
    private long maxUploadSizePerFile = -1;
    private String subPath;
    private String dateFolder;
    private UploadedFilenameFormatter nameFormatter;
    private boolean deletePhysicalFile;
    private String[] includeExtensions;
    private String[] excludeExtensions;

    public FilePolicy() {
        this.setNameFormatter(new DefaultFilenameFormatter());
    }

    protected FilePolicy(FilePolicy basePolicy) {
        this.setRepositoryName(basePolicy.getRepositoryName());
        this.setMaxUploadSize(basePolicy.getMaxUploadSize());
        this.setMaxUploadSizePerFile(basePolicy.getMaxUploadSizePerFile());
        this.setSubPath(basePolicy.getSubPath());
        this.setDateFolder(basePolicy.getDateFolder());
        this.setNameFormatter(basePolicy.getNameFormatter());
        this.setDeletePhysicalFile(basePolicy.isDeletePhysicalFile());
        this.setIncludeExtensions(basePolicy.getIncludeExtensions());
        this.setExcludeExtensions(basePolicy.getExcludeExtensions());
    }

    public String getRepositoryName() {
        return this.repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public File getRepositoryDirectory() {
        if (this.repositoryDirectory == null) {
            this.repositoryDirectory = FileRepositoryUtil.getRepositoryDirectory(this.repositoryName);
        }

        return this.repositoryDirectory;
    }

    public void setRepositoryDirectory(File repositoryDirectory) {
        this.repositoryDirectory = repositoryDirectory;
    }

    public long getMaxUploadSize() {
        return this.maxUploadSize;
    }

    public void setMaxUploadSize(long maxUploadSize) {
        this.maxUploadSize = maxUploadSize;
    }

    public void setMaxUploadSize(String maxUploadSize) {
        this.maxUploadSize = StringUtil.parseSize(maxUploadSize);
    }

    public long getMaxUploadSizePerFile() {
        return this.maxUploadSizePerFile;
    }

    public void setMaxUploadSizePerFile(long maxUploadSizePerFile) {
        this.maxUploadSizePerFile = maxUploadSizePerFile;
    }

    public void setMaxUploadSizePerFile(String maxUploadSizePerFile) {
        this.maxUploadSizePerFile = StringUtil.parseSize(maxUploadSizePerFile);
    }

    public String getSubPath() {
        return this.getSubPath(null);
    }

    public String getSubPath(String defaultPath) {
        return StringUtils.defaultIfEmpty(this.subPath, defaultPath);
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    public String getDateFolder() {
        return this.getDateFolder(false);
    }

    public String getDateFolder(boolean formatted) {
        if (StringUtils.isEmpty(this.dateFolder)) {
            return "";
        }

        if (formatted) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(this.dateFolder);
            return dateFormat.format(new Date());
        }

        return this.dateFolder;
    }

    public void setDateFolder(String dateFolder) {
        this.dateFolder = dateFolder;
    }

    public UploadedFilenameFormatter getNameFormatter() {
        return this.nameFormatter;
    }

    public void setNameFormatter(UploadedFilenameFormatter nameFormatter) {
        this.nameFormatter = nameFormatter;
    }

    public boolean isDeletePhysicalFile() {
        return this.deletePhysicalFile;
    }

    public void setDeletePhysicalFile(boolean deletePhysicalFile) {
        this.deletePhysicalFile = deletePhysicalFile;
    }

    public String[] getIncludeExtensions() {
        return this.includeExtensions;
    }

    public void setIncludeExtensions(String... includeExtensions) {
        this.includeExtensions = includeExtensions;
    }

    public String[] getExcludeExtensions() {
        return this.excludeExtensions;
    }

    public void setExcludeExtensions(String... excludeExtensions) {
        this.excludeExtensions = excludeExtensions;
    }
}
