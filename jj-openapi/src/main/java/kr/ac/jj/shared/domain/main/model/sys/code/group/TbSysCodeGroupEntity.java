package kr.ac.jj.shared.domain.main.model.sys.code.group;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 시스템 - 코드 그룹 Entity
 */
abstract class TbSysCodeGroupEntity extends MainEntity {

    private static final long serialVersionUID = 2646519778437845487L;

    protected String codeGroupId;
    protected String codeGroup;
    protected String codeGroupNm;
    protected String codeGroupDc;
    protected Boolean useYn;

    public String getCodeGroupId() {
        return this.codeGroupId;
    }

    public void setCodeGroupId(String codeGroupId) {
        this.codeGroupId = codeGroupId;
    }

    public String getCodeGroup() {
        return this.codeGroup;
    }

    public void setCodeGroup(String codeGroup) {
        this.codeGroup = codeGroup;
    }

    public String getCodeGroupNm() {
        return this.codeGroupNm;
    }

    public void setCodeGroupNm(String codeGroupNm) {
        this.codeGroupNm = codeGroupNm;
    }

    public String getCodeGroupDc() {
        return this.codeGroupDc;
    }

    public void setCodeGroupDc(String codeGroupDc) {
        this.codeGroupDc = codeGroupDc;
    }

    public Boolean getUseYn() {
        return this.useYn;
    }

    public void setUseYn(Boolean useYn) {
        this.useYn = useYn;
    }

}
