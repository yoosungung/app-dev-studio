package kr.ac.jj.shared.application.admin.sysmanage.resrcemanage.model;

import java.util.List;

import kr.ac.jj.shared.domain.main.model.sys.resrce.TbSysResrce;
import kr.ac.jj.shared.domain.main.model.sys.resrce.author.TbSysResrceAuthorToAuthor;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;

/**
 * 리소스 관리 Model
 */
public class ResourceManageModel {

    private TbSysResrce tbSysResrce;
    private List<TbSysResrceAuthorToAuthor> tbSysResrceAuthorToAuthorList;

    public boolean isEditable() {
        if (this.tbSysResrce == null) {
            return false;
        }

        if (!SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN", "ROLE_SUPER")) {
            return false;
        }

        return true;
    }

    public TbSysResrce getTbSysResrce() {
        return this.tbSysResrce;
    }

    public void setTbSysResrce(TbSysResrce tbSysResrce) {
        this.tbSysResrce = tbSysResrce;
    }

    public List<TbSysResrceAuthorToAuthor> getTbSysResrceAuthorToAuthorList() {
        return this.tbSysResrceAuthorToAuthorList;
    }

    public void setTbSysResrceAuthorToAuthorList(List<TbSysResrceAuthorToAuthor> tbSysResrceAuthorToAuthorList) {
        this.tbSysResrceAuthorToAuthorList = tbSysResrceAuthorToAuthorList;
    }

}
