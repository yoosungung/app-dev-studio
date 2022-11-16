package kr.ac.jj.shared.domain.main.model.sys.menu;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 메뉴
 */
public class TbSysMenu extends TbSysMenuEntity {

    private static final long serialVersionUID = 3266974807308221610L;

    protected String upperMenuNm;

    public TbSysMenu newId() {
        this.setMenuId(IdGenerationUtil.createUid("TB_SYS_MENU"));

        return this;
    }

    public String getUpperMenuNm() {
        if (StringUtils.isEmpty(this.upperMenuId)) {
            return "ROOT";
        }

        return this.upperMenuNm;
    }

    public void setUpperMenuNm(String upperMenuNm) {
        this.upperMenuNm = upperMenuNm;
    }

    public String getUseYnNm() {
        return super.getBooleanCodeName("/common/useYn", this.getUseYn());
    }

}
