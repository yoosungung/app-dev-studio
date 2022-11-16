package kr.ac.jj.shared.infrastructure.framework.web.multipart.exception;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.lang.StringUtil;

public class FileUploadExcludeExtensionsException extends BaseException {

    private static final long serialVersionUID = 8217494329180350038L;

    public FileUploadExcludeExtensionsException(String fileName, String[] excludeExtensions) {
        super("Upload file name \"" + fileName + "\" is forbidden extensions ["
                + StringUtil.join(excludeExtensions, ", ") + "]");
    }

}
