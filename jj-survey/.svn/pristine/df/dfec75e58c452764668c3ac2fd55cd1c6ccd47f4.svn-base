<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
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
                        <span>{{ model.jdSurveyDefinition.name }}</span>
                    </td>
                    <th>설문 분야</th>
                    <td>
                        <span>{{ model.tbSurveyTmplat.surveyRealmNm }}</span>
                    </td>
                </tr>
                <tr>
                    <th>설문지 설명</th>
                    <td colspan="3">
                        <pre>{{ model.jdSurveyDefinition.description }}</pre>
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
                        <div v-html="model.tbSurveyTmplat.qestnrGdcc"></div>
                    </td>
                </tr>
                <tr>
                    <th>설문지 종료 문구</th>
                    <td colspan="3">
                        <div v-html="model.tbSurveyTmplat.qestnrEndc"></div>
                    </td>
                </tr>
                <tr>
                    <th>설문지 배경색</th>
                    <td colspan="3">
                        <label class="color-selector" v-bind:style="{ backgroundColor: model.tbSurveyTmplat.qestnrBcrnColor }">&nbsp;</label>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="viewEditSurvey();" v-if="model.editable">설문지 편집</ui-button>
            <ui-button class="page" v-on:click="viewEditSurvey(true);" v-if="model.editable">설문지 편집(새 창)</ui-button>
            <ui-button class="page" v-on:click="viewEdit();" v-if="model.editable">기본정보 수정</ui-button>
            <ui-button class="page" v-on:click="service['updateStatus']('1');" v-if="model.editable">템플릿 활성</ui-button>
            <ui-button class="page" v-on:click="service['updateStatus']('0');" v-if="!model.editable">템플릿 수정</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="model.editable">삭제</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.read = new Vue({
    el: '#read',
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
            this.service.read(surveyTmplatId);
        },
        // 수정 화면
        viewEdit: function() {
            CommonUtil.changeVue("edit", this.surveyTmplatId);
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
                CommonUtil.changeVue("survey", this.surveyTmplatId);
            }
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
