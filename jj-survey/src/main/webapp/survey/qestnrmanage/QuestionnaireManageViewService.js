function QuestionnaireManageViewService(vue) {

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
    this.read = function(surveyQestnrId, callback) {
        vue.surveyQestnrId = surveyQestnrId;

        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        CommonUtil.axios()
        .get(vue.surveyQestnrId)
        .then(function(response) {
            if (response.data.tbSurveyQestnr.qestnrBcrnColor == null || response.data.tbSurveyQestnr.qestnrBcrnColor == "") {
                response.data.tbSurveyQestnr.qestnrBcrnColor = response.data.backgroundColors[0];
            }

            if (vue.surveyQestnrId == null) {
                for (var key in response.data.tbSurveyQestnrCmmn) {
                    if (response.data.tbSurveyQestnrCmmn[key] === false) {
                        response.data.tbSurveyQestnrCmmn[key] = true;
                    }
                }
            }

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
        [vue.surveyQestnrId == null ? "post" : "put"](vue.surveyQestnrId, vue.model)
        .then(function(response) {
            alert("저장되었습니다.");

            vues.list.service.readList();

            if (vue.surveyQestnrId == null) {
                vue.surveyQestnrId = response.data;
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
        ["delete"](vue.surveyQestnrId)
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
     * 제출(발송)
     */
    this.publish = function(publishModel) {
        if (checkPublishModel(publishModel) != true) {
            return false;
        }

        if (!confirm("설문을 제출(발송)하시겠습니까?\n\n제출(발송) 후에는 더이상 수정할 수 없습니다.\n\n주의하시기 바랍니다.")) {
            return false;
        }

        CommonUtil.axios()
        .put(vue.surveyQestnrId + "?publish", publishModel)
        .then(function(response) {
            alert("성공적으로 제출되었습니다.");

            vues.list.service.readList();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        }).then(function () {
            vue.service.read(vue.surveyQestnrId);
        });
    };

    /**
     * 템플릿 검색 목록 조회
     */
    this.readTemplateSearchList = function(firstPage) {
        vue.gridResult = GridUtil.setLoadStart(vue.gridRequest, firstPage);

        GridUtil.axios(vue)
        .post("readTemplateSearchList", vue.gridRequest)
        .then(function(response) {
            vue.gridResult = response.data.result;
        })
        ["catch"](function(error) {
            vue.gridResult = GridUtil.showAxiosError(error);
        });
    };

    /**
     * 템플릿 불러오기
     */
    this.readTemplate = function(surveyTmplatId, callback) {
        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        CommonUtil.axios()
        .get("?surveyTmplatId=" + surveyTmplatId)
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
     * 이전 설문지 검색 목록 조회
     */
    this.readPrevSurveySearchList = function(firstPage) {
        vue.gridResult = GridUtil.setLoadStart(vue.gridRequest, firstPage);

        GridUtil.axios(vue)
        .post("readPrevSurveySearchList", vue.gridRequest)
        .then(function(response) {
            vue.gridResult = response.data.result;
        })
        ["catch"](function(error) {
            vue.gridResult = GridUtil.showAxiosError(error);
        });
    };

    /**
     * 이전 설문지 불러오기
     */
    this.readPrevSurvey = function(surveyQestnrId, callback) {
        CommonUtil.initModel(vue.model);
        ValidationUtil.init(vue);

        CommonUtil.axios()
        .get("?surveyQestnrId=" + surveyQestnrId)
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
     * 사람(대상자) 목록 조회
     */
    this.readPersonList = function(firstPage) {
        vue.tbSurveyQestnrPersonList.result = GridUtil.setLoadStart(vue.tbSurveyQestnrPersonList.request, firstPage);

        vue.tbSurveyQestnrPersonList.request.search.surveyQestnrId = vue.surveyQestnrId;

        GridUtil.axios(vue)
        .post("readPersonList", vue.tbSurveyQestnrPersonList.request)
        .then(function(response) {
            vue.tbSurveyQestnrPersonList.result = response.data.result;
        })
        ["catch"](function(error) {
            vue.tbSurveyQestnrPersonList.result = GridUtil.showAxiosError(error);
        });
    };

    /**
     * 사람(대상자) 그룹 검색
     */
    this.readPersonGroupList = function(firstPage) {
        vue.gridResult = GridUtil.setLoadStart(vue.gridRequest, firstPage);

        GridUtil.axios(vue)
        .post("readPersonGroupList", vue.gridRequest)
        .then(function(response) {
            vue.gridResult = response.data.result;
        })
        ["catch"](function(error) {
            vue.gridResult = GridUtil.showAxiosError(error);
        });
    };

    /**
     * 그룹별 사람(대상자) 목록 생성
     */
    this.createGroupPersonList = function(row, callback) {
        if (!confirm("선택하신 \"" + row.groupNm + "\" 그룹의 대상자들(총 " + CommonUtil.formatter.number(row.personCo) + "명)을 모두 추가하시겠습니까?")) {
            return;
        }

        CommonUtil.axios()
        .post(vue.gridRequest.search.surveyQestnrId + "?surveyGroupId", row.surveyGroupId)
        .then(function(response) {
            alert("총 " + CommonUtil.formatter.number(row.personCo) + "명 중 " + CommonUtil.formatter.number(response.data) + "명이 추가되었습니다.");

            if (callback != null) {
                callback();
            }
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
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
        .post(vue.gridRequest.search.surveyQestnrId + "?personId", personId)
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
    this.deletePerson = function(surveyPersonId) {
        CommonUtil.axios()
        ["delete"](vue.surveyQestnrId + "?surveyPersonId=" + surveyPersonId)
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
        .get("readDeptTree?surveyQestnrId=" + vue.surveyQestnrId + "&deptSe=" + vue.deptSe)
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
        .post(vue.surveyQestnrId + "?deptId", dataList)
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
        data.append("surveyQestnrId", vue.surveyQestnrId);

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

    /**
     * 미응답자 메일 일괄 발송
     */
    this.sendMail = function(publishModel) {
        if (checkPublishModel(publishModel) != true) {
            return false;
        }

        if (!confirm("설문 미응답자 일괄 재발송 하시겠습니까?")) {
            return false;
        }

        CommonUtil.axios()
        .put(vue.surveyQestnrId + "?sendMail", publishModel)
        .then(function(response) {
            alert("일괄 재발송 되었습니다.");
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 대상 메일 발송
     */
    this.sendMailTarget = function(surveyPersonId, publishModel) {
        if (checkPublishModel(publishModel) != true) {
            return false;
        }

        if (!confirm("재발송 하시겠습니까?")) {
            return false;
        }

        CommonUtil.axios()
        .put(surveyPersonId + "?sendMailTarget", publishModel)
        .then(function(response) {
            alert("재발송 되었습니다.");
            vue.service.readPersonList();
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    function checkPublishModel(publishModel) {
        var selectedCount = 0;

        for (var key in publishModel) {
            if (publishModel[key] === true) {
                selectedCount++;
            }
        }

        if (selectedCount == 0) {
            alert("설문을 발송할 매체를 한개 이상 선택하세요.");
            return;
        }

        return true;
    }

}
