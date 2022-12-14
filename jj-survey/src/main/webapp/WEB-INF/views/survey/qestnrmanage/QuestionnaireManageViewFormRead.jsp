<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        설문지 {{ surveyQestnrId == null ? "등록" : "수정" }}
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
                    <th>설문조사 목적</th>
                    <td colspan="3">
                        <span>{{ model.jdSurveyDefinition.purpose }}</span>
                    </td>
                </tr>
                <tr>
                    <th>설문지 설명</th>
                    <td colspan="3">
                        <pre>{{ model.jdSurveyDefinition.description }}</pre>
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
                <tr>
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
                        <div v-html="model.tbSurveyQestnr.qestnrGdcc"></div>
                    </td>
                </tr>
                <tr>
                    <th>설문지 종료 문구</th>
                    <td colspan="3">
                        <div v-html="model.tbSurveyQestnr.qestnrEndc"></div>
                    </td>
                </tr>
                <tr>
                    <th>설문지 배경색</th>
                    <td colspan="3">
                        <label class="color-selector" v-bind:style="{ backgroundColor: model.tbSurveyQestnr.qestnrBcrnColor }">&nbsp;</label>
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
                        <ui-checkbox inline read-only2 v-model="model.tbSurveyQestnrCmmn.sexdstn">성별</ui-checkbox>
                        <ui-checkbox inline read-only2 v-model="model.tbSurveyQestnrCmmn.area">지역</ui-checkbox>
                        <ui-checkbox inline read-only2 v-model="model.tbSurveyQestnrCmmn.grade">학년</ui-checkbox>
                        <ui-checkbox inline read-only2 v-model="model.tbSurveyQestnrCmmn.cghmy">소속 단과대학</ui-checkbox>
                        <ui-checkbox inline read-only2 v-model="model.tbSurveyQestnrCmmn.subjct">학과</ui-checkbox>
                    </td>
                </tr>
                <tr>
                    <th>만족도 조사</th>
                    <td colspan="3">
                        <ui-checkbox read-only2 v-model="model.tbSurveyQestnrCmmn.stsfdg01">전주대학교에 전반적으로 만족하십니까?</ui-checkbox>
                        <ui-checkbox read-only2 v-model="model.tbSurveyQestnrCmmn.stsfdg02">전주대학교의 교육에 대해 만족하십니까?</ui-checkbox>
                        <ui-checkbox read-only2 v-model="model.tbSurveyQestnrCmmn.stsfdg03">전주대학교의 교육환경에 대해 만족하십니까?</ui-checkbox>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="viewPersonList();">대상자 관리</ui-button>
            <ui-button class="page" v-on:click="viewEditSurvey();">설문지 편집</ui-button>
            <ui-button class="page" v-on:click="viewEditSurvey(true);">설문지 편집(새 창)</ui-button>
            <ui-button class="page" v-on:click="viewEdit();" v-if="model.editable">기본정보 수정</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="model.editable && model.jdSurveyDefinition.status == 'I'">삭제</ui-button>
            <ui-button class="page" v-on:click="viewPublishMediaSelect();" v-if="model.editable">설문 제출(발송)</ui-button>
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
            this.service.read(surveyQestnrId);
        },
        // 수정 화면
        viewEdit: function() {
            CommonUtil.changeVue("edit", this.surveyQestnrId);
        },
        // 대상자 목록 화면
        viewPersonList: function() {
            CommonUtil.popupVue("personList", this.surveyQestnrId);
        },
        // 설문지 수정 화면
        viewEditSurvey: function(newWindow) {
            if (newWindow == true) {
                var specs = [];

                specs.push("channelmode=no");
                specs.push("directories=no");
                specs.push("location=no");
                specs.push("menubar=no");
                specs.push("resizable=yes");
                specs.push("scrollbars=yes");
                specs.push("status=no");
                specs.push("titlebar=no");
                specs.push("toolbar=no");

                var url = CommonUtil.contextPath + "/settings/surveyDefinitions/" + this.model.jdSurveyDefinition.id + "?show";
                var name = "settings_surveyDefinitions_" + this.model.jdSurveyDefinition.id;

                window.open(url, name, specs.join(","), true);
            } else {
                CommonUtil.changeVue("survey", this.surveyQestnrId);
            }
        },
        // 설문 제출(발송) 매체 선택 화면
        viewPublishMediaSelect: function() {
            var vue = this;

            CommonUtil.popupVue("publishMediaSelect", function(publishModel) {
                return vue.service.publish(publishModel);
            });
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
