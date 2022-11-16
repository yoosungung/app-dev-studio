package kr.ac.jj.survey.infrastructure.grid.model;

import java.io.File;
import java.util.List;

import kr.ac.jj.survey.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMap;

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
        private String downloadScope;
        private BaseMap[] headerList;
        private BaseMap[] columnList;
        private List<BaseMap> dataList;
        private Integer colSplit;
        private File workbookFile;

        public Boolean getDownload() {
            return this.download;
        }

        public void setDownload(Boolean download) {
            this.download = download;
        }

        public String getDownloadScope() {
            return this.downloadScope;
        }

        public void setDownloadScope(String downloadScope) {
            this.downloadScope = downloadScope;
        }

        public BaseMap[] getHeaderList() {
            return this.headerList;
        }

        public void setHeaderList(BaseMap[] headerList) {
            this.headerList = headerList;
        }

        public BaseMap[] getColumnList() {
            return this.columnList;
        }

        public void setColumnList(BaseMap[] columnList) {
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
