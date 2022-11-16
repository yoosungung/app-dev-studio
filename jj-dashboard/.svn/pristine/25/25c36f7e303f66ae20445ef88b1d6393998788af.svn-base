function ScoreViewService(vue) {
    this.drawGraph = {};

    this.drawGraph["yearScoreGraph"] = function(search) {
        if (search.startStdYy !== "" && search.endStdYy !== "" &&
                search.startStdYy > search.endStdYy) {
            var copy = copyObject(search);
            search.startStdYy = copy.endStdYy;
            search.endStdYy = copy.startStdYy;
        }

        CommonUtil.axios()
        .post("readScoreData", search)
        .then(function(response) {
            CommonUtil.axios()
            .post("readPercentage", search)
            .then(function(percentResponse) {
                var percentData = percentResponse.data;
                var scoreA = [];
                var scoreB = [];
                var scoreC = [];
                var scoreD = [];
                var scoreF = [];
                for(var i=0; i<percentData.length; i++) {
                    scoreA.push(Math.round((percentData[i].scoreA / percentData[i].total) * 100));
                    scoreB.push(Math.round((percentData[i].scoreB / percentData[i].total) * 100));
                    scoreC.push(Math.round((percentData[i].scoreC / percentData[i].total) * 100));
                    scoreD.push(Math.round((percentData[i].scoreD / percentData[i].total) * 100));
                    scoreF.push(Math.round((percentData[i].scoreF / percentData[i].total) * 100));
                }
                var data = response.data;
                var xAxis = [];
                var hakgi1 = [];
                var hakgi2 = [];
                var numHakgi1 = [];
                var numHakgi2 = [];
                var percent = [];

                for(var i=0; i<data.length; i++) {
                    if (i%2 == 0) {
                        xAxis.push(data[i].stdYy);
                    }
                    if (data[i].sutrHakgi == 1) {
                        hakgi1.push(data[i].sutrPyongAve);
                        numHakgi1.push(data[i].cnt);
                    }
                    if (data[i].sutrHakgi == 2) {
                        hakgi2.push(data[i].sutrPyongAve);
                        numHakgi2.push(data[i].cnt);
                    }
                }
                echarts.dispose(document.getElementById('yearScoreGraph'));
                var myChart = echarts.init(document.getElementById('yearScoreGraph'), 'macarons');
                var option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        },
                        //툴팁 단위 설정. 아래 series순서대로 적용
                        formatter: function (items) {
                            var toolTip = items[0].axisValue + "</br>";
                            for(var i=0; i<items.length; i++) {
                                if (i == 0 || i== 1) {
                                    toolTip += items[i].marker + "" + items[i].seriesName + ": " + items[i].value + "점";
                                }
                                else if (i == 2 || i == 3) {
                                    toolTip += items[i].marker + "" + items[i].seriesName + ": " + items[i].value + "명";
                                }
                                else {
                                    toolTip += items[i].marker + "" + items[i].seriesName + ": " + items[i].value + "%";
                                }

                                if (i != items.length - 1) {
                                    toolTip += "</br>";
                                }
                            }
                            return toolTip;
                        }
                    },
                    legend: {
                        width: 1000,
                        height: 300,
                        data: ['1학기', '2학기', 'A', 'B', 'C', 'D', 'F'],
                        textStyle: {
                            fontSize: 15
                        },
                        itemWidth: 40,
                        itemHeight: 20
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '5%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: xAxis
                        }
                    ],
                    yAxis: [
                        {
                            max: 3.5,
                            min: 2.5,
                            type: 'value'
                        },
                        {
                            max: 60,
                            min: 0,
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}%'
                            }
                        }
                    ],
                    series: [{
                            name: '1학기',
                            type: 'bar',
                            data: hakgi1,
                            itemStyle: {
                                opacity: 0.5,
                            }
                        },
                        {
                            name: '2학기',
                            type: 'bar',
                            data: hakgi2,
                            itemStyle: {
                                opacity: 0.5,
                            }
                        },
                        {
                            name: '1학기 성적취득 학생수',
                            type: 'scatter',
                            data: numHakgi1,
                            itemStyle: {
                                display: "none",
                            }
                        },
                        {
                            name: '2학기 성적취득 학생수',
                            type: 'scatter',
                            data: numHakgi2,
                            itemStyle: {
                                display: "none",
                            }
                        },
                        {
                            name: 'A',
                            type: 'line',
                            yAxisIndex: 1,
                            data: scoreA,
                            symbolSize: 10
                        },
                        {
                            name: 'B',
                            type: 'line',
                            yAxisIndex: 1,
                            data: scoreB,
                            symbolSize: 10
                        },
                        {
                            name: 'C',
                            type: 'line',
                            yAxisIndex: 1,
                            data: scoreC,
                            symbolSize: 10
                        },
                        {
                            name: 'D',
                            type: 'line',
                            yAxisIndex: 1,
                            data: scoreD,
                            symbolSize: 10
                        },
                        {
                            name: 'F',
                            type: 'line',
                            yAxisIndex: 1,
                            data: scoreF,
                            symbolSize: 10
                        }
                    ]
                };
                myChart.on('click', function(params) {
                    console.log("클릭이벤트");
                    vue.search.clickedYear = params.name;
                    vue.service.drawGraph["yearGraph"](vue.search);
                });
                myChart.setOption(option);
                $(window).on("resize", function() {
                    myChart.resize();
                });
            })
            ["catch"](function(error) {
               CommonUtil.showAxiosError(error);
            });
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    }
    this.drawGraph["yearGraph"] = function(search) {
        if (search.startStdYy !== "" && search.endStdYy !== "" &&
                search.startStdYy > search.endStdYy) {
            var copy = copyObject(search);
            search.startStdYy = copy.endStdYy;
            search.endStdYy = copy.startStdYy;
        }

        CommonUtil.axios()
        .post("readTotalYearScoreData", search)
        .then(function(totalResponse) {
            CommonUtil.axios()
            .post("readYearScoreData", search)
            .then(function(response) {
                var data = response.data;
                var yAxis = [];
                var year = [];
                var numYear = [];

                var totalData = totalResponse.data;
                var totalyAxis = [];
                var totalYear = [];
                var totalNumYear = [];

                var dataName = [];

                for(var i=0; i<data.length; i++) {
                    yAxis.push(data[i].sutrYear + "학년");
                    dataName.push(data[i].sutrYear + "학년");
                    year.push(data[i].sutrPyongAve);
                    numYear.push(data[i].cnt);
                    totalYear.push(totalData[i].sutrPyongAve);
                    totalNumYear.push(totalData[i].cnt);
                }

                echarts.dispose(document.getElementById('yearGraph'));
                var myChart = echarts.init(document.getElementById('yearGraph'), 'macarons');
                var option = {
                    title: {},
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        },
                        formatter: function (items) {
                            var toolTip = items[0].axisValue + "</br>";
                            for(var i=0; i<items.length; i++) {
                                if (i == 0 || i== 1) {
                                    toolTip += items[i].marker + "" + items[i].seriesName + ": " + items[i].value + "점";
                                }
                                else if (i == 2 || i == 3) {
                                    toolTip += items[i].marker + "" + items[i].seriesName + ": " + items[i].value + "명";
                                }
                                if (i != items.length - 1) {
                                    toolTip += "</br>";
                                }
                            }
                            return toolTip;
                        },

                    },
                    legend: {
                        data: ['학년', '전체'],
                        itemWidth: 40,
                        itemHeight: 20
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '5%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value',
                        max: 4.5
                    },
                    yAxis: {
                        type: 'category',
                        data: yAxis
                    },
                    series: [
                        {
                            name: '학년',
                            type: 'line',
                            data: year,
                            symbolSize: 10
                        },
                        {
                            name: '전체',
                            type: 'bar',
                            barMaxWidth: 100,
                            data: totalYear,
                            itemStyle: {
                                opacity: 0.5,
                            }
                        },
                        {
                            name: '성적취득 학생수',
                            type: 'scatter',
                            data: numYear,
                            itemStyle: {
                                display: "none",
                            }
                        },
                        {
                            name: '전체 성적취득 학생수',
                            type: 'scatter',
                            data: totalNumYear,
                            itemStyle: {
                                display: "none",
                            }
                        }
                    ]
                };
                if(search.clickedYear != "" && search.clickedYear != null ) {
                    option.title.text = search.clickedYear + '년';
                }
                else {
                    var title = "";
                    title += (search.startStdYy != "") ? search.startStdYy : "전체"
                    title += " ~ ";
                    title += (search.endStdYy != "") ? search.endStdYy : "전체"
                    option.title.text = title;
                }
                vue.search.clickedYear = null;
                myChart.setOption(option);
                $(window).on("resize", function() {
                    myChart.resize();
                });
            })
            ["catch"](function(error) {
                CommonUtil.showAxiosError(error);
            });
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    }

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
