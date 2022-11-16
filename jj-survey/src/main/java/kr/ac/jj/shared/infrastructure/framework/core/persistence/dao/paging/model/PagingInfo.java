package kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.lang.StringUtil;

public class PagingInfo implements Serializable {

    private static final long serialVersionUID = 7523519352063635399L;

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
        if (this.currentPageNo <= 0) {
            return 0;
        }

        return this.recordCountPerPage * (this.currentPageNo - 1);
    }

    public int getCurrentPageLastRecordNo() {
        if (this.currentPageNo <= 0) {
            return 1;
        }

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
        if (this.currentPageNo <= 0) {
            return 1;
        }

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

        private ColumnList columnList;
        private boolean toSnakeCase;

        public ColumnList getColumnList() {
            return this.columnList;
        }

        public void setColumnList(ColumnList columnList) {
            this.columnList = columnList;
        }

        public boolean isToSnakeCase() {
            return this.toSnakeCase;
        }

        public void setToSnakeCase(boolean toSnakeCase) {
            this.toSnakeCase = toSnakeCase;
        }

        public void addColumn(String columnName, String sortDirection) {
            if (this.columnList == null) {
                this.columnList = new ColumnList();
            }

            this.columnList.add(columnName, sortDirection);
        }

        public String toString(String columnNamePrefix, String columnNameSuffix, boolean columnNameToUpperCase) {
            if (this.getColumnList() == null) {
                return null;
            }

            StringBuilder sb = new StringBuilder();

            for (OrderByColumn orderByColumn : this.getColumnList()) {
                if (StringUtils.containsWhitespace(orderByColumn.getColumnName())) {
                    throw new BaseException("Column name \"" + orderByColumn.getColumnName() + "\" is not valid.");
                }

                if (sb.length() > 0) {
                    sb.append(", ");
                }

                String columnName;

                if (this.toSnakeCase) {
                    columnName = StringUtil.toSnakeCase(orderByColumn.getColumnName());
                } else {
                    columnName = orderByColumn.getColumnName();
                }

                if (columnNameToUpperCase) {
                    columnName = columnName.toUpperCase();
                }

                String sortDirection = orderByColumn.getSortDirection().toUpperCase();

                sb.append(columnNamePrefix + columnName + columnNameSuffix + " " + sortDirection);
            }

            return sb.toString();
        }

        public static class ColumnList extends ArrayList<OrderByColumn> {

            private static final long serialVersionUID = 8727137170059007110L;

            public void add(String columnName, String sortDirection) {
                OrderByColumn orderByColumn = new OrderByColumn();
                orderByColumn.setColumnName(columnName);
                orderByColumn.setSortDirection(sortDirection);

                this.add(orderByColumn);
            }

        }

        public static class OrderByColumn {

            private String columnName;
            private String sortDirection;

            public String getColumnName() {
                return this.columnName;
            }

            public void setColumnName(String columnName) {
                this.columnName = columnName;
            }

            public String getSortDirection() {
                return this.sortDirection;
            }

            public void setSortDirection(String sortDirection) {
                this.sortDirection = sortDirection;
            }

        }

    }

    public PagingInfo setPageNo(int currentPageNo) {
        this.setCurrentPageNo(currentPageNo);

        return this;
    }

    public PagingInfo newPageNo(int currentPageNo) {
        PagingInfo pagingInfo = new PagingInfo();
        pagingInfo.setTotalRecordCount(this.getTotalRecordCount());
        pagingInfo.setRecordCountPerPage(this.getRecordCountPerPage());
        pagingInfo.setPageListCount(this.getPageListCount());
        pagingInfo.setCurrentPageNo(currentPageNo);

        return pagingInfo;
    }

}
