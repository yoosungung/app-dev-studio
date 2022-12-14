function SuperCompetenceViewService(vue) {
    this.drawGraph = {};
    this.drawGraph["dataGraph"] = function(search) {
        var readOption = "";
        if(search.daehakName == "" || search.daehakName == null) {
            readOption = "readAvgDaehakData";
        }
        else if(search.hakbuName == "") {
            readOption = "readDaehakYearData";
        }
        else if(search.hakbuName != "") {
            readOption = "readHakbuData";
        }
        CommonUtil.axios()
        .post(readOption, search)
        .then(function(response) {
            var data = response.data;

            //echart variables
            var dataName = [];
            var dataValueList = [];

            var dataValue = {
                    name : "",
                    value : []
                }
            //grid variables
            var tableColumnList = [];
            var tableColumn = {};
            var tableDataList = [];
            var tableData = {};


            for (var i=0; i<data.length; i++) {
                if(readOption === "readDaehakYearData") {
                    dataName.push(data[i].hakgYear);
                    dataValue.name = data[i].hakgYear;

                    tableColumn.header = data[i].hakgYear;
                    tableColumn.name = data[i].hakgYear;

                }
                else if (readOption === "readDaehakData" || readOption === "readAvgDaehakData"){
                    dataName.push(data[i].daehakName);
                    dataValue.name = data[i].daehakName;

                    tableColumn.header = data[i].daehakName;
                    tableColumn.name = data[i].daehakName;
                }
                else if (readOption === "readHakbuData" || readOption === "readAvgHakbuData") {
                    dataName.push(data[i].hakgYear);
                    dataValue.name = data[i].hakgYear;

                    tableColumn.header = data[i].hakgYear;
                    tableColumn.name = data[i].hakgYear;
                }
                tableColumn.sortable = true;
                tableColumn.align = "center";
                dataValue.value.push(data[i].sPoint);
                dataValue.value.push(data[i].uPoint);
                dataValue.value.push(data[i].pPoint);
                dataValue.value.push(data[i].ePoint);
                dataValue.value.push(data[i].rPoint);

                var copy = copyObject(dataValue);
                dataValueList.push(copy);
                dataValue.value = [];

                var copy = copyObject(tableColumn);
                tableColumnList.push(copy);
                tableColumn = {};
            }

            var rowIds = ['s', 'u', 'p', 'e', 'r'];
            var headers = ['??????/??????(S)', '????????????(U)', '????????????(P)', '????????????(E)', '+?????????(R)'];

            for (var i=0; i<rowIds.length; i++) {
                tableData.header = headers[i];
                tableData.id = rowIds[i];
                for (var j=0; j<data.length; j++) {
                    if (readOption === "readDaehakData" || readOption === "readAvgDaehakData") {
                        tableData[data[j].daehakName] = data[j][rowIds[i] + "Point"];
                    }
                    else if (readOption === "readDaehakYearData" || readOption === "readHakbuData" || readOption === "readAvgHakbuData") {
                        tableData[data[j].hakgYear] = data[j][rowIds[i] + "Point"];
                    }
                }
                var copy = copyObject(tableData);
                tableDataList.push(copy);
                tableData = {};
            }
            tableColumnList.unshift({header: "???", name: "header", align: "center", width: "200"});

            const Grid = tui.Grid
            $("#grid").html("");
            var grid = new Grid({
                el: document.getElementById('grid'),
                columnOptions: {minWidth: 100, resizable: true},
                columns: tableColumnList,
                data: tableDataList,
                bodyHeight: 220,
                theme: 'striped'
            });

            Grid.applyTheme('striped');

            var myChart = echarts.init(document.getElementById('dataGraph'));
            var option = {
                title: {
                    text: '??????/?????????',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    top : '5%',
                    data: dataName
                },
                radar: {
                    axisLabel: {
                        show: true,
                        showMinLabel: false
                    },
                    splitArea: {
                        show: false
                    },
                    name: {
                        textStyle: {
                            color: '#fff',
                            backgroundColor: '#999',
                            borderRadius: 3,
                            padding: [3, 5]
                        }
                    },
                    indicator: [
                        { name: '??????,??????', max: 65},
                        { name: '????????????', max: 65},
                        { name: '????????????', max: 65},
                        { name: '????????????', max: 65},
                        { name: '+??? ??????', max: 65}
                    ],
                    center: ['50%', '60%']
                },
                series: [{
                    name: 'SUPER',
                    type: 'radar',
                    data: dataValueList,
                    label: {
                        show: false
                    }
                }]
            };
            myChart.setOption(option);

            $(window).on("resize", function() {
                myChart.resize();
            });
        })
        ["catch"](function(error) {
            CommonUtil.showAxiosError(error);
        });
    }

    this.drawGraph["avgGraph"] = function(search) {
        if(search.daehakName == "") {
            readOption = "readTotalAvgData";
        }
        var readOption = (search.daehakName == "" ) ?  "readTotalAvgData" : "readTotalAvgDaehakData";
        CommonUtil.axios()
        .post(readOption, search)
        .then(function(response) {
            var dataValueList = [];
            var dataAvg = response.data[0]

            var dataValue = {
                    name : "",
                    value : []
                }
            for (var i=0; i<Object.keys(dataAvg).length; i++) {
                var key = Object.keys(dataAvg)[i];
                dataValue.value.push(dataAvg[key]);
            }
            dataValue.name = '????????????';
            dataValueList.push(dataValue);

            var myChart = echarts.init(document.getElementById('avgGraph'));
            var option = {
                title: {
                    text: '????????????',
                    left: 'center'
                },
                tooltip: {},
                radar: {
                    axisLabel: {
                        show: true,
                        showMinLabel: false
                    },
                    splitArea: {
                        show: false
                    },
                    name: {
                        textStyle: {
                            color: '#fff',
                            backgroundColor: '#999',
                            borderRadius: 3,
                            padding: [3, 5],
                            position : 'none'
                        }
                    },
                    indicator: [
                        { name: '??????,??????', max: 65},
                        { name: '????????????', max: 65},
                        { name: '????????????', max: 65},
                        { name: '????????????', max: 65},
                        { name: '+??? ??????', max: 65}
                    ],
                    center: ['50%', '60%']
                },
                series: [{
                    name: '????????????',
                    type: 'radar',
                    data: dataValueList
                }],
                opts: {
                    width: "auto"
                }
            };
            myChart.setOption(option);

            $(window).on("resize", function() {
                myChart.resize();
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

