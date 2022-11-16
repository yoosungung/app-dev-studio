package kr.ac.jj.shared.application.bbs.faq.model;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.domain.main.model.bbs.faq.TbBbsFaq;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;

/**
 * 게시판(FAQ) Model
 */
public class BbsFaqModel {

    private TbBbsFaq tbBbsFaq;

    public boolean isEditable() {
        if (this.tbBbsFaq == null) {
            return false;
        }

        if (BooleanUtils.isTrue(tbBbsFaq.getDeleteYn())) {
            return false;
        }

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        if (StringUtils.equals(this.tbBbsFaq.getWritngPsnId(), loginUser.getPersonId())) {
            return true;
        }

        return false;
    }

    public TbBbsFaq getTbBbsFaq() {
        return this.tbBbsFaq;
    }

    public void setTbBbsFaq(TbBbsFaq tbBbsFaq) {
        this.tbBbsFaq = tbBbsFaq;
    }

}
