package kr.ac.jj.shared.application.admin.sysmanage.authormanage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.admin.sysmanage.authormanage.mapper.AuthorityManageMapper;
import kr.ac.jj.shared.application.admin.sysmanage.authormanage.model.AuthorityManageModel;
import kr.ac.jj.shared.config.props.SharedConfigProperties;
import kr.ac.jj.shared.domain.main.mapper.com.dept.author.TbComDeptAuthorMapper;
import kr.ac.jj.shared.domain.main.mapper.com.dept.author.TbComDeptAuthorToDeptMapper;
import kr.ac.jj.shared.domain.main.mapper.com.dty.author.TbComDtyAuthorMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.author.TbSysAuthorMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.author.relatecode.TbSysAuthorRelateCodeMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.menu.author.TbSysMenuAuthorMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.menu.author.TbSysMenuAuthorToMenuMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.resrce.author.TbSysResrceAuthorMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.resrce.author.TbSysResrceAuthorToResrceMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.rolehierarchy.TbSysRoleHierarchyMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.user.author.TbSysUserAuthorMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.user.author.TbSysUserAuthorToUserMapper;
import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept.DeptSeEnums;
import kr.ac.jj.shared.domain.main.model.com.dept.author.TbComDeptAuthor;
import kr.ac.jj.shared.domain.main.model.com.dept.author.TbComDeptAuthorToDept;
import kr.ac.jj.shared.domain.main.model.sys.author.TbSysAuthor;
import kr.ac.jj.shared.domain.main.model.sys.author.relatecode.TbSysAuthorRelateCode;
import kr.ac.jj.shared.domain.main.model.sys.author.relatecode.TbSysAuthorRelateCodeList;
import kr.ac.jj.shared.domain.main.model.sys.menu.author.TbSysMenuAuthor;
import kr.ac.jj.shared.domain.main.model.sys.menu.author.TbSysMenuAuthorToMenu;
import kr.ac.jj.shared.domain.main.model.sys.resrce.author.TbSysResrceAuthor;
import kr.ac.jj.shared.domain.main.model.sys.resrce.author.TbSysResrceAuthorToResrce;
import kr.ac.jj.shared.domain.main.model.sys.user.author.TbSysUserAuthor;
import kr.ac.jj.shared.domain.main.model.sys.user.author.TbSysUserAuthorToUser;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.DataJobTypes;
import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 권한 관리 Service
 */
@Service
public class AuthorityManageServiceImpl {

    private @Autowired SharedConfigProperties sharedConfig;
    private @Autowired AuthorityManageMapper authorityManageMapper;
    private @Autowired TbSysAuthorMapper tbSysAuthorMapper;
    private @Autowired TbSysRoleHierarchyMapper tbSysRoleHierarchyMapper;
    private @Autowired TbSysMenuAuthorMapper tbSysMenuAuthorMapper;
    private @Autowired TbSysMenuAuthorToMenuMapper tbSysMenuAuthorToMenuMapper;
    private @Autowired TbSysResrceAuthorMapper tbSysResrceAuthorMapper;
    private @Autowired TbSysResrceAuthorToResrceMapper tbSysResrceAuthorToResrceMapper;
    private @Autowired TbSysAuthorRelateCodeMapper tbSysAuthorRelateCodeMapper;
    private @Autowired TbComDeptAuthorMapper tbComDeptAuthorMapper;
    private @Autowired TbComDeptAuthorToDeptMapper tbComDeptAuthorToDeptMapper;
    private @Autowired TbSysUserAuthorMapper tbSysUserAuthorMapper;
    private @Autowired TbComDtyAuthorMapper tbComDtyAuthorMapper;
    private @Autowired TbSysUserAuthorToUserMapper tbSysUserAuthorToUserMapper;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        authorityManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 정렬 순서 목록 조회
     *
     * @return
     */
    public List<TbSysAuthor> readSortOrdrList() {
        return tbSysAuthorMapper.selectSortOrdrList();
    }

