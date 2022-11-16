package kr.ac.jj.shared.infrastructure.framework.web.support.download.exception;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;

public class FileDownloadUndefinedNameException extends BaseException {

    private static final long serialVersionUID = 568907968150427526L;

    public FileDownloadUndefinedNameException() {
        super("File download name is undefined.");
    }

}
