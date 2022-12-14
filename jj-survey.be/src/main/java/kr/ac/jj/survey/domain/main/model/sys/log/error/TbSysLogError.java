package kr.ac.jj.survey.domain.main.model.sys.log.error;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.ac.jj.survey.config.props.ConfigProperties;
import kr.ac.jj.survey.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.survey.infrastructure.framework.core.support.sequence.BaseSequence;
import kr.ac.jj.survey.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 에러 로그
 */
public class TbSysLogError extends TbSysLogErrorEntity {
    private static final long serialVersionUID = -8879390947732344792L;

    private static final BaseSequence errorCodeSeq = new BaseSequence(0, 9999, true);

    public TbSysLogError newId() {
        this.setErrorLogId(IdGenerationUtil.createUid("TB_SYS_LOG_ERROR"));

        return this;
    }

    public TbSysLogError newErrorCode() {
        Date errorDt = (this.getErrorDt() == null ? new Date() : this.getErrorDt());

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HHmmss");

        ConfigProperties config = ApplicationContextUtil.getConfigBean(ConfigProperties.class);

        this.setErrorCode(dateFormat1.format(errorDt) + "T" + dateFormat2.format(errorDt)
                + config.getLog().getError().getServerCode() + errorCodeSeq.getNextValue(4, '0'));

        return this;
    }
}