    /**
     * 정렬 순서 목록 저장
     *
     * @param tbSysAuthorList
     */
    public void updateSortOrdrList(List<TbSysAuthor> tbSysAuthorList) {
        for (int i = 0; i < tbSysAuthorList.size(); i++) {
            tbSysAuthorMapper.updateSortOrdr(tbSysAuthorList.get(i));
        }
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    public AuthorityManageModel read() {
        AuthorityManageModel model = new AuthorityManageModel();

        TbSysAuthor tbSysAuthor = new TbSysAuthor();
        tbSysAuthor.setSortOrdr(tbSysAuthorMapper.selectMaxSortOrdr() + 1);
        tbSysAuthor.setUserAuthorYn(true);
        tbSysAuthor.setUseYn(true);

        model.setTbSysAuthor(tbSysAuthor);

        Map<String, List<TbSysMenuAuthorToMenu>> tbSysAuthorMenuListMap = new HashMap<String, List<TbSysMenuAuthorToMenu>>();
        String[] menuKnds = CodeDataUtil.getCodes("/domain.main.tbSysMenu/menuKnd");

        for (String menuKnd : menuKnds) {
            tbSysAuthorMenuListMap.put(menuKnd, tbSysMenuAuthorToMenuMapper.selectListByAuthorId(null, menuKnd));
        }

        model.setTbSysMenuAuthorToMenuListMap(tbSysAuthorMenuListMap);
        model.setTbSysResrceAuthorToResrceList(tbSysResrceAuthorToResrceMapper.selectListByAuthorId(null));
        model.setTbComDeptAuthorToDeptList(new ArrayList<TbComDeptAuthorToDept>());
        model.setTbSysUserAuthorToUserList(new ArrayList<TbSysUserAuthorToUser>());

        {
            TbSysAuthorRelateCodeList tbSysAuthorRelateCodeList = new TbSysAuthorRelateCodeList();
            tbSysAuthorRelateCodeList.setRelateCodeSe("PERSON_SE");
            tbSysAuthorRelateCodeList.setCodeDataList(CodeDataUtil.getList("/domain.main.tbComPerson/personSe"),
                    sharedConfig.getLogin().getUserPersonSe());

            model.setTbSysAuthorRelateCodePersonSeList(tbSysAuthorRelateCodeList);
        }

        {
            TbSysAuthorRelateCodeList tbSysAuthorRelateCodeList = new TbSysAuthorRelateCodeList();
            tbSysAuthorRelateCodeList.setRelateCodeSe("DTY_NM");

            model.setTbSysAuthorRelateCodeDtyNmList(tbSysAuthorRelateCodeList);
        }

        return model;
    }

    /**
     * 조회
     *
     * @param authorId
     * @return
     */
    public AuthorityManageModel read(String authorId) {
        AuthorityManageModel model = new AuthorityManageModel();

        TbSysAuthor tbSysAuthor = tbSysAuthorMapper.select(authorId);

        model.setTbSysAuthor(tbSysAuthor);

        Map<String, List<TbSysMenuAuthorToMenu>> tbSysAuthorMenuListMap = new HashMap<String, List<TbSysMenuAuthorToMenu>>();
        String[] menuKnds = CodeDataUtil.getCodes("/domain.main.tbSysMenu/menuKnd");

        for (String menuKnd : menuKnds) {
            tbSysAuthorMenuListMap.put(menuKnd, tbSysMenuAuthorToMenuMapper.selectListByAuthorId(authorId, menuKnd));
        }

        model.setTbSysMenuAuthorToMenuListMap(tbSysAuthorMenuListMap);
        model.setTbSysResrceAuthorToResrceList(tbSysResrceAuthorToResrceMapper.selectListByAuthorId(authorId));
        model.setTbComDeptAuthorToDeptList(tbComDeptAuthorToDeptMapper.selectListByAuthorId(authorId));
        model.setTbSysUserAuthorToUserList(tbSysUserAuthorToUserMapper.selectListByAuthorId(authorId));

        {
            TbSysAuthorRelateCodeList tbSysAuthorRelateCodeList = new TbSysAuthorRelateCodeList();
            tbSysAuthorRelateCodeList.setRelateCodeSe("PERSON_SE");
            tbSysAuthorRelateCodeList.setCodeDataList(CodeDataUtil.getList("/domain.main.tbComPerson/personSe"),
                    sharedConfig.getLogin().getUserPersonSe());
            tbSysAuthorRelateCodeList.setValueList(tbSysAuthorRelateCodeMapper
                    .selectListByAuthorIdAndRelateCodeSe(authorId, tbSysAuthorRelateCodeList.getRelateCodeSe()), true);

            model.setTbSysAuthorRelateCodePersonSeList(tbSysAuthorRelateCodeList);
        }

        {
            TbSysAuthorRelateCodeList tbSysAuthorRelateCodeList = new TbSysAuthorRelateCodeList();
            tbSysAuthorRelateCodeList.setRelateCodeSe("DTY_NM");
            tbSysAuthorRelateCodeList.setValueList(tbSysAuthorRelateCodeMapper
                    .selectListByAuthorIdAndRelateCodeSe(authorId, tbSysAuthorRelateCodeList.getRelateCodeSe()), false);

            model.setTbSysAuthorRelateCodeDtyNmList(tbSysAuthorRelateCodeList);
        }

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(AuthorityManageModel model) {
        TbSysAuthor tbSysAuthor = model.getTbSysAuthor().newId();
        String authorId = tbSysAuthor.getAuthorId();

        tbSysAuthor.setBassAuthorYn(false);
        tbSysAuthorMapper.insert(tbSysAuthor);

        this.updateAuthorToMenuList(authorId, model.getTbSysMenuAuthorToMenuListMap());
        this.updateAuthorToResrceList(authorId, model.getTbSysResrceAuthorToResrceList());

        if (BooleanUtils.isTrue(tbSysAuthor.getUserAuthorYn())) {
            this.updateAuthorToDeptList(authorId, model.getTbComDeptAuthorToDeptList());
            this.updateAuthorToUserList(authorId, model.getTbSysUserAuthorToUserList());
            this.updateAuthorToRelateCodeList(authorId, "PERSON_SE", model.getTbSysAuthorRelateCodePersonSeList());
            this.updateAuthorToRelateCodeList(authorId, "DTY_NM", model.getTbSysAuthorRelateCodeDtyNmList());
        }

        return authorId;
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(AuthorityManageModel model) {
        if (!model.isEditable()) {
            throw new BizException("수정할 수 없는 권한입니다.");
        }

        TbSysAuthor tbSysAuthor = model.getTbSysAuthor();
        String authorId = tbSysAuthor.getAuthorId();

        tbSysAuthorMapper.update(tbSysAuthor);

        this.updateAuthorToMenuList(authorId, model.getTbSysMenuAuthorToMenuListMap());
        this.updateAuthorToResrceList(authorId, model.getTbSysResrceAuthorToResrceList());

        if (BooleanUtils.isTrue(tbSysAuthor.getUserAuthorYn())) {
            this.updateAuthorToDeptList(authorId, model.getTbComDeptAuthorToDeptList());
            this.updateAuthorToUserList(authorId, model.getTbSysUserAuthorToUserList());
            this.updateAuthorToRelateCodeList(authorId, "PERSON_SE", model.getTbSysAuthorRelateCodePersonSeList());
            this.updateAuthorToRelateCodeList(authorId, "DTY_NM", model.getTbSysAuthorRelateCodeDtyNmList());
        } else {
            tbComDeptAuthorMapper.deleteListByAuthorId(authorId);
            tbSysUserAuthorMapper.deleteListByAuthorId(authorId);
            tbSysAuthorRelateCodeMapper.deleteListByAuthorId(authorId);
        }
    }

    /**
     * 권한별 메뉴 목록 저장
     *
     * @param authorId
     * @param tbSysMenuAuthorToMenuListMap
     */
    private void updateAuthorToMenuList(String authorId,
            Map<String, List<TbSysMenuAuthorToMenu>> tbSysMenuAuthorToMenuListMap) {

        String[] menuKnds = CodeDataUtil.getCodes("/domain.main.tbSysMenu/menuKnd");

        for (String menuKnd : menuKnds) {
            List<TbSysMenuAuthorToMenu> tbSysMenuAuthorToMenuList = tbSysMenuAuthorToMenuListMap.get(menuKnd);

            for (TbSysMenuAuthorToMenu tbSysMenuAuthorToMenu : tbSysMenuAuthorToMenuList) {
                String menuId = tbSysMenuAuthorToMenu.getMenuId();
                Integer menuAuthorCo = tbSysMenuAuthorToMenu.getMenuAuthorCo();
                boolean menuAuthorYn = BooleanUtils.isTrue(tbSysMenuAuthorToMenu.getMenuAuthorYn());

                if (menuAuthorCo == 0 && menuAuthorYn) {
                    TbSysMenuAuthor tbSysMenuAuthor = new TbSysMenuAuthor();
                    tbSysMenuAuthor.setMenuId(menuId);
                    tbSysMenuAuthor.setAuthorId(authorId);
                    tbSysMenuAuthorMapper.insert(tbSysMenuAuthor);
                } else if (menuAuthorCo > 0 && !menuAuthorYn) {
                    tbSysMenuAuthorMapper.delete(menuId, authorId);
                }
            }
        }
    }

    /**
     * 권한별 리소스 목록 저장
     *
     * @param authorId
     * @param tbSysResrceAuthorToResrceList
     */
    private void updateAuthorToResrceList(String authorId,
            List<TbSysResrceAuthorToResrce> tbSysResrceAuthorToResrceList) {

        for (TbSysResrceAuthorToResrce tbSysResrceAuthorToResrce : tbSysResrceAuthorToResrceList) {
            String resrceId = tbSysResrceAuthorToResrce.getResrceId();
            Integer resrceAuthorCo = tbSysResrceAuthorToResrce.getResrceAuthorCo();
            boolean resrceAuthorYn = BooleanUtils.isTrue(tbSysResrceAuthorToResrce.getResrceAuthorYn());

            if (resrceAuthorCo == 0 && resrceAuthorYn) {
                TbSysResrceAuthor tbSysResrceAuthor = new TbSysResrceAuthor();
                tbSysResrceAuthor.setResrceId(resrceId);
                tbSysResrceAuthor.setAuthorId(authorId);
                tbSysResrceAuthorMapper.insert(tbSysResrceAuthor);
            } else if (resrceAuthorCo > 0 && !resrceAuthorYn) {
                tbSysResrceAuthorMapper.delete(resrceId, authorId);
            }
        }
    }

    /**
     * 권한별 부서 목록 저장
     *
     * @param authorId
     * @param tbComDeptAuthorToDeptList
     */
    private void updateAuthorToDeptList(String authorId, List<TbComDeptAuthorToDept> tbComDeptAuthorToDeptList) {
        for (TbComDeptAuthorToDept tbComDeptAuthorToDept : tbComDeptAuthorToDeptList) {
            String deptId = tbComDeptAuthorToDept.getDeptId();

            if (tbComDeptAuthorToDept.get_JOB_TYPES() == DataJobTypes.DELETE) {
                tbComDeptAuthorMapper.delete(deptId, authorId);
            } else if (tbComDeptAuthorToDept.get_JOB_TYPES() == DataJobTypes.CREATE) {
                TbComDeptAuthor tbComDeptAuthor = new TbComDeptAuthor();
                tbComDeptAuthor.setDeptId(deptId);
                tbComDeptAuthor.setAuthorId(authorId);
                tbComDeptAuthorMapper.insert(tbComDeptAuthor);
            }
        }
    }

    /**
     * 권한별 사용자 목록 저장
     *
     * @param authorId
     * @param tbSysUserAuthorToUserList
     */
    private void updateAuthorToUserList(String authorId, List<TbSysUserAuthorToUser> tbSysUserAuthorToUserList) {
        for (TbSysUserAuthorToUser tbSysUserAuthorToUser : tbSysUserAuthorToUserList) {
            String userId = tbSysUserAuthorToUser.getUserId();

            if (tbSysUserAuthorToUser.get_JOB_TYPES() == DataJobTypes.DELETE) {
                tbSysUserAuthorMapper.delete(userId, authorId);
            } else if (tbSysUserAuthorToUser.get_JOB_TYPES() == DataJobTypes.CREATE) {
                TbSysUserAuthor tbSysUserAuthor = new TbSysUserAuthor();
                tbSysUserAuthor.setUserId(userId);
                tbSysUserAuthor.setAuthorId(authorId);
                tbSysUserAuthorMapper.insert(tbSysUserAuthor);
            }
        }
    }

    /**
     * 권한별 관련코드 목록 저장
     *
     * @param authorId
     * @param relateCodeSe
     * @param tbSysAuthorRelateCodeList
     */
    private void updateAuthorToRelateCodeList(String authorId, String relateCodeSe,
            List<TbSysAuthorRelateCode> tbSysAuthorRelateCodeList) {
        tbSysAuthorRelateCodeMapper.deleteListByAuthorIdAndRelateCodeSe(authorId, relateCodeSe);

        for (TbSysAuthorRelateCode tbSysAuthorRelateCode : tbSysAuthorRelateCodeList) {
            boolean relateCodeAuthorYn = BooleanUtils.isTrue(tbSysAuthorRelateCode.getRelateCodeAuthorYn());

            if (relateCodeAuthorYn && tbSysAuthorRelateCode.get_JOB_TYPES() != DataJobTypes.DELETE) {
                TbSysAuthorRelateCode tbSysAuthorRelateCodeNew = new TbSysAuthorRelateCode();
                tbSysAuthorRelateCodeNew.setAuthorId(authorId);
                tbSysAuthorRelateCodeNew.setRelateCodeSe(relateCodeSe);
                tbSysAuthorRelateCodeNew.setRelateCodeValue(tbSysAuthorRelateCode.getRelateCodeValue());
                tbSysAuthorRelateCodeMapper.insert(tbSysAuthorRelateCodeNew);
            }
        }
    }

    /**
     * 삭제
     *
     * @param authorId
     */
    public void delete(String authorId) {
        AuthorityManageModel model = this.read(authorId);

        if (!model.isDeletable()) {
            throw new BizException("삭제할 수 없는 권한입니다.");
        }

        TbSysAuthor tbSysAuthorDb = tbSysAuthorMapper.select(authorId);

        tbSysRoleHierarchyMapper.deleteListByRoleCode(tbSysAuthorDb.getAuthorCode());
        tbSysMenuAuthorMapper.deleteListByAuthorId(authorId);
        tbSysResrceAuthorMapper.deleteListByAuthorId(authorId);
        tbComDeptAuthorMapper.deleteListByAuthorId(authorId);
        tbSysUserAuthorMapper.deleteListByAuthorId(authorId);
        tbComDtyAuthorMapper.deleteListByAuthorId(authorId);
        tbSysAuthorRelateCodeMapper.deleteListByAuthorIdAndRelateCodeSe(authorId, "PERSON_SE");

        tbSysAuthorMapper.delete(authorId);
    }

    /**
     * 부서 트리 목록 조회
     *
     * @return
     */
    public List<Map<String, Object>> readDeptTreeList() {
        return authorityManageMapper.selectDeptTreeList(DeptSeEnums.EMPLOYEE);
    }

    /**
     * 사용자 검색 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readUserSearchList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        authorityManageMapper.selectUserSearchList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 직무 검색 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readDutySearchList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        authorityManageMapper.selectDutySearchList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

}
