package kr.ac.jj.shared.infrastructure.framework.web.multipart.nameformatter;

import java.io.File;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.file.FileUtil;

public class DefaultFilenameFormatter implements UploadedFilenameFormatter {

    @Override
    public synchronized File getFormattedFile(File uploadedDirectory, String originalFilename) {
        File file = UploadedFilenameUtil.newFile(uploadedDirectory, this.getFormattedName(originalFilename));

        while (file.exists()) {
            try {
                wait(1);
            } catch (InterruptedException e) {
                throw new BaseException(e);
            }

            file = UploadedFilenameUtil.newFile(uploadedDirectory, this.getFormattedName(originalFilename));
        }

        return file;
    }

    private String getFormattedName(String originalFilename) {
        String timestampCycleNo = UploadedFilenameUtil.getTimestampSequenceNo();
        String extension = FileUtil.getExtension(originalFilename);

        if ("".equals(extension)) {
            return timestampCycleNo;
        }

        return timestampCycleNo + "." + extension.toUpperCase();
    }

}
