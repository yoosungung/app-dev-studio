function ServiceGuideViewFormEditService(vue) {

    /**
     * 조회
     */
    this.read = function(callback) {
        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        CommonUtil.axios()
        .get("read")
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
        .put("update", vue.model)
        .then(function(response) {
            alert("저장되었습니다.");
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };
}
