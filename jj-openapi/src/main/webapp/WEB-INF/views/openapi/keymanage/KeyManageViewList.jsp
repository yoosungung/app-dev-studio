<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        키 발급 관리
    </div>

    <div class="pageContents">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readList(true);">
                <colgroup>
                    <col width="10%">
                    <col width="20%">
                    <col width="10%">
                    <col width="12%">
                    <col width="31%">
                    <col >
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>상태</th>
                    <td>
                        <ui-select code-data="[KEY_STTUS]" first-name="(전체)" v-model="gridRequest.search.keySttus"></ui-select>
                    </td>
                    <th>구분별 검색</th>
                    <td colspan="1">
                        <ui-select code-data="[KEY_APPLY_MANAGE]" first-name="(선택)" v-model="gridRequest.search.keyApplyManage"></ui-select>
                    </td>
                    <td>
                        <ui-date v-model="gridRequest.search.beginDt"></ui-date> ~
                        <ui-date v-model="gridRequest.search.endDt"></ui-date>
                    </td>
                    <td colspan="2">
                        <h4>
                            <ui-checkbox v-model="gridRequest.search.wholedate">
                                &nbsp;&nbsp;전체기간
                            </ui-checkbox>
                        </h4>
                    </td>
                </tr>

                <tr>
                    <th>이메일</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.emailAdres"></ui-input>
                    </td>
                    <th>핸드폰</th>
                    <td colspan="3">
                        <ui-input type="text" v-model="gridRequest.search.tlphonNo"></ui-input>
                    </td>


                    <td>
                        <div class="btnArea_center">
                            <ui-button class="btn_total_search" v-on:click="service.readList(true);">조회</ui-button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <!-- 검색조건 영역 끝 -->

        <!-- 결과목록 영역 시작 -->
        <ui-grid-paging v-bind:request="gridRequest" v-bind:result="gridResult" v-bind:read-list="service.readList">
            <table class="bbs_list">
                <thead>
                    <tr>
                        <th width="" column-name="emplNo" column-sort>학/사번</th>
                        <th width="" column-name="applcntNm" column-sort>신청자</th>
                        <th width="" column-name="sttusNm" column-sort>상태</th>
                        <th width="" column-name="rqstdt" column-sort="DESC">신청일</th>
                        <th width="190px" column-name="keyUsePdBegin" column-sort>사용기간</th>
                        <th width="" column-name="exmntDt" column-sort>검토일</th>
                        <th width="" column-name="keyUsePdBegin" column-sort>검토</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.svcKeyId">
                        <td class="tac">
                            {{ row.emplNo }}
                        </td>
                        <td class="tac">{{ row.applcntNm }}</td>
                        <td class="tac">
                            <a v-bind:style="{ color: sttusNmColor(row), fontWeight: 'bold' }" href="javascript:void(0);" v-on:click="viewDialog(row.svcKeyId);" >{{ row.sttusNm }}</a>
                        </td>
                        <td class="tac">{{ formatter.date(row.rqstdt) }}</td>
                        <td class="tac">{{ formatter.date(row.keyUsePdBegin) }} ~ {{ formatter.date(row.keyUsePdEnd) }}</td>
                        <td class="tac">{{ formatter.date(row.exmntDt) }}</td>
                        <td class="tac">
                            <div v-if="row.sttus == 'A'">
                                <ui-button class="btn_write" v-on:click="viewExamination(row.svcKeyId, 'C');">승인</ui-button>
                                <ui-button class="btn_write" v-on:click="viewExamination(row.svcKeyId, 'R');">반려</ui-button>
                            </div>
                            <div v-if="row.sttus == 'C'">
                                <ui-button v-on:click="sendMail(row.svcKeyId);">메일 발송</ui-button>
                            </div>
                            <div v-if="row.sttus == 'R'">
                                반려
                            </td>
                        </td>
                    </tr>
                </tbody>
            </table>
        </ui-grid-paging>
        <!-- 결과목록 영역 시작 -->
    </div>
</div>

<script>
vues.list = new Vue({
    el: '#list',
    data: {
        gridRequest: GridUtil.getRequestWithPaging({}),
        gridResult: {}
    },
    computed: {
        service: function() {
            return new KeyManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.readList();
    },
    methods: {
        // 상태 폰트 색상 반환
        sttusNmColor: function(row) {
            switch (row.sttus) {
            case "A": // 신청
                return "#009900";
            case "C": // 승인
                return "#0000ff";
            case "R": // 반려
                return "#ff0000";
            }
        },
        // 검토(승인/반려) 화면
        viewExamination: function(svcKeyId, examinationMode) {
            CommonUtil.changeVue("edit", svcKeyId, examinationMode);
        },
        // 상세 화면
        viewDetail: function(svcKeyId) {
            CommonUtil.changeVue("read", svcKeyId);
        },
        // 상태 팝업 화면
        viewDialog: function(svcKeyId) {
            CommonUtil.popupVue("status", svcKeyId);
        },
        // 메일 발송
        sendMail: function(svcKeyId) {
            this.service.sendMail(svcKeyId);
        },
    }
});
</script>
