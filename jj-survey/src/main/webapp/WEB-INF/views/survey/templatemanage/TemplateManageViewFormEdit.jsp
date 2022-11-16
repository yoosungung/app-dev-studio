<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageHeader">
        템플릿 {{ surveyTmplatId == null ? "등록" : "수정" }}
    </div>

    <div class="pageContents">
        <h3>템플릿 기본정보</h3>
        <table class="form">
            <colgroup>
                <col width="150px">
                <col width="">
                <col width="150px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>템플릿 이름</th>
                    <td>
                        <ui-input type="text" v-model="model.jdSurveyDefinition.name" v-bind:max-byte="200" v-bind:byte-indicator="false" auto-focus></ui-input>
                        <ui-valid-checker v-bind:value="model.jdSurveyDefinition.name" check="required"></ui-valid-checker>
                    </td>
                    <th>설문 분야</th>
                    <td>
                        <ui-select v-model="model.tbSurveyTmplat.surveyRealmId" code-data="survey.surveyRealmId"></ui-select>
                        <ui-valid-checker v-bind:value="model.tbSurveyTmplat.surveyRealmId" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>설문지 설명</th>
                    <td colspan="3">
                        <ui-textarea rows="3" v-model="model.jdSurveyDefinition.description" v-bind:max-byte="2000" v-bind:byte-indicator="true"></ui-textarea>
                    </td>
                </tr>
                <tr>
                    <th>템플릿 상태</th>
                    <td colspan="3">
                        <span>{{ model.tbSurveyTmplat.tmplatSttusNm }}</span>
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
                        <ui-ckeditor4 v-model="model.tbSurveyTmplat.qestnrGdcc"></ui-ckeditor4>
                    </td>
                </tr>
                <tr>
                    <th>설문지 종료 문구</th>
                    <td colspan="3">
                        <ui-ckeditor4 v-model="model.tbSurveyTmplat.qestnrEndc"></ui-ckeditor4>
                    </td>
                </tr>
                <tr>
                    <th>설문지 배경색</th>
                    <td colspan="3">
                        <label v-for="color in model.backgroundColors" class="color-selector" style="cursor: pointer;" v-bind:style="{ backgroundColor: color }">
                            <input type="radio" v-model="model.tbSurveyTmplat.qestnrBcrnColor" v-bind:value="color">선택
                        </label>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="surveyTmplatId != null">삭제</ui-button>
            <ui-button class="page" v-on:click="viewRead();" v-if="surveyTmplatId != null">취소</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        surveyTmplatId: null,
        model: {
            tbSurveyTmplat: {},
            jdSurveyDefinition: {}
        }
    },
    computed: {
        service: function() {
            return new TemplateManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(surveyTmplatId) {
            this.service.read(surveyTmplatId, function(model) {
                if (surveyTmplatId != null && model.editable !== true) {
                    CommonUtil.replaceVue("read", surveyTmplatId);
                }
            });
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.surveyTmplatId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.replaceVue("list");
        }
    }
});
</script>
