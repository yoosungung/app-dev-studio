function TemplateManageViewService(vue) {

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
    this.read = function(surveyTmplatId, callback) {
        vue.surveyTmplatId = surveyTmplatId;

        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        CommonUtil.axios()
        .get(vue.surveyTmplatId)
        .then(function(response) {
            if (response.data.tbSurveyTmplat.qestnrBcrnColor == null || response.data.tbSurveyTmplat.qestnrBcrnColor == "") {
                response.data.tbSurveyTmplat.qestnrBcrnColor = response.data.backgroundColors[0];
            }

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
     * 저장
     */
    this.update = function() {
        if (!ValidationUtil.check(vue)) {
            return;
        }

        if (!confirm("저장하시겠습니까?")) {
            return;
        }

        CommonUtil.axios()
        [vue.surveyTmplatId == null ? "post" : "put"](vue.surveyTmplatId, vue.model)
        .then(function(response) {
            alert("저장되었습니다.");

            vues.list.service.readList();

            if (vue.surveyTmplatId == null) {
                vue.surveyTmplatId = response.data;
            }

            vue.viewRead();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 수정 - 상태값
     */
    this.updateStatus = function(tmplatSttus) {
        if (tmplatSttus == "0") {
            if (!confirm("템플릿을 \"작성중\" 상태로 변경하시겠습니까?")) {
                return;
            }
        } else if (tmplatSttus == "1") {
            if (!confirm("템플릿을 \"활성\" 상태로 변경하시겠습니까?")) {
                return;
            }
        } else {
            return;
        }

        CommonUtil.axios()
        .put(vue.surveyTmplatId + "?updateStatus", tmplatSttus)
        .then(function(response) {
            alert("변경되었습니다.");

            vue.service.read(vue.surveyTmplatId);
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 삭제
     */
    this.delete = function() {
        if (!confirm("정말로 삭제하시겠습니까?")) {
            return;
        }

        CommonUtil.axios()
        ["delete"](vue.surveyTmplatId)
        .then(function(response) {
            alert("삭제되었습니다.");

            vues.list.service.readList();

            vue.viewList();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

}
