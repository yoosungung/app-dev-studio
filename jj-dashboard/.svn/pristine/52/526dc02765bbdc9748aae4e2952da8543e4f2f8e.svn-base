function RecommendJobViewService(vue) {
    this.readList = function(search) {
        GridUtil.setLoadStart(vue, search);

        GridUtil.axios(vue)
        .post("readList", vue.gridRequest)
        .then(function(response) {
            vue.gridResult = response.data.result;
        })
        ["catch"](function(error) {
            vue.gridResult = GridUtil.showAxiosError(error);
        });
    }
}