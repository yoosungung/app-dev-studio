<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        메일 상세
    </div>

    <div class="pageContents">
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>메일 제목</th>
                    <td colspan="3">
                        <span>{{ model.tbComEmail.emailSj }}</span>
                    </td>
                </tr>
                <tr>
                    <th>발송자</th>
                    <td>
                        <span>{{ model.tbComEmail.senderNm }} &lt;{{ model.tbComEmail.senderEmailAdres }}&gt;</span>
                    </td>
                    <th>작성일시</th>
                    <td>
                        <span>{{ formatter.datetime(model.tbComEmail.writngDt) }}</span>
                    </td>
                </tr>
                <tr>
                    <th>수신자</th>
                    <td colspan="3">
                        <table class="list">
                            <thead>
                                <tr>
                                    <th width="40px">No</th>
                                    <th width="80px">수신유형</th>
                                    <th width="">수신자 메일주소</th>
                                    <th width="">수신자 이름</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(row, index) in model.tbComEmailRecptnList" v-bind:key="row.emailRecptnId">
                                    <th class="tac">{{ index + 1 }}</th>
                                    <td class="tac">{{ row.recptnTy }}</td>
                                    <td class="tac">{{ row.rcverEmailAdres }}</td>
                                    <td class="tac">{{ row.rcverNm }}</td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <th>발송 이력</th>
                    <td colspan="3">
                        <table class="list">
                            <thead>
                                <tr>
                                    <th width="40px">No</th>
                                    <th width="110px">발송서버 IP</th>
                                    <th width="80px">발송상태</th>
                                    <th width="">테스트 메일주소</th>
                                    <th width="160px">발송일시</th>
                                    <th width="">발송결과</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(row, index) in model.tbComEmailSndngList" v-bind:key="row.emailSndngId">
                                    <th class="tac">{{ index + 1 }}</th>
                                    <td class="tac">{{ row.sndngServerIp }}</td>
                                    <td class="tac">{{ row.sndngSttusNm }}</td>
                                    <td class="tac">{{ row.testEmailAdres }}</td>
                                    <td class="tac">{{ formatter.datetime(row.sndngDt) }}</td>
                                    <td class="tal">
                                        <div v-if="row.sndngSttus == 'FAILURE'">
                                            <a href="javascript:void(0);" v-on:click="viewSndngResult();" style="color: red;">Fail</a>
                                            <div id="tbComEmailSndng_sndngResult" style="display: none;">
                                                <div style="white-space: pre;">{{ row.sndngResult }}</div>
                                            </div>
                                        </div>
                                        <div v-else>{{ row.sndngResult }}</div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <th>메일 내용</th>
                    <td colspan="3">
                        <ui-iframe v-model="model.tbComEmail.emailCn"></ui-iframe>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['read'](emailId);">새로고침</ui-button>
            <ui-button class="page" v-on:click="service['createSend']();" v-if="reSendAvail">재발송</ui-button>
            <ui-button class="page" v-on:click="service['delete']();">삭제</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.read = new Vue({
    el: '#read',
    data: {
        model: {
            tbComEmail: {},
            tbComEmailRecptnList: [],
            tbComEmailSndngList: [],
            tbComEmailAtchFileList: []
        }
    },
    computed: {
        service: function() {
            return new EmailLogManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        // 재발송 가능 여부
        reSendAvail: function() {
            var sndngList = this.model.tbComEmailSndngList;

            for (var i = 0; i < sndngList.length; i++) {
                if (sndngList[i].sndngSttus == "READY" || sndngList[i].sndngSttus == "WORKING") {
                    return false;
                }
            }

            return true;
        }
    },
    methods: {
        // 초기화
        init: function(emailId) {
            this.service.read(emailId);
        },
        // 결과 화면
        viewSndngResult: function() {
            $('#tbComEmailSndng_sndngResult').dialog({
                title: "발송결과 상세",
                modal: true,
                width: 900,
                height: 600
            });
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
