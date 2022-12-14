package kr.ac.jj.survey.application.templatemanage.model;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.domain.main.model.jd.survey.definition.JdSurveyDefinition;
import kr.ac.jj.survey.domain.main.model.survey.tmplat.TbSurveyTmplat;

/**
 * 템플릿 관리 Model
 */
public class TemplateManageModel {

    private TbSurveyTmplat tbSurveyTmplat;
    private JdSurveyDefinition jdSurveyDefinition;
    private String[] backgroundColors;

    public boolean isEditable() {
        if (this.tbSurveyTmplat == null) {
            return false;
        }

        if (!StringUtils.equals(this.tbSurveyTmplat.getTmplatSttus(), "0")) {
            return false;
        }

        return true;
    }

    public TbSurveyTmplat getTbSurveyTmplat() {
        return this.tbSurveyTmplat;
    }

    public void setTbSurveyTmplat(TbSurveyTmplat tbSurveyTmplat) {
        this.tbSurveyTmplat = tbSurveyTmplat;
    }

    public JdSurveyDefinition getJdSurveyDefinition() {
        return this.jdSurveyDefinition;
    }

    public void setJdSurveyDefinition(JdSurveyDefinition jdSurveyDefinition) {
        this.jdSurveyDefinition = jdSurveyDefinition;
    }

    public String[] getBackgroundColors() {
        return this.backgroundColors;
    }

    public void setBackgroundColors(String[] backgroundColors) {
        this.backgroundColors = backgroundColors;
    }

}
