package kr.ac.jj.shared.infrastructure.framework.web.support.download.exception;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;

public class FileDownloadUndefinedPathException extends BaseException {

    private static final long serialVersionUID = -6943056059476497045L;

    public FileDownloadUndefinedPathException() {
        super("File download path is undefined.");
    }

}
