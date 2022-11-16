package kr.ac.jj.shared.domain.main.model.bbs.notice;

import java.util.List;

import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 게시판 - 공지사항
 */
public class TbBbsNotice extends TbBbsNoticeEntity {

    private static final long serialVersionUID = 7208794024629333448L;

    private String writngPsnNm;
    private String changePsnNm;
    private List<TbComFile> atchFileList;

    public TbBbsNotice newId() {
        this.setBbscttId(IdGenerationUtil.createUid("TB_BBS_NOTICE"));

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
