function BbsGeneralViewService(vue) {

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
    this.read = function(bbscttId, updateRdcnt, callback) {
        vue.bbscttId = bbscttId;

        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        CommonUtil.axios()
        .get(vue.bbscttId == null ? null : vue.bbscttId + "?updateRdcnt=" + updateRdcnt)
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
     * 조회 - 원본글(답글 작성용)
     */
    this.readForReply = function(bbscttId, callback) {
        vue.bbscttId = bbscttId;

        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        CommonUtil.axios()
        .get(vue.bbscttId)
        .then(function(response) {
            vue.model = response.data;

            vue.model.tbBbsGnrl.bbscttSj = "RE: " + vue.model.tbBbsGnrl.bbscttSj;
            vue.model.tbBbsGnrl.bbscttCn = '<p/><p/><blockquote>' + vue.model.tbBbsGnrl.bbscttCn + '</blockquote>';

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

        if (!confirm("등록하시겠습니까?")) {
            return;
        }

        vue.model.reply = vue.reply;

        CommonUtil.uploadFiles(vue, function() {
            CommonUtil.axios()
            [vue.bbscttId == null || vue.reply == true ? "post" : "put"](vue.bbscttId, vue.model)
            .then(function(response) {
                alert("등록되었습니다.");

                if (vue.bbscttId == null || vue.reply == true) {
                    vue.bbscttId = response.data;
                }

                vue.viewRead();
            })
            ["catch"](function(error) {
                CommonUtil.showAxiosError(error);
            });
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
        ["delete"](vue.bbscttId)
        .then(function(response) {
            alert("삭제되었습니다.");

            vue.viewList();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

}
