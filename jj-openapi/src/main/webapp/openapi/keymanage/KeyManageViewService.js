function KeyManageViewService(vue) {

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
    this.read = function(svcKeyId, callback) {
        vue.svcKeyId = svcKeyId;

        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        CommonUtil.axios()
        .get(vue.svcKeyId)
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
     * 삭제
     */
    this.delete = function(svcKeyId) {
        CommonUtil.axios()
        ["delete"](svcKeyId)
        .then(function(response) {
            alert("삭제되었습니다.");
            vues.list.service.readList();
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

        if (vue.model.tbApiSvcKey.keyUsePdBegin > vue.model.tbApiSvcKey.keyUsePdEnd) {
            alert("사용기간을 확인하십시오.");
            return;
        }

        if (!confirm(vue.examinationModeName + "하시겠습니까?")) {
            return;
        }

        vue.model.tbApiSvcKey.sttus = vue.examinationMode;

        CommonUtil.axios()
        [vue.svcKeyId == null ? "post" : "put"](vue.svcKeyId, vue.model)
        .then(function(response) {
            alert(vue.examinationModeName + "되었습니다.");

            vues.list.service.readList();

            if (vue.svcKeyId == null) {
                vue.svcKeyId = response.data;
            }

            vue.viewRead();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 메일 발송
     */
    this.sendMail = function(svcKeyId) {
        if (!confirm("메일 발송하시겠습니까?")) {
            return;
        }

        var tbApiSvcKey = {};
        tbApiSvcKey.svcKeyId = svcKeyId;

        CommonUtil.axios()
        .put(vue.surveyQestnrId + "?sendMail", tbApiSvcKey)
        .then(function(response) {
            alert("발송 되었습니다.");
            vue.service.readList();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

}
