function BannerManageViewService(vue) {

    /**
     * 목록 조회
     */
    this.readList = function() {
        CommonUtil.axios()
        .post("readList",{})
        .then(function(listResponse) {
            vue.bannerList = listResponse.data;

            CommonUtil.axios()
            .post("readAtchFile", vue.bannerList)
            .then(function(response) {
                vue.bannerList = response.data;
            })
            ["catch"](function(error) {
                CommonUtil.showAxiosError(error);
            });
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 추가
     */
    this.create = function() {
        if (!ValidationUtil.check(vue)) {
            return;
        }
        if (!confirm("등록하시겠습니까?")) {
            return;
        }

        CommonUtil.uploadFiles(vue, function() {
            CommonUtil.axios()
            .post("create", vue.model)
            .then(function(response) {
                vue.model.tbApiBanner = {};
                vue.model.tbApiBanner.atchFileList = [];
                alert("등록되었습니다.");
                vue.service.readList();
            })
            ["catch"](function(error) {
                CommonUtil.showAxiosError(error);
            });
        });
    };

    /**
     * 목록 삭제
     */
    this.deleteList = function() {
        var bannerIdList = [];

        for (let i=0; i<vue.bannerList.length; i++) {
            if (vue.bannerList[i]._rowCheck_ == true) {
                bannerIdList.push(vue.bannerList[i].bannerId);
            }
        }
        if (bannerIdList.length == 0) {
            alert("선택된 항목이 없습니다.");
            return;
        }
        if (!confirm("삭제하시겠습니까?")) {
            return;
        }

        CommonUtil.axios()
        .post("deleteList", bannerIdList)
        .then(function(response) {
            alert("삭제되었습니다.");
            vue.service.readList();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };


    /**
     * 사용여부 업데이트
     */
    this.update = function(banner) {
        CommonUtil.axios()
        .post("useUpdate", banner)
        .then(function(response) {
            alert("수정되었습니다.");
            vue.service.readList();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };



}
