function BbsFaqViewService(vue) {

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
        [vue.bbscttId == null ? "post" : "put"](vue.bbscttId, vue.model)
        .then(function(response) {
            alert("저장되었습니다.");

            vues.list.service.readList();

            if (vue.bbscttId == null) {
                vue.bbscttId = response.data;
            }
            vue.viewRead();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /*
     * 조회
     */
    this.read = function(bbscttId, callback) {
        vue.bbscttId = bbscttId;

        CommonUtil.initModel(vue.model);
        CommonUtil.initValid();

        CommonUtil.axios()
        .get(vue.bbscttId)
        .then(function(response) {
            if (vue.bbscttId == null) {
                return;
                //response.data.tbSysCodeValueList.push(CommonUtil.setJobType({ useYn: true, sortOrdr: 1 }, "C"));
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

            vues.list.service.readList();

            vue.viewList();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };



}
