package kr.ac.jj.survey.application.admin.sysmanage.authormanage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.survey.application.admin.sysmanage.authormanage.mapper.AuthorityManageMapper;
import kr.ac.jj.survey.application.admin.sysmanage.authormanage.model.AuthorityManageModel;
import kr.ac.jj.survey.domain.main.mapper.com.dept.author.TbComDeptAuthorMapper;
import kr.ac.jj.survey.domain.main.mapper.sys.author.TbSysAuthorMapper;
import kr.ac.jj.survey.domain.main.mapper.sys.menu.author.TbSysMenuAuthorMapper;
import kr.ac.jj.survey.domain.main.mapper.sys.resrce.author.TbSysResrceAuthorMapper;
import kr.ac.jj.survey.domain.main.mapper.sys.user.author.TbSysUserAuthorMapper;
import kr.ac.jj.survey.domain.main.model.com.dept.author.TbComDeptAuthor;
import kr.ac.jj.survey.domain.main.model.com.dept.author.TbComDeptAuthorToDept;
import kr.ac.jj.survey.domain.main.model.sys.author.TbSysAuthor;
import kr.ac.jj.survey.domain.main.model.sys.menu.author.TbSysMenuAuthor;
import kr.ac.jj.survey.domain.main.model.sys.menu.author.TbSysMenuAuthorToMenu;
import kr.ac.jj.survey.domain.main.model.sys.resrce.author.TbSysResrceAuthor;
import kr.ac.jj.survey.domain.main.model.sys.resrce.author.TbSysResrceAuthorToResrce;
import kr.ac.jj.survey.domain.main.model.sys.user.author.TbSysUserAuthor;
import kr.ac.jj.survey.domain.main.model.sys.user.author.TbSysUserAuthorToUser;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.DataJobTypes;
import kr.ac.jj.survey.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.survey.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.infrastructure.title.service.TitleServiceImpl;

/**
 * ?????? ?????? Service
 */
@Service
public class AuthorityManageServiceImpl {
    private @Autowired AuthorityManageMapper authorityManageMapper;
    private @Autowired TbSysAuthorMapper tbSysAuthorMapper;
    private @Autowired TbSysMenuAuthorMapper tbSysMenuAuthorMapper;
    private @Autowired TbSysResrceAuthorMapper tbSysResrceAuthorMapper;
    private @Autowired TbComDeptAuthorMapper tbComDeptAuthorMapper;
    private @Autowired TbSysUserAuthorMapper tbSysUserAuthorMapper;
    private @Autowired TitleServiceImpl titleService;

    /**
     * ?????? ??????
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
     * ?????? ?????? ??????
     *
     * @param ordrList
     */
    public void updateOrdrList(BaseMapList ordrList) {
        for (int i = 0; i < ordrList.size(); i++) {
            BaseMap tbSysAuthor = ordrList.get(i);
            tbSysAuthorMapper.updateSortOrdr((String) tbSysAuthor.get("authorId"),
                    (Integer) tbSysAuthor.get("sortOrdr"));
        }
    }

    /**
     * ??????
     *
     * @param authorId
     * @return
     */
    public AuthorityManageModel read(String authorId) {
        AuthorityManageModel model = new AuthorityManageModel();

        TbSysAuthor tbSysAuthor = tbSysAuthorMapper.select(authorId);
        tbSysAuthor.setAuthorNmTitleList(titleService.readList(tbSysAuthor.getAuthorNmTitle()));

        model.setTbSysAuthor(tbSysAuthor);

        Map<String, List<TbSysMenuAuthorToMenu>> tbSysAuthorMenuListMap = new HashMap<String, List<TbSysMenuAuthorToMenu>>();
        String[] menuKnds = CodeDataUtil.getCodes("/domain.main.tbSysMenu/menuKnd");

        for (String menuKnd : menuKnds) {
            tbSysAuthorMenuListMap.put(menuKnd, tbSysMenuAuthorMapper.selectListByAuthorId2(authorId, menuKnd));
        }

        model.setTbSysMenuAuthorToMenuListMap(tbSysAuthorMenuListMap);
        model.setTbSysResrceAuthorToResrceList(tbSysResrceAuthorMapper.selectListByAuthorId(authorId));
        model.setTbComDeptAuthorToDeptList(tbComDeptAuthorMapper.selectListByAuthorId(authorId));
        model.setTbSysUserAuthorToUserList(tbSysUserAuthorMapper.selectListByAuthorId(authorId));

        return model;
    }

    /**
     * ??????
     *
     * @param model
     * @return
     */
    public String create(AuthorityManageModel model) {
        TbSysAuthor tbSysAuthor = model.getTbSysAuthor().newId();
        tbSysAuthor.setBassAuthorYn(false);

        tbSysAuthorMapper.insert(tbSysAuthor);
        titleService.updateList(tbSysAuthor.getAuthorNmTitle(), tbSysAuthor.getAuthorNmTitleList());

        return tbSysAuthor.getAuthorId();
    }

