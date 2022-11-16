package kr.ac.jj.survey.domain.main.model.com.bbs.author;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.ac.jj.survey.domain.main.model.sys.author.TbSysAuthor;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanDeserializer;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanSerializer;

/**
 * 공통 - 게시판별 권한 TO 시스템 - 권한
 */
public class TbComBbsAuthorToAuthor extends TbSysAuthor {
    private static final long serialVersionUID = -4551371703775454983L;

    private String bbsId;
    private Boolean inqirePosblYn;
    private Boolean writngPosblYn;

    public String getBbsId() {
        return this.bbsId;
    }

    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
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
