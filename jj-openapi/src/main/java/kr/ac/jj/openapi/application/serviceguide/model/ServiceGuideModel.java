package kr.ac.jj.openapi.application.serviceguide.model;

import kr.ac.jj.openapi.domain.main.model.serviceguide.TbServiceGuide;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;

/**
 * 서비스 이용안내 Model
 */
public class ServiceGuideModel {

    private TbServiceGuide tbServiceGuide;

    public boolean isEditable() {
        if (this.tbServiceGuide == null) {
            return false;
        }

        if (!SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN", "ROLE_SUPER")) {
            return false;
        }

        return true;
    }

    public TbServiceGuide getTbServiceGuide() {
        return this.tbServiceGuide;
    }

    public void setTbServiceGuide(TbServiceGuide tbServiceGuide) {
        this.tbServiceGuide = tbServiceGuide;
    }

}
