package kr.ac.jj.survey.infrastructure.error.service;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.survey.config.props.ConfigProperties;
import kr.ac.jj.survey.domain.main.model.sys.log.error.TbSysLogError;
import kr.ac.jj.survey.infrastructure.error.model.ErrorHandleModel;
import kr.ac.jj.survey.infrastructure.logging.service.LoggingServiceImpl;

/**
 * 에러 처리 Service
 */
@Service
public class ErrorHandleServiceImpl {
    private @Autowired ConfigProperties config;
    private @Autowired LoggingServiceImpl loggingService;

    /**
     * 로그 생성
     *
     * @param errorHandleModel
     */
    public void createLog(ErrorHandleModel errorHandleModel) {
        if (!config.getLog().getError().isEnable()) {
            return;
        }

        TbSysLogError tbSysLogError = new TbSysLogError();
        tbSysLogError.setErrorMssage(errorHandleModel.getMessage());

        tbSysLogError.set_META("rspnsSttusCode", errorHandleModel.getStatus());

        Throwable throwable = errorHandleModel.getThrowable();

        if (throwable != null) {
            tbSysLogError.setErrorClass(ExceptionUtils.getRootCause(throwable).getClass().getName());
            tbSysLogError.setErrorStack(ExceptionUtils.getStackTrace(throwable));
        }

        loggingService.createError(tbSysLogError);
    }
}
