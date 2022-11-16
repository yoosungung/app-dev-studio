package kr.ac.jj.shared.domain.main.model.com.email.atchfile;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 공통 메일 첨부파일
 */
public class TbComEmailAtchFile extends TbComEmailAtchFileEntity {

    private static final long serialVersionUID = -7092010763219746037L;

    public TbComEmailAtchFile newId() {
        this.setEmailAtchFileId(IdGenerationUtil.createUid("TB_COM_EMAIL_ATCH_FILE"));

        return this;
    }

    @Override
    @JsonIgnore
    public String getFilePath() {
        return this.filePath;
    }

    @Override
    @JsonIgnore
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getFileNm() {
        if (StringUtils.isEmpty(this.fileNm)) {
            return FilenameUtils.getBaseName(this.getFilePath());
        }

        return this.fileNm;
    }

    @JsonIgnore
    public File getFile() {
        return new File(this.getFilePath());
    }

}
