package kr.ac.jj.shared.application.admin.sysmanage.titlemanage.model;

import java.util.List;

import kr.ac.jj.shared.domain.main.model.sys.title.TbSysTitle;

/**
 * 타이틀 관리 Model
 */
public class TitleManageModel {

    private String titleCode;
    private List<TbSysTitle> tbSysTitleList;

    public boolean isEditable() {
        if (this.tbSysTitleList == null) {
            return false;
        }

        return true;
    }

    public String getTitleCode() {
        return this.titleCode;
    }

    public void setTitleCode(String titleCode) {
        this.titleCode = titleCode;
    }

    public List<TbSysTitle> getTbSysTitleList() {
        return this.tbSysTitleList;
    }

    public void setTbSysTitleList(List<TbSysTitle> tbSysTitleList) {
        this.tbSysTitleList = tbSysTitleList;
    }

}
