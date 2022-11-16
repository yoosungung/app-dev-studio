<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<style type="text/css">
table {
    width: 100%;
}
.panel-height-helper{
    height:350px;
}
</style>

<div id="read" style="display: none;">
    <div class="pageHeader">
        학습 분석 - 수강학생 분석
    </div>
    <div class="pageContents">
        <div class="searchArea">
            <table class="searchBox">
                <c:if test="true">
                <colgroup>
                    <col width="4%">
                    <col width="12%">
                    <col width="4%">
                    <col width="12%">
                    <col width="4%">
                    <col width="12%">
                    <col width="4%">
                    <col width="12%">
                    <col width="4%">
                    <col width="12%">
                    <col width="4%">
                    <col width="12%">
                </colgroup>
                <tr>
                    <th>연도</th>
                    <td>
                        <ui-select v-model="search.yearData" code-data="analysis.readYear"></ui-select>
                    </td>
                    <th>학기</th>
                    <td>
                        <ui-select v-model="search.semesterNm" code-data="analysis.readSemester" first-name="전체"></ui-select>
                    </td>
                    <th>대학</th>
                    <td>
                        <ui-select v-model="search.daehakNm" code-data="analysis.readDaehakList" first-name="전체"></ui-select>
                    </td>
                    <th>학과</th>
                    <td>
                        <ui-select v-model="search.hakkwaNm" v-bind:code-data="hakgwaCodeData" first-name="전체"></ui-select>
                    </td>
                    <th>학년</th>
                    <td>
                        <ui-select v-model="search.gradeNm" code-data="analysis.readGrade" first-name="전체"></ui-select>
                    </td>
                    <th>과목</th>
                    <td>
                        <ui-select v-model="search.subjectNm" v-bind:code-data="subjectCodeData" first-name="전체"></ui-select>
                    </td>
                </tr>
                </c:if>
            </table>
        </div>

        <!-- <div class="panel-main">
            <div class="panel-main">
                                수강 계획서
            </div>
        </div> -->

        <div class="panel-main">
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>평균성적</p>
                        </div>
                        <div class="panel-content">
                           <div id="scoreAveGraph" class="chart-container"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>선이수 과목</p>
                        </div>
                        <div class="panel-content">
                           <div id="preSubjectGraph" class="chart-container" style="width: 95%;height: 80%;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-main">
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>학과</p>
                        </div>
                        <div class="panel-content">
                           <div id="hakgwaGraph" class="chart-container" style="width: 90%;height: 85%;"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>장학금</p>
                        </div>
                        <div class="panel-content">
                            <div id="scholarshipGraph" class="chart-container" style="width: 95%;height: 95%;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-main">
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>학년</p>
                        </div>
                        <div class="panel-content">
                            <div id="gradeGraph" class="chart-container"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>성별</p>
                        </div>
                        <div class="panel-content">
                            <div id="sexGraph" class="chart-container"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
vues.read = new Vue({
    el: '#read',
    data: {
        search : {
            yearData: new Date().getFullYear()-1,
            semesterNm: "",
            daehakNm: "",
            hakkwaNm: "",
            gradeNm: "",
            subjectNm: "",
        }
    },
    computed: {
        service: function() {
            return new AnalysisViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        hakgwaCodeData: function() {
            return "analysis.readHakkwa1nameList," + this.search.daehakNm;
        },
        subjectCodeData: function() {
            return "analysis.readSubject," + this.search.yearData + "," + this.search.daehakNm + "," + this.search.hakkwaNm;
        }
    },
    mounted: function() {
        this.readList();
    },
    updated: function() {
        this.readList();
    },
    methods: {
        readList: function() {
            this.service.readList(this.search);
            this.service.readList2(this.search);
            this.service.readList3(this.search);
        }
    }
});
</script>