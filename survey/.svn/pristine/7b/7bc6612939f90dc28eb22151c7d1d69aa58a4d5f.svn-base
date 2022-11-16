package com.jd.survey.zeus.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.jd.survey.com.BaseBean;

/**
 * ZEUS Q&A
 */
public class ZeusQuestionAndAnswer extends BaseBean {
	
	//~ Static fields/initializers =========================================================
	
	private static final long serialVersionUID = -2192394653030771512L;
	
	//~ Default Instance fields ============================================================
	
	/** 게시물 번호 */
	private int postNo;
	
	/** 게시물 제목 */
	private String subject;

	/** 등록자명 */
	private String registNm;
	
	/** 분류코드 */
	private String classCd;
	
	/** 진행상태구분코드 */
	private String statCd;
	
	/** 등록일 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date registDt;

	//~ Instance fields ====================================================================
	
	//~ Constructors =======================================================================
	
	//~ Methods ============================================================================

	//~ Default Methods ====================================================================
	
	public int getPostNo() {
		return postNo;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getRegistNm() {
		return registNm;
	}
	
	public String getClassCd() {
		return classCd;
	}
	
	public String getStatCd() {
		return statCd;
	}
	
	public Date getRegistDt() {
		return registDt;
	}
	
	public void setPostNo(int postNo) {
		this.postNo = postNo;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setRegistNm(String registNm) {
		this.registNm = registNm;
	}
	
	public void setClassCd(String classCd) {
		this.classCd = classCd;
	}
	
	public void setStatCd(String statCd) {
		this.statCd = statCd;
	}
	
	public void setRegistDt(Date registDt) {
		this.registDt = registDt;
	}
	

}