<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="personList" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readPersonList(true);">
                <colgroup>
                    <col width="12%">
                    <col width="">
                    <col width="12%">
                    <col width="">
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>사번</th>
                    <td>
                        <ui-input type="text" v-model="tbSurveyQestnrPersonList.request.search.emplNo"></ui-input>
                    </td>
                    <th>이름</th>
                    <td>
                        <ui-input type="text" v-model="tbSurveyQestnrPersonList.request.search.personNm"></ui-input>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th>이메일 주소</th>
                    <td>
                        <ui-input type="text" v-model="tbSurveyQestnrPersonList.request.search.emailAdres"></ui-input>
                    </td>
                    <th>조직명</th>
                    <td>
                        <ui-input type="text" v-model="tbSurveyQestnrPersonList.request.search.deptNm"></ui-input>
                    </td>
                    <td>
                        <div class="btnArea_center">
                            <ui-button class="btn_total_search" v-on:click="service.readPersonList(true);">조회</ui-button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <!-- 검색조건 영역 끝 -->

        <!-- 결과목록 영역 시작 -->
        <ui-grid-paging v-bind:request="tbSurveyQestnrPersonList.request" v-bind:result="tbSurveyQestnrPersonList.result" v-bind:read-list="service.readPersonList"
            v-bind:buttons="gridButtons"
        >
            <table class="list">
                <thead>
                    <tr>
                        <th width="50px" column-name="rn">No</th>
                        <th width="" column-name="emplNo" column-sort="ASC">사번</th>
                        <th width="" column-name="koreanNm" column-sort="">이름</th>
                        <th width="" column-name="emailAdres" column-sort="">이메일 주소</th>
                        <th width="" column-name="tlphonNo" column-sort="">전화 번호</th>
                        <th width="" column-name="deptNames" column-sort="">조직명</th>
                        <th width="70px">{{ model.tbSurveyQestnr.sndngYn ? "설문 상태" : "삭제"}}</th>
                        <th width="80px" v-if="model.tbSurveyQestnr.sndngYn">재발송</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="row in tbSurveyQestnrPersonList.result.list" v-bind:key="row.surveyPersonId">
                        <th class="tac">{{ row.rn }}</th>
                        <td class="tac">{{ row.emplNo }}</td>
                        <td class="tac">{{ row.koreanNm }}</td>
                        <td class="tal">{{ row.emailAdres }}</td>
                        <td class="tal">{{ row.tlphonNo }}</td>
                        <td class="tal">{{ row.deptNames }}</td>
                        <td class="tac">
                            <span v-if="model.tbSurveyQestnr.sndngYn">{{ row.surveyStatusNm }}</span>
                            <ui-button v-else v-on:click="deletePerson(row);">삭제</ui-button>
                        </td>
                        <td class="tac" v-if="model.tbSurveyQestnr.sndngYn">
                            <ui-button v-if="row.surveyStatus == 'S'" v-on:click="viewSurveyAnswer(row);">결과보기</ui-button>
                            <ui-button v-else-if="row.surveyStatus != 'D' && model.jdSurveyDefinition.expired != true"
                                v-on:click="sendMailTarget(row);"
                            >재발송</ui-button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </ui-grid-paging>
        <!-- 결과목록 영역 시작 -->
    </div>
</div>

<script>
vues.personList = new Vue({
    el: '#personList',
    data: {
        surveyQestnrId: null,
        model: {
            tbSurveyQestnr: {},
            tbSurveyQestnrCmmn: {},
            jdSurveyDefinition: {}
        },
        tbSurveyQestnrPersonList: {
            request: GridUtil.getRequestWithPaging({}),
            result: {}
        }
    },
    computed: {
        service: function() {
            return new QuestionnaireManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        gridButtons: function() {
            var buttons = [];

            if (this.model.tbSurveyQestnr.sndngYn == null) {
                return buttons;
            }

            if (this.model.tbSurveyQestnr.sndngYn != true) {
                buttons.push({ className: "page", onClick: function() { vues.personList.viewTargetPersonAdd("personGroupList"); }, label: "대상자 추가(그룹)" });
                buttons.push({ className: "page", onClick: function() { vues.personList.viewTargetPersonAdd("personSearchList"); }, label: "대상자 추가(개별)" });
                buttons.push({ className: "page", onClick: function() { vues.personList.viewTargetPersonAdd("personDeptTree"); }, label: "대상자 추가(조직)" });
            } else {
                buttons.push({ className: "page", onClick: function() { vues.personList.sendMail(); }, label: "미응답자 일괄 재발송" });
            }

            return buttons;
        }
    },
    methods: {
        // 초기화
        init: function(surveyQestnrId) {
            var vue = this;

            this.service.read(surveyQestnrId, function() {
                vue.service.readPersonList(true);

                $(vue.$el).dialog({
                    title: "설문 대상자 관리",
                    modal: true,
                    width: 1200,
                    height: 800,
                    close: function() {
                        vues.list.service.readList();
                    }
                });
            });
        },
        // 대상자 추가 화면
        viewTargetPersonAdd: function(vueName) {
            CommonUtil.popupVue(vueName, this.surveyQestnrId);
        },
        // 대상자 삭제
        deletePerson: function(row) {
            this.service.deletePerson(row.surveyPersonId);
        },
        // 미응답자 메일 일괄 발송
        sendMail: function() {
            var vue = this;

            CommonUtil.popupVue("publishMediaSelect", function(publishModel) {
                return vue.service.sendMail(publishModel);
            });
        },
        // 대상 메일 발송
        sendMailTarget: function(row) {
            var vue = this;

            CommonUtil.popupVue("publishMediaSelect", function(publishModel) {
                return vue.service.sendMailTarget(row.surveyPersonId, publishModel);
            });
        },
        // 설문지 응답 화면
        viewSurveyAnswer: function(row) {
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

            var url = CommonUtil.contextPath + "/surveys/private/" + row.surveyId + "?show=1";
            var name = "survey_answer_" + row.surveyId;

            window.open(url, name, specs.join(","), true);
        }
    }
});
</script>
