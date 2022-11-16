package com.jd.survey.pub.domain;

import com.jd.survey.com.BaseBean;

/**
 * 통계
 */
public class SurveyDefinitionStatus extends BaseBean {
	
	//~ Static fields/initializers =========================================================
	
	private static final long serialVersionUID = 7635130345691097975L;
	
	//~ Default Instance fields ============================================================
	
	/** 코드 */
	private String code;
	
	/** 이름 */
	private String name;

	/** 건수 */
	private double cnt;

	
	//~ Instance fields ====================================================================
	
	//~ Constructors =======================================================================
	
	//~ Methods ============================================================================

	//~ Default Methods ====================================================================
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public double getCnt() {
		return cnt;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCnt(double cnt) {
		this.cnt = cnt;
	}
}