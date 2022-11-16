var GridUtil = {};

GridUtil.globalProps = {
    paging: {
        recordCountPerPageList: [10, 20, 50, 100, 200],
        recordCountPerPage: 20,
        pageListCount: 10
    }
};

GridUtil.getRequestWithPaging = function(search, orderByColumns) {
    var gridRequest = {};

    if (search == null) {
        gridRequest.search = {};
    } else {
        gridRequest.search = search;
    }

    gridRequest.paging = {
        recordCountPerPage: GridUtil.globalProps.paging.recordCountPerPage,
        pageListCount: GridUtil.globalProps.paging.pageListCount,
        currentPageNo: 1,
        orderBy: {
            columns: orderByColumns,
            toSnakeCase: true
        }
    };

    return gridRequest;
};

GridUtil.setLoadStart = function(vue, firstPage) {
    if (vue.gridRequest.excel == null) {
        vue.gridResult = { loadStarted: true };

        if (firstPage === true) {
            vue.gridRequest.paging.currentPageNo = 1;
        }
    }
};

GridUtil.loadData = function(vue, result) {
    if (vue.gridRequest.excel == null) {
        vue.gridResult = result;
    } else {
        vue.gridRequest.excel = null;
    }
};

GridUtil.setError = function(vue, error) {
    if (vue.gridRequest.excel == null) {
        vue.gridResult = { error: true };
    } else {
        vue.gridRequest.excel = null;
    }
};
