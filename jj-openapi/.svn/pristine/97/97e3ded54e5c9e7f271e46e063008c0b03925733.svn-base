<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        키 신청 현황
        <div class="btn_area">
            <ui-button class="btn_write" onclick="vues.list.viewCreate();">키 신청</ui-button>
        </div>
    </div>

    <div class="pageContents">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readList(true);">
                <colgroup>
                    <col width="10%">
                    <col width="25%">
                    <col width="7%">
                    <col width="32%">
                    <col>
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>상태</th>
                    <td>
                        <ui-select code-data="[KEY_STTUS]" first-name="(전체)" v-model="gridRequest.search.keySttus"></ui-select>
                    </td>
                    <th>신청일</th>

                    <td colspan="1">
                        <ui-date v-model="gridRequest.search.rqstdtBegin"></ui-date> ~
                        <ui-date v-model="gridRequest.search.rqstdtEnd"></ui-date>
                    </td>
                    <td>
                        <h4>
                            <ui-checkbox v-model="gridRequest.search.wholedate">
                                &nbsp;&nbsp;전체기간
                            </ui-checkbox>
                        </h4>
                    </td>
                    <td>
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
            <table class="bbs_list">
                <thead>
                    <tr>
                        <th width="" column-name="apiKey" column-sort>키</th>
                        <th width="" column-name="sttus" column-sort>상태</th>
                        <th width="" column-name="rqstdt" column-sort="DESC">신청일</th>
                        <th width="" column-name="keyUsePdBegin" column-sort>사용기간</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.svcKeyId">
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="viewDetail(row.svcKeyId);">{{ row.apiKey }}</a>
                        </td>
                        <td class="tac" v-if="row.sttusNm == '승인'" style="color: #0000ff; font-weight: bold;">
                            {{ row.sttusNm }}
                        </td>
                        <td class="tac" v-if="row.sttusNm == '반려'"  style="color: #ff0000; font-weight: bold;">
                            {{ row.sttusNm }}
                        </td>
                        <td class="tac" v-if="row.sttusNm == '신청'"  style="color: #009900; font-weight: bold;">
                            {{ row.sttusNm }}
                        </td>
                        <td class="tac">{{ formatter.date(row.rqstdt) }}</td>
                        <td class="tac">{{ formatter.date(row.keyUsePdBegin) }} ~ {{ formatter.date(row.keyUsePdEnd) }}</td>
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
            return new KeyStatusViewService(this);
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
            CommonUtil.changeVue("edit");
        },
        // 상세 화면
        viewDetail: function(svcKeyId) {
            CommonUtil.changeVue("read", svcKeyId);
        }
    }
});
</script>
