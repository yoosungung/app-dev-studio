package kr.ac.jj.openapi.application.bannermanage.model;

import kr.ac.jj.openapi.domain.main.model.api.banner.TbApiBanner;

/**
 * 배너 관리 Model
 */
public class BannerManageModel {

    private TbApiBanner tbApiBanner;

    public TbApiBanner getTbApiBanner() {
        return this.tbApiBanner;
    }

    public void setTbApiSvcKey(TbApiBanner tbApiBanner) {
        this.tbApiBanner = tbApiBanner;
    }

}
