package kr.ac.jj.shared.domain.main.model.sys.log.query;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.dizitart.no2.Document;

import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.uid.UidUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;
import kr.ac.jj.shared.infrastructure.logging.collection.LoggingCollections;
import kr.ac.jj.shared.infrastructure.logging.util.LoggingUtil;

/**
 * 시스템 - 쿼리 로그
 */
public class TbSysLogQuery extends TbSysLogQueryEntity {

    private static final long serialVersionUID = 4011836691313334093L;

    public TbSysLogQuery() {
        this.setQueryLogId(UidUtil.generateUid());
        this.setLogId(LoggingUtil.getCurrentLogId());
        this.setQueryDt(new Date());
    }

    public TbSysLogQuery newId() {
        this.setQueryLogId(IdGenerationUtil.getUid());

        return this;
    }

    public void setExecutTime(String executTime) {
        if (StringUtils.isNotEmpty(executTime)) {
            this.setExecutTime(Long.parseLong(executTime, 10));
        }
    }

    public void insertQueue() {
        Document document = LoggingUtil.newCurrentDocument(this);

        if (document != null) {
            LoggingCollections loggingCollections = ApplicationContextUtil.getBean(LoggingCollections.class);
            loggingCollections.tbSysLogQuery.insert(document);
        }
    }

}
