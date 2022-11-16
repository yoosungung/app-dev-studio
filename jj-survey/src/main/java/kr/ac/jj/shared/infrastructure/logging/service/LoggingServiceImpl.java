package kr.ac.jj.shared.infrastructure.logging.service;

import java.util.List;

import org.dizitart.no2.Document;
import org.dizitart.no2.Filter;
import org.dizitart.no2.filters.Filters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.config.props.SharedConfigProperties;
import kr.ac.jj.shared.config.props.SharedConfigProperties.Log;
import kr.ac.jj.shared.domain.main.mapper.sys.log.TbSysLogMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.log.error.TbSysLogErrorMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.log.login.TbSysLogLoginMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.log.menu.TbSysLogMenuMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.log.query.TbSysLogQueryMapper;
import kr.ac.jj.shared.domain.main.model.sys.log.TbSysLog;
import kr.ac.jj.shared.domain.main.model.sys.log.error.TbSysLogError;
import kr.ac.jj.shared.domain.main.model.sys.log.login.TbSysLogLogin;
import kr.ac.jj.shared.domain.main.model.sys.log.menu.TbSysLogMenu;
import kr.ac.jj.shared.domain.main.model.sys.log.query.TbSysLogQuery;
import kr.ac.jj.shared.infrastructure.logging.collection.LoggingCollections;

/**
 * 로깅 Service
 */
@Service
public class LoggingServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(LoggingServiceImpl.class);

    private @Autowired SharedConfigProperties sharedConfig;
    private @Autowired TbSysLogMapper tbSysLogMapper;
    private @Autowired TbSysLogQueryMapper tbSysLogQueryMapper;
    private @Autowired TbSysLogErrorMapper tbSysLogErrorMapper;
    private @Autowired TbSysLogLoginMapper tbSysLogLoginMapper;
    private @Autowired TbSysLogMenuMapper tbSysLogMenuMapper;

    /**
     * 생성
     *
     * @param loggingCollections
     * @return
     */
    public int create(LoggingCollections loggingCollections) {
        List<Document> documentList = loggingCollections.tbSysLog.find(Filters.eq("completed", true)).toList();

        for (Document document : documentList) {
            try {
                this.createDocument(loggingCollections, document);
            } catch (RuntimeException e) {
                log.error("Create Logging Error", e);
            } finally {
                this.removeDocument(loggingCollections, document);
            }
        }

        return documentList.size();
    }

    private void createDocument(LoggingCollections loggingCollections, Document document) {
        Log logConfig = sharedConfig.getLog();

        TbSysLog tbSysLog = (TbSysLog) document.get("data");

        Filter logIdFilter = Filters.eq("logId", tbSysLog.getLogId());

        List<Document> tbSysLogQueryList = loggingCollections.tbSysLogQuery.find(logIdFilter).toList();
        List<Document> tbSysLogErrorList = loggingCollections.tbSysLogError.find(logIdFilter).toList();
        List<Document> tbSysLogLoginList = loggingCollections.tbSysLogLogin.find(logIdFilter).toList();
        List<Document> tbSysLogMenuList = loggingCollections.tbSysLogMenu.find(logIdFilter).toList();

        boolean isError = (tbSysLogErrorList.size() > 0);
        boolean tbSysLogInserted = false;

        tbSysLog.setSuccesYn(!isError);

        if (logConfig.isEnable() && logConfig.getAction().isEnable()) {
            tbSysLogMapper.insert(tbSysLog);
            tbSysLogInserted = true;
        }

        for (Document subDocument : tbSysLogQueryList) {
            TbSysLogQuery tbSysLogQuery = (TbSysLogQuery) subDocument.get("data");

            if (logConfig.isEnable()
                    && (logConfig.getQuery().isEnable() || (isError && logConfig.getError().isEnable()))) {
                if (!tbSysLogInserted) {
                    tbSysLogMapper.insert(tbSysLog);
                    tbSysLogInserted = true;
                }

                tbSysLogQueryMapper.insert(tbSysLogQuery);
            }
        }

        for (Document subDocument : tbSysLogErrorList) {
            TbSysLogError tbSysLogError = (TbSysLogError) subDocument.get("data");

            if (logConfig.isEnable() && logConfig.getError().isEnable()) {
                if (!tbSysLogInserted) {
                    tbSysLogMapper.insert(tbSysLog);
                    tbSysLogInserted = true;
                }

                tbSysLogErrorMapper.insert(tbSysLogError);
            }
        }

        for (Document subDocument : tbSysLogLoginList) {
            TbSysLogLogin tbSysLogLogin = (TbSysLogLogin) subDocument.get("data");

            if (logConfig.isEnable() && logConfig.getLogin().isEnable()) {
                if (!tbSysLogInserted) {
                    tbSysLogMapper.insert(tbSysLog);
                    tbSysLogInserted = true;
                }

                tbSysLogLoginMapper.insert(tbSysLogLogin);
            }
        }

        for (Document subDocument : tbSysLogMenuList) {
            TbSysLogMenu tbSysLogMenu = (TbSysLogMenu) subDocument.get("data");

            if (logConfig.isEnable() && logConfig.getMenu().isEnable()) {
                if (!tbSysLogInserted) {
                    tbSysLogMapper.insert(tbSysLog);
                    tbSysLogInserted = true;
                }

                tbSysLogMenuMapper.insert(tbSysLogMenu);
            }
        }
    }

    private void removeDocument(LoggingCollections loggingCollections, Document document) {
        TbSysLog tbSysLog = (TbSysLog) document.get("data");

        Filter logIdFilter = Filters.eq("logId", tbSysLog.getLogId());

        List<Document> tbSysLogQueryList = loggingCollections.tbSysLogQuery.find(logIdFilter).toList();
        List<Document> tbSysLogErrorList = loggingCollections.tbSysLogError.find(logIdFilter).toList();
        List<Document> tbSysLogLoginList = loggingCollections.tbSysLogLogin.find(logIdFilter).toList();
        List<Document> tbSysLogMenuList = loggingCollections.tbSysLogMenu.find(logIdFilter).toList();

        for (Document subDocument : tbSysLogQueryList) {
            loggingCollections.tbSysLogQuery.remove(subDocument);
        }

        for (Document subDocument : tbSysLogErrorList) {
            loggingCollections.tbSysLogError.remove(subDocument);
        }

        for (Document subDocument : tbSysLogLoginList) {
            loggingCollections.tbSysLogLogin.remove(subDocument);
        }

        for (Document subDocument : tbSysLogMenuList) {
            loggingCollections.tbSysLogMenu.remove(subDocument);
        }

        loggingCollections.tbSysLog.remove(document);
    }

}
