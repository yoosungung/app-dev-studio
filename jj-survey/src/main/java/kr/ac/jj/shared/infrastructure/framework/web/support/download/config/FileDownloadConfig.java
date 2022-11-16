package kr.ac.jj.shared.infrastructure.framework.web.support.download.config;

public class FileDownloadConfig {

    private String contentTransferEncoding;
    private Boolean downloadFilenameUrlEncode;
    private Boolean downloadFilenameEncoding;

    public String getContentTransferEncoding() {
        return this.contentTransferEncoding;
    }

    public void setContentTransferEncoding(String contentTransferEncoding) {
        this.contentTransferEncoding = contentTransferEncoding;
    }

    public Boolean getDownloadFilenameUrlEncode() {
        return this.downloadFilenameUrlEncode;
    }

    public void setDownloadFilenameUrlEncode(Boolean downloadFilenameUrlEncode) {
        this.downloadFilenameUrlEncode = downloadFilenameUrlEncode;
    }

    public Boolean getDownloadFilenameEncoding() {
        return this.downloadFilenameEncoding;
    }

    public void setDownloadFilenameEncoding(Boolean downloadFilenameEncoding) {
        this.downloadFilenameEncoding = downloadFilenameEncoding;
    }

}
