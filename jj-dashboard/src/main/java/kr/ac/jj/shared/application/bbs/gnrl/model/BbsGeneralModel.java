package kr.ac.jj.shared.application.bbs.gnrl.model;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.domain.main.model.bbs.gnrl.TbBbsGnrl;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;

/**
 * 게시판(일반) Model
 */
public class BbsGeneralModel {

    private TbBbsGnrl tbBbsGnrl;
    private Boolean reply;

    public boolean isEditable() {
        if (this.tbBbsGnrl == null) {
            return false;
        }

        if (BooleanUtils.isTrue(tbBbsGnrl.getDeleteYn())) {
            return false;
        }

        if (SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN")) {
            return true;
        }

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        if (StringUtils.equals(this.tbBbsGnrl.getWritngPsnId(), loginUser.getPersonId())) {
            return true;
        }

        return false;
    }

    public TbBbsGnrl getTbBbsGnrl() {
        return this.tbBbsGnrl;
    }

    public void setTbBbsGnrl(TbBbsGnrl tbBbsGnrl) {
        this.tbBbsGnrl = tbBbsGnrl;
    }

    public Boolean getReply() {
        return this.reply;
    }

    public void setReply(Boolean reply) {
        this.reply = reply;
    }

}
