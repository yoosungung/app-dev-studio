package kr.ac.jj.shared.application.admin.sysmanage.menumanage.model;

import java.util.List;

import kr.ac.jj.shared.domain.main.model.sys.menu.TbSysMenu;
import kr.ac.jj.shared.domain.main.model.sys.menu.author.TbSysMenuAuthorToAuthor;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;

/**
 * 메뉴 관리 Model
 */
public class MenuManageModel {

    private TbSysMenu tbSysMenu;
    private List<TbSysMenuAuthorToAuthor> tbSysMenuAuthorToAuthorList;
    private Integer childMenuCount;

    public boolean isEditable() {
        if (this.tbSysMenu == null) {
            return false;
        }

        if (!SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN", "ROLE_SUPER")) {
            return false;
        }

        return true;
    }

    public TbSysMenu getTbSysMenu() {
        return this.tbSysMenu;
    }

    public void setTbSysMenu(TbSysMenu tbSysMenu) {
        this.tbSysMenu = tbSysMenu;
    }

    public List<TbSysMenuAuthorToAuthor> getTbSysMenuAuthorToAuthorList() {
        return this.tbSysMenuAuthorToAuthorList;
    }

    public void setTbSysMenuAuthorToAuthorList(List<TbSysMenuAuthorToAuthor> tbSysMenuAuthorToAuthorList) {
        this.tbSysMenuAuthorToAuthorList = tbSysMenuAuthorToAuthorList;
    }

    public Integer getChildMenuCount() {
        return this.childMenuCount;
    }

    public void setChildMenuCount(Integer childMenuCount) {
        this.childMenuCount = childMenuCount;
    }

}
