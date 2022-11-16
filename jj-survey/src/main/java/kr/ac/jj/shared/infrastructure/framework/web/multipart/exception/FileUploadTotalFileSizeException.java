package kr.ac.jj.shared.infrastructure.framework.web.multipart.exception;

import java.text.NumberFormat;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;

public class FileUploadTotalFileSizeException extends BaseException {

    private static final long serialVersionUID = -1814716066285285508L;

    public FileUploadTotalFileSizeException(long totUploadSize, long maxUploadSize) {
        super("Total upload file size [" + NumberFormat.getNumberInstance().format(totUploadSize)
                + " byte] exceeded setting [" + NumberFormat.getNumberInstance().format(maxUploadSize) + " byte].");
    }

}
