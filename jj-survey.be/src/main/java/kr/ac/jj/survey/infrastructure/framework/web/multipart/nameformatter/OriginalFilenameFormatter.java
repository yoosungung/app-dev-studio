package kr.ac.jj.survey.infrastructure.framework.web.multipart.nameformatter;

import java.io.File;

import kr.ac.jj.survey.infrastructure.framework.core.support.io.file.FileUtil;

public class OriginalFilenameFormatter implements UploadedFilenameFormatter {
    @Override
    public synchronized File getFormattedFile(File uploadedDirectory, String originalFilename) {
        File file = UploadedFilenameUtil.newFile(uploadedDirectory, originalFilename);

        String baseName = FileUtil.getBaseName(originalFilename);
        String extension = FileUtil.getExtension(originalFilename);

        int count = 1;

        while (file.exists()) {
            file = UploadedFilenameUtil.newFile(uploadedDirectory,
                    baseName + "(" + Integer.toString(count++, 10) + ")" + extension);
        }

        return file;
    }
}
