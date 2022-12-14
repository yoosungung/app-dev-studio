var ipsyGraphJong;
var ipsyGraphJung;
var ipsyGraphGyo;

function IpsyViewService(vue) {

    // 연도별 표보기
    this.readList = function(search) {
        CommonUtil.axios()
        .post("readList", search || {})
        .then(function(response) {
            console.log(response.data);
            //200721 표 순서 변경
            var tempArr = [];
            tempArr.push(response.data[5]);
            tempArr.push(response.data[3]);
            tempArr.push(response.data[2]);
            tempArr.push(response.data[0]);
            tempArr.push(response.data[1]);
            tempArr.push(response.data[6]);
            tempArr.push(response.data[4]);
            tempArr.push(response.data[7]);
            response.data = tempArr;
            //
            console.log(response.data);
            if (response.data === null || response.data === undefined || response.data.length === 0) {
                html += '<tr><td colspan="5" rowspan="6" class="tac">NO DATA</td></tr>';
                $('#tbody').html(html);
            } else {
                var header = ['일반고','자율고','특목고','특성화고','검정고시','기타']
                var arr_header = ['고교유형'];
                var arr_ilban = ['일반고'];
                var arr_free = ['자율고'];
                var arr_special = ['특목고'];
                var arr_specialist = ['특성화고'];
                var arr_gumjung = ['검정고시'];
                var arr_gyta = ['기타'];

                $(response.data).each(function(i,v) {
                    arr_header.push(v.header);
                    arr_ilban.push(v.cntIlban);
                    arr_free.push(v.cntFree);
                    arr_special.push(v.cntSpecial);
                    arr_specialist.push(v.cntSpecialist);
                    arr_gumjung.push(v.cntGumjung);
                    arr_gyta.push(v.cntGyta);
                });

                var hhtml = '<tr>';
                $(arr_header).each(function(i,v) {
                    hhtml += '<th>'+v+'</th>';
                });
                hhtml += '</tr>';
                $('#thead').html(hhtml);

                var html = '';
                html += '<tr>';
                function _f(i,v) {
                    if (i===0) {
                        html += '<th style="background-color:#faedd2;">'+comma(v)+'</th>';
                    } else {
                        html += '<td>'+comma(v)+'</td>';
                    }
                }
                $(arr_ilban).each(function(i,v) {
                    _f(i,v);
                });
                html += '</tr>';
                html += '<tr>';
                $(arr_free).each(function(i,v) {
                    _f(i,v);
                });
                html += '</tr>';
                html += '<tr>';
                $(arr_special).each(function(i,v) {
                    _f(i,v);
                });
                html += '</tr>';
                html += '<tr>';
                $(arr_specialist).each(function(i,v) {
                    _f(i,v);
                });
                html += '</tr>';
                html += '<tr>';
                $(arr_gumjung).each(function(i,v) {
                    _f(i,v);
                });
                html += '</tr>';
                html += '<tr>';
                $(arr_gyta).each(function(i,v) {
                    _f(i,v);
                });
                html += '</tr>';

                $('#tbody').html(html);
            }
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    }


    this.readChartList = function(search) {

        CommonUtil.axios()
        .post("readChartList", search || {})
        .then(function(response) {

            if (response.data === null || response.data === undefined || response.data.length === 0) {
                return false;
            }

            // 수시-학생부종합
            ipsyGraphJong = echarts.init(document.getElementById('ipsyGraphJong'), 'macarons');
            ipsyGraphJong.showLoading();

            // 정시
            ipsyGraphJung = echarts.init(document.getElementById('ipsyGraphJung'), 'macarons');
            ipsyGraphJung.showLoading();

            // 수시-학생부교과
            ipsyGraphGyo = echarts.init(document.getElementById('ipsyGraphGyo'), 'macarons');
            ipsyGraphGyo.showLoading();
            var color = [
                    { normal: { color: '#5b73d5' } },
                    { normal: { color: '#ed7d31' } },
                    { normal: { color: '#70ad47' } },
                    { normal: { color: '#e1c000' } },
                    { normal: { color: '#7030a0' } },
                    { normal: { color: '#000000' } },
                    { normal: { color: 'black' } },
            ];
            var arr_jong = [];
            var arr_jung = [];
            var arr_gyo = [];
            var arr_entrance_jong = [];
            var arr_entrance_jung = [];
            var arr_entrance_gyo = [];
            var arr_ihLclass = ['일반고','자율고','특목고','특성화고','검정고시','기타'];

            $(response.data).each(function(i,v) {
                var arr = [];
                arr.push({ value: v.cntIlbanRate, itemStyle: color[0] });
                arr.push({ value: v.cntFreeRate, itemStyle: color[1] });
                arr.push({ value: v.cntSpecialRate, itemStyle: color[2] });
                arr.push({ value: v.cntSpecialistRate, itemStyle: color[3] });
                arr.push({ value: v.cntGumjungRate, itemStyle: color[4] });
                arr.push({ value: v.cntGytaRate, itemStyle: color[5] });

                var arr_entrance = [];
                arr_entrance.push({ value: v.entranceCntIlbanRate, itemStyle: color[0] });
                arr_entrance.push({ value: v.entranceCntFreeRate, itemStyle: color[1] });
                arr_entrance.push({ value: v.entranceCntSpecialRate, itemStyle: color[2] });
                arr_entrance.push({ value: v.entranceCntSpecialistRate, itemStyle: color[3] });
                arr_entrance.push({ value: v.entranceCntGumjungRate, itemStyle: color[4] });
                arr_entrance.push({ value: v.entranceCntGytaRate, itemStyle: color[5] });

                if (v.ipsyGubunName === "학생부종합") {
                    arr_jong = arr_jong.concat(arr);
                    arr_entrance_jong = arr_entrance.concat(arr_entrance_jong);
                } else if (v.ipsyGubunName === "학생부교과") {
                    arr_gyo = arr_gyo.concat(arr);
                    arr_entrance_gyo = arr_entrance.concat(arr_entrance_gyo);
                } else if (v.ipsyGubunName === "정시") {
                    arr_jung = arr_jung.concat(arr);
                    arr_entrance_jung = arr_entrance.concat(arr_entrance_jung);
                }

            });

            /*공통 옵션*/
            var option = {
                    title: [{
//                            text: '고교유형별 지원현황'
//                        }, {
//                            text: '고교유형별 등록현황'
//                        }, {
                            show: arr_ihLclass.length === 0,
                            textStyle: {
                                color: "grey",
                                fontSize: 20
                            },
                            text: "No data",
                            left: "center",
                            top: "center"
                    }],
                    grid: [{
                        top: '2%',
                        width: '75%',
                        left: 0,
                        containLabel: true
                    }, {
                        top: '2%',
                        left: '75%',
                        containLabel: true
                    }],
                    tooltip: {
                        trigger: 'item',
                        triggerOn: 'mousemove'
                    },
//                    legend: {
//                        data: arr_ihLclass
//                    },
            };


            // 학생부종합
            ipsyGraphJong.hideLoading();
            var option_jong = {
                    xAxis: [{
                        type: 'category',
                        data: arr_ihLclass,
                        gridIndex: 0,
                    },{
                        type: 'category',
                        data: ['등록현황'],
                        gridIndex: 1,
                    }],
                    yAxis: [{
                        type: 'value', gridIndex: 0
                    },{
                        type: 'value', gridIndex: 1
                    }],
                    series: [{
                            data: arr_jong,
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
                            },
                            type: 'bar',
                            seriesLayoutBy: 'row',
                        }, {
                            type: 'bar',
                            stack: '总量',
                            label: {
                                show: true,
                                position: 'inside',
                                formatter: function(d) {
                                    return d.data.value + "%";
                                }
                            },
                            data: arr_entrance_jong,
                            xAxisIndex: 1,
                            yAxisIndex: 1,
                            tooltip: {
                                formatter: function (item) {
                                    return item.marker + "" + item.name + ": " + item.value + "%";
                                }
                            }
                        }]
            };
            Object.assign(option_jong,option);
            ipsyGraphJong.setOption(option_jong);
            $(window).on("resize", function() {
                ipsyGraphJong.resize();
            });


            // 정시
            ipsyGraphJung.hideLoading();
            var option_jung = {
                    xAxis: [{
                        type: 'category',
                        data: arr_ihLclass,
                        gridIndex: 0,
                    },{
                        type: 'category',
                        data: ['비율'],
                        gridIndex: 1,
                    }],
                    yAxis: [{
                        type: 'value', gridIndex: 0
                    },{
                        type: 'value', gridIndex: 1
                    }],
                    series: [{
                        data: arr_jung,
                        label: {
                            show: true,
                            position: 'top',
                            formatter: function(d) {
                                return d.data.value + "%";
                            }
                        },
                        type: 'bar',
                        tooltip: {
                            formatter: function (item) {
                                return item.marker + "" + item.name + ": " + item.value + "%";
                            }
                        }
                    }, {
                        type: 'bar',
                        stack: '总量',
                        label: {
                            show: true,
                            position: 'inside',
                            formatter: function(d) {
                                return d.data.value + "%";
                            }
                        },
                        data: arr_entrance_jung,
                        xAxisIndex: 1,
                        yAxisIndex: 1,
                        tooltip: {
                            formatter: function (item) {
                                return item.marker + "" + item.name + ": " + item.value + "%";
                            }
                        }
                    }]
            };
            Object.assign(option_jung,option);
            ipsyGraphJung.setOption(option_jung);
            $(window).on("resize", function() {
                ipsyGraphJung.resize();
            });


            // 학생부교과
            ipsyGraphGyo.hideLoading();
            var option_gyo = {
                    xAxis: [{
                        type: 'category',
                        data: arr_ihLclass,
                        gridIndex: 0,
                    },{
                        type: 'category',
                        data: ['비율'],
                        gridIndex: 1,
                    }],
                    yAxis: [{
                        type: 'value', gridIndex: 0
                    },{
                        type: 'value', gridIndex: 1
                    }],
                    series: [{
                        data: arr_gyo,
                        label: {
                            show: true,
                            position: 'top',
                            formatter: function(d) {
                                return d.data.value + "%";
                            }
                        },
                        type: 'bar',
                        tooltip: {
                            formatter: function (item) {
                                return item.marker + "" + item.name + ": " + item.value + "%";
                            }
                        }
                    }, {
                        type: 'bar',
                        stack: '总量',
                        label: {
                            show: true,
                            position: 'inside',
                            formatter: function(d) {
                                return d.data.value + "%";
                            }
                        },
                        data: arr_entrance_gyo,
                        xAxisIndex: 1,
                        yAxisIndex: 1,
                        tooltip: {
                            formatter: function (item) {
                                return item.marker + "" + item.name + ": " + item.value + "%";
                            }
                        }
                    }]
            };
            Object.assign(option_gyo,option);
            ipsyGraphGyo.setOption(option_gyo);
            $(window).on("resize", function() {
                ipsyGraphGyo.resize();
            });

        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    }
}
