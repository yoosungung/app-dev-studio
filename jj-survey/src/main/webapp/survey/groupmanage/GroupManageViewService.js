function GroupManageViewService(vue) {

    /**
     * 목록 조회
     */
    this.readList = function(firstPage) {
        vue.gridResult = GridUtil.setLoadStart(vue.gridRequest, firstPage);

        GridUtil.axios(vue)
        .post("readList", vue.gridRequest)
        .then(function(response) {
            vue.gridResult = response.data.result;
        })
        ["catch"](function(error) {
            vue.gridResult = GridUtil.showAxiosError(error);
        });
    };

    /**
     * 조회
     */
    this.read = function(surveyGroupId, callback) {
        vue.surveyGroupId = surveyGroupId;

        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        CommonUtil.axios()
        .get(vue.surveyGroupId)
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
        [vue.surveyGroupId == null ? "post" : "put"](vue.surveyGroupId, vue.model)
        .then(function(response) {
            alert("저장되었습니다.");

            vues.list.service.readList();

            if (vue.surveyGroupId == null) {
                vue.surveyGroupId = response.data;
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
        ["delete"](vue.surveyGroupId)
        .then(function(response) {
            alert("삭제되었습니다.");

            vues.list.service.readList();

            vue.viewList();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 사람(대상자) 목록 조회
     */
    this.readPersonList = function(firstPage) {
        vue.tbSurveyGroupPersonList.result = GridUtil.setLoadStart(vue.tbSurveyGroupPersonList.request, firstPage);

        vue.tbSurveyGroupPersonList.request.search.surveyGroupId = vue.surveyGroupId;

        GridUtil.axios(vue)
        .post("readPersonList", vue.tbSurveyGroupPersonList.request)
        .then(function(response) {
            vue.tbSurveyGroupPersonList.result = response.data.result;
        })
        ["catch"](function(error) {
            vue.tbSurveyGroupPersonList.result = GridUtil.showAxiosError(error);
        });
    };

    /**
     * 사람(대상자) 목록 검색
     */
    this.readPersonSearchList = function(firstPage) {
        vue.gridResult = GridUtil.setLoadStart(vue.gridRequest, firstPage);

        GridUtil.axios(vue)
        .post("readPersonSearchList", vue.gridRequest)
        .then(function(response) {
            vue.gridResult = response.data.result;
        })
        ["catch"](function(error) {
            vue.gridResult = GridUtil.showAxiosError(error);
        });
    };

    /**
     * 사람(대상자) 생성
     */
    this.createPerson = function(personId, callback) {
        CommonUtil.axios()
        .post(vue.gridRequest.search.surveyGroupId + "?personId", personId)
        .then(function(response) {
            if (callback != null) {
                callback();
            }
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 사람(대상자) 삭제
     */
    this.deletePerson = function(personId) {
        CommonUtil.axios()
        ["delete"](vue.surveyGroupId + "?personId=" + personId)
        .then(function(response) {
            vue.service.readPersonList();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 조직 트리 조회
     */
    this.readDeptTree = function() {
        this.currentDeptSe = vue.deptSe;

        GridUtil.axios(vue)
        .get("readDeptTree?surveyGroupId=" + vue.surveyGroupId + "&deptSe=" + vue.deptSe)
        .then(function(response) {
            vue.treeData = response.data;
        })
        ["catch"](function(error) {
            vue.gridResult = GridUtil.showAxiosError(error);
        });
    };

    /**
     * 조직별 사람(대상자) 목록 생성
     */
    this.createDeptPersonList = function(dataList, callback) {
        if (dataList.length == 0) {
            alert("추가할 조직을 선택하세요.");
            return;
        }

        if (!confirm("선택하신 " + dataList.length + " 개 조직의 사용자들을 모두 추가하시겠습니까?")) {
            return;
        }

        CommonUtil.axios()
        .post(vue.surveyGroupId + "?deptId", dataList)
        .then(function(response) {
            if (callback != null) {
                callback();
            }
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 엑셀 업로드
     */
    this.excelUpload = function(callback) {
        if (!confirm("업로드 하시겠습니까?")) {
            return;
        }

        var data = new FormData();
        data.append("upload", vue.file);
        data.append("surveyGroupId", vue.surveyGroupId);

        CommonUtil.axios()
        .post("uploadExcel", data, {
            headers: {
                "Content-Type": "multipart/form-data; charset=utf-8;"
            }
        })
        .then(function(response) {
            if (callback != null) {
                callback();
            }
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

}
