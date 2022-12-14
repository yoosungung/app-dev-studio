package kr.ac.jj.survey.domain.main.model.com.bbs;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.ac.jj.survey.domain.main.model.MainEntity;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanDeserializer;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanSerializer;

/**
 * 공통 - 게시판 Entity
 */
abstract class TbComBbsEntity extends MainEntity {
    private static final long serialVersionUID = 8814958809664817581L;

    protected String bbsId;
    protected String bbsCode;
    protected String bbsNmTitle;
    protected String bbsDc;
    protected String atchFilePolicy;
    protected Boolean answerPosblYn;
    protected Boolean rdcntIndictYn;
    protected Boolean noticeBbsYn;
    protected Boolean editrApplcYn;

    public String getBbsId() {
        return this.bbsId;
    }

    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
    }

    public String getBbsCode() {
        return this.bbsCode;
    }

    public void setBbsCode(String bbsCode) {
        this.bbsCode = bbsCode;
    }

    public String getBbsNmTitle() {
        return this.bbsNmTitle;
    }

    public void setBbsNmTitle(String bbsNmTitle) {
        this.bbsNmTitle = bbsNmTitle;
    }

    public String getBbsDc() {
        return this.bbsDc;
    }

    public void setBbsDc(String bbsDc) {
        this.bbsDc = bbsDc;
    }

    public String getAtchFilePolicy() {
        return this.atchFilePolicy;
    }

    public void setAtchFilePolicy(String atchFilePolicy) {
        this.atchFilePolicy = atchFilePolicy;
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getAnswerPosblYn() {
        return this.answerPosblYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    public void setAnswerPosblYn(Boolean answerPosblYn) {
        this.answerPosblYn = answerPosblYn;
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getRdcntIndictYn() {
        return this.rdcntIndictYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    public void setRdcntIndictYn(Boolean rdcntIndictYn) {
        this.rdcntIndictYn = rdcntIndictYn;
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getNoticeBbsYn() {
        return this.noticeBbsYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    public void setNoticeBbsYn(Boolean noticeBbsYn) {
        this.noticeBbsYn = noticeBbsYn;
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getEditrApplcYn() {
        return this.editrApplcYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    public void setEditrApplcYn(Boolean editrApplcYn) {
        this.editrApplcYn = editrApplcYn;
    }
}
