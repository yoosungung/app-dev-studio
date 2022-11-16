<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        서비스 로그
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
                    <th>API키</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.apiKey"></ui-input>
                    </td>
                    <th>호출일자</th>
                    <td >
                        <ui-date v-model="gridRequest.search.apiDtStart"></ui-date> ~
                        <ui-date v-model="gridRequest.search.apiDtEnd"></ui-date>
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
                        <th width="" column-name="apiKey" column-sort>API키</th>
                        <th width="" column-name="svcNm" column-sort>서비스명</th>
                        <th width="" column-name="callUserNM" column-sort>호출자명</th>
                        <th width="" column-name="callDt" column-sort>호출일자</th>
                        <th width="" column-name="callResult" column-sort>결과코드</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.svcHistId">
                        <td class="tac">{{ row.apiKey }}</td>
                        <td class="tac">{{ row.svcNm }}</td>
                        <td class="tac">{{ row.callUserNm }}</td>
                        <td class="tac">{{ formatter.datetime(row.callDt) }}</td>
                        <td class="tac">{{ row.callResult }}</td>
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
            return new ApiLogManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.readList();
    }
});
</script>
