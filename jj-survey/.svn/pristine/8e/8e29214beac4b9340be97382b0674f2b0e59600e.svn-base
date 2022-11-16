<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/util/GridUtil.js"></script>

<div id="list">
    <div class="pageHeader">
        설문 목록
    </div>
    <div class="pageContents">
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
                        <td class="tal">{{ row.name }}</td>
                        <td class="tac">{{ row.pdDt }}</td>
                        <td class="tal" v-if="row.surveyStatus == 'N' || row.surveyStatus == 'I'">
                            <a href="javascript:void(0);" v-on:click="popSurvey(row.shrtenUrl);" >{{ row.surveyStatusNm }}</a>
                        </td>
                        <td class="tal" v-if="row.surveyStatus == 'S' || row.surveyStatus == 'D' || row.surveyStatus == 'R'">
                            {{ row.surveyStatusNm }}
                        </td>
                    </tr>
                </tbody>
            </table>
        </ui-grid-paging>
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
            return new MainViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.readList();
    },
    methods: {
        popSurvey: function(shrtenUrl) {
            window.open(shrtenUrl,"_blank");
        },
    }
});
</script>
