var scoreAveGraph;
var preSubjectGraph;
var hakgwaGraph;
var scholarshipGraph;
var gradeGraph;
var sexGraph;

function AnalysisViewService(vue) {

    this.readList = function(search) {
        var color = [
                { normal: { color: '#5b73d5' } },
                { normal: { color: '#e1c000' } },
                { normal: { color: '#70ad47' } },
                { normal: { color: '#ed7d31' } },
                { normal: { color: '#7030a0' } },
                { normal: { color: '#000000' } },
                { normal: { color: 'black' } },
        ];

        // 평균성적
        scoreAveGraph = echarts.init(document.getElementById('scoreAveGraph'), 'macarons');
        scoreAveGraph.showLoading();

        // 학년
        gradeGraph = echarts.init(document.getElementById('gradeGraph'), 'macarons');
        gradeGraph.showLoading();

        // 학과
        hakgwaGraph = echarts.init(document.getElementById('hakgwaGraph'), 'macarons');
        hakgwaGraph.showLoading();

        // 성별
        sexGraph = echarts.init(document.getElementById('sexGraph'), 'macarons');
        sexGraph.showLoading();

        CommonUtil.axios()
        .post("readList", search)
        .then(function(response) {

            if (response.data === null || response.data === undefined || response.data.length === 0) {
                return false;
            }

            var arr_ave = [];
            var arr_grade = [];
            var arr_hakgwa = [];
            var arr_sex = [];

            for (var i=0; i<response.data.length; i++) {
                var v = response.data[i];
                if (v.type === 'PYONG') {
                    arr_ave.push({ value: v.cnt1, itemStyle: color[0] });
                    arr_ave.push({ value: v.cnt2, itemStyle: color[1] });
                    arr_ave.push({ value: v.cnt3, itemStyle: color[2] });
                    arr_ave.push({ value: v.cnt4, itemStyle: color[3] });
                    arr_ave.push({ value: v.cnt5, itemStyle: color[4] });
                } else if (v.type === 'GARDE') {
                    arr_grade.push({ value: v.cnt1, itemStyle: color[0] });
                    arr_grade.push({ value: v.cnt2, itemStyle: color[1] });
                    arr_grade.push({ value: v.cnt3, itemStyle: color[2] });
                    arr_grade.push({ value: v.cnt4, itemStyle: color[3] });
                    arr_grade.push({ value: v.cnt5, itemStyle: color[4] });
                } else if (v.type === 'HAKGWA') {
                    arr_hakgwa.push({ name: v.label, value: v.cnt1, itemStyle: color[i%5] });
                } else if (v.type === 'SEX') {
                    arr_sex.push({ name: "남", value: v.cnt1, itemStyle: color[0] });
                    arr_sex.push({ name: "여", value: v.cnt2, itemStyle: color[1] });
                }
            };

            /*공통 옵션*/
            var option = {
                grid: {
                    width: '80%',
                    bottom: '15%',
                    containLabel: true
                },
                title: {
                    show: false,
                    textStyle: {
                        color: "grey",
                        fontSize: 20
                    },
                    text: "No data",
                    left: "center",
                    top: "center"
                },
                tooltip: {
                    trigger: 'item',
                    triggerOn: 'mousemove'
                }
            };


            var arr_grade_label = ["1학년","2학년","3학년","4학년","5학년"];

            // 평균성적
            echarts.dispose(document.getElementById('scoreAveGraph'));
            scoreAveGraph = echarts.init(document.getElementById('scoreAveGraph'), 'macarons');
            scoreAveGraph.hideLoading();
            var option_ave = {
                xAxis: {
                    type: 'category',
                    data: arr_grade_label
                },
                yAxis: {
                    type: 'value',
                    name: '평균'
                },
                series: [{
                    data: arr_ave,
                    type: 'bar',
                    label: {
                        show: true,
                        position: 'top',
                    },
                    tooltip: {
                        formatter: function (item) {
                            return item.marker + "" + item.name + ": " + item.value + "점";
                        }
                    }
                }]
            }
            Object.assign(option_ave,option);
            option_ave.title.show = arr_ave.length === 0;
            scoreAveGraph.setOption(option_ave);
            $(window).on("resize", function() {
                scoreAveGraph.resize();
            });


            // 학과
            hakgwaGraph.hideLoading();
            echarts.dispose(document.getElementById('hakgwaGraph'));
            hakgwaGraph = echarts.init(document.getElementById('hakgwaGraph'), 'macarons');
            var option_hak = {
                legend: {
                    type: 'scroll',
                    orient: 'vertical',
                    right: 10,
                    top: 20,
                    bottom: 20,
                    data: arr_hakgwa,
                },
                series: [{
                    data: arr_hakgwa,
                    type: 'pie',
                    radius: '55%',
                    center: ['40%', '50%'],
                    label: {
                        show: true,
                        position: 'top',
                        formatter: function(d) {
                            return d.data.value + "%";
                        }
                    },
                    tooltip: {
                        formatter: function (item) {
                            return item.marker + "" + item.name + ": " + item.value + "%";
                        }
                    }
                }]
            }
            Object.assign(option_hak,option);
            option_hak.title.show = arr_hakgwa.length === 0;
            hakgwaGraph.setOption(option_hak);
            $(window).on("resize", function() {
                hakgwaGraph.resize();
            });


            // 학년
            gradeGraph.hideLoading();
            echarts.dispose(document.getElementById('gradeGraph'));
            gradeGraph = echarts.init(document.getElementById('gradeGraph'), 'macarons');
            var option_grade = {
                xAxis: {
                    type: 'category',
                    data: arr_grade_label
                },
                yAxis: {
                    type: 'value',
                    name: '인원수(명)'
                },
                series: [{
                    data: arr_grade,
                    type: 'bar',
                    label: {
                        show: true,
                        position: 'top',
                        formatter: function(d) {
                            return d.data.value + "명";
                        }
                    },
                    tooltip: {
                        formatter: function (item) {
                            return item.marker + "" + item.name + ": " + item.value + "명";
                        }
                    }
                }]
            }
            Object.assign(option_grade,option);
            option_grade.title.show = arr_grade.length === 0;
            gradeGraph.setOption(option_grade);
            $(window).on("resize", function() {
                gradeGraph.resize();
            });


            // 성별
            sexGraph.hideLoading();
            sexGraph.hideLoading();
            echarts.dispose(document.getElementById('sexGraph'));
            sexGraph = echarts.init(document.getElementById('sexGraph'), 'macarons');
            var option_sex = {
                legend: {
                    type: 'scroll',
                    orient: 'vertical',
                    right: 10,
                    top: 20,
                    bottom: 20,
                    data: arr_sex,
                },
                series: [{
                    data: arr_sex,
                    type: 'pie',
                    radius: '55%',
                    center: ['40%', '45%'],
                    label: {
                        show: true,
                        position: 'top',
                        formatter: function(d) {
                            return d.data.value + "%";
                        }
                    },
                    tooltip: {
                        formatter: function (item) {
                            return item.marker + "" + item.name + ": " + item.value + "%";
                        }
                    }
                }]
            }
            Object.assign(option_sex,option);
            option_sex.title.show = arr_sex.length === 0;
            sexGraph.setOption(option_sex);
            $(window).on("resize", function() {
                sexGraph.resize();
            });
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    }


    this.readList2 = function(search) {

        // 선이수 과목
        preSubjectGraph = echarts.init(document.getElementById('preSubjectGraph'), 'macarons');
        preSubjectGraph.showLoading();

        CommonUtil.axios()
        .post("readList2", search)
        .then(function(response) {

            var arr_pre = { header: [], value: [] };

            for (var i=0; i<response.data.length; i++) {
                var v = response.data[i];
                if (v.type === 'PRESUB') {
                    arr_pre["header"].unshift(v.kangGwamokNm);
                    arr_pre["value"].unshift(v.cnt);
                }
            };

            echarts.dispose(document.getElementById('preSubjectGraph'));
            preSubjectGraph = echarts.init(document.getElementById('preSubjectGraph'), 'macarons');
            preSubjectGraph.hideLoading();
            var option_pre = {
                color: ['#3398DB'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                title: [
                    {
                        show: arr_pre["header"].length === 0,
                        textStyle: {
                            color: "grey",
                            fontSize: 20
                        },
                        text: "No data",
                        left: "center",
                        top: "center",
                    }
                ],
                grid: {
                    left: '3%',
                    right: '4%',
                    top: '2%',
                    bottom: '5%',
                    containLabel: true
                },
                yAxis: [
                    {
                        type: 'category',
                        data: arr_pre["header"],
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                xAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
//                        name: '',
                        type: 'bar',
                        barWidth: '60%',
                        data: arr_pre["value"],
                    }
                ]
            };
            preSubjectGraph.setOption(option_pre);
            $(window).on("resize", function() {
                preSubjectGraph.resize();
            });

        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    }


    this.readList3 = function(search) {

        // 장학금
        scholarshipGraph = echarts.init(document.getElementById('scholarshipGraph'), 'macarons');
        scholarshipGraph.showLoading();

        CommonUtil.axios()
        .post("readList3", search)
        .then(function(response) {

            if (response.data === null || response.data === undefined || response.data.length === 0) {
                return false;
            }

            var arr_scholar = { header: [], value: [], ave: [] };

            for (var i=0; i<response.data.length; i++) {
                var v = response.data[i];
                if (v.type === 'SCHOLARSHIP') {
                    arr_scholar["header"].push(v.jamaGubunNm);
                    arr_scholar["value"].push(v.cnt);
                    arr_scholar["ave"].push(v.jamaKumaekAvg);
                }
            };


            // 장학금
            echarts.dispose(document.getElementById('scholarshipGraph'));
            scholarshipGraph = echarts.init(document.getElementById('scholarshipGraph'), 'macarons');
            scholarshipGraph.hideLoading();
            var option_scholar = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        crossStyle: {
                            color: '#999'
                        }
                    }
                },
                title: [
                    {
                        show: arr_scholar["header"].length === 0,
                        textStyle: {
                            color: "grey",
                            fontSize: 20
                        },
                        text: "No data",
                        left: "center",
                        top: "center",
                    }
                ],
                legend: {
                    data: ['평균금액', '건수']
                },
                grid: {
                    left: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: arr_scholar["header"],
                        axisPointer: {
                            type: 'shadow'
                        }
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '금액',
                        min: 0,
                        max: maxFn(arr_scholar["ave"]),
                        interval: Math.ceil(maxFn(arr_scholar["ave"])/arr_scholar["ave"].length),
                        axisLabel: {
                            formatter: '{value} 원'
                        }
                    },
                    {
                        type: 'value',
                        name: '건수',
                        min: 0,
                        max: maxFn(arr_scholar["value"]),
                        interval: Math.ceil(maxFn(arr_scholar["value"])/arr_scholar["value"].length),
                        axisLabel: {
                            formatter: '{value} 건'
                        }
                    }
                ],
                series: [
                    {
                        name: '평균금액',
                        type: 'bar',
                        data: arr_scholar["ave"],
                    },
                    {
                        name: '건수',
                        type: 'line',
                        yAxisIndex: 1,
                        data: arr_scholar["value"],
                    }
                ]
            };
            scholarshipGraph.setOption(option_scholar);
            $(window).on("resize", function() {
                scholarshipGraph.resize();
            });

        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    }
}