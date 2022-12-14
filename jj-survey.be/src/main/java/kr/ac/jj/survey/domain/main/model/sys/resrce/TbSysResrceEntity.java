package kr.ac.jj.survey.domain.main.model.sys.resrce;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.ac.jj.survey.domain.main.model.MainEntity;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanDeserializer;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanSerializer;

/**
 * 시스템 - 리소스 Entity
 */
abstract class TbSysResrceEntity extends MainEntity {
    private static final long serialVersionUID = -3543926975402000312L;

    protected String resrceId;
    protected String resrceTy;
    protected String resrcePttrn;
    protected Integer sortOrdr;
    protected Boolean useYn;

    public String getResrceId() {
        return this.resrceId;
    }

    public void setResrceId(String resrceId) {
        this.resrceId = resrceId;
    }

    public String getResrceTy() {
        return this.resrceTy;
    }

    public void setResrceTy(String resrceTy) {
        this.resrceTy = resrceTy;
    }

    public String getResrcePttrn() {
        return this.resrcePttrn;
    }

    public void setResrcePttrn(String resrcePttrn) {
        this.resrcePttrn = resrcePttrn;
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
