package kr.ac.jj.survey.domain.main.model.sys.menu;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.domain.main.model.sys.title.TbSysTitle;
import kr.ac.jj.survey.infrastructure.idgen.util.IdGenerationUtil;
import kr.ac.jj.survey.infrastructure.title.util.TitleUtil;

/**
 * 시스템 - 메뉴
 */
public class TbSysMenu extends TbSysMenuEntity {
    private static final long serialVersionUID = 3266974807308221610L;

    protected String upperMenuNmTitle;
    private List<TbSysTitle> menuNmTitleList;

    public TbSysMenu newId() {
        this.setMenuId(IdGenerationUtil.createUid("TB_SYS_MENU"));

        return this;
    }

    public String getUpperMenuNmTitle() {
        return this.upperMenuNmTitle;
    }

    public void setUpperMenuNmTitle(String upperMenuNmTitle) {
        this.upperMenuNmTitle = upperMenuNmTitle;
    }

    public String getUpperMenuNm() {
        if (StringUtils.isEmpty(this.upperMenuId)) {
            return "ROOT";
        }

        return TitleUtil.getTitleCn(this.getUpperMenuNmTitle());
    }

    public String getMenuNm() {
        return TitleUtil.getTitleCn(this.getMenuNmTitle());
    }

    @Override
    public String getMenuNmTitle() {
        return StringUtils.defaultIfEmpty(this.menuNmTitle, "SYS_MENU_NM." + this.getMenuId());
    }

    public List<TbSysTitle> getMenuNmTitleList() {
        return this.menuNmTitleList;
    }

    public void setMenuNmTitleList(List<TbSysTitle> menuNmTitleList) {
        this.menuNmTitleList = menuNmTitleList;
    }

    public String getUseYnNm() {
        return super.getBooleanCodeName("/common/useYn", this.getUseYn());
    }
}
