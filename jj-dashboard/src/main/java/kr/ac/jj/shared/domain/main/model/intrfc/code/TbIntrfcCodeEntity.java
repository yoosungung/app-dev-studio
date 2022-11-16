package kr.ac.jj.shared.domain.main.model.intrfc.code;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 인터페이스 - 코드 Entity
 */
abstract class TbIntrfcCodeEntity extends MainEntity {

    private static final long serialVersionUID = -1316433749018057257L;

    protected String codeTy;
    protected String codeValue;
    protected String codeNm;
    protected String codeIndictYn;

    public String getCodeTy() {
        return this.codeTy;
    }

    public void setCodeTy(String codeTy) {
        this.codeTy = codeTy;
    }

    public String getCodeValue() {
        return this.codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeNm() {
        return this.codeNm;
    }

    public void setCodeNm(String codeNm) {
        this.codeNm = codeNm;
    }

    public String getCodeIndictYn() {
        return this.codeIndictYn;
    }

    public void setCodeIndictYn(String codeIndictYn) {
        this.codeIndictYn = codeIndictYn;
    }

}
