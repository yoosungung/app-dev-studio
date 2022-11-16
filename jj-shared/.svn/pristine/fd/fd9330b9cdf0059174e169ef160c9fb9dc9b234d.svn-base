function CodeManageViewService(vue) {

    /**
     * 목록 조회
     */
    this.readList = function(firstPage) {
        GridUtil.setLoadStart(vue, firstPage);

        GridUtil.axios(vue)
        .post("readList", vue.gridRequest)
        .then(function(response) {
            GridUtil.loadData(vue, response.data.result);
        })
        ["catch"](function(error) {
            GridUtil.showAxiosError(vue, error);
        });
    };

    /**
     * 조회
     */
    this.read = function(codeGroupId, callback) {
        vue.codeGroupId = codeGroupId;

        CommonUtil.initModel(vue.model);
        CommonUtil.initValid();

        CommonUtil.axios()
        .get(vue.codeGroupId)
        .then(function(response) {
            if (vue.codeGroupId == null) {
                response.data.tbSysCodeValueList.push(CommonUtil.setJobType({ useYn: true, sortOrdr: 1 }, "C"));
            }

            vue.model = response.data;

            if (callback != null) {
                callback(response.data);
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
        if (!CommonUtil.checkValid()) {
            return;
        }

        if (!confirm("저장하시겠습니까?")) {
            return;
        }

        CommonUtil.axios()
        [vue.codeGroupId == null ? "post" : "put"](vue.codeGroupId, vue.model)
        .then(function(response) {
            alert("저장되었습니다.");

            vues.list.service.readList();

            if (vue.codeGroupId == null) {
                vue.codeGroupId = response.data;
            }

            vue.viewRead();
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
        ["delete"](vue.codeGroupId)
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
