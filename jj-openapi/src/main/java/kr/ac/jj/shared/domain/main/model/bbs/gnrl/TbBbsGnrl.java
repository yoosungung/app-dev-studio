package kr.ac.jj.shared.domain.main.model.bbs.gnrl;

import java.util.List;

import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 게시판 - 일반(답변형)
 */
public class TbBbsGnrl extends TbBbsGnrlEntity {

    private static final long serialVersionUID = 2912393490246628109L;

    private String writngPsnNm;
    private String changePsnNm;
    private List<TbComFile> atchFileList;

    public TbBbsGnrl newId() {
        this.setBbscttId(IdGenerationUtil.createUid("TB_BBS_GNRL"));

        return this;
    }

    public String getWritngPsnNm() {
        return this.writngPsnNm;
    }

    public void setWritngPsnNm(String writngPsnNm) {
        this.writngPsnNm = writngPsnNm;
    }

    public String getChangePsnNm() {
        return this.changePsnNm;
    }

    public void setChangePsnNm(String changePsnNm) {
        this.changePsnNm = changePsnNm;
    }

    public List<TbComFile> getAtchFileList() {
        return this.atchFileList;
    }

    public void setAtchFileList(List<TbComFile> atchFileList) {
        this.atchFileList = atchFileList;
    }

}
