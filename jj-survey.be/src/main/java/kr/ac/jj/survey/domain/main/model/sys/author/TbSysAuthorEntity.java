package kr.ac.jj.survey.domain.main.model.sys.author;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.ac.jj.survey.domain.main.model.MainEntity;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanDeserializer;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanSerializer;

/**
 * 시스템 - 권한 Entity
 */
abstract class TbSysAuthorEntity extends MainEntity {
    private static final long serialVersionUID = 8594900524261042021L;

    protected String authorId;
    protected String authorCode;
    protected String authorNmTitle;
    protected String authorDc;
    protected Integer sortOrdr;
    protected Boolean useYn;
    protected Boolean bassAuthorYn;
    protected Boolean userAuthorYn;

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorCode() {
        return this.authorCode;
    }

    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }

    public String getAuthorNmTitle() {
        return this.authorNmTitle;
    }

    public void setAuthorNmTitle(String authorNmTitle) {
        this.authorNmTitle = authorNmTitle;
    }

    public String getAuthorDc() {
        return this.authorDc;
    }

    public void setAuthorDc(String authorDc) {
        this.authorDc = authorDc;
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

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getBassAuthorYn() {
        return this.bassAuthorYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    public void setBassAuthorYn(Boolean bassAuthorYn) {
        this.bassAuthorYn = bassAuthorYn;
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getUserAuthorYn() {
        return this.userAuthorYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    public void setUserAuthorYn(Boolean userAuthorYn) {
        this.userAuthorYn = userAuthorYn;
    }
}
