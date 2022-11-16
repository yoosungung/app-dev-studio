package kr.ac.jj.shared.application.admin.sysmanage.menumanage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.admin.sysmanage.menumanage.mapper.MenuManageMapper;
import kr.ac.jj.shared.application.admin.sysmanage.menumanage.model.MenuManageModel;
import kr.ac.jj.shared.domain.main.mapper.sys.menu.TbSysMenuMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.menu.author.TbSysMenuAuthorMapper;
import kr.ac.jj.shared.domain.main.model.sys.menu.TbSysMenu;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.shared.infrastructure.framework.core.support.lang.StringUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.support.MessageUtil;

/**
 * 메뉴 관리 Service
 */
@Service
public class MenuManageServiceImpl {

    private @Autowired MenuManageMapper menuManageMapper;
    private @Autowired TbSysMenuMapper tbSysMenuMapper;
    private @Autowired TbSysMenuAuthorMapper tbSysMenuAuthorMapper;

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
     * 목록 조회
     *
     * @param menuKnd
     * @param upperMenuId
     * @return
     */
    public List<Map<String, Object>> readList(String menuKnd, String upperMenuId) {
        return menuManageMapper.selectList(menuKnd, upperMenuId);
    }

    /**
     * 목록 순서 저장
     *
     * @param ordrList
     */
    public void updateOrdrList(BaseMapList ordrList) {
        for (int i = 0; i < ordrList.size(); i++) {
            menuManageMapper.updateOrdr(ordrList.get(i));
        }
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    public MenuManageModel read() {
        MenuManageModel menuManageModel = new MenuManageModel();

        return menuManageModel;
    }

    /**
     * 조회
     *
     * @param menuId
     * @return
     */
    public MenuManageModel read(String menuId) {
        MenuManageModel menuManageModel = new MenuManageModel();

        TbSysMenu tbSysMenu = tbSysMenuMapper.select(menuId);

        menuManageModel.setTbSysMenu(tbSysMenu);

        return menuManageModel;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(MenuManageModel model) {
        TbSysMenu tbSysMenu = model.getTbSysMenu().newId();

        tbSysMenuMapper.insert(tbSysMenu);

        return tbSysMenu.getMenuId();
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(MenuManageModel model) {
        TbSysMenu tbSysMenu = model.getTbSysMenu();
        TbSysMenu tbSysMenuDb = tbSysMenuMapper.select(tbSysMenu.getMenuId());
        boolean upperMenuIdChanged = !StringUtil.equalsIfNullToEmpty(tbSysMenu.getUpperMenuId(),
                tbSysMenuDb.getUpperMenuId());

        if (upperMenuIdChanged) {
            tbSysMenu.setMenuOrdr(null);
        }

        tbSysMenuMapper.update(tbSysMenu);

        if (upperMenuIdChanged) {
            this.updateLevel(tbSysMenu.getMenuKnd(), tbSysMenu.getUpperMenuId());
        }
    }

    /**
     * 레벨 수정
     *
     * @param menuKnd
     * @param upperMenuId
     */
    private void updateLevel(String menuKnd, String upperMenuId) {
        menuManageMapper.updateSubLevel(menuKnd, upperMenuId);

        List<TbSysMenu> subMenuList = menuManageMapper.selectSubList(menuKnd, upperMenuId);

        for (TbSysMenu tbSysMenu : subMenuList) {
            this.updateLevel(menuKnd, tbSysMenu.getMenuId());
        }
    }

    /**
     * 삭제
     *
     * @param menuId
     */
    public void delete(String menuId) {
        tbSysMenuAuthorMapper.deleteListByMenuId(menuId);

        if (tbSysMenuMapper.delete(menuId) == 0) {
            throw new BizException(MessageUtil.getMessage(
                    "admin.sysmanage.menumanage.message.deleteNotAvailIfChildrenExists", "하위메뉴가 있는 항목은 삭제할 수 없습니다."));
        }
    }

}
