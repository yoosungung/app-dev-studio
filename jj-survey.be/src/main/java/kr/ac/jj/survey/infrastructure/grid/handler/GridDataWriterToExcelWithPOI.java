package kr.ac.jj.survey.infrastructure.grid.handler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.ac.jj.survey.application.common.util.CommonUtil;
import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.ValueConverter;
import kr.ac.jj.survey.infrastructure.framework.core.support.io.util.IOUtil;
import kr.ac.jj.survey.infrastructure.grid.model.DataCellMergeInfo;
import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;

public class GridDataWriterToExcelWithPOI extends GridDataWriterToExcel {
    private static final Logger log = LoggerFactory.getLogger(GridDataWriterToExcelWithPOI.class);

    private Workbook workbook;
    private Sheet sheet;
    private Set<String> mergedStartCell;
    private Map<Integer, DataCellMergeInfo> dataCellMergeInfoMap;

    public GridDataWriterToExcelWithPOI(GridRequest gridRequest) {
        super(gridRequest);
    }

    @Override
    public void writeStart() {
        this.workbook = new SXSSFWorkbook(100);

        for (int c = 0; c < this.columnList.length; c++) {
            String name = (String) this.columnList[c].get("name");
            String align = (String) this.columnList[c].get("align");
            String formatter = (String) this.columnList[c].get("formatter");

            CellStyle cellStyle = this.workbook.createCellStyle();
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);

            if (StringUtils.equals(align, "center")) {
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
            } else if (StringUtils.equals(align, "right")) {
                cellStyle.setAlignment(HorizontalAlignment.RIGHT);
            }

            XSSFFont cellFont = (XSSFFont) this.workbook.createFont();
            cellFont.setFontName("Arial");
            cellFont.setFontHeightInPoints((short) 9);
            cellStyle.setFont(cellFont);

            DataFormat dataFormat = this.workbook.createDataFormat();

            if (StringUtils.equals(name, "rn")) {
                cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cellStyle.setDataFormat(dataFormat.getFormat("#"));
                for (BaseMap header : this.headerList) {
                    header.put("rn", "No");
                }
                this.columnList[c].put("width", 45);
            } else if (StringUtils.equals(formatter, "number") || StringUtils.equals(formatter, "any-number")) {
                int decimalPlaces = (int) this.columnList[c].get("decimalPlaces");
                if (decimalPlaces > 0) {
                    cellStyle.setDataFormat(
                            dataFormat.getFormat("#,##0." + StringUtils.leftPad("0", decimalPlaces, '0')));
                } else {
                    cellStyle.setDataFormat(dataFormat.getFormat("###,0"));
                }
            } else if (StringUtils.equals(formatter, "date") || StringUtils.equals(formatter, "any-date")) {
                cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd"));
            } else if (StringUtils.equals(formatter, "any-datetime")) {
                cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd HH:mm:ss"));
            }

            this.columnList[c].put("cellStyle", cellStyle);
        }

