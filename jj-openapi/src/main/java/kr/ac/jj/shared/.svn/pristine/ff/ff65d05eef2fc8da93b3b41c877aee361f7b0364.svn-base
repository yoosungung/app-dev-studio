package kr.ac.jj.shared.infrastructure.framework.web.multipart.exception;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.lang.StringUtil;

public class FileUploadIncludeExtensionsException extends BaseException {

    private static final long serialVersionUID = -7248689929045529432L;

    public FileUploadIncludeExtensionsException(String fileName, String[] includeExtensions) {
        super("Upload file name \"" + fileName + "\" is not allowed extensions ["
                + StringUtil.join(includeExtensions, ", ") + "]");
    }

}
