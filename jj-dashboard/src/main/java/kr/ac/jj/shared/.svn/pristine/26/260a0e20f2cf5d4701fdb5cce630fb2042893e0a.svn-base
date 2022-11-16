package kr.ac.jj.shared.infrastructure.grid.model;

import java.io.File;
import java.util.List;

import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMap;

public class GridRequest {

    private String progressKey;
    private PagingInfo paging;
    private BaseMap search;
    private ExcelDownload excel;

    public String getProgressKey() {
        return this.progressKey;
    }

    public void setProgressKey(String progressKey) {
        this.progressKey = progressKey;
    }

    public PagingInfo getPaging() {
        return this.paging;
    }

    public void setPaging(PagingInfo paging) {
        this.paging = paging;
    }

    public void newPaging() {
        this.setPaging(new PagingInfo());
    }

    public BaseMap getSearch() {
        return this.search;
    }

    public void setSearch(BaseMap search) {
        this.search = search;
    }

    public ExcelDownload getExcel() {
        return this.excel;
    }

    public void setExcel(ExcelDownload excel) {
        this.excel = excel;
    }

    public static class ExcelDownload {

        private Boolean download;
        private BaseMap[] headerList;
        private GridExcelColumn[] columnList;
        private List<BaseMap> dataList;
        private Integer colSplit;
        private File workbookFile;

        public Boolean getDownload() {
            return this.download;
        }

        public void setDownload(Boolean download) {
            this.download = download;
        }

        public BaseMap[] getHeaderList() {
            return this.headerList;
        }

        public void setHeaderList(BaseMap[] headerList) {
            this.headerList = headerList;
        }

        public GridExcelColumn[] getColumnList() {
            return this.columnList;
        }

        public void setColumnList(GridExcelColumn[] columnList) {
            this.columnList = columnList;
        }

        public List<BaseMap> getDataList() {
            return this.dataList;
        }

        public void setDataList(List<BaseMap> dataList) {
            this.dataList = dataList;
        }

        public Integer getColSplit() {
            return this.colSplit;
        }

        public void setColSplit(Integer colSplit) {
            this.colSplit = colSplit;
        }

        public File getWorkbookFile() {
            return this.workbookFile;
        }

        public void setWorkbookFile(File workbookFile) {
            this.workbookFile = workbookFile;
        }

    }

}
