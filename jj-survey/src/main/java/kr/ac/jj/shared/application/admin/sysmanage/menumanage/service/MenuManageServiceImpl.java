package kr.ac.jj.shared.application.admin.sysmanage.menumanage.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.admin.sysmanage.menumanage.mapper.MenuManageMapper;
import kr.ac.jj.shared.application.admin.sysmanage.menumanage.model.MenuManageModel;
import kr.ac.jj.shared.domain.main.mapper.sys.log.menu.TbSysLogMenuMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.menu.TbSysMenuMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.menu.author.TbSysMenuAuthorMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.menu.author.TbSysMenuAuthorToAuthorMapper;
import kr.ac.jj.shared.domain.main.model.sys.menu.TbSysMenu;
import kr.ac.jj.shared.domain.main.model.sys.menu.author.TbSysMenuAuthor;
import kr.ac.jj.shared.domain.main.model.sys.menu.author.TbSysMenuAuthorToAuthor;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.web.context.support.MessageUtil;

/**
 * 메뉴 관리 Service
 */
@Service
public class MenuManageServiceImpl {

    private @Autowired MenuManageMapper menuManageMapper;
    private @Autowired TbSysMenuMapper tbSysMenuMapper;
    private @Autowired TbSysMenuAuthorMapper tbSysMenuAuthorMapper;
    private @Autowired TbSysMenuAuthorToAuthorMapper tbSysMenuAuthorToAuthorMapper;
    private @Autowired TbSysLogMenuMapper tbSysLogMenuMapper;

    /**
     * 트리 조회
     *
     * @param menuKnd
     * @return
     */
    public List<Map<String, Object>> readTree(String menuKnd) {
        return menuManageMapper.selectTree(menuKnd);
    }

    /**
     * 트리 조회
     *
     * @param menuKnd
     * @param maxLevel
     * @return
     */
    public List<Map<String, Object>> readTreeForUpperMenuSelect(String menuKnd, Integer maxLevel) {
        return menuManageMapper.selectTreeForUpperMenuSelect(menuKnd, maxLevel);
    }

    /**
     * 메뉴 순서 목록 저장
     *
     * @param tbSysMenuList
     */
    public void updateMenuOrdrList(List<TbSysMenu> tbSysMenuList) {
        for (int i = 0; i < tbSysMenuList.size(); i++) {
            tbSysMenuMapper.updateMenuOrdr(tbSysMenuList.get(i));
        }
    }

    /**
     * 조회 - 생성용
     *
     * @param upperMenuId
     * @return
     */
    public MenuManageModel readForCreate(String upperMenuId) {
        MenuManageModel model = new MenuManageModel();

        TbSysMenu tbSysMenu = new TbSysMenu();
        tbSysMenu.setUseYn(true);

        if (StringUtils.isNotEmpty(upperMenuId)) {
            TbSysMenu tbSysMenuUpper = tbSysMenuMapper.select(upperMenuId);

            tbSysMenu.setMenuKnd(tbSysMenuUpper.getMenuKnd());
            tbSysMenu.setUpperMenuId(upperMenuId);
            tbSysMenu.setUpperMenuNm(tbSysMenuUpper.getMenuNm());
        }

        model.setTbSysMenu(tbSysMenu);
        model.setTbSysMenuAuthorToAuthorList(tbSysMenuAuthorToAuthorMapper.selectListByMenuId(null));

        return model;
    }

