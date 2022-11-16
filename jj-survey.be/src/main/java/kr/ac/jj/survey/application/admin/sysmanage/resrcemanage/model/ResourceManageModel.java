package kr.ac.jj.survey.application.admin.sysmanage.resrcemanage.model;

import java.util.List;

import kr.ac.jj.survey.domain.main.model.sys.resrce.TbSysResrce;
import kr.ac.jj.survey.domain.main.model.sys.resrce.author.TbSysResrceAuthorToAuthor;

/**
 * 리소스 관리 Model
 */
public class ResourceManageModel {
    private TbSysResrce tbSysResrce;
    private List<TbSysResrceAuthorToAuthor> tbSysResrceAuthorToAuthorList;

    public TbSysResrce getTbSysResrce() {
        return this.tbSysResrce;
    }

    public void setTbSysResrce(TbSysResrce tbSysResrce) {
        this.tbSysResrce = tbSysResrce;
    }

    public List<TbSysResrceAuthorToAuthor> getTbSysResrceAuthorToAuthorList() {
        return this.tbSysResrceAuthorToAuthorList;
    }

    public void setTbSysResrceAuthorToAuthorList(List<TbSysResrceAuthorToAuthor> tbSysResrceAuthorToAuthorList) {
        this.tbSysResrceAuthorToAuthorList = tbSysResrceAuthorToAuthorList;
    }
}
