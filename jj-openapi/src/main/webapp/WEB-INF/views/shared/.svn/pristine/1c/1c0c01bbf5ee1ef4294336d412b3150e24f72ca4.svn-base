<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        사용자 목록
    </div>

    <div class="pageContents">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readList(true);">
                <colgroup>
                    <col width="10%">
                    <col>
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>사용자명</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.name"></ui-input>
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
                        <th width="100px" column-name="emplNo" column-sort="ASC">학/사번</th>
                        <th width="" column-name="koreanNm" column-sort>이름</th>
                        <th width="" column-name="ofcpsNm" column-sort>직위</th>
                        <th width="" column-name="emailAdres" column-sort>이메일 주소</th>
                        <th width="" column-name="deptNames" column-sort>학과/부서</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.personId">
                        <td class="tac">{{ row.emplNo }}</td>
                        <td class="tac">{{ row.koreanNm }}</td>
                        <td class="tac">{{ row.ofcpsNm }}</td>
                        <td class="tac">{{ row.emailAdres }}</td>
                        <td class="tac">{{ row.deptNames }}</td>
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
            return new PersonManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.readList();
    },
    methods: {
    }
});
</script>
