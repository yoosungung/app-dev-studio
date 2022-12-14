var myChart;
function EntranceViewService(vue) {

    this.readList = function(search) {
        CommonUtil.axios()
        .post("readList", search)
        .then(function(response) {
            var data = {};
            data.nodes = [];
            data.links = [];
            /*ie 대응*/
            // response.data.map((v,i)=>v.source === "name" ? data.nodes.push({ "name" : v.target }) : data.links.push({ "source": v.source, "target": v.target, "value": v.value }) );
            for (var i=0; i<response.data.length; i++) {
                var v = response.data[i];
                if (v.source === "name") {
                    data.nodes.push({ "name" : v.target });
                } else {
                    data.links.push({ "source": v.source, "target": v.target, "value": v.value });
                }
            }
            /*ie 대응*/
            echarts.dispose(document.getElementById('entranceGraph'));
            var myChart = echarts.init(document.getElementById('entranceGraph'), 'macarons');
            var option = {
                    title: {
                        show: data.nodes.length === 0,
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
                    },
                    series: [
                        {
                            type: 'sankey',
                            data: data.nodes,
                            links: data.links,
                            focusNodeAdjacency: 'allEdges',
                            itemStyle: {
                                borderWidth: 1,
                                borderColor: '#aaa'
                            },
                            lineStyle: {
                                color: 'source',
                                curveness: 0.5
                            },
                            tooltip: {
                                formatter: function (item) {
                                    return item.marker + "" + item.name + ": " + item.value + "명";
                                }
                            }
                        }
                    ]
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