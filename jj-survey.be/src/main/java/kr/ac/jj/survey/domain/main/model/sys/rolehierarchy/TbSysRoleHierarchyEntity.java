package kr.ac.jj.survey.domain.main.model.sys.rolehierarchy;

import kr.ac.jj.survey.domain.main.model.MainEntity;

/**
 * 시스템 - 역할 계층 Entity
 */
abstract class TbSysRoleHierarchyEntity extends MainEntity {
    private static final long serialVersionUID = -7242417225534194120L;

    protected String roleHierarchyId;
    protected String parntsRoleCode;
    protected String chldrnRoleCode;
    protected Integer sortOrdr;

    public String getRoleHierarchyId() {
        return this.roleHierarchyId;
    }

    public void setRoleHierarchyId(String roleHierarchyId) {
        this.roleHierarchyId = roleHierarchyId;
    }

    public String getParntsRoleCode() {
        return this.parntsRoleCode;
    }

    public void setParntsRoleCode(String parntsRoleCode) {
        this.parntsRoleCode = parntsRoleCode;
    }

    public String getChldrnRoleCode() {
        return this.chldrnRoleCode;
    }

    public void setChldrnRoleCode(String chldrnRoleCode) {
        this.chldrnRoleCode = chldrnRoleCode;
    }

    public Integer getSortOrdr() {
        return this.sortOrdr;
    }

    public void setSortOrdr(Integer sortOrdr) {
        this.sortOrdr = sortOrdr;
    }
}