    /**
     * 조회
     *
     * @param menuId
     * @return
     */
    public MenuManageModel read(String menuId) {
        MenuManageModel model = new MenuManageModel();

        TbSysMenu tbSysMenu = tbSysMenuMapper.select(menuId);

        model.setTbSysMenu(tbSysMenu);
        model.setTbSysMenuAuthorToAuthorList(tbSysMenuAuthorToAuthorMapper.selectListByMenuId(menuId));
        model.setChildMenuCount(menuManageMapper.selectChildMenuCount(menuId));

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(MenuManageModel model) {
        TbSysMenu tbSysMenu = model.getTbSysMenu().newId();
        List<TbSysMenuAuthorToAuthor> tbSysMenuAuthorToAuthorList = model.getTbSysMenuAuthorToAuthorList();
        String menuId = tbSysMenu.getMenuId();

        tbSysMenuMapper.insert(tbSysMenu);

        this.updateAuthorList(menuId, tbSysMenuAuthorToAuthorList);

        return menuId;
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(MenuManageModel model) {
        TbSysMenu tbSysMenu = model.getTbSysMenu();
        List<TbSysMenuAuthorToAuthor> tbSysMenuAuthorToAuthorList = model.getTbSysMenuAuthorToAuthorList();
        String menuId = tbSysMenu.getMenuId();

        tbSysMenuMapper.update(tbSysMenu);

        this.updateAuthorList(menuId, tbSysMenuAuthorToAuthorList);
    }

    /**
     * 권한 목록 저장
     *
     * @param menuId
     * @param tbSysMenuAuthorToAuthorList
     */
    private void updateAuthorList(String menuId, List<TbSysMenuAuthorToAuthor> tbSysMenuAuthorToAuthorList) {
        tbSysMenuAuthorMapper.deleteListByMenuId(menuId);

        for (TbSysMenuAuthorToAuthor tbSysMenuAuthorToAuthor : tbSysMenuAuthorToAuthorList) {
            if (BooleanUtils.isTrue(tbSysMenuAuthorToAuthor.getMenuAuthorYn())) {
                TbSysMenuAuthor tbSysMenuAuthor = new TbSysMenuAuthor();
                tbSysMenuAuthor.setMenuId(menuId);
                tbSysMenuAuthor.setAuthorId(tbSysMenuAuthorToAuthor.getAuthorId());
                tbSysMenuAuthorMapper.insert(tbSysMenuAuthor);
            }
        }
    }

    /**
     * 수정 - 상위 메뉴
     *
     * @param tbSysMenu
     */
    public void updateUpperMenu(TbSysMenu tbSysMenu) {
        TbSysMenu tbSysMenuDb = tbSysMenuMapper.select(tbSysMenu.getMenuId());

        if (StringUtils.equals(StringUtils.defaultString(tbSysMenu.getUpperMenuId()),
                StringUtils.defaultString(tbSysMenuDb.getUpperMenuId()))) {
            return;
        }

        tbSysMenuDb.setUpperMenuId(tbSysMenu.getUpperMenuId());
        tbSysMenuDb.setMenuLevel(null);
        tbSysMenuDb.setMenuOrdr(null);

        tbSysMenuMapper.update(tbSysMenuDb);

        this.updateLevel(tbSysMenuDb.getMenuKnd(), tbSysMenuDb.getMenuId());
    }

    /**
     * 레벨 수정
     *
     * @param menuKnd
     * @param upperMenuId
     */
    private void updateLevel(String menuKnd, String upperMenuId) {
        tbSysMenuMapper.updateChildLevel(menuKnd, upperMenuId);

        List<String> childMenuIdList = tbSysMenuMapper.selectChildMenuIdList(menuKnd, upperMenuId);

        for (String menuId : childMenuIdList) {
            this.updateLevel(menuKnd, menuId);
        }
    }

    /**
     * 삭제
     *
     * @param menuId
     */
    public void delete(String menuId) {
        tbSysLogMenuMapper.deleteListByMenuId(menuId);
        tbSysMenuAuthorMapper.deleteListByMenuId(menuId);

        if (tbSysMenuMapper.deleteNotExistsChildMenu(menuId) == 0) {
            throw new BizException(MessageUtil.getMessage(
                    "admin.sysmanage.menumanage.message.deleteNotAvailIfChildrenExists", "하위메뉴가 있는 항목은 삭제할 수 없습니다."));
        }
    }

}