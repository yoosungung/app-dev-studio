package com.jd.survey.zeus.web.cmd;

import com.jd.survey.com.BaseCmd;
import com.jd.survey.zeus.domain.ZeusUser;

public class ZeusUserCmd extends BaseCmd<ZeusUser> {

	//~ Static fields/initializers =========================================================

	private static final long serialVersionUID = 5156258346059767875L;
	
	//~ Instance fields ====================================================================
	
	/** 실태조사 아이디 */
	private long surveyId;
	
	//~ Constructors =======================================================================

	public ZeusUserCmd() {
		super(new String[]{"joinDt", "userId", "userNm", "email", "organCd", "organNm"}, true);
		super.setPageSize(20);
	}
	
	//~ Methods ============================================================================
	
	
	
	public long getSurveyId() {
		return surveyId;
	}
	
	public void setSurveyId(long surveyId) {
		this.surveyId = surveyId;
	}
	
}
