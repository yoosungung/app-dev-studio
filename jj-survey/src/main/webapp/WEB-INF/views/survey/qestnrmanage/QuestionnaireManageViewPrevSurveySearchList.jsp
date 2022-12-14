<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="prevSurveySearchList" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readPrevSurveySearchList(true);">
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
                            <ui-button class="btn_total_search" v-on:click="service.readPrevSurveySearchList(true);">조회</ui-button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <!-- 검색조건 영역 끝 -->

        <!-- 결과목록 영역 시작 -->
        <ui-grid-paging v-bind:request="gridRequest" v-bind:result="gridResult" v-bind:read-list="service.readPrevSurveySearchList">
            <table class="bbs_list">
                <thead>
                    <tr>
                        <th width="" column-name="name" column-sort>설문지</th>
                        <th width="" column-name="surveyRealmNm" column-sort>설문 분야</th>
                        <th width="150px" column-name="statusName" column-sort>상태</th>
                        <th width="200px" column-name="startDt" column-sort="DESC">설문 기간</th>
                        <th width="150px" column-name="cnrsYnNm" column-sort>공유 여부</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.surveyQestnrId">
                        <td class="tal">
                            <a href="javascript:void(0);" v-on:click="selectRow(row);">{{ row.name }}</a>
                        </td>
                        <td class="tac">{{ row.surveyRealmNm }}</td>
                        <td class="tac">{{ row.statusName }}</td>
                        <td class="tac">{{ formatter.date(row.startDt) }} ~ {{ formatter.date(row.endDt) }}</td>
                        <td class="tac">{{ row.cnrsYnNm }}</td>
                    </tr>
                </tbody>
            </table>
        </ui-grid-paging>
        <!-- 결과목록 영역 시작 -->
    </div>
</div>

<script>
vues.prevSurveySearchList = new Vue({
    el: '#prevSurveySearchList',
    data: {
        gridRequest: GridUtil.getRequestWithPaging({}),
        gridResult: {}
    },
    computed: {
        service: function() {
            return new QuestionnaireManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function() {
            this.service.readPrevSurveySearchList();

            $(this.$el).dialog({
                title: "이전 설문지 검색",
                modal: true,
                width: 1000,
                height: 600
            });
        },
        // 선택
        selectRow: function(row) {
            if (!confirm("\"" + row.name + "\" 설문지를 불러오시겠습니까?")) {
                return;
            }

            var vue = this;

            vues.edit.service.readPrevSurvey(row.surveyQestnrId, function() {
                $(vue.$el).dialog("close");
            });
        }
    }
});
</script>
