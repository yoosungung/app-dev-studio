package kr.ac.jj.openapi.application.servicelog.model;

import kr.ac.jj.openapi.domain.main.model.api.svc.key.TbApiSvcKey;
import kr.ac.jj.openapi.domain.main.model.api.svc.key.hist.TbApiSvcKeyHist;

/**
 * 에러 로그 관리 Model
 */
public class ApiLogManageModel {

    private TbApiSvcKeyHist apiSvcKeyhist;
    private TbApiSvcKey apiSvcKey;

    public TbApiSvcKeyHist getApiSvcKeyhist() {
        return this.apiSvcKeyhist;
    }
    public void setApiSvcKeyhist(TbApiSvcKeyHist apiSvcKeyhist) {
        this.apiSvcKeyhist = apiSvcKeyhist;
    }
    public TbApiSvcKey getApiSvcKey() {
        return this.apiSvcKey;
    }
    public void setApiSvcKey(TbApiSvcKey apiSvcKey) {
        this.apiSvcKey = apiSvcKey;
    }

}
