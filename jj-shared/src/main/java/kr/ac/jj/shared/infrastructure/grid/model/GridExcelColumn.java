package kr.ac.jj.shared.infrastructure.grid.model;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.poi.ss.usermodel.CellStyle;

public class GridExcelColumn {

    private String name;
    private String align;
    private String formatter;
    private Integer width;
    private Boolean hidden;
    private Integer decimalPlaces;
    private CellStyle cellStyle;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlign() {
        return this.align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getFormatter() {
        return this.formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Boolean getHidden() {
        return BooleanUtils.isTrue(this.hidden);
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Integer getDecimalPlaces() {
        return this.decimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public CellStyle getCellStyle() {
        return this.cellStyle;
    }

    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

}
