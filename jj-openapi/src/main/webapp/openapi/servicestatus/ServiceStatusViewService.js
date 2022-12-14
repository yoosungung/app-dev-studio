function ServiceStatusViewService(vue) {

    /**
     * 목록 조회
     */
    this.readList = function(firstPage) {
        vue.gridResult = GridUtil.setLoadStart(vue.gridRequest, firstPage);

        GridUtil.axios(vue)
        .post("readList", vue.gridRequest)
        .then(function(response) {
            vue.gridResult = response.data.result;
        })
        ["catch"](function(error) {
            vue.gridResult = GridUtil.showAxiosError(error);
        });
    };

    /**
     * 조회
     */
    this.read = function(svcId, callback) {
        vue.svcId = svcId;
        vue.apiRequest = null;
        vue.apiResult = null;

        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        CommonUtil.axios()
        .get(vue.svcId)
        .then(function(response) {
            vue.model = response.data;

            if (callback != null) {
                callback(vue.model);
            }
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * API 요청
     */
    this.api = function(callback) {
         if (!ValidationUtil.check(vue)) {
             return;
         }

        var apiUrl = "/apilist/ApiList";
        vue.apiRequest = { apiKey: null, dataFormat: null, paging: {} , search: {} };
        vue.apiResult = null;

        for (var i = 0; i < vue.model.tbApiSvcMapngList.length; i++) {
            var tbApiSvcMapng = vue.model.tbApiSvcMapngList[i];

            if (tbApiSvcMapng.vriabl == "apiKey") {
                vue.apiRequest.apiKey = tbApiSvcMapng.inputVal;
            } else if (tbApiSvcMapng.vriabl == "currentPageNo" || tbApiSvcMapng.vriabl == "recordCountPerPage") {
                vue.apiRequest.paging[tbApiSvcMapng.vriabl] = tbApiSvcMapng.inputVal;
            } else if (tbApiSvcMapng.vriabl == "dataFormat") {
                vue.apiRequest.dataFormat = tbApiSvcMapng.inputVal;
            } else {
                vue.apiRequest.search[tbApiSvcMapng.vriabl] = tbApiSvcMapng.inputVal;
            }
        }

        CommonUtil.axios()
        ["post"](apiUrl + vue.model.tbApiSvc.url, vue.apiRequest)
        .then(function(response) {
            vue.apiResult = response.data;
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 다운로드
     */
    this.download = function() {
        if (!ValidationUtil.check(vue)) {
            return;
        }

        var apiUrl = "/apilist/ApiList";
        vue.apiRequest = { apiKey: null, dataFormat: null, paging: {} , search: {} };

        for (var i = 0; i < vue.model.tbApiSvcMapngList.length; i++) {
            var tbApiSvcMapng = vue.model.tbApiSvcMapngList[i];

            if (tbApiSvcMapng.vriabl == "apiKey") {
                vue.apiRequest.apiKey = tbApiSvcMapng.inputVal;
            } else if (tbApiSvcMapng.vriabl == "currentPageNo" || tbApiSvcMapng.vriabl == "recordCountPerPage") {
                vue.apiRequest.paging[tbApiSvcMapng.vriabl] = tbApiSvcMapng.inputVal;
            } else if (tbApiSvcMapng.vriabl == "dataFormat") {
                vue.apiRequest.dataFormat = tbApiSvcMapng.inputVal;
            } else {
                vue.apiRequest.search[tbApiSvcMapng.vriabl] = tbApiSvcMapng.inputVal;
            }
        }

        CommonUtil.axios()
        ["post"](apiUrl + vue.model.tbApiSvc.url + "?download", vue.apiRequest)
        .then(function(response) {
            CommonUtil.downloadTempFile(response.data, vue.model.tbApiSvc.svcNm + ".txt");
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

}
