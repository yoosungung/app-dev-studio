function MainViewService(vue) {

    /**
     * 공지사항 목록 조회
     */
    this.readNoticeList = function() {
        CommonUtil.axios(vue)
        .get("readNoticeList.do", {})
        .then(function(response) {
            vue.noticeList = response.data;
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * Q&A 목록 조회
     */
    this.readQnaList = function() {
        CommonUtil.axios(vue)
        .get("readQnaList.do", {})
        .then(function(response) {
            vue.qnaList = response.data;
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * FAQ 목록 조회
     */
    this.readFaqList = function() {
        CommonUtil.axios(vue)
        .get("readFaqList.do", {})
        .then(function(response) {
            vue.faqList = response.data;
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 사용중 배너 목록 조회
     */
    this.readBannerList = function() {
        CommonUtil.axios(vue)
        .get("readBannerList.do", {})
        .then(function(response) {
            $('#slides').empty();

            var bannerList = response.data;

            if (bannerList.length == 1) {
                var filePath = bannerList[0];
                $('#slide_container').append('<img id=\"single_banner\" src=\"' + filePath + '\">');
            } else if (bannerList.length > 1) {
                for (let i=0; i<bannerList.length; i++) {
                    var filePath = bannerList[i];
                    $('#slides').append('<div><img src=\"' + filePath + '\"></div>');
                }

                $('#slides').slidesjs({
                    width: 1294,
                    height: 364,
                    play: {
                        active: true,
                        auto: true,
                        interval: 4000,
                        swap: true
                    }
                });
            }
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };
}
