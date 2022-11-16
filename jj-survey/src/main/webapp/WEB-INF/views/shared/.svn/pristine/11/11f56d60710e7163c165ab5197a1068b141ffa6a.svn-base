<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="userSearchList" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readUserSearchList(true);">
                <colgroup>
                    <col width="12%">
                    <col width="">
                    <col width="12%">
                    <col width="">
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>사번</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.emplNo" auto-focus></ui-input>
                    </td>
                    <th>이름</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.name"></ui-input>
                    </td>
                    <td>
                        <div class="btnArea_center">
                            <ui-button class="btn_total_search" v-on:click="service.readUserSearchList(true);">조회</ui-button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <!-- 검색조건 영역 끝 -->

        <!-- 결과목록 영역 시작 -->
        <ui-grid-paging v-bind:request="gridRequest" v-bind:result="gridResult" v-bind:read-list="service.readUserSearchList">
            <table class="bbs_list">
                <thead>
                    <tr>
                        <th width="" column-name="deptNm1" column-sort>대학(소속)</th>
                        <th width="" column-name="deptNm2" column-sort>학과(부서)</th>
                        <th width="" column-name="emplNo" column-sort="ASC">사번</th>
                        <th width="" column-name="koreanNm" column-sort>이름</th>
                        <th width="" column-name="rspofcNm" column-sort>직급</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list">
                        <td class="tac">{{ row.deptNm1 }}</td>
                        <td class="tac">{{ row.deptNm2 }}</td>
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="selectRow(row);">{{ row.emplNo }}</a>
                        </td>
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="selectRow(row);">{{ row.koreanNm }}</a>
                        </td>
                        <td class="tac">{{ row.rspofcNm }}</td>
                    </tr>
                </tbody>
            </table>
        </ui-grid-paging>
        <!-- 결과목록 영역 시작 -->
    </div>
</div>

<script>
vues.userSearchList = new Vue({
    el: '#userSearchList',
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
                title: "사용자 검색",
                modal: true,
                width: 1000,
                height: 750
            });
        },
        // 선택
        selectRow: function(row) {
            this.$emit("selectRow", row);
        }
    }
});
</script>
