package kr.ac.jj.shared.infrastructure.framework.web.multipart.repository;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.config.props.SharedConfigProperties;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.file.FileUtil;

public class FileRepositoryUtil {

    public static File getRepositoryDirectory(String repositoryName) {
        if (StringUtils.isEmpty(repositoryName)) {
            return null;
        }

        SharedConfigProperties sharedConfig = ApplicationContextUtil.getConfigBean(SharedConfigProperties.class);

        Map<String, String> repositories = sharedConfig.getFile().getRepository().getRepositories();
        String repositoryLocation = repositories.get(repositoryName);

        if (StringUtils.isEmpty(repositoryLocation)) {
            throw new BaseException("File repository name [" + repositoryName + "] is not available.");
        }

        File dir;

        try {
            dir = FileUtil.getResourceFile(repositoryLocation).getCanonicalFile();
        } catch (IOException e) {
            throw new BaseException(e);
        }

        if (!FileUtil.isDirectory(dir) && sharedConfig.getFile().getRepository().isCreateIfNotExists()) {
            dir.mkdirs();
        }

        if (!FileUtil.isDirectory(dir)) {
            throw new BaseException(
                    "File repository directory [" + FileUtil.getCanonicalPath(dir) + "] is not available.");
        }

        return dir;
    }

}
