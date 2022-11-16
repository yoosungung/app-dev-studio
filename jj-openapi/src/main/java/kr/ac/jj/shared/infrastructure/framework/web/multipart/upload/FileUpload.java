package kr.ac.jj.shared.infrastructure.framework.web.multipart.upload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.exception.FileUploadExcludeExtensionsException;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.exception.FileUploadIncludeExtensionsException;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.exception.FileUploadMaxFileSizeException;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.exception.FileUploadTotalFileSizeException;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.model.UploadedFile;
import kr.ac.jj.shared.infrastructure.framework.web.multipart.policy.FilePolicy;

public class FileUpload {

    private final List<UploadedFile> uploadedFileList;

    public FileUpload(MultipartHttpServletRequest request) {
        this(null, request);
    }

    public FileUpload(FilePolicy filePolicy, MultipartHttpServletRequest request) {
        this.checkFileItemList(filePolicy, request);

        this.uploadedFileList = new ArrayList<UploadedFile>();

        Iterator<String> fileNames = request.getFileNames();

        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            List<MultipartFile> multipartFileList = request.getFiles(fileName);

            for (int i = 0, ii = multipartFileList.size(); i < ii; i++) {
                MultipartFile multipartFile = multipartFileList.get(i);

                if (multipartFile == null || multipartFile.isEmpty()) {
                    continue;
                }

                if (filePolicy == null) {
                    this.uploadedFileList.add(new UploadedFile(multipartFile));
                } else {
                    UploadedFile uploadedFile = new UploadedFile(filePolicy, multipartFile);

                    try {
                        multipartFile.transferTo(uploadedFile.getUploadedFile());
                        this.uploadedFileList.add(uploadedFile);
                    } catch (IllegalStateException e) {
                        throw new BaseException(e);
                    } catch (IOException e) {
                        throw new BaseException(e);
                    }
                }
            }
        }
    }

    private void checkFileItemList(FilePolicy filePolicy, MultipartHttpServletRequest request) {
        if (filePolicy == null) {
            return;
        }

        Iterator<String> fileNames = request.getFileNames();
        String[] includeExtensions = filePolicy.getIncludeExtensions();
        String[] excludeExtensions = filePolicy.getExcludeExtensions();
        long maxFileSize = filePolicy.getMaxUploadSizePerFile();
        long maxUploadSize = filePolicy.getMaxUploadSize();
        long totUploadSize = 0;

        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            List<MultipartFile> multipartFileList = request.getFiles(fileName);

            if (includeExtensions != null && includeExtensions.length > 0) {
                for (MultipartFile multipartFile : multipartFileList) {
                    if (this.checkExtension(multipartFile.getOriginalFilename(), includeExtensions) == 0) {
                        throw new FileUploadIncludeExtensionsException(multipartFile.getOriginalFilename(),
                                includeExtensions);
                    }
                }
            }

            if (excludeExtensions != null && excludeExtensions.length > 0) {
                for (MultipartFile multipartFile : multipartFileList) {
                    if (this.checkExtension(multipartFile.getOriginalFilename(), excludeExtensions) == 1) {
                        throw new FileUploadExcludeExtensionsException(multipartFile.getOriginalFilename(),
                                excludeExtensions);
                    }
                }
            }

            if (maxFileSize > -1) {
                for (MultipartFile multipartFile : multipartFileList) {
                    if (multipartFile.getSize() > maxFileSize) {
                        throw new FileUploadMaxFileSizeException(multipartFile.getOriginalFilename(),
                                multipartFile.getSize(), maxFileSize);
                    }
                }
            }

            if (maxUploadSize > -1) {
                for (MultipartFile multipartFile : multipartFileList) {
                    totUploadSize += multipartFile.getSize();

                    if (totUploadSize > maxUploadSize) {
                        throw new FileUploadTotalFileSizeException(totUploadSize, maxUploadSize);
                    }
                }
            }
        }
    }

    private short checkExtension(String fileName, String[] checkStrings) {
        if (StringUtils.isEmpty(fileName)) {
            return -1;
        }

        String fileExtension = FilenameUtils.getExtension(fileName);

        for (String string : checkStrings) {
            if (string.trim().equalsIgnoreCase(fileExtension)) {
                return 1;
            }
        }

        return 0;
    }

    public List<UploadedFile> getUploadedFileList() {
        return this.uploadedFileList;
    }

}
