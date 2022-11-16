/**
 * @param vue
 * @returns
 */
function DropOutViewService(vue) {
    /**
    * 목록 조회
    */
    this.readList = function() {
        CommonUtil.axios()
        .post("readList", vue.search)
        .then(function(response) {
            var data = response.data;

             //grid variables
            var tableColumnList = [];
            var tableDataList = [];
            var tableData = {};

            tableColumnList = [{header: "이름", name: "ipsyName", align: "center", width: "300", sortable: true },
                {header: "학번", name: "ipsyHakbun", align: "center", width: "300", sortable: true}];

            for(let i=0; i<data.length; i++) {
                tableDataList.push({"ipsyName": data[i].ipsyName, "ipsyHakbun": data[i].ipsyHakbun});
            }

            const Grid = tui.Grid
            $("#grid").html("");
            var grid = new Grid({
                el: document.getElementById('grid'),
                columnOptions: {minWidth: 100, resizable: true},
                columns: tableColumnList,
                data: tableDataList,
                bodyHeight: 700,
                theme: 'striped'
            });

            grid.on("click", function(event) {
                readStudent(grid.getRow(event.rowKey).ipsyHakbun);
            });
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

/**
 * 학생 조회
 */
var readStudent = function(hakbun) {
    CommonUtil.axios()
    .get("readStudent/" + hakbun)
    .then(function(response) {
        if (response != null) {
            vues.list.student= response.data;
            var student = response.data;

            var fontStyle = {
                    'font-size': '18px',
                    'font-weight': 'bold'
            };

            var textColor = {
                ipsyPaper21: {color: ""},
                ipsyBongsaTime: {color: ""},
                ipsyPaperAve: {color: ""},
                ipsyHuboSeq: {color: ""},
                ipsyKumjung: {color: ""},
                ipsyHigh100: {color: ""},
                graduatedYy: {color: ""},
                ipsyGasanJumsu11: {color: ""},
                ipsyGasanJumsu23: {color: ""},
                ipsyGasanJumsu24: {color: ""},
                ipsyMeunjubAve: {color: ""},
                ipsyPaper11: {color: ""},
                ipsyJangIphak: {color: ""},
                ipsyTotalJumsu1: {color: ""},
                ipsyJangSuup: {color: ""}
            };

            if (student.ipsyPaper21 <= 19.7) {
                textColor.ipsyPaper21.color = "red";
                Object.assign(textColor.ipsyPaper21, fontStyle);
            }
            if (student.ipsyBongsaTime >= 13.5) {
                textColor.ipsyBongsaTime.color = "red";
                Object.assign(textColor.ipsyBongsaTime, fontStyle);
            }
            if (student.ipsyPaperAve >= 68.6 && student.ipsyPaperAve <= 80) {
                textColor.ipsyPaperAve.color = "red";
                Object.assign(textColor.ipsyPaperAve, fontStyle);
            }
            if (student.ipsyHuboSeq >= 15) {
                textColor.ipsyHuboSeq.color = "red";
                Object.assign(textColor.ipsyHuboSeq, fontStyle);
            }
            if (student.ipsyKumjung >= 87) {
                textColor.ipsyKumjung.color = "red";
                Object.assign(textColor.ipsyKumjung, fontStyle);
            }
            if (student.ipsyHigh100 >= 4.4) {
                textColor.ipsyHigh100.color = "red";
                Object.assign(textColor.ipsyHigh100, fontStyle);
            }
            if (student.graduatedYy >= 7) {
                textColor.graduatedYy.color = "red";
                Object.assign(textColor.graduatedYy, fontStyle);
            }
            if (student.ipsyGasanJumsu11 >= 702 && ipsyGasanJumsu11 <= 512) {
                textColor.ipsyGasanJumsu11.color = "red";
                Object.assign(textColor.ipsyGasanJumsu11, fontStyle);
            }
            if (student.ipsyGasanJumsu23 >= 160) {
                textColor.ipsyGasanJumsu23.color = "red";
                Object.assign(textColor.ipsyGasanJumsu23, fontStyle);
            }
            if (student.ipsyGasanJumsu24 >= 695) {
                textColor.ipsyGasanJumsu24.color = "red";
                Object.assign(textColor.ipsyGasanJumsu24, fontStyle);
            }
            if (student.ipsyMeunjubAve >= 82) {
                textColor.ipsyMeunjubAve.color = "red";
                Object.assign(textColor.ipsyMeunjubAve, fontStyle);
            }
            if (student.ipsyPaper11 >= 28.5) {
                textColor.ipsyPaper11.color = "red";
                Object.assign(textColor.ipsyPaper11, fontStyle);
            }
            if (student.ipsyJangIphak <= 57000) {
                textColor.ipsyJangIphak.color = "red";
                Object.assign(textColor.ipsyJangIphak, fontStyle);
            }
            if (student.ipsyTotalJumsu1 >= 930) {
                textColor.ipsyTotalJumsu1.color = "red";
                Object.assign(textColor.ipsyTotalJumsu1, fontStyle);
            }
            if (student.ipsyJangSuup >= 3645000) {
                textColor.ipsyJangSuup.color = "red";
                Object.assign(textColor.ipsyJangSuup, fontStyle);
            }

            vues.list.textColor = textColor;
        }
    })
    ["catch"](function(error) {
        CommonUtil.showAxiosError(error);
    });
};

