package kr.ac.jj.survey.domain.main.model.survey.group;

import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 설문_그룹 Entity
 */
abstract class TbSurveyGroupEntity extends MainEntity {

    private static final long serialVersionUID = -6102370059303610736L;

    protected String surveyGroupId;
    protected String groupNm;
    protected String groupDc;
    protected String cnrsYn;
    protected String registPsnId;
    protected Date registDt;
    protected String registDeptId;
    protected String atchFileId;

    public String getSurveyGroupId() {
        return this.surveyGroupId;
    }

    public void setSurveyGroupId(String surveyGroupId) {
        this.surveyGroupId = surveyGroupId;
    }

    public String getGroupNm() {
        return this.groupNm;
    }

    public void setGroupNm(String groupNm) {
        this.groupNm = groupNm;
    }

    public String getGroupDc() {
        return this.groupDc;
    }

    public void setGroupDc(String groupDc) {
        this.groupDc = groupDc;
    }

    public String getCnrsYn() {
        return this.cnrsYn;
    }

    public void setCnrsYn(String cnrsYn) {
        this.cnrsYn = cnrsYn;
    }

    public String getRegistPsnId() {
        return this.registPsnId;
    }

    public void setRegistPsnId(String registPsnId) {
        this.registPsnId = registPsnId;
    }

    public Date getRegistDt() {
        return this.registDt;
    }

    public void setRegistDt(Date registDt) {
        this.registDt = registDt;
    }

    public String getRegistDeptId() {
        return this.registDeptId;
    }

    public void setRegistDeptId(String registDeptId) {
        this.registDeptId = registDeptId;
    }

    public String getAtchFileId() {
        return this.atchFileId;
    }

    public void setAtchFileId(String atchFileId) {
        this.atchFileId = atchFileId;
    }

}
