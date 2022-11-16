package kr.ac.jj.survey.application.groupmanage.model;

import java.util.List;
import java.util.Map;

import kr.ac.jj.survey.domain.main.model.survey.group.TbSurveyGroup;
import kr.ac.jj.survey.domain.main.model.survey.group.TbSurveyGroupToDept;

/**
 * 설문지 관리 Model
 */
public class GroupManageModel {

    private TbSurveyGroup tbSurveyGroup;
    private Map<String, List<TbSurveyGroupToDept>> tbSurveyGroupToDeptListMap;

    public boolean isEditable() {
        if (this.tbSurveyGroup == null) {
            return false;
        }

        return true;
    }

    public TbSurveyGroup getTbSurveyGroup() {
        return this.tbSurveyGroup;
    }

    public void setTbSurveyGroup(TbSurveyGroup tbSurveyGroup) {
        this.tbSurveyGroup = tbSurveyGroup;
    }

    public Map<String, List<TbSurveyGroupToDept>> getTbSurveyGroupToDeptListMap() {
        return this.tbSurveyGroupToDeptListMap;
    }

    public void setTbSurveyGroupToDeptListMap(Map<String, List<TbSurveyGroupToDept>> tbSurveyGroupToDeptListMap) {
        this.tbSurveyGroupToDeptListMap = tbSurveyGroupToDeptListMap;
    }

}
