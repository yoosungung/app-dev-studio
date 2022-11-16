package kr.ac.jj.shared.domain.main.model.com.file;

import java.util.ArrayList;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

public class TbComFileList extends ArrayList<TbComFile> {

    private static final long serialVersionUID = -8280773260324878309L;

    private String fileGroupId;

    public void newFileGroupId() {
        this.setFileGroupId(IdGenerationUtil.createUid("TB_COM_FILE.FILE_GROUP_ID"));
    }

    public String getFileGroupId() {
        return this.fileGroupId;
    }

    public void setFileGroupId(String fileGroupId) {
        this.fileGroupId = fileGroupId;

        for (TbComFile tbComFile : this) {
            tbComFile.setFileGroupId(fileGroupId);
        }
    }

    @Override
    public boolean add(TbComFile tbComFile) {
        tbComFile.setFileGroupId(this.getFileGroupId());

        return super.add(tbComFile);
    }

}
