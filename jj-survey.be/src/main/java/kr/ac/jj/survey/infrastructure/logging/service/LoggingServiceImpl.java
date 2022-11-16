package kr.ac.jj.survey.infrastructure.logging.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.jj.survey.config.props.ConfigProperties;
import kr.ac.jj.survey.domain.main.mapper.sys.log.TbSysLogMapper;
import kr.ac.jj.survey.domain.main.mapper.sys.log.error.TbSysLogErrorMapper;
import kr.ac.jj.survey.domain.main.mapper.sys.log.login.TbSysLogLoginMapper;
import kr.ac.jj.survey.domain.main.mapper.sys.log.menu.TbSysLogMenuMapper;
import kr.ac.jj.survey.domain.main.mapper.sys.log.query.TbSysLogQueryMapper;
import kr.ac.jj.survey.domain.main.model.sys.log.TbSysLog;
import kr.ac.jj.survey.domain.main.model.sys.log.error.TbSysLogError;
import kr.ac.jj.survey.domain.main.model.sys.log.login.TbSysLogLogin;
import kr.ac.jj.survey.domain.main.model.sys.log.menu.TbSysLogMenu;
import kr.ac.jj.survey.domain.main.model.sys.log.query.TbSysLogQuery;
import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.survey.infrastructure.framework.core.persistence.dao.util.DataUpdatedCheckUtil;
import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.survey.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.survey.infrastructure.logging.util.LoggingUtil;
import kr.ac.jj.survey.infrastructure.user.BaseLoginUser;
import kr.ac.jj.survey.infrastructure.util.BaseUtil;

/**
 * 로깅 Service
 */
@Service
public class LoggingServiceImpl {
    private @Autowired ConfigProperties config;
    private @Autowired TbSysLogMapper tbSysLogMapper;
    private @Autowired TbSysLogMenuMapper tbSysLogMenuMapper;
    private @Autowired TbSysLogQueryMapper tbSysLogQueryMapper;
    private @Autowired TbSysLogLoginMapper tbSysLogLoginMapper;
    private @Autowired TbSysLogErrorMapper tbSysLogErrorMapper;

