package com.jd.survey.pub.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.jd.survey.com.BaseBean;

/**
 * 설문조사 댓글
 */
public class SurveyComment extends BaseBean {
	
	//~ Static fields/initializers =========================================================
	
	private static final long serialVersionUID = 5913313636780482541L;
	
	//~ Default Instance fields ============================================================
	
	/** 아이디 */
	private long id;
	
	/** 사용자아이디 */
	private long userId;
	
	/** 댓글 */
	private String comments;
	
	/** 수정일 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date modifyDt;
	
	/** 등록일 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date registDt;
	
	/** 설문조사아이디 */
	private long surveyDefinitionId;
	
	//~ Instance fields ====================================================================
	
	/** 사용자명 */
	private String userNm;
	
	//~ Constructors =======================================================================
	
	//~ Methods ============================================================================

	//~ Default Methods ====================================================================
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public Date getModifyDt() {
		return modifyDt;
	}

	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	public Date getRegistDt() {
		return registDt;
	}

	public void setRegistDt(Date registDt) {
		this.registDt = registDt;
	}

	public long getSurveyDefinitionId() {
		return surveyDefinitionId;
	}

	public void setSurveyDefinitionId(long surveyDefinitionId) {
		this.surveyDefinitionId = surveyDefinitionId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
}