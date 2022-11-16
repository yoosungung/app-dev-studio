<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        에러 로그
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
                    <th>에러 코드</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.errorCode"></ui-input>
                    </td>
                    <th>사용자명</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.userNm"></ui-input>
                    </td>
                </tr>
                <tr>
                    <th>에러일자</th>
                    <td>
                        <ui-date v-model="gridRequest.search.errorDtStart"></ui-date> ~
                        <ui-date v-model="gridRequest.search.errorDtEnd"></ui-date>
                    </td>
                    <th>페이지 경로</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.requstPath"></ui-input>
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
                        <th width="" column-name="errorCode" column-sort>에러 코드</th>
                        <th width="" column-name="userNm" column-sort>사용자명</th>
                        <th width="" column-name="requstPath" column-sort>페이지 경로</th>
                        <th width="" column-name="requstTy" column-sort>요청 유형</th>
                        <th width="" column-name="requstMthd" column-sort>요청 방식</th>
                        <th width="" column-name="rspnsSttusCode" column-sort>응답 코드</th>
                        <th width="" column-name="errorDt" column-sort="DESC">에러 일시</th>
                        <th width="" column-name="requstIp" column-sort>요청 IP</th>
                        <th width="" column-name="serverIp" column-sort>서버 IP</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.errorLogId">
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="viewDetail(row.errorLogId);">{{ row.errorCode }}</a>
                        </td>
                        <td class="tac">{{ row.userNm }}</td>
                        <td class="tal" style="word-break: break-all;">{{ row.requstPath }}</td>
                        <td class="tac">{{ row.requstTy }}</td>
                        <td class="tac">{{ row.requstMthd }}</td>
                        <td class="tac">{{ row.rspnsSttusCode }}</td>
                        <td class="tac">{{ formatter.datetime(row.errorDt) }}</td>
                        <td class="tac">{{ row.requstIp }}</td>
                        <td class="tac">{{ row.serverIp }}</td>
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
            return new ErrorLogManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.readList();
    },
    methods: {
        // 생성 화면
        viewCreate: function() {
            CommonUtil.popupVue("create");
        },
        // 상세 화면
        viewDetail: function(errorLogId) {
            CommonUtil.changeVue("read", errorLogId);
        }
    }
});
</script>
