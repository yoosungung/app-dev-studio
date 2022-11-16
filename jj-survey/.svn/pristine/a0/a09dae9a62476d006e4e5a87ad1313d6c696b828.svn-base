<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageHeader">
        설문 분야 {{ surveyRealmId == null ? "등록" : "수정" }}
    </div>

    <div class="pageContents">
        <h3>설문 분야 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>설문 분야</th>
                    <td colspan="3">
                        <ui-input type="text" v-model="model.tbSurveyRealm.surveyRealmNm" v-bind:max-byte="200" v-bind:byte-indicator="false" auto-focus></ui-input>
                        <ui-valid-checker v-bind:value="model.tbSurveyRealm.surveyRealmNm" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>설명</th>
                    <td colspan="3">
                        <ui-textarea v-model="model.tbSurveyRealm.surveyRealmDc" v-bind:max-byte="2000" v-bind:byte-indicator="true"></ui-textarea>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="surveyRealmId != null">삭제</ui-button>
            <ui-button class="page" v-on:click="viewRead();" v-if="surveyRealmId != null">취소</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        surveyRealmId: null,
        model: {
            tbSurveyRealm: {}
        }
    },
    computed: {
        service: function() {
            return new RealmManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(surveyRealmId) {
            this.service.read(surveyRealmId, function(model) {
                if (surveyRealmId != null && model.editable !== true) {
                    CommonUtil.replaceVue("read", surveyRealmId);
                }
            });
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.surveyRealmId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.replaceVue("list");
        }
    }
});
</script>
