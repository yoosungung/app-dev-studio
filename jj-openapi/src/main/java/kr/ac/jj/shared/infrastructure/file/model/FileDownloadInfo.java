package kr.ac.jj.shared.infrastructure.file.model;

import java.io.File;

public class FileDownloadInfo {

    private File downloadFile;
    private String originalFileName;
    private boolean deleteDownloadFile;

    public File getDownloadFile() {
        return this.downloadFile;
    }

    public void setDownloadFile(File downloadFile) {
        this.downloadFile = downloadFile;
    }

    public String getOriginalFileName() {
        return this.originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public boolean isDeleteDownloadFile() {
        return this.deleteDownloadFile;
    }

    public void setDeleteDownloadFile(boolean deleteDownloadFile) {
        this.deleteDownloadFile = deleteDownloadFile;
    }

}
