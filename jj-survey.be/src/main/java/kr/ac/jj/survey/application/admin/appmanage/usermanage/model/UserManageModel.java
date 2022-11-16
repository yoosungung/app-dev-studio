package kr.ac.jj.survey.application.admin.appmanage.usermanage.model;

import kr.ac.jj.survey.domain.main.model.com.person.TbComPerson;

/**
 * 사용자 관리 Model
 */
public class UserManageModel {
    private TbComPerson tbComPerson;

    public boolean isEditable() {
        if (this.tbComPerson == null) {
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

}
