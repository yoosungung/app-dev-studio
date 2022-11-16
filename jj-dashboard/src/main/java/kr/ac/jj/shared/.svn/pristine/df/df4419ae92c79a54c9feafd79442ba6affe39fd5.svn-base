package kr.ac.jj.shared.infrastructure.util;

import org.apache.poi.ss.usermodel.Cell;

/**
 * 엑셀 Util
 */
public class ExcelUtil {

    /**
     * Cell 값 반환
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        String value;

        switch (cell.getCellTypeEnum()) {
        case FORMULA:
            value = cell.getCellFormula();
            break;
        case NUMERIC:
            value = cell.getNumericCellValue() + "";
            break;
        case STRING:
            value = cell.getStringCellValue();
            break;
        case BOOLEAN:
            value = cell.getBooleanCellValue() + "";
            break;
        case ERROR:
            value = cell.getErrorCellValue() + "";
            break;
        case BLANK:
            value = "";
            break;
        default:
            value = cell.getStringCellValue();
            break;
        }

        if (value == null) {
            return null;
        }

        return value.trim();
    }

}
