package kr.ac.jj.shared.infrastructure.grid.handler;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.grid.model.GridExcelColumn;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest.ExcelDownload;
import kr.ac.jj.shared.infrastructure.util.BaseUtil;

public abstract class GridDataWriterToExcel extends GridDataWriter {

    protected BaseMap[] headerList;
    protected GridExcelColumn[] columnList;

    public GridDataWriterToExcel(GridRequest gridRequest) {
        super(gridRequest);

        ExcelDownload excel = this.gridRequest.getExcel();

        this.headerList = excel.getHeaderList();
        this.columnList = excel.getColumnList();

        File tempFile = BaseUtil.getTempFile();

        HttpServletResponse response = RequestContextUtil.getResponse();
        response.setHeader("X-Grid-Excel-File-Name", tempFile.getName());

        excel.setWorkbookFile(tempFile);
    }

    protected void deleteWorkbookFile() {
        ExcelDownload excel = this.gridRequest.getExcel();

        if (excel != null && excel.getWorkbookFile() != null) {
            excel.getWorkbookFile().delete();
        }
    }

    protected String getHeaderName(int headerRowIndex, int columnIndex) {
        return (String) this.headerList[headerRowIndex].get(this.columnList[columnIndex].getName());
    }

    protected int getRnColumnIndex() {
        int rnColIndex = -1;

        for (BaseMap header : this.headerList) {
            ArrayList<String> headerKeyList = new ArrayList<String>(header.keySet());

            rnColIndex = Math.max(rnColIndex, headerKeyList.indexOf("rn"));
        }

        return rnColIndex;
    }

}
