package kr.ac.jj.shared.domain.main.model.sys.menu;

import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 시스템 - 메뉴
 */
public class TbSysMenu extends TbSysMenuEntity {

    private static final long serialVersionUID = 3266974807308221610L;

    private String upperMenuNm;

    public TbSysMenu newId() {
        this.setMenuId(IdGenerationUtil.createUid("TB_SYS_MENU"));

        return this;
    }

    public String getUpperMenuNm() {
        return this.upperMenuNm;
    }

    public void setUpperMenuNm(String upperMenuNm) {
        this.upperMenuNm = upperMenuNm;
    }

    public String getUseYnNm() {
        return CodeDataUtil.getCodeName("/common/useYn", this.getUseYn());
    }

}
