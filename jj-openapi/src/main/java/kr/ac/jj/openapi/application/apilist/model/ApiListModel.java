package kr.ac.jj.openapi.application.apilist.model;

import java.util.List;

import kr.ac.jj.openapi.domain.main.model.api.svc.TbApiSvc;
import kr.ac.jj.openapi.domain.main.model.api.svc.mapng.TbApiSvcMapng;

/**
 * 서비스 관리 Model
 */
public class ApiListModel {

    private TbApiSvc tbApiSvc;
    private List<TbApiSvcMapng> tbApiSvcMapngList;

    public boolean isEditable() {
        if (this.tbApiSvc == null) {
            return false;
        }

        return true;
    }

    public TbApiSvc getTbApiSvc() {
        return this.tbApiSvc;
    }

    public void setTbApiSvc(TbApiSvc tbApiSvc) {
        this.tbApiSvc = tbApiSvc;
    }

    public List<TbApiSvcMapng> getTbApiSvcMapngList() {
        return this.tbApiSvcMapngList;
    }

    public void setTbApiSvcMapngList(List<TbApiSvcMapng> tbApiSvcMapngList) {
        this.tbApiSvcMapngList = tbApiSvcMapngList;
    }

}
