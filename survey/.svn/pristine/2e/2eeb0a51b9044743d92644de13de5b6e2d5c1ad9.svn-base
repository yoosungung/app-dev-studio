package com.jd.survey.pub.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.jd.survey.com.BaseBean;

/**
 * 사용자설문조사
 */
public class SurveySecUserDefinition extends BaseBean {
	
	//~ Static fields/initializers =========================================================
	
	private static final long serialVersionUID = 6354804371226699870L;
	
	//~ Default Instance fields ============================================================
	
	/** 조사설정 아이디 */
	private long surveyDefinitionId;
	
	private long surveyId;
	
	/** 사용자아이디 */
	private long userId;
	
	/**사용자로그인*/
	private String login;
	
	private String name;
	
	private String organNm;
	
	private String status;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date submissionDate;
	
	//~ Instance fields ====================================================================
	
	//~ Constructors =======================================================================
	
	//~ Methods ============================================================================

	//~ Default Methods ====================================================================
	
	public long getSurveyDefinitionId() {
		return surveyDefinitionId;
	}

	public long getUserId() {
		return userId;
	}

	public String getLogin() {
		return login;
	}

	public void setSurveyDefinitionId(long surveyDefinitionId) {
		this.surveyDefinitionId = surveyDefinitionId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public long getSurveyId() {
		return surveyId;
	}

	public String getName() {
		return name;
	}

	public String getOrganNm() {
		return organNm;
	}

	public String getStatus() {
		return status;
	}

	public void setSurveyId(long surveyId) {
		this.surveyId = surveyId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrganNm(String organNm) {
		this.organNm = organNm;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	
	
}