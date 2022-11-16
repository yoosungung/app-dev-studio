package kr.ac.jj.survey.domain.main.model.sys.resrce.author;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.ac.jj.survey.domain.main.model.sys.author.TbSysAuthor;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanDeserializer;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanSerializer;

/**
 * 시스템 - 리소스별 권한 TO 시스템 - 권한
 */
public class TbSysResrceAuthorToAuthor extends TbSysAuthor {
    private static final long serialVersionUID = 5062246819228370041L;

    private String resrceId;
    private Boolean allowYn;

    public String getResrceId() {
        return this.resrceId;
    }

    public void setResrceId(String resrceId) {
        this.resrceId = resrceId;
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getAllowYn() {
        return this.allowYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    public void setAllowYn(Boolean allowYn) {
        this.allowYn = allowYn;
    }
}
