var GridUtil = {};

GridUtil.globalProps = {
    paging: {
        recordCountPerPageList: [10, 20, 50, 100, 200],
        recordCountPerPage: 10,
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
    vue.gridResult = { loadStarted: true };

    if (firstPage === true) {
        vue.gridRequest.paging.currentPageNo = 1;
    }
};

GridUtil.loadData = function(vue, result) {
    vue.gridResult = result;
};

GridUtil.axios = function(vue) {
    var axios = CommonUtil.axios();

    axios.post_origin = axios.post;

    axios.post = function(url, data, config) {
        var $button = $(vue.$el).find('[name="excelDownloadButton"]');

        $button.data("grid-load-info", JSON.stringify({ url: url, data: data, config: config }));

        var promise = axios.post_origin(url, data, config);

        return promise;
    }

    return axios;
};

GridUtil.showAxiosError = function(vue, error) {
    CommonUtil.showAxiosError(error);

    vue.gridResult = { error: true };
};

GridUtil.downloadExcel = function(vue, downloadScope) {
    var $button = $(vue.$el).find('[name="excelDownloadButton"]');

    if ($button.data("grid-load-info") == null) {
        popClose();
        return;
    }

    var loadInfo = JSON.parse($button.data("grid-load-info"));
    var $table = $(vue.$el).next('table');
    var $thead = $table.children('thead');
    var $tbody = $table.children('tbody');

    if (downloadScope == "2") {
        loadInfo.data.paging.recordCountPerPage = -1;
    }

    loadInfo.data.excel = { download: true };

    loadInfo.data.excel.headerList = (function() {
        var headerList = [];

        $thead.children('tr').each(function() {
            var header = {};

            $(this).children('th').each(function() {
                var $th = $(this);

                header[$th.data("column-name")] = $th.text();
            });

            headerList.push(header);
        });

        return headerList;
    })();

    loadInfo.data.excel.columnList = (function() {
        var columnList = [];
        var $tds = $tbody.children('tr:first').children('th,td');

        $thead.children('tr:last').children('th').each(function() {
            var $th = $(this);
            var column = {};

            column.name = $th.data("column-name");

            if (column.name == null || column.name == "") {
                return true;
            }

            column.width = $th.width();
            column.align = $tds.eq($th.index()).css("text-align");

            columnList.push(column);
        });

        return columnList;
    })();

    if (loadInfo.data.excel.columnList.length == 0) {
        alert("다운로드할 컬럼이 정의되지 않았습니다.");
        return;
    }

    CommonUtil.axios()
    .post(loadInfo.url, loadInfo.data, loadInfo.config)
    .then(function(response) {
        var excelFileName = response.headers["x-grid-excel-file-name"];

        CommonUtil.downloadTempFile(excelFileName, $('.subContent > h2').text() + ".xlsx");

        popClose();
    })
    ["catch"](function(error) {
        CommonUtil.showAxiosError(error);
    });
};
