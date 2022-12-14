<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="personGroupList" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readPersonGroupList(true);">
                <colgroup>
                    <col width="12%">
                    <col width="">
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>그룹명</th>
                    <td>
                        <input type="text" v-model="gridRequest.search.groupNm">
                    </td>
                    <td>
                        <div class="btnArea_center">
                            <ui-button class="btn_total_search" v-on:click="service.readPersonGroupList(true);">조회</ui-button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <!-- 검색조건 영역 끝 -->

        <!-- 결과목록 영역 시작 -->
        <ui-grid-paging v-bind:request="gridRequest" v-bind:result="gridResult" v-bind:read-list="service.readPersonGroupList">
            <table class="bbs_list">
                <thead>
                    <tr>
                        <th width="" column-name="groupNm" column-sort>그룹</th>
                        <th width="" column-name="groupDc" column-sort>그룹 설명</th>
                        <th width="150px" column-name="cnrsYn" column-sort>공유 여부</th>
                        <th width="100px" column-name="registDt" column-sort="DESC">등록일</th>
                        <th width="100px" column-name="registPsnNm" column-sort>등록자</th>
                        <th width="100px" column-name="personCo" column-sort>대상자 수</th>
                        <th width="70px">추가</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="row in gridResult.list" v-bind:key="row.surveyGroupId">
                        <td class="tal">{{ row.groupNm }}</td>
                        <td class="tal">{{ row.groupDc }}</td>
                        <td class="tac">{{ row.cnrsYnNm }}</td>
                        <td class="tac">{{ formatter.date(row.registDt) }}</td>
                        <td class="tac">{{ row.registPsnNm }}</td>
                        <td class="tac">{{ formatter.number(row.personCo) }}</td>
                        <td class="tac">
                            <ui-button v-on:click="createGroupPersonList(row);">추가</ui-button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </ui-grid-paging>
        <!-- 결과목록 영역 시작 -->
    </div>
</div>

<script>
vues.personGroupList = new Vue({
    el: '#personGroupList',
    data: {
        gridRequest: GridUtil.getRequestWithPaging({}),
        gridResult: {}
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
            this.gridRequest.search.surveyQestnrId = surveyQestnrId;
            this.gridResult = {};

            this.service.readPersonGroupList(true);

            $(this.$el).dialog({
                title: "그룹 검색",
                modal: true,
                width: 1000,
                height: 750,
                close: function() {
                    vues.personList.service.readPersonList();
                }
            });
        },
        // 그룹별 사람 목록 생성
        createGroupPersonList: function(row) {
            var vue = this;

            this.service.createGroupPersonList(row, function() {
                $(vue.$el).dialog("close");
            });
        }
    }
});
</script>
