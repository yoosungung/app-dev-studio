package kr.ac.jj.shared.application.admin.appmanage.deptmanage.model;

import java.util.List;

import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept;
import kr.ac.jj.shared.domain.main.model.com.dept.author.TbComDeptAuthorToAuthor;

/**
 * 부서 관리 Model
 */
public class DepartmentManageModel {

    private TbComDept tbComDept;
    private List<TbComDeptAuthorToAuthor> tbComDeptAuthorToAuthorList;

    public boolean isEditable() {
        if (this.tbComDept == null) {
            return false;
        }

        return true;
    }

    public TbComDept getTbComDept() {
        return this.tbComDept;
    }

    public void setTbComDept(TbComDept tbComDept) {
        this.tbComDept = tbComDept;
    }

    public List<TbComDeptAuthorToAuthor> getTbComDeptAuthorToAuthorList() {
        return this.tbComDeptAuthorToAuthorList;
    }

    public void setTbComDeptAuthorToAuthorList(List<TbComDeptAuthorToAuthor> tbComDeptAuthorToAuthorList) {
        this.tbComDeptAuthorToAuthorList = tbComDeptAuthorToAuthorList;
    }

}
