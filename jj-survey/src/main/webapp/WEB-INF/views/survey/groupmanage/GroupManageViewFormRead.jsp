<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        그룹 상세
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
                        <span>{{ model.tbSurveyGroup.groupNm }}</span>
                    </td>
                </tr>
                <tr>
                    <th>그룹 공유여부</th>
                    <td>
                        <span>{{ model.tbSurveyGroup.cnrsYnNm }}</span>
                    </td>
                    <th>등록 부서</th>
                    <td>
                        <span>{{ model.tbSurveyGroup.registDeptNm }}</span>
                    </td>
                </tr>
                <tr>
                    <th>설명</th>
                    <td colspan="3">
                        <span>{{ model.tbSurveyGroup.groupDc }}</span>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="viewTargetPersonAdd('personSearchList');" v-if="model.editable">대상자 추가(개별)</ui-button>
            <ui-button class="page" v-on:click="viewTargetPersonAdd('personDeptTree');" v-if="model.editable">대상자 추가(조직)</ui-button>
            <ui-button class="page" v-on:click="viewTargetPersonAdd('personExcelUpload');" v-if="model.editable">대상자 추가(엑셀)</ui-button>
            <ui-button class="page" v-on:click="viewEdit();" v-if="model.editable">그룹 정보 수정</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="model.editable">삭제</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>

        <div class="table full">
            <h3>대상자 목록</h3>
        </div>
        <ui-grid-paging v-bind:request="tbSurveyGroupPersonList.request" v-bind:result="tbSurveyGroupPersonList.result" v-bind:read-list="service.readPersonList">
            <table class="list">
                <thead>
                    <tr>
                        <th width="50px" column-name="rn">No</th>
                        <th width="" column-name="emplNo" column-sort="ASC">사번</th>
                        <th width="" column-name="koreanNm" column-sort="">이름</th>
                        <th width="" column-name="emailAdres" column-sort="">이메일 주소</th>
                        <th width="" column-name="tlphonNo" column-sort="">전화 번호</th>
                        <th width="" column-name="deptNames" column-sort="">조직명</th>
                        <th width="70px">삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="row in tbSurveyGroupPersonList.result.list" v-bind:key="row.personId">
                        <th class="tac">{{ row.rn }}</th>
                        <td class="tac">{{ row.emplNo }}</td>
                        <td class="tac">{{ row.koreanNm }}</td>
                        <td class="tal">{{ row.emailAdres }}</td>
                        <td class="tal">{{ row.tlphonNo }}</td>
                        <td class="tal">{{ row.deptNames }}</td>
                        <td class="tac">
                            <ui-button v-on:click="deletePerson(row);">삭제</ui-button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </ui-grid-paging>
    </div>
</div>

<script>
vues.read = new Vue({
    el: '#read',
    data: {
        surveyGroupId: null,
        model: {
            tbSurveyGroup: {}
        },
        tbSurveyGroupPersonList: {
            request: GridUtil.getRequestWithPaging({}),
            result: {}
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
            this.service.read(surveyGroupId);
            this.service.readPersonList();
        },
        // 대상자 추가 화면
        viewTargetPersonAdd: function(vueName) {
            CommonUtil.popupVue(vueName, this.surveyGroupId);
        },
        // 대상자 삭제
        deletePerson: function(row) {
            this.service.deletePerson(row.personId);
        },
        // 수정 화면
        viewEdit: function() {
            CommonUtil.changeVue("edit", this.surveyGroupId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
