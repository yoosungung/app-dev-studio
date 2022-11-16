package kr.ac.jj.survey.domain.main.model.jd.survey.definition;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;

/**
 *
 */
public class JdSurveyDefinition extends JdSurveyDefinitionEntity {

    private static final long serialVersionUID = -380950327936519416L;

    public String getIsPublicName() {
        return BooleanUtils.isTrue(this.isPublic) ? "공개" : "비공개";
    }

    public String getStatusName() {
        return CodeDataUtil.getCodeName("[SURVEY_DEFINITION_STATUS]", this.status);
    }

    @Override
    public void setEndDt(Date endDt) {
        if (endDt == null) {
            this.endDt = endDt;
            return;
        }

        String endDtStr = DateFormatUtils.format(endDt, "yyyy-MM-dd 23:59:59");

        try {
            this.endDt = DateUtils.parseDate(endDtStr, "yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isExpired() {
        return this.endDt != null && new Date().compareTo(this.endDt) > 0;
    }

}
