package kr.ac.jj.survey.domain.main.model.com.file;

import java.io.File;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.jj.survey.infrastructure.framework.core.support.context.ApplicationContextUtil;
import kr.ac.jj.survey.infrastructure.framework.core.support.io.file.FileUtil;
import kr.ac.jj.survey.infrastructure.framework.web.multipart.policy.FilePolicies;
import kr.ac.jj.survey.infrastructure.framework.web.multipart.policy.FilePolicy;
import kr.ac.jj.survey.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 공통 파일
 */
public class TbComFile extends TbComFileEntity {
    private static final long serialVersionUID = 6920524501563122524L;

    public TbComFile() {
        this.setDeleteYn(false);
    }

    public TbComFile newId() {
        this.setFileId(IdGenerationUtil.createUid("TB_COM_FILE"));

        return this;
    }

    public void createDwldId() {
        this.dwldId = UUID.randomUUID().toString().toUpperCase();
    }

    @JsonIgnore
    public File getFile() {
        FilePolicies filePolicies = ApplicationContextUtil.getBean(FilePolicies.class);
        FilePolicy filePolicy = filePolicies.getFilePolicy(this.getFilePolicy());

        File file = new File(filePolicy.getRepositoryDirectory() + this.getStrePath(), this.getStreFileNm());

        return file;
    }

    @JsonIgnore
    public String getCanonicalPath() {
        return FileUtil.getCanonicalPath(this.getFile());
    }
}
