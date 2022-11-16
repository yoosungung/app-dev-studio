<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        SMS 로그
        <div class="btn_area">
            <ui-button class="btn_write" onclick="vues.list.viewCreate();">발송 테스트</ui-button>
        </div>
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
                    <th>검색어</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.searchText"></ui-input>
                    </td>
                    <th>메시지 상태</th>
                    <td>
                        <ui-select v-model="gridRequest.search.sndngSttus" code-data="/domain.main.tbComSms/tranStatus" first-name="(전체)"></ui-select>
                    </td>
                    <td rowspan="2" style="vertical-align: bottom;">
                        <div class="btnArea_center">
                            <ui-button class="btn_total_search" v-on:click="service.readList(true);">조회</ui-button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>전송일자</th>
                    <td>
                        <ui-date v-model="gridRequest.search.tranDateStart"></ui-date> ~
                        <ui-date v-model="gridRequest.search.tranDateEnd"></ui-date>
                    </td>
                    <th>전송결과</th>
                    <td>
                        <ui-select v-model="gridRequest.search.tranRslt" code-data="/domain.main.tbComSms/tranRslt" first-name="(전체)"></ui-select>
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
                        <th width="110px" column-name="tranEtc2" column-sort>메시지 키</th>
                        <th width="" column-name="tranEtc1" column-sort>메시지 제목</th>
                        <th width="150px" column-name="tranCallback" column-sort>송신자 전화번호</th>
                        <th width="150px" column-name="tranPhone" column-sort>수신자 전화번호</th>
                        <th width="90px" column-name="tranEtc3" column-sort>수신자 이름</th>
                        <th width="90px" column-name="tranTypeNm" column-sort>메시지 유형</th>
                        <th width="" column-name="tranStatusNm" column-sort>메시지 상태</th>
                        <th width="" column-name="tranRsltNm" column-sort>전송결과</th>
                        <th width="160px" column-name="tranDate" column-sort="DESC">전송일시</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.smsId">
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="viewDetail(row.smsId);">{{ row.tranEtc2 }}</a>
                        </td>
                        <td class="tac">{{ row.tranEtc1 }}</td>
                        <td class="tac">{{ row.tranCallback }}</td>
                        <td class="tac">{{ row.tranPhone }}</td>
                        <td class="tac">{{ row.tranEtc3 }}</td>
                        <td class="tac">{{ row.tranTypeNm }}</td>
                        <td class="tac">{{ row.tranStatusNm }}</td>
                        <td class="tac">{{ row.tranRsltNm }}</td>
                        <td class="tac">{{ formatter.datetime(row.tranDate) }}</td>
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
            return new SmsLogManageViewService(this);
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
        viewDetail: function(smsId) {
            CommonUtil.changeVue("read", smsId);
        }
    }
});
</script>
