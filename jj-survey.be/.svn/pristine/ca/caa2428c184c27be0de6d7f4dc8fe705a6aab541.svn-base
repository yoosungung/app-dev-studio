package kr.ac.jj.survey.infrastructure.security.model;

public class SecuredRoleHierarchy {
    private String parntsRoleCode;
    private String chldrnRoleCode;

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

    public String getHierarchyString() {
        return this.getChldrnRoleCode() + " > " + this.getParntsRoleCode();
    }
}
