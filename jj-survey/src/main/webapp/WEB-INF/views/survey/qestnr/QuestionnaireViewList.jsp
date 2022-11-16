<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        설문 목록
        <div class="btn_area">
            <ui-button onclick="vues.list.service.readList();">새로고침</ui-button>
        </div>
    </div>

    <div class="pageContents">
        <!-- 결과목록 영역 시작 -->
        <ui-grid-paging v-bind:request="gridRequest" v-bind:result="gridResult" v-bind:read-list="service.readList">
            <table class="bbs_list">
                <thead>
                    <tr>
                        <th width="" column-name="name" column-sort>설문명</th>
                        <th width="" column-name="pdDt" column-sort>기간</th>
                        <th width="150px" column-name="surveyStatusNm" column-sort>진행여부</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="row in gridResult.list">
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="viewSurvey(row);">{{ row.name }}</a>
                        </td>
                        <td class="tac">{{ row.pdDt }}</td>
                        <td class="tac">{{ row.surveyStatusNm }}</td>
                    </tr>
                </tbody>
            </table>`
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
            return new QuestionnaireViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.readList();
    },
    methods: {
        // 설문조사 화면
        viewSurvey: function(row) {
            window.open(CommonUtil.contextPath + "/survey/" + row.id + "?conectLink=SITE", "jj-survey-" + row.surveyPersonId);
        }
    }
});
</script>
