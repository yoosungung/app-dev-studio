<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        코드 관리
        <div class="btn_area">
            <ui-button class="btn_write" onclick="vues.list.viewCreate();">코드 등록</ui-button>
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
                    <th>사용여부</th>
                    <td>
                        <ui-select code-data="/common/useYn" first-name="(전체)" v-model="gridRequest.search.useYn"></ui-select>
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
                        <th width="" column-name="codeGroup" column-sort="ASC">코드그룹</th>
                        <th width="" column-name="codeGroupNm" column-sort>코드그룹 이름</th>
                        <th width="" column-name="codeValueCo" column-sort>코드 갯수</th>
                        <th width="" column-name="codeGroupDc" column-sort>코드그룹 설명</th>
                        <th width="" column-name="useYnNm" column-sort>사용여부</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.codeGroupId">
                        <td class="tal">
                            <a href="javascript:void(0);" v-on:click="viewDetail(row.codeGroupId);">{{ row.codeGroup }}</a>
                        </td>
                        <td class="tal">{{ row.codeGroupNm }}</td>
                        <td class="tac">{{ row.codeValueCo }}</td>
                        <td class="tal">{{ row.codeGroupDc }}</td>
                        <td class="tac">{{ row.useYnNm }}</td>
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
            return new CodeManageViewService(this);
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
        viewDetail: function(codeGroupId) {
            CommonUtil.changeVue("read", codeGroupId);
        }
    }
});
</script>
