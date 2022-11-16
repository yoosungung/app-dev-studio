package kr.ac.jj.shared.infrastructure.framework.web.support.download.exception;

import java.io.File;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.file.FileUtil;

public class FileDownloadNotExistsException extends BaseException {

    private static final long serialVersionUID = -2515445478917741011L;

    private final File file;

    public FileDownloadNotExistsException(File file) {
        super("The file [" + FileUtil.getCanonicalPath(file) + "] no longer exists.");

        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

}