        this.sheet = this.createSheet();
    }

    @Override
    public void writeRowData(Map<String, Object> rowData) {
        int rowCount = this.sheet.getPhysicalNumberOfRows();

        Row row = this.sheet.createRow(rowCount);

        row.setHeight((short) (13 * 20));

        for (int c = 0; c < this.columnList.length; c++) {
            String name = (String) this.columnList[c].get("name");
            String formatter = (String) this.columnList[c].get("formatter");
            Object value = rowData.get(name);

            Cell cell = row.createCell(c);

            cell.setCellStyle((CellStyle) this.columnList[c].get("cellStyle"));

            if (value == null || "".equals(value)) {
                cell.setCellValue("");
            } else if (StringUtils.equals(name, "rn")) {
                cell.setCellValue(value instanceof Integer ? (Integer) value : Integer.parseInt(value.toString(), 10));
            } else if (StringUtils.equals(formatter, "number") || StringUtils.equals(formatter, "any-number")) {
                cell.setCellValue(ValueConverter.getDouble(value));
            } else if (StringUtils.equals(formatter, "date") || StringUtils.equals(formatter, "any-date")
                    || StringUtils.equals(formatter, "any-datetime")) {
                if (value instanceof Date) {
                    cell.setCellValue(ValueConverter.getDate(value));
                } else {
                    String dateStr = ValueConverter.getString(value);

                    if (dateStr.length() == 8) {
                        try {
                            cell.setCellValue(DateUtils.parseDate(dateStr, "yyyyMMdd"));
                        } catch (ParseException e) {
                            cell.setCellValue(dateStr);
                        }
                    } else {
                        cell.setCellValue(dateStr);
                    }
                }
            } else {
                cell.setCellValue(ValueConverter.getString(value));
            }
        }

        this.addDataCellMergedRegion(row.getRowNum());

        if (rowCount >= Math.pow(2, 20) - 1) {
            this.sheet = this.createSheet();
        }
    }

    @Override
    public void writeEnd(boolean success) {
        this.addDataCellMergedRegion();

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(this.gridRequest.getExcel().getWorkbookFile());
            this.workbook.write(fos);
        } catch (FileNotFoundException e) {
            this.deleteWorkbookFile();
            throw new BaseException(e);
        } catch (IOException e) {
            this.deleteWorkbookFile();
            throw new BaseException(e);
        } finally {
            IOUtil.closeQuietly(fos);

            if (!success) {
                this.deleteWorkbookFile();
            }
        }
    }

    private Sheet createSheet() {
        this.addDataCellMergedRegion();

        Sheet sheet = this.workbook.createSheet("Sheet" + (this.workbook.getNumberOfSheets() + 1));

        CellStyle headerStyle = this.workbook.createCellStyle();
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont headerFont = (XSSFFont) this.workbook.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 9);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        int headerRows = this.headerList.length;
        int headerCols = this.columnList.length;
        Integer colSplit = this.gridRequest.getExcel().getColSplit();

        if (colSplit == null || colSplit <= 0) {
            colSplit = this.getRnColumnIndex() + 1;
        }

        sheet.setDisplayGridlines(false);
        sheet.createFreezePane(colSplit, headerRows);

        for (int r = 0; r < headerRows; r++) {
            Row row = sheet.createRow(r);
            row.setHeight((short) (15 * 20));

            for (int c = 0; c < headerCols; c++) {
                Cell cell = row.createCell(c);
                cell.setCellValue(this.getHeaderName(r, c));
                cell.setCellStyle(headerStyle);
            }
        }

        for (int r = 0; r < headerRows; r++) {
            int mergeStartCol = 0;

            for (int c = 1; c < headerCols; c++) {
                if (StringUtils.equals(this.getHeaderName(r, c), this.getHeaderName(r, mergeStartCol))) {
                    continue;
                }

                if (mergeStartCol != c - 1) {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(r, r, mergeStartCol, c - 1);
                    this.addMergedRegion(sheet, cellRangeAddress);
                }

                mergeStartCol = c;
            }

            if (mergeStartCol != headerCols - 1) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(r, r, mergeStartCol, headerCols - 1);
                this.addMergedRegion(sheet, cellRangeAddress);
            }
        }

        for (int c = 0; c < headerCols; c++) {
            int mergeStartRow = 0;

            for (int r = 1; r < headerRows; r++) {
                if (StringUtils.equals(this.getHeaderName(r, c), this.getHeaderName(mergeStartRow, c))) {
                    continue;
                }

                if (mergeStartRow != r - 1) {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(mergeStartRow, r - 1, c, c);
                    this.addMergedRegion(sheet, cellRangeAddress);
                }

                mergeStartRow = r;
            }

            if (mergeStartRow != headerRows - 1) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(mergeStartRow, headerRows - 1, c, c);
                this.addMergedRegion(sheet, cellRangeAddress);
            }
        }

        for (int c = 0, cc = this.columnList.length; c < cc; c++) {
            sheet.setColumnWidth(c, ((int) this.columnList[c].get("width")) * 35);
            sheet.setColumnHidden(c, (boolean) this.columnList[c].get("hidden"));
        }

        return sheet;
    }

    private void addDataCellMergedRegion(int rowNum) {
        if (this.sheet == null) {
            return;
        }

        Integer colSplit = this.gridRequest.getExcel().getColSplit();

        if (colSplit == null || colSplit <= 1) {
            return;
        }

        if (this.dataCellMergeInfoMap == null) {
            this.dataCellMergeInfoMap = new HashMap<Integer, DataCellMergeInfo>();
        }

        int rnColIndex = this.getRnColumnIndex();
        Row row = this.sheet.getRow(rowNum);

        for (int c = rnColIndex + 1; c < colSplit - 1; c++) {
            String currCellValue = this.getAllCellValue(row, c);

            DataCellMergeInfo dataCellMergeInfo = this.dataCellMergeInfoMap.get(c);

            if (dataCellMergeInfo == null) {
                dataCellMergeInfo = new DataCellMergeInfo(rowNum, currCellValue);
                this.dataCellMergeInfoMap.put(c, dataCellMergeInfo);
            }

            if (StringUtils.equals(currCellValue, dataCellMergeInfo.getMergeValue())) {
                continue;
            }

            if (dataCellMergeInfo.getMergeStartRow() != rowNum - 1) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(dataCellMergeInfo.getMergeStartRow(),
                        rowNum - 1, c, c);
                this.addMergedRegion(this.sheet, cellRangeAddress);
            }

            dataCellMergeInfo.reset(rowNum, currCellValue);
        }
    }

    private void addDataCellMergedRegion() {
        if (this.sheet == null) {
            return;
        }

        Integer colSplit = this.gridRequest.getExcel().getColSplit();

        if (colSplit == null || colSplit <= 1) {
            return;
        }

        if (this.dataCellMergeInfoMap == null) {
            this.dataCellMergeInfoMap = new HashMap<Integer, DataCellMergeInfo>();
        }

        int rnColIndex = this.getRnColumnIndex();

        for (int c = rnColIndex + 1; c < colSplit - 1; c++) {
            DataCellMergeInfo dataCellMergeInfo = this.dataCellMergeInfoMap.get(c);

            if (dataCellMergeInfo.getMergeStartRow() != this.sheet.getLastRowNum()) {
                CellRangeAddress cellRangeAddress = new CellRangeAddress(dataCellMergeInfo.getMergeStartRow(),
                        this.sheet.getLastRowNum(), c, c);
                this.addMergedRegion(this.sheet, cellRangeAddress);
            }
        }
    }

    private String getAllCellValue(Row row, int colIndex) {
        int rnColIndex = this.getRnColumnIndex();
        StringBuilder sb = new StringBuilder();

        for (int i = rnColIndex + 1; i <= colIndex; i++) {
            sb.append("$||$").append(CommonUtil.getExcelCellValue(row.getCell(i))).append("$||$");
        }

        return sb.toString();
    }

    private void addMergedRegion(Sheet sheet, CellRangeAddress cellRangeAddress) {
        if (this.mergedStartCell == null) {
            this.mergedStartCell = new HashSet<String>();
        }

        String startCell = cellRangeAddress.getFirstColumn() + ":" + cellRangeAddress.getFirstRow();

        if (this.mergedStartCell.contains(startCell)) {
            return;
        }

        this.mergedStartCell.add(startCell);

        try {
            sheet.addMergedRegion(cellRangeAddress);
        } catch (RuntimeException e) {
            if (log.isErrorEnabled()) {
                log.warn("엑셀 머지 에러 : {} => {}", cellRangeAddress, ExceptionUtils.getRootCauseMessage(e));
            }
        }
    }
}
