package kr.ac.jj.shared.domain.main.model.bbs.notice;

import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 게시판 - 공지사항 Entity
 */
abstract class TbBbsNoticeEntity extends MainEntity {

    private static final long serialVersionUID = -4383480330839863752L;

    protected String bbscttId;
    protected Long bbscttNo;
    protected String bbscttSj;
    protected String bbscttCn;
    protected Date noticeBeginDe;
    protected Date noticeEndDe;
    protected String atchFileId;
    protected Integer rdcnt;
    protected Boolean deleteYn;
    protected String writngPsnId;
    protected Date writngDt;
    protected String changePsnId;
    protected Date changeDt;

    public String getBbscttId() {
        return this.bbscttId;
    }

    public void setBbscttId(String bbscttId) {
        this.bbscttId = bbscttId;
    }

    public Long getBbscttNo() {
        return this.bbscttNo;
    }

    public void setBbscttNo(Long bbscttNo) {
        this.bbscttNo = bbscttNo;
    }

    public String getBbscttSj() {
        return this.bbscttSj;
    }

    public void setBbscttSj(String bbscttSj) {
        this.bbscttSj = bbscttSj;
    }

    public String getBbscttCn() {
        return this.bbscttCn;
    }

    public void setBbscttCn(String bbscttCn) {
        this.bbscttCn = bbscttCn;
    }

    public Date getNoticeBeginDe() {
        return this.noticeBeginDe;
    }

    public void setNoticeBeginDe(Date noticeBeginDe) {
        this.noticeBeginDe = noticeBeginDe;
    }

    public Date getNoticeEndDe() {
        return this.noticeEndDe;
    }

    public void setNoticeEndDe(Date noticeEndDe) {
        this.noticeEndDe = noticeEndDe;
    }

    public String getAtchFileId() {
        return this.atchFileId;
    }

    public void setAtchFileId(String atchFileId) {
        this.atchFileId = atchFileId;
    }

    public Integer getRdcnt() {
        return this.rdcnt;
    }

    public void setRdcnt(Integer rdcnt) {
        this.rdcnt = rdcnt;
    }

    public Boolean getDeleteYn() {
        return this.deleteYn;
    }

    public void setDeleteYn(Boolean deleteYn) {
        this.deleteYn = deleteYn;
    }

    public String getWritngPsnId() {
        return this.writngPsnId;
    }

    public void setWritngPsnId(String writngPsnId) {
        this.writngPsnId = writngPsnId;
    }

    public Date getWritngDt() {
        return this.writngDt;
    }

    public void setWritngDt(Date writngDt) {
        this.writngDt = writngDt;
    }

    public String getChangePsnId() {
        return this.changePsnId;
    }

    public void setChangePsnId(String changePsnId) {
        this.changePsnId = changePsnId;
    }

    public Date getChangeDt() {
        return this.changeDt;
    }

    public void setChangeDt(Date changeDt) {
        this.changeDt = changeDt;
    }

}
