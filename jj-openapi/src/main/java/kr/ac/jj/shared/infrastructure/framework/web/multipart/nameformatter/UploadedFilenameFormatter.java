package kr.ac.jj.shared.infrastructure.framework.web.multipart.nameformatter;

import java.io.File;

public interface UploadedFilenameFormatter {

    public File getFormattedFile(File uploadedDirectory, String originalFilename);

}
