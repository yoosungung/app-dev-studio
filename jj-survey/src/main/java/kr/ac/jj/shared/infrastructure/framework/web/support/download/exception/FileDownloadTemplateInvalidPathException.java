package kr.ac.jj.shared.infrastructure.framework.web.support.download.exception;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;

public class FileDownloadTemplateInvalidPathException extends BaseException {

    private static final long serialVersionUID = 4780222073449526682L;

    private final String templatePath;

    public FileDownloadTemplateInvalidPathException(String templatePath) {
        super("Template file path [" + templatePath + "] is not available.");

        this.templatePath = templatePath;
    }

    public String getTemplatePath() {
        return this.templatePath;
    }

}
