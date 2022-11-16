package kr.ac.jj.shared.application.admin.sysmanage.authormanage.model;

import java.util.List;

import kr.ac.jj.shared.domain.main.model.sys.rolehierarchy.TbSysRoleHierarchy;

/**
 * 권한 계층 관리 Model
 */
public class AuthorityHierarchyManageModel {

    private List<TbSysRoleHierarchy> tbSysRoleHierarchyList;

    public List<TbSysRoleHierarchy> getTbSysRoleHierarchyList() {
        return this.tbSysRoleHierarchyList;
    }

    public void setTbSysRoleHierarchyList(List<TbSysRoleHierarchy> tbSysRoleHierarchyList) {
        this.tbSysRoleHierarchyList = tbSysRoleHierarchyList;
    }

}
