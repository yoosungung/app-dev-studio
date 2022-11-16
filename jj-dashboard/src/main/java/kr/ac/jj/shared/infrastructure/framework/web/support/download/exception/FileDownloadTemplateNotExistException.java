package kr.ac.jj.shared.infrastructure.framework.web.support.download.exception;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;

public class FileDownloadTemplateNotExistException extends BaseException {

    private static final long serialVersionUID = -6707623780491874109L;

    private final String templatePath;

    public FileDownloadTemplateNotExistException(String templatePath) {
        super("Template file [" + templatePath + "] is not exists.");

        this.templatePath = templatePath;
    }

    public String getTemplatePath() {
        return this.templatePath;
    }

}
