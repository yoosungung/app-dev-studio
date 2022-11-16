<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        설문 결과 목록
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
                    <th>설문 이름</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.name"></ui-input>
                    </td>
                    <th>설문 분야</th>
                    <td>
                        <ui-select v-model="gridRequest.search.surveyRealmId" code-data="survey.surveyRealmId" first-name="(전체)"></ui-select>
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
                        <th width="" column-name="name" column-sort>설문 이름</th>
                        <th width="150px" column-name="surveyRealmNm" column-sort>설문 분야</th>
                        <th width="200px" column-name="pdDt" column-sort="DESC">설문 기간</th>
                        <th width="120px" column-name="endYnNm" column-sort>종료 여부</th>
                        <th width="100px" column-name="personCo" column-sort>대상자 수</th>
                        <th width="100px" column-name="surveyCo" column-sort>응답자 수<br>(전체)</th>
                        <th width="100px" column-name="submitCo" column-sort>응답자 수<br>(제출)</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.surveyQestnrId">
                        <td class="tal">
                            <a href="javascript:void(0);" v-on:click="viewDetail(row.surveyQestnrId);">{{ row.name }}</a>
                        </td>
                        <td class="tac">{{ row.surveyRealmNm }}</td>
                        <td class="tac">{{ row.pdDt }}</td>
                        <td class="tac">{{ row.endYnNm }}</td>
                        <td class="tac">{{ row.personCo }}</td>
                        <td class="tac">{{ row.surveyCo }}</td>
                        <td class="tac">{{ row.submitCo }}</td>
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
            return new SurveyResultViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.readList();
    },
    methods: {
        // 상세 화면
        viewDetail: function(surveyQestnrId) {
            CommonUtil.changeVue("read", surveyQestnrId);
        },
        // 대상자 목록 화면
        viewPersonList: function(row) {
            CommonUtil.popupVue("personList", row.surveyQestnrId);
        }
    }
});
</script>
