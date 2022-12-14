<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf"%>

<div id="list" style="display: none;">
    <div class="pageHeader">
        선도학생 커리어 패스
        <div class="btn_area">
            <ui-button class="btn_excel" v-on:click="viewExcel();">선도학생 추가(엑셀)</ui-button>
            <ui-button class="btn_write" v-on:click="viewCreate();">선도학생 등록</ui-button>
        </div>
    </div>
    <div class="pageContents">
        <div class="searchArea">
            <table class="searchBox">
                <colgroup>
                    <col width="10%">
                    <col width="20%">
                    <col width="10%">
                    <col width="20%">
                    <col width="10%">
                    <col width="20%">
                    <col width="10%">
                </colgroup>
                <tr>
                    <th>대학</th>
                    <td><ui-select v-model="gridRequest.search.daehakName" code-data="careerpath.readDaehakCode" first-name="전체"></ui-select></td>
                    <th>학과</th>
                    <td><ui-select v-model="gridRequest.search.hakbuName" v-bind:code-data="hakbuCodeData" first-name="전체"></ui-select></td>
                    <th>취업년도</th>
                    <td colspan="2">
                        <span><ui-input style="width:45%; height:23px;" v-model="gridRequest.search.startYear"></ui-input>
                         ~  <ui-input style="width:45%; height:23px;" v-model="gridRequest.search.endYear"></span></ui-input>
                    </td>
                </tr>
                <tr>
                    <th>학번</th>
                    <td><span> <ui-input type="text" v-model="gridRequest.search.hakbun"></ui-input></span></td>
                    <th>취업처</th>
                    <td><span> <ui-input type="text" v-model="gridRequest.search.remark"></ui-input></span></td>
                    <td><div></div></td>
                    <td></td><td></td>
                    <td>
                        <div class="btnArea_center">
                            <ui-button class="btn_total_search" v-on:click="service.readList(true);">조회</ui-button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>

        <ui-grid-paging v-bind:request="gridRequest" v-bind:result="gridResult" v-bind:read-list="service.readList">
        <table class="bbs_list">
            <thead>
                <tr>
                    <th width="100px" column-name="seqOrder">번호</th>
                    <th width="" column-name="daehakName">단과대학</th>
                    <th width="" column-name="hakbuName">학과</th>
                    <th width="" column-name="hakbun">학번</th>
                    <th width="" column-name="remark">취업처</th>
                    <th width="" column-name="position">직급</th>
                </tr>
            </thead>
            <tbody v-cloak>
                <tr v-for="row in gridResult.list" v-bind:key="row.seqOrder">
                    <td class="tac">{{ row.seqOrder }}</td>
                    <td class="tac">{{ row.daehakName }}</td>
                    <td class="tac">{{ row.hakbuName }}</td>
                    <td class="tac">
                        <a href="javascript:void(0);" v-on:click="viewDetail(row.hakbun);">
                            {{ row.hakbun.substring(0,4).concat("*****") }}
                        </a>
                    </td>
                    <td class="tac">{{ row.remark }}</td>
                    <td class="tac">{{ row.position }}</td>
                </tr>
            </tbody>
        </table>
        <div class="page-space tar">
        </div>
        </ui-grid-paging>
    </div>
</div>
<script>
vues.list = new Vue({
    el : '#list',
    data : {
        gridRequest: GridUtil.getRequestWithPaging({}, {seqOrder: "ASC"}),
        gridResult: {}
    },
    computed : {
        service : function() {
            return new CareerPathViewService(this);
        },
        formatter : function() {
            return CommonUtil.formatter;
        },
        hakbuCodeData: function() {
            return "careerpath.readHakbuCode," + this.gridRequest.search.daehakName;
        },
    },
    mounted: function() {
        this.service.readList();
    },
    methods: {
        // 생성 화면
        viewCreate : function() {
            CommonUtil.changeVue("edit");
        },
        // 상세 화면
        viewDetail : function(hakbun) {
            CommonUtil.changeVue("read", hakbun);
        },
        // 엑셀 화면
        viewExcel : function() {
            CommonUtil.popupVue('excelUpload');
        }
    }
});
</script>
