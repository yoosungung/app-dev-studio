<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="survey" style="display: none;">
    <div class="pageHeader">
        설문지 편집
    </div>

    <div class="pageContents">
        <div ref="surveyContainer"></div>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="viewRead();" v-if="surveyQestnrId != null">기본정보 조회</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.survey = new Vue({
    el: '#survey',
    data: {
        surveyQestnrId: null,
        model: {
            tbSurveyQestnr: {},
            jdSurveyDefinition: {}
        }
    },
    computed: {
        service: function() {
            return new QuestionnaireManageViewService(this);
        },
        $surveyContainer: function() {
            return $(this.$refs.surveyContainer);
        }
    },
    methods: {
        // 초기화
        init: function(surveyQestnrId) {
            var vue = this;

            this.service.read(surveyQestnrId, function(model) {
                var pageSrc = CommonUtil.contextPath + "/settings/surveyDefinitions/" + model.jdSurveyDefinition.id + "?show";

                vue.$surveyContainer.html('<iframe src="' + pageSrc + '" style="width: 100%; border: none;"></iframe>');

                vue.resizeSurveyFrameHeight();
            });
        },
        // 설문지 IFRAME 높이 조정
        resizeSurveyFrameHeight: function() {
            var vueHeight = $(this.$el).height("100%").innerHeight();
            var headerHeight = $(this.$el).children('.pageHeader').outerHeight(true);
            var pageSpaceHeight = $(this.$el).find('.page-space').outerHeight(true);

            this.$surveyContainer.find('iframe').height(vueHeight - headerHeight - pageSpaceHeight);
        },
        // 조회 화면
        viewRead: function() {
            this.$surveyContainer.empty();

            CommonUtil.replaceVue("read", this.surveyQestnrId);
        },
        // 목록 화면
        viewList: function() {
            this.$surveyContainer.empty();

            CommonUtil.replaceVue("list");
        }
    }
});

$(window).resize(function() {
    vues.survey.resizeSurveyFrameHeight();
});
</script>
