package kr.ac.jj.survey.application.qestnrmanage.model;

import org.apache.commons.lang3.BooleanUtils;

import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;
import kr.ac.jj.survey.domain.main.model.jd.survey.definition.JdSurveyDefinition;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.TbSurveyQestnr;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.cmmn.TbSurveyQestnrCmmn;

/**
 * 설문지 관리 Model
 */
public class QuestionnaireManageModel {

    private TbSurveyQestnr tbSurveyQestnr;
    private TbSurveyQestnrCmmn tbSurveyQestnrCmmn;
    private JdSurveyDefinition jdSurveyDefinition;
    private Long surveyDefinitionId;
    private String[] backgroundColors;

    public boolean isEditable() {
        if (!SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN", "ROLE_SURVEY_ADMIN")) {
            return false;
        }

        if (this.tbSurveyQestnr == null || this.jdSurveyDefinition == null) {
            return false;
        }

        if (BooleanUtils.isTrue(this.tbSurveyQestnr.getSndngYn())) {
            return false;
        }

        return true;
    }

    public TbSurveyQestnr getTbSurveyQestnr() {
        return this.tbSurveyQestnr;
    }

    public void setTbSurveyQestnr(TbSurveyQestnr tbSurveyQestnr) {
        this.tbSurveyQestnr = tbSurveyQestnr;
    }

    public TbSurveyQestnrCmmn getTbSurveyQestnrCmmn() {
        return this.tbSurveyQestnrCmmn;
    }

    public void setTbSurveyQestnrCmmn(TbSurveyQestnrCmmn tbSurveyQestnrCmmn) {
        if (tbSurveyQestnrCmmn == null) {
            this.tbSurveyQestnrCmmn = new TbSurveyQestnrCmmn();

            if (this.tbSurveyQestnr != null) {
                this.tbSurveyQestnrCmmn.setSurveyQestnrId(this.tbSurveyQestnr.getSurveyQestnrId());
            }
        } else {
            this.tbSurveyQestnrCmmn = tbSurveyQestnrCmmn;
        }
    }

    public JdSurveyDefinition getJdSurveyDefinition() {
        return this.jdSurveyDefinition;
    }

    public void setJdSurveyDefinition(JdSurveyDefinition jdSurveyDefinition) {
        this.jdSurveyDefinition = jdSurveyDefinition;
    }

    public Long getSurveyDefinitionId() {
        return this.surveyDefinitionId;
    }

    public void setSurveyDefinitionId(Long surveyDefinitionId) {
        this.surveyDefinitionId = surveyDefinitionId;
    }

    public String[] getBackgroundColors() {
        return this.backgroundColors;
    }

    public void setBackgroundColors(String[] backgroundColors) {
        this.backgroundColors = backgroundColors;
    }

}
