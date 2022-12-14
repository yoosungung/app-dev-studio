var ipsyGraphJong;
var ipsyGraphJung;
var ipsyGraphGyo;

function IpsyViewService(vue) {

    // 연도별 표보기
    this.readList = function(search) {
        CommonUtil.axios()
        .post("readList", search || {})
        .then(function(response) {

            if (response.data === null || response.data === undefined || response.data.length === 0) {
                html += '<tr><td colspan="5" rowspan="9" class="tac">NO DATA</td></tr>';
                $('#tbody').html(html);
            } else {
                $('.ipsy_yy').text(response.data[0].ipsyYy || new Date().getFullYear())

                var html = [];

                $(response.data).each(function(i,v) {
                    html.push('<tr>');
                    html.push('    <td>'+v.header+'</td>');
                    html.push('    <td>'+v.accpJong+'</td>');
                    html.push('    <td>'+v.accpGyo+'</td>');
                    html.push('    <td>'+v.minJong+'</td>');
                    html.push('    <td>'+v.minGyo+'</td>');
                    html.push('    <td>'+v.realJong+'</td>');
                    html.push('    <td>'+v.realGyo+'</td>');
                    html.push('    <td>'+v.highGyo+'</td>');
                    html.push('    <td>'+v.highGyo+'</td>');
                    html.push('</tr>');
                });

                $('#tbody').html(html.join(''));
            }
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    }


    this.readChartList = function() {

        CommonUtil.axios()
        .post("readChartList", {})
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

            var arr_header = ["경쟁률","최소경쟁률","실질경쟁률"];
            var arr_ipsyYy = {};
            var obj_jong = { arrAccpRate: [], arrMinAccpRate: [], arrRealAccpRate: [] };
            var obj_gyo = { arrAccpRate: [], arrMinAccpRate: [], arrRealAccpRate: [] };
            var obj_jung = { arrAccpRate: [], arrMinAccpRate: [], arrRealAccpRate: [] };

            for (var i=0; i<response.data.length; i++) {
                var v = response.data[i];

                if (v.ipsyGubunName === "학생부종합") {
                    obj_jong["arrAccpRate"].push(v.ipsyAccpRate);
                    obj_jong["arrMinAccpRate"].push(v.ipsyMinAccpRate);
                    obj_jong["arrRealAccpRate"].push(v.ipsyRealAccpRate);
                } else if (v.ipsyGubunName === "정시") {
                    obj_jung["arrAccpRate"].push(v.ipsyAccpRate);
                    obj_jung["arrMinAccpRate"].push(v.ipsyMinAccpRate);
                    obj_jung["arrRealAccpRate"].push(v.ipsyRealAccpRate);
                } else if (v.ipsyGubunName === "학생부교과") {
                    obj_gyo["arrAccpRate"].push(v.ipsyAccpRate);
                    obj_gyo["arrMinAccpRate"].push(v.ipsyMinAccpRate);
                    obj_gyo["arrRealAccpRate"].push(v.ipsyRealAccpRate);
                }

                arr_ipsyYy[v.ipsyYy] = 1;
            }

            /*공통 옵션*/
            var option = {
                title: [
                        {
                            show: Object.keys(arr_ipsyYy).length === 0,
                            textStyle: {
                                color: "grey",
                                fontSize: 20
                            },
                            text: "No data",
                            left: "center",
                            top: "center",
                        }
                    ],
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: arr_header
                },
                grid: {
                    top: '10%',
                    height: "70%",
                    left: '3%',
                    right: '7%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: Object.keys(arr_ipsyYy)
                },
                yAxis: {
                    type: 'value'
                },
            };

            // 학생부종합
            ipsyGraphJong.hideLoading();
            var option_jong = {
                series: [
                    {
                        name: arr_header[0],
                        type: 'line',
                        data: obj_jong["arrAccpRate"],
                    },
                    {
                        name: arr_header[1],
                        type: 'line',
                        data: obj_jong["arrMinAccpRate"],
                    },
                    {
                        name: arr_header[2],
                        type: 'line',
                        data: obj_jong["arrRealAccpRate"],
                    },
                ]
            };
            Object.assign(option_jong,option);
            ipsyGraphJong.setOption(option_jong);
            $(window).on("resize", function() {
                ipsyGraphJong.resize();
            });


            // 정시
            ipsyGraphJung.hideLoading();
            var option_jung = {
                series: [
                    {
                        name: arr_header[0],
                        type: 'line',
                        data: obj_jung["arrAccpRate"],
                    },
                    {
                        name: arr_header[1],
                        type: 'line',
                        data: obj_jung["arrMinAccpRate"],
                    },
                    {
                        name: arr_header[2],
                        type: 'line',
                        data: obj_jung["arrRealAccpRate"],
                    },
                ]
            };
            Object.assign(option_jung,option);
            ipsyGraphJung.setOption(option_jung);
            $(window).on("resize", function() {
                ipsyGraphJung.resize();
            });


            // 학생부교과
            ipsyGraphGyo.hideLoading();
            var option_gyo = {
                series: [
                    {
                        name: arr_header[0],
                        type: 'line',
                        data: obj_gyo["arrAccpRate"],
                    },
                    {
                        name: arr_header[1],
                        type: 'line',
                        data: obj_gyo["arrMinAccpRate"],
                    },
                    {
                        name: arr_header[2],
                        type: 'line',
                        data: obj_gyo["arrRealAccpRate"],
                    },
                ]
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
