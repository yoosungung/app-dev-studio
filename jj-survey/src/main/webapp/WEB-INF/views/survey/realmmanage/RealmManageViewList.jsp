<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        설문 분야 관리
        <div class="btn_area">
            <ui-button class="btn_write" onclick="vues.list.viewCreate();">등록</ui-button>
        </div>
    </div>

    <div class="pageContents">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readList(true);">
                <colgroup>
                    <col width="12%">
                    <col width="">
                    <col width="12%">
                    <col width="">
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>설문 분야</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.surveyRealmNm"></ui-input>
                    </td>
                    <th>설명</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.surveyRealmDc"></ui-input>
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
                        <th width="200px" column-name="surveyRealmNm" column-sort="ASC">설문 분야</th>
                        <th width="" column-name="surveyRealmDc" column-sort>설명</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.surveyRealmId">
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="viewDetail(row.surveyRealmId);">{{ row.surveyRealmNm }}</a>
                        </td>
                        <td class="tal">{{ row.surveyRealmDc }}</td>
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
            return new RealmManageViewService(this);
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
        viewDetail: function(surveyRealmId) {
            CommonUtil.changeVue("read", surveyRealmId);
        }
    }
});
</script>
