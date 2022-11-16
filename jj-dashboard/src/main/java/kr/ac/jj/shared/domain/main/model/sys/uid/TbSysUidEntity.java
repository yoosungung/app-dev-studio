package kr.ac.jj.shared.domain.main.model.sys.uid;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 시스템 - UID Entity
 */
abstract class TbSysUidEntity extends MainEntity {

    private static final long serialVersionUID = 1175707378069827668L;

    protected String uidValue;
    protected String uidSe;

    public String getUidValue() {
        return this.uidValue;
    }

    public void setUidValue(String uidValue) {
        this.uidValue = uidValue;
    }

    public String getUidSe() {
        return this.uidSe;
    }

    public void setUidSe(String uidSe) {
        this.uidSe = uidSe;
    }

}
