<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        접속 로그
    </div>

    <div class="pageContents">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readList(true);">
                <colgroup>
                    <col width="10%">
                    <col width="35%">
                    <col width="10%">
                    <col>
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>로그인 ID</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.loginNm"></ui-input>
                    </td>
                    <th>호출일자</th>
                    <td >
                        <ui-date v-model="gridRequest.search.logDtStart"></ui-date> ~
                        <ui-date v-model="gridRequest.search.logDtEnd"></ui-date>
                    </td>
                    <td rowspan="2" style="vertical-align: bottom;">
                        <div class="btnArea_center">
                            <ui-button class="btn_total_search" v-on:click="service.readList(true);">조회</ui-button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <!-- 검색조건 영역 끝 -->

        <!-- 결과목록 영역 시작 -->
        <ui-grid-paging v-bind:request="gridRequest" v-bind:result="gridResult" v-bind:read-list="service.readList">
            <table class="bbs_list auto">
                <thead>
                    <tr>
                        <th width="" column-name="loginNm" column-sort>로그인 ID</th>
                        <th width="" column-name="loginTy" column-sort>로그인 타입</th>
                        <th width="" column-name="succesYn" column-sort>성공 여부</th>
                        <th width="" column-name="logDt" column-sort="DESC">호출일자</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.loginLogId">
                        <td class="tac">{{ row.loginNm }}</td>
                        <td class="tac">{{ row.loginTy }}</td>
                        <td class="tac">{{ getSuccesYn(row.succesYn) }}</td>
                        <td class="tac">{{ formatter.datetime(row.logDt) }}</td>
                    </tr>
                </tbody>
            </table>
        </ui-grid-paging>
        <!-- 결과목록 영역 시작 -->
    </div>
</div>

<script>
vues.list = new Vue({
    el: '#list',
    data: {
        gridRequest: GridUtil.getRequestWithPaging({}),
        gridResult: {}
    },
    computed: {
        service: function() {
            return new LoginLogManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.readList();
    },
    methods: {
        getSuccesYn: function(succesYn) {
            return succesYn ? "성공" : "실패";
        }
    }
});
</script>
