package com.jd.survey.zeus.domain;

import com.jd.survey.com.BaseBean;

public class TbAccessLog extends BaseBean {
    // ~ Static fields/initializers
    // =========================================================

    static final long serialVersionUID = -5074151734000439536L;

    // ~ Instance fields
    // ====================================================================

    /** 접속일시 */
    private String accessDt;

    /** 접속카운트 */
    private String accessCnt;
    /** 오늘 카운트 */
    private String accessTodayCnt;

    /** 메뉴별 접속 코드 */
    private String accessCode;

    // ~ Constructors
    // =======================================================================

    // ~ Methods
    // ============================================================================

    public String getAccessDt() {
        return accessDt;
    }

    public String getAccessCnt() {
        return accessCnt;
    }

    public String getAccessTodayCnt() {
        return accessTodayCnt;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessDt(String accessDt) {
        this.accessDt = accessDt;
    }

    public void setAccessCnt(String accessCnt) {
        this.accessCnt = accessCnt;
    }

    public void setAccessTodayCnt(String accessTodayCnt) {
        this.accessTodayCnt = accessTodayCnt;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

}
