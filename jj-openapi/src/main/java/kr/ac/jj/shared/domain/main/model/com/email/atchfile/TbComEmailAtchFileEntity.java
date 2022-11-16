package kr.ac.jj.shared.domain.main.model.com.email.atchfile;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 공통 메일 첨부파일 Entity
 */
abstract class TbComEmailAtchFileEntity extends MainEntity {

    private static final long serialVersionUID = -1054142072059154184L;

    protected String emailAtchFileId;
    protected String emailId;
    protected Integer atchFileSn;
    protected String filePath;
    protected String fileNm;

    public String getEmailAtchFileId() {
        return this.emailAtchFileId;
    }

    public void setEmailAtchFileId(String emailAtchFileId) {
        this.emailAtchFileId = emailAtchFileId;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Integer getAtchFileSn() {
        return this.atchFileSn;
    }

    public void setAtchFileSn(Integer atchFileSn) {
        this.atchFileSn = atchFileSn;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileNm() {
        return this.fileNm;
    }

    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }

}
