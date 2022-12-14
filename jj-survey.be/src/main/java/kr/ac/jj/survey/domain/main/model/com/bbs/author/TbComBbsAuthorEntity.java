package kr.ac.jj.survey.domain.main.model.com.bbs.author;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.ac.jj.survey.domain.main.model.MainEntity;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanDeserializer;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanSerializer;

/**
 * 공통 - 게시판별 권한 Entity
 */
abstract class TbComBbsAuthorEntity extends MainEntity {
    private static final long serialVersionUID = 5303852714973800672L;

    protected String bbsId;
    protected String authorId;
    protected Boolean inqirePosblYn;
    protected Boolean writngPosblYn;

    public String getBbsId() {
        return this.bbsId;
    }

    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
    }

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getInqirePosblYn() {
        return this.inqirePosblYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    public void setInqirePosblYn(Boolean inqirePosblYn) {
        this.inqirePosblYn = inqirePosblYn;
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getWritngPosblYn() {
        return this.writngPosblYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    public void setWritngPosblYn(Boolean writngPosblYn) {
        this.writngPosblYn = writngPosblYn;
    }
}
