package kr.ac.jj.survey.domain.main.model.sys.menu;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import kr.ac.jj.survey.domain.main.model.MainEntity;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanDeserializer;
import kr.ac.jj.survey.infrastructure.databind.NumericBooleanSerializer;

/**
 * 시스템 - 메뉴 Entity
 */
abstract class TbSysMenuEntity extends MainEntity {
    private static final long serialVersionUID = 3012327116216930640L;

    protected String menuId;
    protected String menuKnd;
    protected String upperMenuId;
    protected Integer menuLevel;
    protected String menuNmTitle;
    protected Integer menuOrdr;
    protected String menuPath;
    protected Boolean useYn;

    public String getMenuId() {
        return this.menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuKnd() {
        return this.menuKnd;
    }

    public void setMenuKnd(String menuKnd) {
        this.menuKnd = menuKnd;
    }

    public String getUpperMenuId() {
        return this.upperMenuId;
    }

    public void setUpperMenuId(String upperMenuId) {
        this.upperMenuId = upperMenuId;
    }

    public Integer getMenuLevel() {
        return this.menuLevel;
    }

    public void setMenuLevel(Integer menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuNmTitle() {
        return this.menuNmTitle;
    }

    public void setMenuNmTitle(String menuNmTitle) {
        this.menuNmTitle = menuNmTitle;
    }

    public Integer getMenuOrdr() {
        return this.menuOrdr;
    }

    public void setMenuOrdr(Integer menuOrdr) {
        this.menuOrdr = menuOrdr;
    }

    public String getMenuPath() {
        return this.menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    @JsonSerialize(using = NumericBooleanSerializer.class)
    public Boolean getUseYn() {
        return this.useYn;
    }

    @JsonDeserialize(using = NumericBooleanDeserializer.class)
    public void setUseYn(Boolean useYn) {
        this.useYn = useYn;
    }
}
