package kr.ac.jj.shared.application.admin.sysmanage.codemanage.model;

import java.util.List;

import kr.ac.jj.shared.domain.main.model.sys.code.group.TbSysCodeGroup;
import kr.ac.jj.shared.domain.main.model.sys.code.value.TbSysCodeValue;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;

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

        if (!SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN", "ROLE_SUPER")) {
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
