package kr.ac.jj.shared.domain.main.model.com.dty;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 공통 - 직무 Entity
 */
abstract class TbComDtyEntity extends MainEntity {

    private static final long serialVersionUID = -5076247580265350668L;

    protected String dtyId;
    protected String dtySe;
    protected String dtyCode;
    protected String dtyNm;
    protected Boolean useYn;

    public String getDtyId() {
        return this.dtyId;
    }

    public void setDtyId(String dtyId) {
        this.dtyId = dtyId;
    }

    public String getDtySe() {
        return this.dtySe;
    }

    public void setDtySe(String dtySe) {
        this.dtySe = dtySe;
    }

    public String getDtyCode() {
        return this.dtyCode;
    }

    public void setDtyCode(String dtyCode) {
        this.dtyCode = dtyCode;
    }

    public String getDtyNm() {
        return this.dtyNm;
    }

    public void setDtyNm(String dtyNm) {
        this.dtyNm = dtyNm;
    }

    public Boolean getUseYn() {
        return this.useYn;
    }

    public void setUseYn(Boolean useYn) {
        this.useYn = useYn;
    }

}
