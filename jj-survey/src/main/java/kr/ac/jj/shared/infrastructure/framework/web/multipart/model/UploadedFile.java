package kr.ac.jj.shared.infrastructure.framework.web.multipart.model;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.jj.shared.infrastructure.framework.core.support.io.file.FileUtil;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.policy.FilePolicy;

public class UploadedFile {

    private final FilePolicy filePolicy;
    private final MultipartFile multipartFile;

    private final String originalFilename;
    private final String extension;
    private final long size;

    private final String uploadedDateFolder;
    private final File uploadedDirectory;
    private final File uploadedFile;

    public UploadedFile(MultipartFile multipartFile) {
        this(null, multipartFile);
    }

    public UploadedFile(FilePolicy filePolicy, MultipartFile multipartFile) {
        this.filePolicy = filePolicy;
        this.multipartFile = multipartFile;

        this.originalFilename = FilenameUtils.getName(multipartFile.getOriginalFilename());
        this.extension = FileUtil.getExtension(this.originalFilename);
        this.size = multipartFile.getSize();

        if (filePolicy == null) {
            this.uploadedDateFolder = null;
            this.uploadedDirectory = null;
            this.uploadedFile = null;
        } else {
            this.uploadedDateFolder = StringUtils.defaultString(filePolicy.getDateFolder(true));
            this.uploadedDirectory = new File(filePolicy.getRepositoryDirectory(),
                    filePolicy.getSubPath("") + this.uploadedDateFolder);

            if (!FileUtil.isDirectory(this.uploadedDirectory)) {
                this.uploadedDirectory.mkdirs();
            }

            this.uploadedFile = filePolicy.getNameFormatter().getFormattedFile(this.uploadedDirectory,
                    this.originalFilename);
        }
    }

    public FilePolicy getFilePolicy() {
        return this.filePolicy;
    }

    public MultipartFile getMultipartFile() {
        return this.multipartFile;
    }

    public String getUploadedDateFolder() {
        return this.uploadedDateFolder;
    }

    public File getUploadedDirectory() {
        return this.uploadedDirectory;
    }

    public String getOriginalFilename() {
        return this.originalFilename;
    }

    public String getExtension() {
        return this.extension == null ? "" : this.extension;
    }

    public long getSize() {
        return this.size;
    }

    public File getUploadedFile() {
        return this.uploadedFile;
    }

    public String getUploadedFilename() {
        return this.uploadedFile.getName();
    }

    public String getUploadedFilePathname() {
        return FileUtil.getCanonicalPath(this.uploadedFile);
    }

}
