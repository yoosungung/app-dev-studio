package kr.ac.jj.shared.application.bbs.notice.model;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.domain.main.model.bbs.notice.TbBbsNotice;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;

/**
 * 게시판(공지사항) Model
 */
public class BbsNoticeModel {

    private TbBbsNotice tbBbsNotice;

    public boolean isEditable() {
        if (this.tbBbsNotice == null) {
            return false;
        }

        if (BooleanUtils.isTrue(tbBbsNotice.getDeleteYn())) {
            return false;
        }

        LoginUser loginUser = SessionContextUtil.getLoginUser();

        if (StringUtils.equals(this.tbBbsNotice.getWritngPsnId(), loginUser.getPersonId())) {
            return true;
        }

        return false;
    }

    public TbBbsNotice getTbBbsNotice() {
        return this.tbBbsNotice;
    }

    public void setTbBbsNotice(TbBbsNotice tbBbsNotice) {
        this.tbBbsNotice = tbBbsNotice;
    }

}
