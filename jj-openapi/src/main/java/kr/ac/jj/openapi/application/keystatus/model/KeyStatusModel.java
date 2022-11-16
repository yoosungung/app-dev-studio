package kr.ac.jj.openapi.application.keystatus.model;

import kr.ac.jj.openapi.domain.main.model.api.svc.key.TbApiSvcKey;
import kr.ac.jj.shared.domain.main.model.com.person.TbComPerson;

/**
 * 키 신청 현황 Model
 */
public class KeyStatusModel {

    private TbApiSvcKey tbApiSvcKey;
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

    public TbComPerson getTbComPerson() {
        return this.tbComPerson;
    }

    public void setTbComPerson(TbComPerson tbComPerson) {
        this.tbComPerson = tbComPerson;
    }

}
