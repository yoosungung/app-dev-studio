package kr.ac.jj.openapi.application.servicemanage.model;

import java.util.List;
import java.util.Map;

import kr.ac.jj.openapi.domain.main.model.api.svc.TbApiSvc;
import kr.ac.jj.openapi.domain.main.model.api.svc.mapng.TbApiSvcMapng;
import kr.ac.jj.openapi.domain.main.model.api.svc.result.TbApiSvcResult;

/**
 * 서비스 관리 Model
 */
public class ServiceManageModel {

    private TbApiSvc tbApiSvc;
    private List<TbApiSvcMapng> tbApiSvcMapngList;
    private List<TbApiSvcResult> tbApiSvcResultList;
    private Map<String, Object> tbApiSvcGubun;

    public boolean isEditable() {
        if (this.tbApiSvc == null) {
            return false;
        }

        return true;
    }

    public List<TbApiSvcResult> getTbApiSvcResultList() {
        return this.tbApiSvcResultList;
    }

    public void setTbApiSvcResultList(List<TbApiSvcResult> tbApiSvcResultList) {
        this.tbApiSvcResultList = tbApiSvcResultList;
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

    public Map<String, Object> getTbApiSvcGubun() {
        return this.tbApiSvcGubun;
    }

    public void setTbApiSvcGubun(Map<String, Object> tbApiSvcGubun) {
        this.tbApiSvcGubun = tbApiSvcGubun;
    }



}
