package kr.ac.jj.survey.application.admin.appmanage.bbsmanage.model;

import java.util.List;

import kr.ac.jj.survey.domain.main.model.com.bbs.TbComBbs;
import kr.ac.jj.survey.domain.main.model.com.bbs.author.TbComBbsAuthorToAuthor;

/**
 * 게시판 관리 Model
 */
public class BbsManageModel {
    private TbComBbs tbComBbs;
    private List<TbComBbsAuthorToAuthor> tbComBbsAuthorToAuthorList;

    public boolean isEditable() {
        if (this.tbComBbs == null) {
            return false;
        }

        return true;
    }

    public TbComBbs getTbComBbs() {
        return this.tbComBbs;
    }

    public void setTbComBbs(TbComBbs tbComBbs) {
        this.tbComBbs = tbComBbs;
    }

    public List<TbComBbsAuthorToAuthor> getTbComBbsAuthorToAuthorList() {
        return this.tbComBbsAuthorToAuthorList;
    }

    public void setTbComBbsAuthorToAuthorList(List<TbComBbsAuthorToAuthor> tbComBbsAuthorToAuthorList) {
        this.tbComBbsAuthorToAuthorList = tbComBbsAuthorToAuthorList;
    }
}
