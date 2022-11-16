package kr.ac.jj.shared.domain.main.model.sys.log.login;

import org.dizitart.no2.Document;

import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.uid.UidUtil;
import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;
import kr.ac.jj.shared.infrastructure.logging.collection.LoggingCollections;
import kr.ac.jj.shared.infrastructure.logging.util.LoggingUtil;

/**
 * 시스템 - 로그인 로그
 */
public class TbSysLogLogin extends TbSysLogLoginEntity {

    private static final long serialVersionUID = 7223780037969399536L;

    public TbSysLogLogin() {
        this.setLoginLogId(UidUtil.generateUid());
        this.setLogId(LoggingUtil.getCurrentLogId());
    }

    public TbSysLogLogin newId() {
        this.setLoginLogId(IdGenerationUtil.getUid());

        return this;
    }

    public String getSuccesYnNm() {
        return CodeDataUtil.getCodeName("/common/yesNo", this.getSuccesYn());
    }

    public void insertQueue() {
        Document document = LoggingUtil.newCurrentDocument(this);

        if (document != null) {
            LoggingCollections loggingCollections = ApplicationContextUtil.getBean(LoggingCollections.class);
            loggingCollections.tbSysLogLogin.insert(document);
        }
    }

}
