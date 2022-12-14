/**
 * @param vue
 * @returns
 */
function CareerPathViewService(vue) {
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
     * 학생 조회
     */
    this.readStudent = function() {
        CommonUtil.axios()
        .get("readStudent/" + vue.hakbun)
        .then(function(response) {
            if (response != null) {
                vue.student = response.data;
            }
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 차트 데이터 조회 - 수강 데이터
     */
    this.readChartData_sugang = function() {
        CommonUtil.axios()
        .post("readSugangData", {"hakbun": vue.hakbun, "search": makeInQuery(vue.search)} )
        .then(function(response) {
            drawChart_sugang(response.data);
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };

    /**
     * 차트 생성 - 수강 데이터
     *
     * @param sugangData
     * @returns
     */
    function drawChart_sugang(sugangData) {
        //전체 차트 데이터
        var chartData = {};
        if (sugangData.length != 0) {
            var chartDataChildren = [];

            //학년 배열 생성
            var yearArr = [];
            yearArr.push(sugangData[0].progYear);
            for (var i=1; i<sugangData.length; i++) {
                for (var j=0; j<yearArr.length; j++) {
                    if(sugangData[i].progYear == yearArr[j]) {
                        break;
                    }
                    if(j == yearArr.length -1) {
                        yearArr.push(sugangData[i].progYear);
                    }
                }
            }

            //학년별 학기 배열 생성

            for (var i=0; i<yearArr.length; i++) {
                var hakgiArr = [];
                var hakgiChildren = [];
                var yearChild = {};
                yearChild.name = yearArr[i] + "학년";

                for (var j=0; j<sugangData.length; j++) {
                    if (sugangData[j].progYear == yearArr[i]) {
                        var hakgiChild = {};
                        if (hakgiArr.length == 0) {
                            hakgiArr.push(sugangData[j].progHakgi);
                            hakgiChild.name = sugangData[j].progHakgi + "학기";
                            hakgiChildren.push(copyObject(hakgiChild));
                        }
                        else {
                            for (var k=0; k<hakgiArr.length; k++) {
                                if(sugangData[j].progHakgi == hakgiArr[k]) {
                                    break;
                                }
                                if(k == hakgiArr.length -1) {
                                    hakgiArr.push(sugangData[j].progHakgi);
                                    hakgiChild.name = sugangData[j].progHakgi + "학기";
                                    hakgiChildren.push(copyObject(hakgiChild));
                                }
                            }
                        }
                    }
                }

                //gwamokGubunNm 배열 생성
                for (var j=0; j<hakgiArr.length; j++) {
                    var gubunArr = [];
                    var gubunChildren = [];
                    for (var k=0; k<sugangData.length; k++) {
                        if (sugangData[k].progYear == yearArr[i] && sugangData[k].progHakgi == hakgiArr[j]) {
                            var gubunChild = {};

                            gubunChild.label = makeFontColor(sugangData[k].gwamokGubunNm);
                            //20.04.29 기초 -> 전공기초로 변경
                            if (sugangData[k].gwamokGubunNm == "기초") {
                                sugangData[k].gwamokGubunNm = "전공기초";
                            }
                            if (gubunArr.length == 0) {
                                gubunArr.push(sugangData[k].gwamokGubunNm);
                                gubunChild.name = sugangData[k].gwamokGubunNm;
                                gubunChildren.push(copyObject(gubunChild));
                            }
                            else {
                                for (var l=0; l<gubunArr.length; l++) {
                                    if (sugangData[k].gwamokGubunNm == gubunArr[l]) {
                                        break;
                                    }
                                    if (l == gubunArr.length -1) {
                                        gubunArr.push(sugangData[k].gwamokGubunNm);
                                        gubunChild.name = sugangData[k].gwamokGubunNm;
                                        gubunChildren.push(copyObject(gubunChild));
                                    }
                                }
                            }
                        }
                    }

                    //gwamokName 추가
                    for (var k=0; k<gubunArr.length; k++) {
                        var gwamokChildren = [];
                        for (var l=0; l<sugangData.length; l++) {
                            if (sugangData[l].progYear == yearArr[i] && sugangData[l].progHakgi == hakgiArr[j]
                                && sugangData[l].gwamokGubunNm == gubunArr[k]) {
                                var gwamokChild = {};
                                gwamokChild.label = makeFontColor(sugangData[l].gwamokGubunNm);
                                gwamokChild.name = sugangData[l].gwamokName;
                                gwamokChildren.push(copyObject(gwamokChild));
                            }
                        }
                        gubunChildren[k].children = copyObject(gwamokChildren);
                    }

                    //hakgiChildren에 gubunChildren 추가
                    hakgiChildren[j].children = copyObject(gubunChildren);
                }

                yearChild.children = copyObject(hakgiChildren);
                chartDataChildren.push(copyObject(yearChild));
            }
            chartData.name = "";
            chartData.children = chartDataChildren;
        }


        var chart = echarts.init(document.getElementById('sugangGraph'), 'macarons');
        var chartHeight = "90%";
        var option = {
            tooltip: {
                trigger: 'item',
                triggerOn: 'mousemove'
            },
            series:[
                {
                    type: 'tree',
                    id: 0,
                    name: 'tree1',
                    data: [chartData],
                    height: chartHeight,

                    top: '2%',
                    left: '3%',
                    bottom: '10%',
                    right: '15%',

                    symbolSize: 10,

                    edgeForkPosition: '63%',
                    initialTreeDepth: 4,

                    lineStyle: {
                        width: 4
                    },

                    label: {
                        backgroundColor: '#fff',
                        position: 'left',
                        verticalAlign: 'middle',
                        align: 'right'
                    },

                    leaves: {
                        label: {
                            position: 'right',
                            verticalAlign: 'middle',
                            align: 'left'
                        }
                    },

                    expandAndCollapse: true,
                    animationDuration: 550,
                    animationDurationUpdate: 750
                }
            ]
        };

        //20.04.29 노드 개수별 트리크기 조절추가
        if (sugangData.length > 40) {
            option.series[0].height = '90%'
        }
        else if (sugangData.length > 30 ) {
            option.series[0].height = '80%'
        }
        else if (sugangData.length > 20 ) {
            option.series[0].height = '70%'
        }
        else
            option.series[0].height = '50%'


        chart.setOption(option);
        $(window).on("resize", function() {
            chart.resize();
        });
    }

    this.update = function() {
        if (!ValidationUtil.check(vue)) {
            return;
        }
        if (!confirm("등록하시겠습니까?")) {
            return;
        }

        if (vue.hakbun == null) {
            CommonUtil.axios()
            .post("create", vue.student)
            .then(function(response) {
                alert("등록되었습니다.");
                CommonUtil.changeVue("read", vue.student.hakbun);
            })
            ["catch"](function(error) {
                CommonUtil.showAxiosError(error);
            });
        }
        else {
            CommonUtil.axios()
            ["put"](vue.hakbun, vue.student)
            .then(function(response) {
                alert("등록되었습니다.");
                CommonUtil.changeVue("read", vue.student.hakbun);
            })
            ["catch"](function(error) {
                CommonUtil.showAxiosError(error);
            });
        }
    };

    this.delete = function() {
        if (!confirm("정말로 삭제하시겠습니까?")) {
            return;
        }
        CommonUtil.axios()
        ["delete"](vue.hakbun)
        .then(function(response) {
            alert("삭제되었습니다.");
            vues.list.service.readList();
            CommonUtil.changeVue("list");
        })

    }

    /**
     * 엑셀 업로드
     */
    this.excelUpload = function(callback) {
        if (!confirm("업로드 하시겠습니까?")) {
            return;
        }

        var data = new FormData();
        data.append("upload", vue.file);

        CommonUtil.axios()
        .post("uploadExcel", data, {
            headers: {
                "Content-Type": "multipart/form-data; charset=utf-8;"
            }
        })
        .then(function(response) {
            if (callback != null) {
                alert("등록되었습니다.");
                callback();
            }
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    };
}


function copyObject(obj){
    if (obj === null || typeof obj !== 'object') {
        return obj;
    }

    const copiedObject = obj.constructor();

    for (let key in obj) {
        if (obj.hasOwnProperty(key)) {
            copiedObject[key] = copyObject(obj[key]);
        }

    }
    return copiedObject;
}

function makeInQuery(searchObj) {
    //('교양필수', '전공필수', '전공기초', '교양선택', '전공선택', '교직')
    var query = "(";
    var keys = Object.keys(searchObj);

    for (let i=0; i<keys.length; i++) {
        if (searchObj[keys[i]]) {
            var val;
            switch (keys[i]) {
                case "jeonpil":
                    val = "전공필수";
                    break;
                case "jeonsun":
                    val = "전공선택";
                    break;
                case "kyopil":
                    val = "교양필수";
                    break;
                case "kyosun":
                    val = "교양선택";
                    break;
                case "gicho":
                    val = "기초";
                    break;
                case "kyogic":
                    val = "교직";
                    break;
                default:
                    break;
            }
            query += "\'" + val + "\',";
        }
        if (i == keys.length - 1){
            query = query.slice(0, -1);
            query += ")";
        }
    }
    return query;
}

function makeFontColor(gawmokGbn) {
    var labelColor = {}

    switch (gawmokGbn) {
        case "전공필수":
      case "전공선택":
      case "전공기초":
      case "기초":
         labelColor.color = "#ec292d";
         break;
      case "교양필수":
      case "교양선택":
         labelColor.color = "#00a853";
         break;
      case "교직":
         labelColor.color = "#74458d";
         break;
    }

    return labelColor;
}
