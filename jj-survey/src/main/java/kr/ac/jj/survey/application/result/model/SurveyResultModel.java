package kr.ac.jj.survey.application.result.model;

import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.survey.domain.main.model.jd.survey.definition.JdSurveyDefinition;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.TbSurveyQestnr;

/**
 * 설문 결과 Model
 */
public class SurveyResultModel {

    private TbSurveyQestnr tbSurveyQestnr;
    private JdSurveyDefinition jdSurveyDefinition;
    private BaseMapList deviceSubmitList;
    private BaseMapList cmmnRspnsList;

    public TbSurveyQestnr getTbSurveyQestnr() {
        return this.tbSurveyQestnr;
    }

    public void setTbSurveyQestnr(TbSurveyQestnr tbSurveyQestnr) {
        this.tbSurveyQestnr = tbSurveyQestnr;
    }

    public JdSurveyDefinition getJdSurveyDefinition() {
        return this.jdSurveyDefinition;
    }

    public void setJdSurveyDefinition(JdSurveyDefinition jdSurveyDefinition) {
        this.jdSurveyDefinition = jdSurveyDefinition;
    }

    public BaseMapList getDeviceSubmitList() {
        return this.deviceSubmitList;
    }

    public void setDeviceSubmitList(BaseMapList deviceSubmitList) {
        this.deviceSubmitList = deviceSubmitList;
    }

    public BaseMapList getCmmnRspnsList() {
        return this.cmmnRspnsList;
    }

    public void setCmmnRspnsList(BaseMapList cmmnRspnsList) {
        this.cmmnRspnsList = cmmnRspnsList;
    }

}
