package kr.ac.jj.shared.domain.main.model.sys.author.relatecode;

/**
 * 시스템 - 권한별 관련 코드
 */
public class TbSysAuthorRelateCode extends TbSysAuthorRelateCodeEntity {

    private static final long serialVersionUID = -2387161233969070304L;

    private String relateCodeValueNm;
    private Boolean relateCodeAuthorYn;

    public String getRelateCodeValueNm() {
        return this.relateCodeValueNm;
    }

    public void setRelateCodeValueNm(String relateCodeValueNm) {
        this.relateCodeValueNm = relateCodeValueNm;
    }

    public Boolean getRelateCodeAuthorYn() {
        return this.relateCodeAuthorYn;
    }

    public void setRelateCodeAuthorYn(Boolean relateCodeAuthorYn) {
        this.relateCodeAuthorYn = relateCodeAuthorYn;
    }

}
