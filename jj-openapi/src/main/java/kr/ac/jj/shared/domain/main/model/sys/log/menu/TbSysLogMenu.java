package kr.ac.jj.shared.domain.main.model.sys.log.menu;

import org.dizitart.no2.Document;

import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.logging.collection.LoggingCollections;
import kr.ac.jj.shared.infrastructure.logging.util.LoggingUtil;

/**
 * 시스템 - 메뉴 로그
 */
public class TbSysLogMenu extends TbSysLogMenuEntity {

    private static final long serialVersionUID = 8015352316423178838L;

    public TbSysLogMenu() {
        this.setLogId(LoggingUtil.getCurrentLogId());
    }

    public void insertQueue() {
        Document document = LoggingUtil.newCurrentDocument(this);

        if (document != null) {
            LoggingCollections loggingCollections = ApplicationContextUtil.getBean(LoggingCollections.class);
            loggingCollections.tbSysLogMenu.insert(document);
        }
    }

}
