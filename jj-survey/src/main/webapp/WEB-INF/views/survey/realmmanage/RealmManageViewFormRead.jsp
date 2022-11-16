<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        설문 분야 상세
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
                        <span>{{ model.tbSurveyRealm.surveyRealmNm }}</span>
                    </td>
                </tr>
                <tr>
                    <th>설명</th>
                    <td colspan="3">
                        <span>{{ model.tbSurveyRealm.surveyRealmDc }}</span>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="viewEdit();" v-if="model.editable">수정</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="model.editable">삭제</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.read = new Vue({
    el: '#read',
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
            this.service.read(surveyRealmId);
        },
        // 수정 화면
        viewEdit: function() {
            CommonUtil.changeVue("edit", this.surveyRealmId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
