package com.jd.survey.pub.web.cmd;

import com.jd.survey.com.BaseCmd;
import com.jd.survey.pub.domain.SurveyDefinition;

public class SurveyDefinitionCmd extends BaseCmd<SurveyDefinition> {

	//~ Static fields/initializers =========================================================

	private static final long serialVersionUID = -5254443416109984903L;
	
	//~ Instance fields ====================================================================

	/** 실태조사 아이디 */
	private String surveyId;
	
	//~ Constructors =======================================================================

	public SurveyDefinitionCmd() {
		super(new String[]{"endDt", "userCnt"}, true);
		super.setPageSize(20);
	}
	
	//~ Methods ============================================================================
	
	
	public String getSurveyId() {
		return surveyId;
	}
	
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	
}
