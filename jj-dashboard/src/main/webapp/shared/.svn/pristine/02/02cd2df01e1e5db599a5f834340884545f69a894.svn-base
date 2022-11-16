var GridUtil = {};

GridUtil.globalProps = {
    paging: {
        recordCountPerPageList: [10, 20, 50, 100, 200],
        recordCountPerPage: 10,
        pageListCount: 10
    }
};

GridUtil.getRequestWithPaging = function(search, orderByColumns) {
    var request = {};

    if (search == null) {
        request.search = {};
    } else {
        request.search = search;
    }

    request.paging = {
        recordCountPerPage: GridUtil.globalProps.paging.recordCountPerPage,
        pageListCount: GridUtil.globalProps.paging.pageListCount,
        currentPageNo: 1,
        orderBy: {
            columnList: (function() {
                if (orderByColumns == null) {
                    return null;
                }

                var orderByList = [];

                for (var columnName in orderByColumns) {
                    orderByList.push({
                        columnName: columnName,
                        sortDirection: orderByColumns[columnName]
                    });
                }

                return orderByList;
            })(),
            toSnakeCase: true
        }
    };

    return request;
};

GridUtil.setLoadStart = function(request, firstPage) {
    if (firstPage === true && request.paging != null) {
        request.paging.currentPageNo = 1;
    }

    return { loadStarted: true };
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

GridUtil.showAxiosError = function(error) {
    CommonUtil.showAxiosError(error);

    return { error: true };
};

GridUtil.downloadExcel = function(vue, downloadScope) {
    var $button = $(vue.$el).find('[name="excelDownloadButton"]');

    if ($button.data("grid-load-info") == null) {
        popClose();
        return;
    }

    var loadInfo = JSON.parse($button.data("grid-load-info"));
    var $table = $(vue.$el).children('table');
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

                header[$th.attr("column-name")] = $.trim($th.text());
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

            column.name = $th.attr("column-name");

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

        var title = $(vue.$el).closest('.pageContents').siblings('.pageHeader').contents().filter(function() {
            return this.nodeType === 3 && $.trim(this.nodeValue) !== "";
        }).text();

        CommonUtil.downloadTempFile(excelFileName, $.trim(title) + ".xlsx");

        $('#excelDownloadPopup').dialog("close");
    })
    ["catch"](function(error) {
        CommonUtil.showAxiosError(error);
    });
};
