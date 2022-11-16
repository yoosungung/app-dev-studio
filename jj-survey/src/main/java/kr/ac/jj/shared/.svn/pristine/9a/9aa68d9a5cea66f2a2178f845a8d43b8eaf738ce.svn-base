package kr.ac.jj.shared.application.admin.sysmanage.authormanage.model;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;

import kr.ac.jj.shared.domain.main.model.com.dept.author.TbComDeptAuthorToDept;
import kr.ac.jj.shared.domain.main.model.sys.author.TbSysAuthor;
import kr.ac.jj.shared.domain.main.model.sys.author.relatecode.TbSysAuthorRelateCode;
import kr.ac.jj.shared.domain.main.model.sys.menu.author.TbSysMenuAuthorToMenu;
import kr.ac.jj.shared.domain.main.model.sys.resrce.author.TbSysResrceAuthorToResrce;
import kr.ac.jj.shared.domain.main.model.sys.user.author.TbSysUserAuthorToUser;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;

/**
 * 권한 관리 Model
 */
public class AuthorityManageModel {

    private TbSysAuthor tbSysAuthor;
    private Map<String, List<TbSysMenuAuthorToMenu>> tbSysMenuAuthorToMenuListMap;
    private List<TbSysResrceAuthorToResrce> tbSysResrceAuthorToResrceList;
    private List<TbComDeptAuthorToDept> tbComDeptAuthorToDeptList;
    private List<TbSysUserAuthorToUser> tbSysUserAuthorToUserList;
    private List<TbSysAuthorRelateCode> tbSysAuthorRelateCodePersonSeList;
    private List<TbSysAuthorRelateCode> tbSysAuthorRelateCodeDtyNmList;

    public boolean isEditable() {
        if (this.tbSysAuthor == null) {
            return false;
        }

        if (!SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN", "ROLE_SUPER")) {
            return false;
        }

        return true;
    }

    public boolean isDeletable() {
        if (!this.isEditable()) {
            return false;
        }

        if (BooleanUtils.isTrue(this.tbSysAuthor.getBassAuthorYn())) {
            return false;
        }

        return true;
    }

    public TbSysAuthor getTbSysAuthor() {
        return this.tbSysAuthor;
    }

    public void setTbSysAuthor(TbSysAuthor tbSysAuthor) {
        this.tbSysAuthor = tbSysAuthor;
    }

    public Map<String, List<TbSysMenuAuthorToMenu>> getTbSysMenuAuthorToMenuListMap() {
        return this.tbSysMenuAuthorToMenuListMap;
    }

    public void setTbSysMenuAuthorToMenuListMap(Map<String, List<TbSysMenuAuthorToMenu>> tbSysMenuAuthorToMenuListMap) {
        this.tbSysMenuAuthorToMenuListMap = tbSysMenuAuthorToMenuListMap;
    }

    public List<TbSysResrceAuthorToResrce> getTbSysResrceAuthorToResrceList() {
        return this.tbSysResrceAuthorToResrceList;
    }

    public void setTbSysResrceAuthorToResrceList(List<TbSysResrceAuthorToResrce> tbSysResrceAuthorToResrceList) {
        this.tbSysResrceAuthorToResrceList = tbSysResrceAuthorToResrceList;
    }

    public List<TbComDeptAuthorToDept> getTbComDeptAuthorToDeptList() {
        return this.tbComDeptAuthorToDeptList;
    }

    public void setTbComDeptAuthorToDeptList(List<TbComDeptAuthorToDept> tbComDeptAuthorToDeptList) {
        this.tbComDeptAuthorToDeptList = tbComDeptAuthorToDeptList;
    }

    public List<TbSysUserAuthorToUser> getTbSysUserAuthorToUserList() {
        return this.tbSysUserAuthorToUserList;
    }

    public void setTbSysUserAuthorToUserList(List<TbSysUserAuthorToUser> tbSysUserAuthorToUserList) {
        this.tbSysUserAuthorToUserList = tbSysUserAuthorToUserList;
    }

    public List<TbSysAuthorRelateCode> getTbSysAuthorRelateCodePersonSeList() {
        return this.tbSysAuthorRelateCodePersonSeList;
    }

    public void setTbSysAuthorRelateCodePersonSeList(List<TbSysAuthorRelateCode> tbSysAuthorRelateCodePersonSeList) {
        this.tbSysAuthorRelateCodePersonSeList = tbSysAuthorRelateCodePersonSeList;
    }

    public List<TbSysAuthorRelateCode> getTbSysAuthorRelateCodeDtyNmList() {
        return this.tbSysAuthorRelateCodeDtyNmList;
    }

    public void setTbSysAuthorRelateCodeDtyNmList(List<TbSysAuthorRelateCode> tbSysAuthorRelateCodeDtyNmList) {
        this.tbSysAuthorRelateCodeDtyNmList = tbSysAuthorRelateCodeDtyNmList;
    }

}