    /**
     * ??????
     *
     * @param model
     */
    public void update(AuthorityManageModel model) {
        TbSysAuthor tbSysAuthor = model.getTbSysAuthor();

        tbSysAuthorMapper.update(tbSysAuthor);
        titleService.updateList(tbSysAuthor.getAuthorNmTitle(), tbSysAuthor.getAuthorNmTitleList());

        // ????????? ?????? ?????? ??????
        tbSysMenuAuthorMapper.deleteListByAuthorId(tbSysAuthor.getAuthorId());
        String[] menuKnds = CodeDataUtil.getCodes("/domain.main.tbSysMenu/menuKnd");
        for (String menuKnd : menuKnds) {
            List<TbSysMenuAuthorToMenu> tbSysMenuAuthorToMenuList = model.getTbSysMenuAuthorToMenuListMap()
                    .get(menuKnd);
            for (TbSysMenuAuthorToMenu tbSysMenuAuthorToMenu : tbSysMenuAuthorToMenuList) {
                if (tbSysMenuAuthorToMenu.getMenuAuthorCo() != 0) {
                    TbSysMenuAuthor tbSysMenuAuthor = new TbSysMenuAuthor();
                    tbSysMenuAuthor.setMenuId(tbSysMenuAuthorToMenu.getMenuId());
                    tbSysMenuAuthor.setAuthorId(tbSysAuthor.getAuthorId());
                    tbSysMenuAuthorMapper.insert(tbSysMenuAuthor);
                }
            }
        }

        // ????????? ????????? ?????? ??????
        List<TbSysResrceAuthorToResrce> tbSysResrceAuthorToResrceList = model.getTbSysResrceAuthorToResrceList();
        for (TbSysResrceAuthorToResrce tbSysResrceAuthorToResrce : tbSysResrceAuthorToResrceList) {
            if (tbSysResrceAuthorToResrce.get_JOB_TYPES() == DataJobTypes.DELETE) {
                tbSysResrceAuthorMapper.delete(tbSysResrceAuthorToResrce.getResrceId(), tbSysAuthor.getAuthorId());
            } else if (tbSysResrceAuthorToResrce.get_JOB_TYPES() == DataJobTypes.CREATE) {
                TbSysResrceAuthor tbSysResrceAuthor = new TbSysResrceAuthor();
                tbSysResrceAuthor.setResrceId(tbSysResrceAuthorToResrce.getResrceId());
                tbSysResrceAuthor.setAuthorId(tbSysAuthor.getAuthorId());
                tbSysResrceAuthorMapper.insert(tbSysResrceAuthor);
            }
        }

        if (tbSysAuthor.getUserAuthorYn()) {
            // ????????? ?????? ?????? ??????
            List<TbComDeptAuthorToDept> tbComDeptAuthorToDeptList = model.getTbComDeptAuthorToDeptList();
            for (TbComDeptAuthorToDept tbComDeptAuthorToDept : tbComDeptAuthorToDeptList) {
                if (tbComDeptAuthorToDept.get_JOB_TYPES() == DataJobTypes.DELETE) {
                    tbComDeptAuthorMapper.delete(tbComDeptAuthorToDept.getDeptId(), tbSysAuthor.getAuthorId());
                } else if (tbComDeptAuthorToDept.get_JOB_TYPES() == DataJobTypes.CREATE) {
                    TbComDeptAuthor tbComDeptAuthor = new TbComDeptAuthor();
                    tbComDeptAuthor.setDeptId(tbComDeptAuthorToDept.getDeptId());
                    tbComDeptAuthor.setAuthorId(tbSysAuthor.getAuthorId());
                    tbComDeptAuthorMapper.insert(tbComDeptAuthor);
                }
            }

            // ????????? ????????? ?????? ??????
            List<TbSysUserAuthorToUser> tbSysUserAuthorToUserList = model.getTbSysUserAuthorToUserList();
            for (TbSysUserAuthorToUser tbSysUserAuthorToUser : tbSysUserAuthorToUserList) {
                if (tbSysUserAuthorToUser.get_JOB_TYPES() == DataJobTypes.DELETE) {
                    tbSysUserAuthorMapper.delete(tbSysUserAuthorToUser.getUserId(), tbSysAuthor.getAuthorId());
                } else if (tbSysUserAuthorToUser.get_JOB_TYPES() == DataJobTypes.CREATE) {
                    TbSysUserAuthor tbSysUserAuthor = new TbSysUserAuthor();
                    tbSysUserAuthor.setUserId(tbSysUserAuthorToUser.getUserId());
                    tbSysUserAuthor.setAuthorId(tbSysAuthor.getAuthorId());
                    tbSysUserAuthorMapper.insert(tbSysUserAuthor);
                }
            }
        } else {
            // ????????? ?????? ?????? ??????
            tbComDeptAuthorMapper.deleteListByAuthorId(tbSysAuthor.getAuthorId());

            // ????????? ????????? ?????? ??????
            tbSysUserAuthorMapper.deleteListByAuthorId(tbSysAuthor.getAuthorId());
        }
    }

    /**
     * ??????
     *
     * @param authorId
     */
    public void delete(String authorId) {
        TbSysAuthor tbSysAuthorDb = tbSysAuthorMapper.select(authorId);

        tbSysMenuAuthorMapper.deleteListByAuthorId(authorId);
        tbSysResrceAuthorMapper.deleteListByAuthorId(authorId);
        tbComDeptAuthorMapper.deleteListByAuthorId(authorId);
        tbSysUserAuthorMapper.deleteListByAuthorId(authorId);

        titleService.deleteList(tbSysAuthorDb.getAuthorNmTitle());
        tbSysAuthorMapper.delete(authorId);
    }
}
