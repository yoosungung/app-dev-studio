function MenuManageViewService(vue) {

    /**
     * 트리 조회
     */
    this.readTree = function() {
        var args = arguments;

        CommonUtil.axios()
        .get("readTree?menuKnd=" + vue.menuKnd)
        .then(function(response) {
            response.data.splice(0, 0, vue.tbSysMenuRoot);

            vues.list.$refs.menuTree.saveStatus();

            vue.treeData = response.data;

            if (args.length > 0) {
                vue.service.read(args[0]);
            }
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 트리 조회 - 상위 메뉴 선택용
     */
    this.readTreeForUpperMenuSelect = function() {
        CommonUtil.axios()
        .get("readTreeForUpperMenuSelect?menuKnd=" + vues.list.menuKnd + "&maxLevel=" + (vues.list.maxLevel - 1))
        .then(function(response) {
            response.data.splice(0, 0, vue.tbSysMenuRoot);

            vue.treeData = response.data;
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 조회 - 생성용
     */
    this.readForCreate = function(upperMenuId, callback) {
        vue.menuId = null;
        vue.upperMenuId = upperMenuId;

        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        CommonUtil.axios()
        .get("?upperMenuId=" + (vue.upperMenuId == null ? "" : vue.upperMenuId))
        .then(function(response) {
            vue.model = response.data;

            if (vue.model.tbSysMenu.menuKnd == null) {
                vue.model.tbSysMenu.menuKnd = vues.list.menuKnd;
            }

            if (vue.model.tbSysMenu.upperMenuId == null) {
                vue.model.tbSysMenu.upperMenuNm = vues.list.tbSysMenuRoot.menuNm;
            }

            if (callback != null) {
                callback(vue.model);
            }
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 조회
     */
    this.read = function(menuId, callback) {
        vue.menuId = menuId;

        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        if (vue.menuId == null) {
            vue.model.tbSysMenu = vue.tbSysMenuRoot;

            if (callback != null) {
                callback(vue.model);
            }

            return;
        }

        CommonUtil.axios()
        .get(vue.menuId)
        .then(function(response) {
            vue.model = response.data;

            if (vue.model.tbSysMenu.upperMenuId == null) {
                vue.model.tbSysMenu.upperMenuNm = vues.list.tbSysMenuRoot.menuNm;
            }

            if (vue.menuId != null) {
                vues.list.$refs.menuTree.updateItemData(vue.model.tbSysMenu);
            }

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
    this.update = function(callback) {
        if (!ValidationUtil.check(vue)) {
            return;
        }

        if (!confirm("저장하시겠습니까?")) {
            return;
        }

        CommonUtil.axios()
        [vue.menuId == null ? "post" : "put"](vue.menuId, vue.model)
        .then(function(response) {
            alert("저장되었습니다.");

            vues.list.service.readTree(vues.list.menuId);

            $(vue.$el).dialog("close");
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 수정 - 상위 메뉴
     */
    this.updateUpperMenu = function(upperMenuId, callback) {
        if (!confirm("상위메뉴를 변경하시겠습니까?")) {
            return;
        }

        var tbSysMenu = {};
        tbSysMenu.menuId = vue.menuId
        tbSysMenu.upperMenuId = upperMenuId;

        CommonUtil.axios()
        .put(vue.menuId + "?upperMenuId", tbSysMenu)
        .then(function(response) {
            alert("저장되었습니다.");

            vues.list.service.readTree(vue.menuId);

            $(vue.$el).dialog("close");
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
        ["delete"](vue.menuId)
        .then(function(response) {
            alert("삭제되었습니다.");

            vues.list.service.readTree(vue.model.tbSysMenu.upperMenuId);
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 메뉴 순서 목록 저장
     */
    this.updateMenuOrdrList = function(tbSysMenuList) {
        if (tbSysMenuList == null) {
            return;
        }

        CommonUtil.axios()
        .put("updateMenuOrdrList", tbSysMenuList)
        .then(function(response) {
            vues.list.service.readTree();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

}
