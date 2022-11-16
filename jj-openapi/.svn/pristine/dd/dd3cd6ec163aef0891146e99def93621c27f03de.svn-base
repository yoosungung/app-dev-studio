function UserLoginViewService(vue) {

    /**
     * 로그인
     */
    this.login = function(loginTy, username) {
        var data;
        var config;

        if (loginTy == null) {
            if (!ValidationUtil.check(vue)) {
                return;
            }

            data = new FormData();
            data.append("loginNm", vue.model.loginNm);
            data.append("loginPassword", vue.model.loginPassword);
        } else {
            config = {
                headers: {
                    loginTy: loginTy,
                    username: username
                }
            };
        }

        CommonUtil.axios()
        .post("/common/user/login.do", data, config)
        .then(function(response) {
            if (response.data == "UsernameEmptyValueException") {
                vue.viewLogin();
            } else if (response.data == "UsernameNotFoundException") {
                vue.viewLogin();
            } else if (window.location.pathname != vue.url.loginPath) {
                window.location.reload();
            } else if (vue.url.targetUrl != "") {
                window.location.replace(vue.url.targetUrl);
            } else {
                window.location.replace(CommonUtil.contextPath + "/");
            }
        })
        ["catch"](function(error) {
            if (loginTy == null) {
                CommonUtil.showAxiosError(error);
            } else {
                vue.viewLogin();
            }
        });
    }

}
