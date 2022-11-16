function AuthorityHierarchyManageViewService(vue) {
    var serviceUrl = "/admin/sysmanage/authormanage/AuthorityHierarchyManage/";

    /**
     * 조회
     */
    this.read = function() {
        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        CommonUtil.axios()
        .get(serviceUrl)
        .then(function(response) {
            vue.model = response.data;
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
        .put(serviceUrl, vue.model)
        .then(function(response) {
            alert("저장되었습니다.");

            vue.closePopup();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

}
