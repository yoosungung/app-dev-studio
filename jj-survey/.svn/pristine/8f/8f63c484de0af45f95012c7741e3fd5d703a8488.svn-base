package com.jd.survey.pub.web.cmd;

import com.jd.survey.com.BaseCmd;
import com.jd.survey.pub.domain.SurveySecUserDefinition;

public class SurveySecUserDefinitionCmd extends BaseCmd<SurveySecUserDefinition> {

    // ~ Static fields/initializers
    // =========================================================

    private static final long serialVersionUID = -422791989283444248L;

    // ~ Instance fields
    // ====================================================================

    /** 분야 아이디 */
    private long departmentId;

    /** 실태조사 설정 아이디 */
    private long surveyDefinitionId;

    /** 사용자 조사상태 */
    private String status;

    // ~ Constructors
    // =======================================================================

    public SurveySecUserDefinitionCmd() {
        super(new String[] { "creationDate" }, true);
        super.setPageSize(20);
    }

    // ~ Methods
    // ============================================================================

    public long getSurveyDefinitionId() {
        return surveyDefinitionId;
    }

    public void setSurveyDefinitionId(long surveyDefinitionId) {
        this.surveyDefinitionId = surveyDefinitionId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
