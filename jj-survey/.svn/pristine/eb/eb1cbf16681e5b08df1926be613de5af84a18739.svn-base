<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageHeader">
        설문지 {{ surveyQestnrId == null ? "등록" : "수정" }}
        <div class="btn_area">
            <ui-button v-if="surveyQestnrId == null" v-on:click="viewTemplateList();">템플릿 불러오기</ui-button>
            <ui-button v-if="surveyQestnrId == null" v-on:click="viewPrevSurveyList();">이전 설문지 불러오기</ui-button>
        </div>
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
                        <ui-input type="text" v-model="model.jdSurveyDefinition.name" v-bind:max-byte="200" v-bind:byte-indicator="false" auto-focus></ui-input>
                        <ui-valid-checker v-bind:value="model.jdSurveyDefinition.name" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>설문 분야</th>
                    <td>
                        <ui-select v-model="model.tbSurveyQestnr.surveyRealmId" code-data="survey.surveyRealmId" first-name="(선택)"></ui-select>
                        <ui-valid-checker v-bind:value="model.tbSurveyQestnr.surveyRealmId" check="required"></ui-valid-checker>
                    </td>
                    <th>등록 부서</th>
                    <td>
                        <ui-select v-model="model.tbSurveyQestnr.registDeptId" v-bind:code-data="'survey.surveyRegistDeptId,' + (surveyQestnrId || '')" v-bind:default-index="0"></ui-select>
                    </td>
                </tr>
                <tr>
                    <th>설문조사 목적</th>
                    <td colspan="3">
                        <ui-input type="text" v-model="model.jdSurveyDefinition.purpose" v-bind:max-byte="2000" v-bind:byte-indicator="false"></ui-input>
                        <ui-valid-checker v-bind:value="model.jdSurveyDefinition.purpose" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>설문지 설명</th>
                    <td colspan="3">
                        <ui-textarea rows="3" v-model="model.jdSurveyDefinition.description" v-bind:max-byte="2000" v-bind:byte-indicator="true"></ui-textarea>
                    </td>
                </tr>
                <tr>
                    <th>설문지 공유여부</th>
                    <td>
                        <ui-select v-model="model.tbSurveyQestnr.cnrsYn" code-data="[CNRS_YN]" first-name="(선택)"></ui-select>
                        <ui-valid-checker v-bind:value="model.tbSurveyQestnr.cnrsYn" check="required"></ui-valid-checker>
                    </td>
                    <th>설문 기간</th>
                    <td>
                        <ui-date v-model="model.jdSurveyDefinition.startDt"></ui-date> ~
                        <ui-date v-model="model.jdSurveyDefinition.endDt"></ui-date>
                        <ui-valid-checker v-bind:value="model.jdSurveyDefinition.startDt && model.jdSurveyDefinition.endDt" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr v-if="surveyQestnrId != null">
                    <th>상태</th>
                    <td>
                        <span>{{ model.jdSurveyDefinition.statusName }}</span>
                    </td>
                    <th>제출(발송) 여부</th>
                    <td>
                        <span>{{ model.tbSurveyQestnr.sndngYnNm }}</span>
                    </td>
                </tr>
            </tbody>
        </table>

        <h3>설문지 스킨정보</h3>
        <table class="form">
            <colgroup>
                <col width="150px">
                <col width="">
                <col width="150px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>설문지 안내 문구</th>
                    <td colspan="3">
                        <ui-ckeditor4 v-model="model.tbSurveyQestnr.qestnrGdcc"></ui-ckeditor4>
                    </td>
                </tr>
                <tr>
                    <th>설문지 종료 문구</th>
                    <td colspan="3">
                        <ui-ckeditor4 v-model="model.tbSurveyQestnr.qestnrEndc"></ui-ckeditor4>
                    </td>
                </tr>
                <tr>
                    <th>설문지 배경색</th>
                    <td colspan="3">
                        <label v-for="color in model.backgroundColors" class="color-selector" style="cursor: pointer;" v-bind:style="{ backgroundColor: color }">
                            <input type="radio" v-model="model.tbSurveyQestnr.qestnrBcrnColor" v-bind:value="color">선택
                        </label>
                    </td>
                </tr>
            </tbody>
        </table>

        <h3>공통 문항</h3>
        <table class="form">
            <colgroup>
                <col width="150px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>공통 문항</th>
                    <td colspan="3">
                        <ui-checkbox inline v-model="model.tbSurveyQestnrCmmn.sexdstn">성별</ui-checkbox>
                        <ui-checkbox inline v-model="model.tbSurveyQestnrCmmn.area">지역</ui-checkbox>
                        <ui-checkbox inline v-model="model.tbSurveyQestnrCmmn.grade">학년</ui-checkbox>
                        <ui-checkbox inline v-model="model.tbSurveyQestnrCmmn.cghmy">소속 단과대학</ui-checkbox>
                        <ui-checkbox inline v-model="model.tbSurveyQestnrCmmn.subjct">학과</ui-checkbox>
                    </td>
                </tr>
                <tr>
                    <th>만족도 조사</th>
                    <td colspan="3">
                        <ui-checkbox v-model="model.tbSurveyQestnrCmmn.stsfdg01">전주대학교에 전반적으로 만족하십니까?</ui-checkbox>
                        <ui-checkbox v-model="model.tbSurveyQestnrCmmn.stsfdg02">전주대학교의 교육에 대해 만족하십니까?</ui-checkbox>
                        <ui-checkbox v-model="model.tbSurveyQestnrCmmn.stsfdg03">전주대학교의 교육환경에 대해 만족하십니까?</ui-checkbox>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="surveyQestnrId != null">삭제</ui-button>
            <ui-button class="page" v-on:click="viewRead();" v-if="surveyQestnrId != null">취소</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        surveyQestnrId: null,
        model: {
            tbSurveyQestnr: {},
            tbSurveyQestnrCmmn: {},
            jdSurveyDefinition: {}
        }
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
        init: function(surveyQestnrId) {
            this.service.read(surveyQestnrId, function(model) {
                if (surveyQestnrId != null && model.editable !== true) {
                    CommonUtil.replaceVue("read", surveyQestnrId);
                }
            });
        },
        // 템플릿 검색 화면
        viewTemplateList: function() {
            CommonUtil.popupVue("templateSearchList");
        },
        // 이전 설문지 검색 화면
        viewPrevSurveyList: function() {
            CommonUtil.popupVue("prevSurveySearchList");
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.surveyQestnrId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.replaceVue("list");
        }
    }
});
</script>
