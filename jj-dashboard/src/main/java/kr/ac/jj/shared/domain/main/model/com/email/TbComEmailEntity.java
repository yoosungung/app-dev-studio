package kr.ac.jj.shared.domain.main.model.com.email;

import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 공통 메일 Entity
 */
abstract class TbComEmailEntity extends MainEntity {

    private static final long serialVersionUID = -2530057516077854150L;

    protected String emailId;
    protected String emailSj;
    protected String emailCn;
    protected String senderEmailAdres;
    protected String senderNm;
    protected String senderPersonId;
    protected Date writngDt;
    protected String relateId;

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailSj() {
        return this.emailSj;
    }

    public void setEmailSj(String emailSj) {
        this.emailSj = emailSj;
    }

    public String getEmailCn() {
        return this.emailCn;
    }

    public void setEmailCn(String emailCn) {
        this.emailCn = emailCn;
    }

    public String getSenderEmailAdres() {
        return this.senderEmailAdres;
    }

    public void setSenderEmailAdres(String senderEmailAdres) {
        this.senderEmailAdres = senderEmailAdres;
    }

    public String getSenderNm() {
        return this.senderNm;
    }

    public void setSenderNm(String senderNm) {
        this.senderNm = senderNm;
    }

    public String getSenderPersonId() {
        return this.senderPersonId;
    }

    public void setSenderPersonId(String senderPersonId) {
        this.senderPersonId = senderPersonId;
    }

    public Date getWritngDt() {
        return this.writngDt;
    }

    public void setWritngDt(Date writngDt) {
        this.writngDt = writngDt;
    }

    public String getRelateId() {
        return this.relateId;
    }

    public void setRelateId(String relateId) {
        this.relateId = relateId;
    }

}
