package kr.ac.jj.shared.application.admin.appmanage.personmanage.model;

import kr.ac.jj.shared.domain.main.model.com.person.TbComPerson;
import kr.ac.jj.shared.domain.main.model.sys.user.TbSysUser;
import kr.ac.jj.shared.infrastructure.framework.web.security.util.SecurityContextUtil;

/**
 * 사람 관리 Model
 */
public class PersonManageModel {

    private TbComPerson tbComPerson;
    private TbSysUser tbSysUser;

    public boolean isEditable() {
        if (this.tbComPerson == null) {
            return false;
        }

        if (!SecurityContextUtil.isUserReachableAuthority("ROLE_ADMIN", "ROLE_SUPER")) {
            return false;
        }

        return true;
    }

    public TbComPerson getTbComPerson() {
        return this.tbComPerson;
    }

    public void setTbComPerson(TbComPerson tbComPerson) {
        this.tbComPerson = tbComPerson;
    }

    public TbSysUser getTbSysUser() {
        return this.tbSysUser;
    }

    public void setTbSysUser(TbSysUser tbSysUser) {
        this.tbSysUser = tbSysUser;
    }

}
