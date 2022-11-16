package kr.ac.jj.survey.domain.main.model.com.bbsctt;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.ac.jj.survey.domain.main.model.MainEntity;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanDeserializer;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanSerializer;

/**
 * 공통 - 게시글 Entity
 */
abstract class TbComBbscttEntity extends MainEntity {
    private static final long serialVersionUID = -7459688073238439769L;

    protected String bbscttId;
    protected String bbsId;
    protected Long bbscttNo;
    protected String bbscttSj;
    protected String bbscttCn;
    protected String atchFileId;
    protected Integer rdcnt;
    protected String frstBbscttId;
    protected Integer answerLevel;
    protected Integer answerOrdr;
    protected Boolean deleteYn;
    protected String writngPsnId;
    protected Date writngDt;
    protected String changePsnId;
    protected Date changeDt;
    protected Date noticeBeginDe;
    protected Date noticeEndDe;
    protected Integer popupPrhibtPd;

    public String getBbscttId() {
        return this.bbscttId;
    }

    public void setBbscttId(String bbscttId) {
        this.bbscttId = bbscttId;
    }

    public String getBbsId() {
        return this.bbsId;
    }

    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
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

    public String getFrstBbscttId() {
        return this.frstBbscttId;
    }

    public void setFrstBbscttId(String frstBbscttId) {
        this.frstBbscttId = frstBbscttId;
    }

    public Integer getAnswerLevel() {
        return this.answerLevel;
    }

    public void setAnswerLevel(Integer answerLevel) {
        this.answerLevel = answerLevel;
    }

    public Integer getAnswerOrdr() {
        return this.answerOrdr;
    }

    public void setAnswerOrdr(Integer answerOrdr) {
        this.answerOrdr = answerOrdr;
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getDeleteYn() {
        return this.deleteYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
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

    public Integer getPopupPrhibtPd() {
        return this.popupPrhibtPd;
    }

    public void setPopupPrhibtPd(Integer popupPrhibtPd) {
        this.popupPrhibtPd = popupPrhibtPd;
    }
}
