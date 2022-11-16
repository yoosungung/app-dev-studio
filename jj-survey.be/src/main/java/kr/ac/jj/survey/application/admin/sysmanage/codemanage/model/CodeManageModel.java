package kr.ac.jj.survey.application.admin.sysmanage.codemanage.model;

import java.util.List;

import kr.ac.jj.survey.domain.main.model.sys.code.group.TbSysCodeGroup;
import kr.ac.jj.survey.domain.main.model.sys.code.value.TbSysCodeValue;

/**
 * 코드 관리 Model
 */
public class CodeManageModel {
    private TbSysCodeGroup tbSysCodeGroup;
    private List<TbSysCodeValue> tbSysCodeValueList;

    public boolean isEditable() {
        if (this.tbSysCodeGroup == null) {
            return false;
        }

        return true;
    }

    public TbSysCodeGroup getTbSysCodeGroup() {
        return this.tbSysCodeGroup;
    }

    public void setTbSysCodeGroup(TbSysCodeGroup tbSysCodeGroup) {
        this.tbSysCodeGroup = tbSysCodeGroup;
    }

    public List<TbSysCodeValue> getTbSysCodeValueList() {
        return this.tbSysCodeValueList;
    }

    public void setTbSysCodeValueList(List<TbSysCodeValue> tbSysCodeValueList) {
        this.tbSysCodeValueList = tbSysCodeValueList;
    }
}
