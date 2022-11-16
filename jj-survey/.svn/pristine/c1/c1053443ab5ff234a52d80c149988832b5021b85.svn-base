<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<style>
.progress-bar {
    border: 1px solid rgba(0,0,0,0.1);
    padding: 3px;
    height: 7px;
}

.progress-bar > .progress-bar-value {
    background-color: #5db2ff;
    height:100%;
}
</style>

<div id="read" style="display: none;">
    <div class="pageHeader">
        설문 결과 상세
    </div>

    <div class="pageContents">
        <h3>설문지 기본정보</h3>
        <table class="form">
            <colgroup>
                <col width="150px">
                <col width="">
                <col width="150px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>설문 이름</th>
                    <td colspan="3">
                        <span>{{ model.jdSurveyDefinition.name }}</span>
                    </td>
                </tr>
                <tr>
                    <th>설문 분야</th>
                    <td>
                        <span>{{ model.tbSurveyQestnr.surveyRealmNm }}</span>
                    </td>
                    <th>등록 부서</th>
                    <td>
                        <span>{{ model.tbSurveyQestnr.registDeptNm }}</span>
                    </td>
                </tr>
                <tr>
                    <th>설문지 공유여부</th>
                    <td>
                        <span>{{ model.tbSurveyQestnr.cnrsYnNm }}</span>
                    </td>
                    <th>설문 기간</th>
                    <td>
                        <span>{{ formatter.date(model.jdSurveyDefinition.startDt) }}</span> ~
                        <span>{{ formatter.date(model.jdSurveyDefinition.endDt) }}</span>
                    </td>
                </tr>
            </tbody>
        </table>

        <h3>디바이스별 응답정보</h3>
        <table class="list">
            <thead>
                <tr>
                    <th width="" rowspan="2">일자</th>
                    <th width="" colspan="2">PC</th>
                    <th width="" colspan="2">모바일</th>
                    <th width="" colspan="2">합계</th>
                </tr>
                <tr>
                    <th width="">진행중</th>
                    <th width="">제출완료</th>
                    <th width="">진행중</th>
                    <th width="">제출완료</th>
                    <th width="">진행중</th>
                    <th width="">제출완료</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="row in model.deviceSubmitList">
                    <td class="tac">{{ row.submitDate }}</td>
                    <td class="tac">{{ row.normalICo }}</td>
                    <td class="tac">{{ row.normalSCo }}</td>
                    <td class="tac">{{ row.mobileICo }}</td>
                    <td class="tac">{{ row.mobileSCo }}</td>
                    <td class="tac">{{ row.sumICo }}</td>
                    <td class="tac">{{ row.sumSCo }}</td>
                </tr>
            </tbody>
        </table>

        <h3>공통문항 응답통계</h3>
        <table class="list">
            <colgroup>
                <col width="20%"></col>
                <col width="20%"></col>
                <col width="10%"></col>
                <col width="10%"></col>
                <col width="40%"></col>
            </colgroup>
            <thead>
                <tr>
                    <th>문항</th>
                    <th>항목</th>
                    <th>설문수</th>
                    <th colspan="2">백분율</th>
                </tr>
            </thead>
            <tbody v-if="getRowCount('CMMN') == 0">
                <tr>
                    <td colspan="5" style="text-align: center;">(공통문항 없음)</td>
                </tr>
            </tbody>
            <tbody v-else>
                <tr v-for="(row, index) in model.cmmnRspnsList" v-if="row.surveyDiv == 'CMMN'">
                    <th v-if="index == 0 || model.cmmnRspnsList[index - 1].surveyCode != row.surveyCode"
                        v-bind:rowspan="getRowspan(row)"
                    >{{ row.codeTitle }}</th>
                    <th>{{ row.codeValueNm }}</th>
                    <td class="tar">{{ row.rspnsCo }}</td>
                    <td class="tar">{{ getPercent(row) }}%</td>
                    <td class="tac">
                        <div class="progress-bar">
                            <div class="progress-bar-value" v-bind:style="{ width: getPercent(row) + '%' }"></div>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>

        <h3>만족도 응답통계</h3>
        <table class="list">
            <colgroup>
                <col width="20%"></col>
                <col width="20%"></col>
                <col width="10%"></col>
                <col width="10%"></col>
                <col width="40%"></col>
            </colgroup>
            <thead>
                <tr>
                    <th>문항</th>
                    <th>항목</th>
                    <th>설문수</th>
                    <th colspan="2">백분율</th>
                </tr>
            </thead>
            <tbody v-if="getRowCount('STSFDG') == 0">
                <tr>
                    <td colspan="5" style="text-align: center;">(만족도 조사 없음)</td>
                </tr>
            </tbody>
            <tbody v-else>
                <tr v-for="(row, index) in model.cmmnRspnsList" v-if="row.surveyDiv == 'STSFDG'">
                    <th v-if="index == 0 || model.cmmnRspnsList[index - 1].surveyCode != row.surveyCode"
                        v-bind:rowspan="getRowspan(row)"
                    >{{ row.codeTitle }}</th>
                    <th>{{ row.codeValueNm }}</th>
                    <td class="tar">{{ row.rspnsCo }}</td>
                    <td class="tar">{{ getPercent(row) }}%</td>
                    <td class="tac">
                        <div class="progress-bar">
                            <div class="progress-bar-value" v-bind:style="{ width: getPercent(row) + '%' }"></div>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>

        <h3>설문문항 응답통계</h3>
        <div ref="surveyStatisticsContainer"></div>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="downloadExcel();">엑셀 다운로드</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.read = new Vue({
    el: '#read',
    data: {
        surveyQestnrId: null,
        model: {
            tbSurveyQestnr: {},
            jdSurveyDefinition: {},
            deviceSubmitList: [],
            cmmnRspnsList: []
        }
    },
    computed: {
        service: function() {
            return new SurveyResultViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        $surveyStatisticsContainer: function() {
            return $(this.$refs.surveyStatisticsContainer);
        }
    },
    methods: {
        // 초기화
        init: function(surveyQestnrId) {
            var vue = this;

            this.service.read(surveyQestnrId, function(model) {
                var pageSrc = CommonUtil.contextPath + "/statistics/list?sid=" + model.jdSurveyDefinition.id;

                vue.$surveyStatisticsContainer.html('<iframe src="' + pageSrc + '" style="width: 100%; border: none;" auto-height></iframe>');
            });
        },
        // 엑셀 다운로드
        downloadExcel: function() {
            this.service.downloadExcel();
        },
        // 목록 화면
        viewList: function() {
            this.$surveyStatisticsContainer.empty();

            CommonUtil.changeVue("list");
        },
        getRowCount: function(surveyDiv) {
            var count = 0;

            for (var i = 0; i < this.model.cmmnRspnsList.length; i++) {
                var cmmnRspns = this.model.cmmnRspnsList[i];

                if (cmmnRspns.surveyDiv == surveyDiv) {
                    count++;
                }
            }

            return count;
        },
        getRowspan: function(row) {
            var rowspan = 0;

            for (var i = 0; i < this.model.cmmnRspnsList.length; i++) {
                var cmmnRspns = this.model.cmmnRspnsList[i];

                if (cmmnRspns.surveyCode == row.surveyCode) {
                    rowspan++;
                }
            }

            return rowspan;
        },
        getPercent: function(row) {
            if (row.rspnsCo == null || row.rspnsCo == 0) {
                return 0;
            }

            var totalCo = 0;

            for (var i = 0; i < this.model.cmmnRspnsList.length; i++) {
                var cmmnRspns = this.model.cmmnRspnsList[i];

                if (cmmnRspns.surveyCode == row.surveyCode && cmmnRspns.rspnsCo != null) {
                    totalCo += cmmnRspns.rspnsCo;
                }
            }

            return (row.rspnsCo / totalCo) * 100;
        }
    }
});
</script>
