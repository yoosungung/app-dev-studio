package kr.ac.jj.openapi.application.keymanage.model;

import kr.ac.jj.openapi.domain.main.model.api.svc.key.TbApiSvcKey;
import kr.ac.jj.shared.domain.main.model.com.person.TbComPerson;

/**
 * 키 발급 관리 Model
 */
public class KeyManageModel {

    private TbApiSvcKey tbApiSvcKey;
    private TbComPerson applcntTbComPerson;
    private TbComPerson tbComPerson;

    public boolean isEditable() {
        if (this.tbApiSvcKey == null) {
            return false;
        }

        return true;
    }

    public TbApiSvcKey getTbApiSvcKey() {
        return this.tbApiSvcKey;
    }

    public void setTbApiSvcKey(TbApiSvcKey tbApiSvcKey) {
        this.tbApiSvcKey = tbApiSvcKey;
    }

    public TbComPerson getApplcntTbComPerson() {
        return this.applcntTbComPerson;
    }

    public void setApplcntTbComPerson(TbComPerson applcntTbComPerson) {
        this.applcntTbComPerson = applcntTbComPerson;
    }

    public TbComPerson getTbComPerson() {
        return this.tbComPerson;
    }

    public void setTbComPerson(TbComPerson tbComPerson) {
        this.tbComPerson = tbComPerson;
    }

}
