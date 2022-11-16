package kr.ac.jj.shared.domain.main.model.bbs.faq;

import java.util.List;

import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 게시판 - FAQ
 */
public class TbBbsFaq extends TbBbsFaqEntity {

    private static final long serialVersionUID = -1923901590321934319L;

    private String writngPsnNm;
    private String changePsnNm;
    private List<TbComFile> atchFileList;

    public TbBbsFaq newId() {
        this.setBbscttId(IdGenerationUtil.createUid("TB_BBS_FAQ"));

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
