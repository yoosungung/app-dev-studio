package com.jd.survey.zeus.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.jd.survey.com.BaseBean;

/**
 * 사용자
 */
public class ZeusUser extends BaseBean {

    // ~ Static fields/initializers
    // =========================================================

    private static final long serialVersionUID = 5913313636780482541L;

    // ~ Default Instance fields
    // ============================================================

    /** 아이디 */
    private String userId;

    /** 비밀번호 */
    private String password;

    /** 한글명 */
    private String userNm;

    /** 이메일 */
    private String email;

    /** 비밀번호 복호화 값 */
    private String salt;

    /** 가입일 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinDt;

    /** 기관코드 */
    private String organCd;

    /** 기관명 */
    private String organNm;

    /** 설문조사설정 아이디 */
    private String surveyDefinitionId;

    private String grade;

    // ~ Instance fields
    // ====================================================================

    // ~ Constructors
    // =======================================================================

    // ~ Methods
    // ============================================================================

    // ~ Default Methods
    // ====================================================================

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getOrganCd() {
        return organCd;
    }

    public void setOrganCd(String organCd) {
        this.organCd = organCd;
    }

    public String getOrganNm() {
        return organNm;
    }

    public void setOrganNm(String organNm) {
        this.organNm = organNm;
    }

    public Date getJoinDt() {
        return joinDt;
    }

    public void setJoinDt(Date joinDt) {
        this.joinDt = joinDt;
    }

    public String getSurveyDefinitionId() {
        return surveyDefinitionId;
    }

    public void setSurveyDefinitionId(String surveyDefinitionId) {
        this.surveyDefinitionId = surveyDefinitionId;
    }

    public String getGrade() {
        String str = "";
        if (this.grade.equals("ZEUS_USER"))
            str = "일반사용자";
        else if (this.grade.equals("ZEUS_TEMP_PW"))
            str = "임시비밀번호회원";
        else if (this.grade.equals("ZEUS_EQUIP"))
            str = "장비담당자";
        else if (this.grade.equals("ZEUS_CLOUD"))
            str = "기관담당자";
        else if (this.grade.equals("ZEUS_CALL"))
            str = "온라인상담담당자";
        else if (this.grade.equals("ZEUS_PRIVACY_MASTER"))
            str = "개인정보담당관";
        else if (this.grade.equals("ZEUS_SYS_MASTER"))
            str = "시스템관리자";
        return str;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
