<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="dutySearchList" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readDutySearchList(true);">
                <colgroup>
                    <col width="12%">
                    <col width="">
                    <col width="12%">
                    <col width="">
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>구분</th>
                    <td>
                        <ui-select v-model="gridRequest.search.dtySe" code-data="/domain.main.tbComDty/dtySe" first-name="(전체)"></ui-select>
                    </td>
                    <th>검색어</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.searchText" auto-focus></ui-input>
                    </td>
                    <td>
                        <div class="btnArea_center">
                            <ui-button class="btn_total_search" v-on:click="service.readDutySearchList(true);">조회</ui-button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <!-- 검색조건 영역 끝 -->

        <!-- 결과목록 영역 시작 -->
        <ui-grid-paging v-bind:request="gridRequest" v-bind:result="gridResult" v-bind:read-list="service.readDutySearchList">
            <table class="bbs_list">
                <thead>
                    <tr>
                        <th width="" column-name="dtySeNm" column-sort="ASC">구분</th>
                        <th width="" column-name="dtyCode" column-sort>보직코드</th>
                        <th width="" column-name="dtyNm" column-sort>보직명</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.dtyId">
                        <td class="tac">{{ row.dtySeNm }}</td>
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="selectRow(row);">{{ row.dtyCode }}</a>
                        </td>
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="selectRow(row);">{{ row.dtyNm }}</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </ui-grid-paging>
        <!-- 결과목록 영역 시작 -->
    </div>
</div>

<script>
vues.dutySearchList = new Vue({
    el: '#dutySearchList',
    data: {
        gridRequest: GridUtil.getRequestWithPaging({}),
        gridResult: {}
    },
    computed: {
        service: function() {
            return new AuthorityManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function() {
            $(this.$el).dialog({
                title: "보직 검색",
                modal: true,
                width: 800,
                height: 600
            });
        },
        // 선택
        selectRow: function(row) {
            this.$emit("selectRow", row);
        }
    }
});
</script>
