package kr.ac.jj.survey.domain.main.model.sys.menu.author;

import kr.ac.jj.survey.domain.main.model.sys.author.TbSysAuthor;

/**
 * 시스템 - 메뉴별 권한 TO 시스템 - 권한
 */
public class TbSysMenuAuthorToAuthor extends TbSysAuthor {
    private static final long serialVersionUID = -5567312591415328234L;

    private String menuId;

    public String getMenuId() {
        return this.menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
