package kr.ac.jj.shared.infrastructure.framework.web.multipart.exception;

import java.text.NumberFormat;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;

public class FileUploadMaxFileSizeException extends BaseException {

    private static final long serialVersionUID = -8598421039872464891L;

    public FileUploadMaxFileSizeException(String fileName, long fileSize, long maxFileSize) {
        super("Upload file \"" + fileName + "\"'s size [" + NumberFormat.getNumberInstance().format(fileSize)
                + " byte] exceeded setting [" + NumberFormat.getNumberInstance().format(maxFileSize) + " byte].");
    }

}
