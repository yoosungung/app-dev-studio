package kr.ac.jj.shared.infrastructure.error.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.jj.shared.infrastructure.error.model.ErrorHandleModel;
import kr.ac.jj.shared.infrastructure.error.service.ErrorHandleServiceImpl;

/**
 * 에러 처리 Controller
 */
@Controller
public class ErrorHandleController extends BasicErrorController {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandleController.class);

    private @Autowired ErrorHandleServiceImpl errorHandleService;

    private final ErrorAttributes errorAttributes;

    public ErrorHandleController(ErrorAttributes errorAttributes, ServerProperties serverProperties,
            List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);

        this.errorAttributes = errorAttributes;
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        this.createLog(request);

        return super.errorHtml(request, response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        this.createLog(request);

        return super.error(request);
    }

    private void createLog(HttpServletRequest request) {
        try {

            WebRequest webRequest = new ServletWebRequest(request);
            Map<String, Object> errorAttributes = super.getErrorAttributes(request, false);
            Throwable throwable = this.errorAttributes.getError(webRequest);

            if (throwable != null && this.isLogWriteThrowable(throwable)) {
                log.error(throwable.getMessage(), throwable);
            }

            ErrorHandleModel errorHandleModel = new ErrorHandleModel(errorAttributes, throwable);

            if (!StringUtils.endsWith(errorHandleModel.getPath(), ".map")) {
                errorHandleService.createLog(errorHandleModel);
            }

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        }
    }

    private boolean isLogWriteThrowable(Throwable throwable) {
        if (throwable instanceof MissingServletRequestParameterException) {
            return true;
        }

        if (throwable instanceof HttpMessageNotWritableException) {
            return true;
        }

        return false;
    }

}
