<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        직무 목록
        <div class="btn_area">
            <ui-button class="btn_write" onclick="vues.list.viewCreate();">직무 등록</ui-button>
        </div>
    </div>

    <div class="pageContents">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readList(true);">
                <colgroup>
                    <col width="10%">
                    <col>
                    <col width="10%">
                    <col>
                    <col width="10%">
                    <col>
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>직무 구분</th>
                    <td>
                        <ui-select v-model="gridRequest.search.dtySe" code-data="/domain.main.tbComDty/dtySe" first-name="(전체)"></ui-select>
                    </td>
                    <th>검색어</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.searchText"></ui-input>
                    </td>
                    <th>사용 여부</th>
                    <td>
                        <ui-select v-model="gridRequest.search.useYn" code-data="/common/useYn" first-name="(전체)"></ui-select>
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
                        <th width="" column-name="dtySeNm" column-sort="ASC">직무 구분</th>
                        <th width="" column-name="dtyCode" column-sort="ASC">직무 코드</th>
                        <th width="" column-name="dtyNm" column-sort>직무 이름</th>
                        <th width="" column-name="useYnNm" column-sort>사용 여부</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.dtyId">
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="viewDetail(row.dtyId);">{{ row.dtySeNm }}</a>
                        </td>
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="viewDetail(row.dtyId);">{{ row.dtyCode }}</a>
                        </td>
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="viewDetail(row.dtyId);">{{ row.dtyNm }}</a>
                        </td>
                        <td class="tac">{{ row.useYnNm }}</td>
                    </tr>
                </tbody>
            </table>
        </ui-grid-paging>
        <!-- 결과목록 영역 시작 -->
    </div>
</div>

<script>
var vues = {};

vues.list = new Vue({
    el: '#list',
    data: {
        gridRequest: GridUtil.getRequestWithPaging({}),
        gridResult: {}
    },
    computed: {
        service: function() {
            return new DutyManageViewService(this);
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
        viewDetail: function(dtyId) {
            CommonUtil.changeVue("read", dtyId);
        }
    }
});
</script>
