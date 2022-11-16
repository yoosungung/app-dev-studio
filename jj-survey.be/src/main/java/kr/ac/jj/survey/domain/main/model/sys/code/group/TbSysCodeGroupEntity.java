package kr.ac.jj.survey.domain.main.model.sys.code.group;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.ac.jj.survey.domain.main.model.MainEntity;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanDeserializer;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanSerializer;

/**
 * 시스템 - 코드 그룹 Entity
 */
abstract class TbSysCodeGroupEntity extends MainEntity {
    private static final long serialVersionUID = 2646519778437845487L;

    protected String codeGroupId;
    protected String codeGroup;
    protected String codeGroupNmTitle;
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

    public String getCodeGroupNmTitle() {
        return this.codeGroupNmTitle;
    }

    public void setCodeGroupNmTitle(String codeGroupNmTitle) {
        this.codeGroupNmTitle = codeGroupNmTitle;
    }

    public String getCodeGroupDc() {
        return this.codeGroupDc;
    }

    public void setCodeGroupDc(String codeGroupDc) {
        this.codeGroupDc = codeGroupDc;
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getUseYn() {
        return this.useYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    public void setUseYn(Boolean useYn) {
        this.useYn = useYn;
    }
}
