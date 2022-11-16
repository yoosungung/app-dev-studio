package kr.ac.jj.survey.domain.main.model.sys.code.value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.ac.jj.survey.domain.main.model.MainEntity;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanDeserializer;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanSerializer;

/**
 * 시스템 - 코드 값 Entity
 */
abstract class TbSysCodeValueEntity extends MainEntity {
    private static final long serialVersionUID = -7157536365717789143L;

    protected String codeValueId;
    protected String codeGroupId;
    protected String codeValue;
    protected String codeValueNmTitle;
    protected String codeValueDc;
    protected Integer sortOrdr;
    protected Boolean useYn;

    public String getCodeValueId() {
        return this.codeValueId;
    }

    public void setCodeValueId(String codeValueId) {
        this.codeValueId = codeValueId;
    }

    public String getCodeGroupId() {
        return this.codeGroupId;
    }

    public void setCodeGroupId(String codeGroupId) {
        this.codeGroupId = codeGroupId;
    }

    public String getCodeValue() {
        return this.codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public String getCodeValueNmTitle() {
        return this.codeValueNmTitle;
    }

    public void setCodeValueNmTitle(String codeValueNmTitle) {
        this.codeValueNmTitle = codeValueNmTitle;
    }

    public String getCodeValueDc() {
        return this.codeValueDc;
    }

    public void setCodeValueDc(String codeValueDc) {
        this.codeValueDc = codeValueDc;
    }

    public Integer getSortOrdr() {
        return this.sortOrdr;
    }

    public void setSortOrdr(Integer sortOrdr) {
        this.sortOrdr = sortOrdr;
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
