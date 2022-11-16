function SurveyResultViewService(vue) {

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
    this.read = function(surveyQestnrId, callback) {
        vue.surveyQestnrId = surveyQestnrId;

        CommonUtil.initModel(vue.model);

        CommonUtil.axios()
        .get(vue.surveyQestnrId)
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
     * 엑셀 다운로드
     */
    this.downloadExcel = function() {
        CommonUtil.axios()
        .post(vue.surveyQestnrId + "?createExcel", null)
        .then(function(response) {
            CommonUtil.downloadTempFile(response.data, vue.model.jdSurveyDefinition.name + ".xlsx");
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

}
