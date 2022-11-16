package kr.ac.jj.shared.domain.main.model.sys.intrfc;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 시스템 - 인터페이스 Entity
 */
abstract class TbSysIntrfcEntity extends MainEntity {

    private static final long serialVersionUID = 1780213080435435100L;

    protected String tableNm;
    protected String idValue;

    public String getTableNm() {
        return this.tableNm;
    }

    public void setTableNm(String tableNm) {
        this.tableNm = tableNm;
    }

    public String getIdValue() {
        return this.idValue;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

}
