package kr.ac.jj.survey.application.admin.sysmanage.authormanage.model;

import java.util.List;
import java.util.Map;

import kr.ac.jj.survey.domain.main.model.com.dept.author.TbComDeptAuthorToDept;
import kr.ac.jj.survey.domain.main.model.sys.author.TbSysAuthor;
import kr.ac.jj.survey.domain.main.model.sys.menu.author.TbSysMenuAuthorToMenu;
import kr.ac.jj.survey.domain.main.model.sys.resrce.author.TbSysResrceAuthorToResrce;
import kr.ac.jj.survey.domain.main.model.sys.user.author.TbSysUserAuthorToUser;

/**
 * 권한 관리 Model
 */
public class AuthorityManageModel {
    private TbSysAuthor tbSysAuthor;
    private Map<String, List<TbSysMenuAuthorToMenu>> tbSysMenuAuthorToMenuListMap;
    private List<TbSysResrceAuthorToResrce> tbSysResrceAuthorToResrceList;
    private List<TbComDeptAuthorToDept> tbComDeptAuthorToDeptList;
    private List<TbSysUserAuthorToUser> tbSysUserAuthorToUserList;

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
}
