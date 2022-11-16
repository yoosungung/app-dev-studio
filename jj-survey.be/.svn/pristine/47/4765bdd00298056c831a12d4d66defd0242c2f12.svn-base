package kr.ac.jj.survey.infrastructure.framework.web.multipart.nameformatter;

import java.io.File;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;

public class SimpleFilenameFormatter implements UploadedFilenameFormatter {
    @Override
    public synchronized File getFormattedFile(File uploadedDirectory, String originalFilename) {
        File file = UploadedFilenameUtil.newFile(uploadedDirectory, UploadedFilenameUtil.getTimestampSequenceNo());

        while (file.exists()) {
            try {
                wait(1);
            } catch (InterruptedException e) {
                throw new BaseException(e);
            }

            file = UploadedFilenameUtil.newFile(uploadedDirectory, UploadedFilenameUtil.getTimestampSequenceNo());
        }

        return file;
    }
}
