function DepartmentManageViewService(vue) {

    /**
     * 트리 조회
     */
    this.readTree = function() {
        var args = arguments;

        CommonUtil.axios()
        .get("readTree")
        .then(function(response) {
            response.data.splice(0, 0, vue.tbComDeptRoot);

            vues.list.$refs.deptTree.saveStatus();

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
     * 조회
     */
    this.read = function(deptId, callback) {
        vue.deptId = deptId;

        CommonUtil.initModel(vue.model);

        if (vue.deptId == null) {
            vue.model.tbComDept = vue.tbComDeptRoot;

            if (callback != null) {
                callback(vue.model);
            }

            return;
        }

        CommonUtil.axios()
        .get(vue.deptId)
        .then(function(response) {
            vue.model = response.data;

            if (vue.model.tbComDept.upperDeptId == null) {
                vue.model.tbComDept.upperDeptNm = vues.list.tbComDeptRoot.deptNm;
            }

            if (vue.deptId != null) {
                vues.list.$refs.deptTree.updateItemData(vue.model.tbComDept);
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
        .put(vue.deptId, vue.model)
        .then(function(response) {
            alert("저장되었습니다.");

            vues.list.service.readTree(vues.list.deptId);

            $(vue.$el).dialog("close");
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

}
