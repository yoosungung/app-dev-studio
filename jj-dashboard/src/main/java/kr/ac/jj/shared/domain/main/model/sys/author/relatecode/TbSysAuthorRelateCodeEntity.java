package kr.ac.jj.shared.domain.main.model.sys.author.relatecode;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 시스템 - 권한별 관련 코드 Entity
 */
abstract class TbSysAuthorRelateCodeEntity extends MainEntity {

    private static final long serialVersionUID = -2079572375626094723L;

    protected String authorId;
    protected String relateCodeSe;
    protected String relateCodeValue;

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getRelateCodeSe() {
        return this.relateCodeSe;
    }

    public void setRelateCodeSe(String relateCodeSe) {
        this.relateCodeSe = relateCodeSe;
    }

    public String getRelateCodeValue() {
        return this.relateCodeValue;
    }

    public void setRelateCodeValue(String relateCodeValue) {
        this.relateCodeValue = relateCodeValue;
    }

}