    /**
     * 생성
     *
     * @param requstBody
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TbSysLog create(String requstBody) {
        TbSysLog tbSysLog = new TbSysLog();

        BaseLoginUser loginUser = SessionContextUtil.getLoginUser(BaseLoginUser.class);

        tbSysLog.setUserId(loginUser == null ? "(UNKNOWN)" : loginUser.getUserId());
        tbSysLog.setLogDt(new Date());

        try {
            tbSysLog.setServerIp(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            throw new BaseException(e);
        }

        HttpServletRequest request = RequestContextUtil.getRequest();

        if (request != null) {
            String servletPath = request.getServletPath();
            String queryString = request.getQueryString();

            tbSysLog.setRequstIp(RequestContextUtil.getClientIpAddress());
            tbSysLog.setRequstTy(RequestContextUtil.getRequestType().name());
            tbSysLog.setRequstHost(RequestContextUtil.getServerURL());
            tbSysLog.setRequstPath(request.getContextPath() + servletPath
                    + (StringUtils.isEmpty(queryString) ? "" : "?" + queryString));
            tbSysLog.setRequstHder(BaseUtil.getHeaderValues(request));

            if ("/common/user/login.do".equals(servletPath)) {
                tbSysLog.setRequstParamtr("(Privacy Protected)");
            } else {
                tbSysLog.setRequstParamtr(BaseUtil.getParameterValues(request));
            }
        }

        tbSysLog.setRequstBody(requstBody);

        if (config.getLog().isEnable()) {
            try {
                DataUpdatedCheckUtil.setDisabled(true);
                LoggingUtil.setLoggingType("sub");
                tbSysLogMapper.insert(tbSysLog.newId());
            } catch (RuntimeException e) {
                throw e;
            } finally {
                DataUpdatedCheckUtil.setDisabled(false);
                LoggingUtil.removeLoggingType();
            }
        }

        LoggingUtil.setTbSysLog(tbSysLog);

        return tbSysLog;
    }

    /**
     * 수정 - 응답 상태 코드, 성공 여부
     *
     * @param rspnsSttusCode
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateRspnsSttusCode(int rspnsSttusCode) {
        TbSysLog tbSysLog = LoggingUtil.getTbSysLog();

        if (tbSysLog == null) {
            return;
        }

        tbSysLog.setRspnsSttusCode(rspnsSttusCode);

        try {
            DataUpdatedCheckUtil.setDisabled(true);
            LoggingUtil.setLoggingType("sub");
            tbSysLogMapper.updateRspnsSttusCode(tbSysLog);
        } catch (RuntimeException e) {
            throw e;
        } finally {
            DataUpdatedCheckUtil.setDisabled(false);
            LoggingUtil.removeLoggingType();
        }
    }

    private TbSysLog createLog() {
        TbSysLog tbSysLog = LoggingUtil.getTbSysLog();

        if (tbSysLog == null) {
            return null;
        }

        if (StringUtils.isNotEmpty(tbSysLog.getLogId())) {
            return tbSysLog;
        }

        try {
            DataUpdatedCheckUtil.setDisabled(true);
            LoggingUtil.setLoggingType("sub");
            tbSysLogMapper.insert(tbSysLog.newId());
            LoggingUtil.setTbSysLog(tbSysLog);
        } catch (RuntimeException e) {
            throw e;
        } finally {
            DataUpdatedCheckUtil.setDisabled(false);
            LoggingUtil.removeLoggingType();
        }

        return tbSysLog;
    }

    /**
     * 메뉴 생성
     *
     * @param tbSysLogMenu
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createMenu(TbSysLogMenu tbSysLogMenu) {
        if (tbSysLogMenu == null) {
            return;
        }

        TbSysLog tbSysLog = this.createLog();

        if (tbSysLog != null) {
            tbSysLogMenu.setLogId(tbSysLog.getLogId());
        }

        try {
            DataUpdatedCheckUtil.setDisabled(true);
            LoggingUtil.setLoggingType("sub");
            tbSysLogMenuMapper.insert(tbSysLogMenu);
        } catch (RuntimeException e) {
            throw e;
        } finally {
            DataUpdatedCheckUtil.setDisabled(false);
            LoggingUtil.removeLoggingType();
        }
    }

    /**
     * 쿼리 생성
     *
     * @param tbSysLogQuery
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createQuery(TbSysLogQuery tbSysLogQuery) {
        if (tbSysLogQuery == null) {
            return;
        }

        TbSysLog tbSysLog = this.createLog();

        if (tbSysLog != null) {
            tbSysLogQuery.setLogId(tbSysLog.getLogId());
        }

        tbSysLogQuery.setQueryDt(new Date());

        try {
            DataUpdatedCheckUtil.setDisabled(true);
            LoggingUtil.setLoggingType("sub");
            tbSysLogQueryMapper.insert(tbSysLogQuery.newId());
        } catch (RuntimeException e) {
            throw e;
        } finally {
            DataUpdatedCheckUtil.setDisabled(false);
            LoggingUtil.removeLoggingType();
        }
    }

    /**
     * 로그인 생성
     *
     * @param tbSysLogLogin
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createLogin(TbSysLogLogin tbSysLogLogin) {
        if (tbSysLogLogin == null) {
            return;
        }

        TbSysLog tbSysLog = this.createLog();

        if (tbSysLog != null) {
            tbSysLogLogin.setLogId(tbSysLog.getLogId());
        }

        try {
            DataUpdatedCheckUtil.setDisabled(true);
            LoggingUtil.setLoggingType("sub");
            tbSysLogLoginMapper.insert(tbSysLogLogin.newId());
        } catch (RuntimeException e) {
            throw e;
        } finally {
            DataUpdatedCheckUtil.setDisabled(false);
            LoggingUtil.removeLoggingType();
        }
    }

    /**
     * 에러 생성
     *
     * @param tbSysLogError
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createError(TbSysLogError tbSysLogError) {
        if (tbSysLogError == null) {
            return;
        }

        TbSysLog tbSysLog = this.createLog();

        if (tbSysLog != null) {
            this.updateRspnsSttusCode((Integer) tbSysLogError.get_META("rspnsSttusCode"));
            tbSysLogError.setLogId(tbSysLog.getLogId());
        }

        tbSysLogError.setErrorDt(new Date());

        try {
            DataUpdatedCheckUtil.setDisabled(true);
            LoggingUtil.setLoggingType("sub");
            tbSysLogErrorMapper.insert(tbSysLogError.newId().newErrorCode());
        } catch (RuntimeException e) {
            throw e;
        } finally {
            DataUpdatedCheckUtil.setDisabled(false);
            LoggingUtil.removeLoggingType();
        }
    }
}
