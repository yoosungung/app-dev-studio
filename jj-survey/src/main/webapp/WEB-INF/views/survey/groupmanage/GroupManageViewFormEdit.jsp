<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageHeader">
        그룹 {{ surveyGroupId == null ? "등록" : "수정" }}
    </div>

    <div class="pageContents">
        <h3>그룹 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>그룹 이름</th>
                    <td colspan="3">
                        <ui-input type="text" v-model="model.tbSurveyGroup.groupNm" v-bind:max-byte="100" auto-focus></ui-input>
                        <ui-valid-checker v-bind:value="model.tbSurveyGroup.groupNm" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>그룹 공유여부</th>
                    <td>
                        <ui-select v-model="model.tbSurveyGroup.cnrsYn" code-data="[CNRS_YN]" first-name="(선택)"></ui-select>
                        <ui-valid-checker v-bind:value="model.tbSurveyGroup.cnrsYn" check="required"></ui-valid-checker>
                    </td>
                    <th>등록 부서</th>
                    <td>
                        <ui-select v-model="model.tbSurveyGroup.registDeptId" v-bind:code-data="'survey.groupRegistDeptId,' + (surveyGroupId || '')" v-bind:default-index="0"></ui-select>
                    </td>
                </tr>
                <tr>
                    <th>설명</th>
                    <td colspan="3">
                        <ui-input type="text" v-model="model.tbSurveyGroup.groupDc" v-bind:max-byte="1000"><ui-input>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="surveyGroupId != null">삭제</ui-button>
            <ui-button class="page" v-on:click="viewRead();" v-if="surveyGroupId != null">취소</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        surveyGroupId: null,
        model: {
            tbSurveyGroup: {}
        }
    },
    computed: {
        service: function() {
            return new GroupManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(surveyGroupId) {
            this.service.read(surveyGroupId, function(model) {
                if (surveyGroupId != null && model.editable !== true) {
                    CommonUtil.replaceVue("read", surveyGroupId);
                }
            });
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.surveyGroupId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.replaceVue("list");
        }
    }
});
</script>
