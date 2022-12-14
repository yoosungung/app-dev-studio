<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        그룹 관리
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
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>그룹명</th>
                    <td>
                        <input type="text" v-model="gridRequest.search.groupNm">
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
                        <th width="" column-name="groupNm" column-sort>그룹</th>
                        <th width="" column-name="groupDc" column-sort>그룹 설명</th>
                        <th width="150px" column-name="cnrsYnNm" column-sort>공유 여부</th>
                        <th width="*" column-name="registDeptNm" column-sort>등록 부서</th>
                        <th width="100px" column-name="registDt" column-sort="DESC">등록일</th>
                        <th width="100px" column-name="registPsnNm" column-sort>등록자</th>
                        <th width="100px" column-name="personCo" column-sort>대상자 수</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="row in gridResult.list" v-bind:key="row.surveyGroupId">
                        <td class="tal">
                            <a href="javascript:void(0);" v-on:click="viewDetail(row.surveyGroupId);">{{ row.groupNm }}</a>
                        </td>
                        <td class="tal">{{ row.groupDc }}</td>
                        <td class="tac">{{ row.cnrsYnNm }}</td>
                        <td class="tac">{{ row.registDeptNm }}</td>
                        <td class="tac">{{ formatter.date(row.registDt) }}</td>
                        <td class="tac">{{ row.registPsnNm }}</td>
                        <td class="tac">{{ formatter.number(row.personCo) }}</td>
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
            return new GroupManageViewService(this);
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
        viewDetail: function(surveyGroupId) {
            CommonUtil.changeVue("read", surveyGroupId);
        }
    }
});
</script>
