package kr.ac.jj.shared.application.admin.appmanage.dtymanage.model;

import java.util.List;

import kr.ac.jj.shared.domain.main.model.com.dty.TbComDty;
import kr.ac.jj.shared.domain.main.model.com.dty.author.TbComDtyAuthorToAuthor;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;

/**
 * 직무 관리 Model
 */
public class DutyManageModel {

    private TbComDty tbComDty;
    private List<TbComDtyAuthorToAuthor> tbComDtyAuthorToAuthorList;

    public boolean isEditable() {
        if (this.tbComDty == null) {
            return false;
        }

        if (!SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN", "ROLE_SUPER")) {
            return false;
        }

        return true;
    }

    public TbComDty getTbComDty() {
        return this.tbComDty;
    }

    public void setTbComDty(TbComDty tbComDty) {
        this.tbComDty = tbComDty;
    }

    public List<TbComDtyAuthorToAuthor> getTbComDtyAuthorToAuthorList() {
        return this.tbComDtyAuthorToAuthorList;
    }

    public void setTbComDtyAuthorToAuthorList(List<TbComDtyAuthorToAuthor> tbComDtyAuthorToAuthorList) {
        this.tbComDtyAuthorToAuthorList = tbComDtyAuthorToAuthorList;
    }

}
