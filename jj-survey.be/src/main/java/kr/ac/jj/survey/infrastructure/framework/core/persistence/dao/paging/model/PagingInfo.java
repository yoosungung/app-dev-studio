package kr.ac.jj.survey.infrastructure.framework.core.persistence.dao.paging.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.survey.infrastructure.framework.core.support.lang.StringUtil;

public class PagingInfo implements Serializable {
    private static final long serialVersionUID = 7764039442282572574L;

    private int recordCountPerPage;
    private int pageListCount;
    private int currentPageNo;
    private int totalRecordCount;
    private OrderBy orderBy;

    public PagingInfo() {
        this.recordCountPerPage = 20;
        this.pageListCount = 10;
        this.currentPageNo = 1;
        this.totalRecordCount = -1;
    }

    public int getRecordCountPerPage() {
        return this.recordCountPerPage;
    }

    public void setRecordCountPerPage(int recordCountPerPage) {
        this.recordCountPerPage = recordCountPerPage;
    }

    public int getPageListCount() {
        return this.pageListCount;
    }

    public void setPageListCount(int pageListCount) {
        this.pageListCount = pageListCount;
    }

    public int getCurrentPageNo() {
        return this.currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getTotalRecordCount() {
        return this.totalRecordCount;
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public int getCurrentPageFirstRecordIndex() {
        return this.recordCountPerPage * (this.currentPageNo - 1);
    }

    public int getCurrentPageLastRecordNo() {
        return this.recordCountPerPage * this.currentPageNo;
    }

    public int getTotalPageCount() {
        return this.recordCountPerPage > 0 ? ((this.totalRecordCount - 1) / this.recordCountPerPage) + 1 : -1;
    }

    public int getFirstPageNo() {
        return 1;
    }

    public int getPreviousPageNo() {
        return Math.max(this.getFirstPageNoOnPageList() - 1, 1);
    }

    public int getFirstPageNoOnPageList() {
        return this.pageListCount > 0 ? ((this.currentPageNo - 1) / this.pageListCount) * this.pageListCount + 1 : -1;
    }

    public int getLastPageNoOnPageList() {
        return Math.min(this.getFirstPageNoOnPageList() + this.pageListCount - 1, this.getTotalPageCount());
    }

    public int getNextPageNo() {
        return Math.min(this.getLastPageNoOnPageList() + 1, this.getTotalPageCount());
    }

    public int getLastPageNo() {
        return this.getTotalPageCount();
    }

    public OrderBy getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    public static class OrderBy {
        private Map<String, String> columns;
        private boolean toSnakeCase;

        public Map<String, String> getColumns() {
            return this.columns;
        }

        public void setColumns(Map<String, String> columns) {
            this.columns = columns;
        }

        public boolean isToSnakeCase() {
            return this.toSnakeCase;
        }

        public void setToSnakeCase(boolean toSnakeCase) {
            this.toSnakeCase = toSnakeCase;
        }

        public String toString(String columnNamePrefix, String columnNameSuffix, boolean columnNameToUpperCase) {
            if (this.columns == null) {
                return null;
            }

            StringBuilder sb = new StringBuilder();

            for (Entry<String, String> entry : this.columns.entrySet()) {
                if (StringUtils.containsWhitespace(entry.getKey())) {
                    throw new BaseException("Column name \"" + entry.getKey() + "\" is not valid.");
                }

                if (sb.length() > 0) {
                    sb.append(", ");
                }

                String columnName;

                if (this.toSnakeCase) {
                    columnName = StringUtil.toSnakeCase(entry.getKey());
                } else {
                    columnName = entry.getKey();
                }

                if (columnNameToUpperCase) {
                    columnName = columnName.toUpperCase();
                }

                sb.append(columnNamePrefix + columnName + columnNameSuffix + " " + entry.getValue().toUpperCase());
            }

            return sb.toString();
        }
    }
}
