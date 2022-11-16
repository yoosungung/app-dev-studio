package kr.ac.jj.shared.application.admin.sysmanage.authormanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.admin.sysmanage.authormanage.mapper.AuthorityHierarchyManageMapper;
import kr.ac.jj.shared.application.admin.sysmanage.authormanage.model.AuthorityHierarchyManageModel;
import kr.ac.jj.shared.domain.main.mapper.sys.rolehierarchy.TbSysRoleHierarchyMapper;
import kr.ac.jj.shared.domain.main.model.sys.rolehierarchy.TbSysRoleHierarchy;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.DataJobTypes;

/**
 * 권한 계층 관리 Service
 */
@Service
public class AuthorityHierarchyManageServiceImpl {

    private @Autowired AuthorityHierarchyManageMapper authorityHierarchyManageMapper;
    private @Autowired TbSysRoleHierarchyMapper tbSysRoleHierarchyMapper;

    /**
     * 조회
     *
     * @return
     */
    public AuthorityHierarchyManageModel read() {
        AuthorityHierarchyManageModel model = new AuthorityHierarchyManageModel();

        model.setTbSysRoleHierarchyList(authorityHierarchyManageMapper.selectList());

        return model;
    }

    /**
     * 저장
     *
     * @param model
     */
    public void update(AuthorityHierarchyManageModel model) {
        List<TbSysRoleHierarchy> tbSysRoleHierarchyList = model.getTbSysRoleHierarchyList();

        authorityHierarchyManageMapper.deleteList();

        for (TbSysRoleHierarchy tbSysRoleHierarchy : tbSysRoleHierarchyList) {
            if (tbSysRoleHierarchy.get_JOB_TYPES() != DataJobTypes.DELETE) {
                tbSysRoleHierarchyMapper.insert(tbSysRoleHierarchy.newId());
            }
        }
    }

}
