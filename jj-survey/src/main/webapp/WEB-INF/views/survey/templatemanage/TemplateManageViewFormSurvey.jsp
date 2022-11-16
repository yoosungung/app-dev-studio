<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="survey" style="display: none;">
    <div class="pageHeader">
        템플릿 편집
    </div>

    <div class="pageContents">
        <div ref="surveyContainer"></div>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="viewRead();" v-if="surveyTmplatId != null">기본정보 조회</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.survey = new Vue({
    el: '#survey',
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
        }
    },
    methods: {
        // 초기화
        init: function(surveyTmplatId) {
            var vue = this;

            this.service.read(surveyTmplatId, function(model) {
                if (surveyTmplatId != null && model.editable !== true) {
                    CommonUtil.replaceVue("read", surveyTmplatId);
                } else {
                    var pageSrc = CommonUtil.contextPath + "/settings/surveyDefinitions/" + model.jdSurveyDefinition.id + "?show";

                    $(vue.$refs.surveyContainer).html('<iframe src="' + pageSrc + '" style="width: 100%; height: 1000px; border: none;"></iframe>');
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
