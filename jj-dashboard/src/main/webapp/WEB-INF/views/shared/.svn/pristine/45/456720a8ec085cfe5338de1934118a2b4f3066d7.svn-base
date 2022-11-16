<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        SMS 상세
    </div>

    <div class="pageContents">
        <table class="form">
            <colgroup>
                <col width="150px">
                <col width="">
                <col width="150px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>TRAN_PR</th>
                    <td>
                        <span>{{ model.tbComSms.tranPr }}</span>
                    </td>
                    <th>부서코드</th>
                    <td>
                        <span>{{ model.tbComSms.deptCode }}</span>
                    </td>
                </tr>
                <tr>
                    <th>TRAN_REFKEY</th>
                    <td>
                        <span>{{ model.tbComSms.tranRefkey }}</span>
                    </td>
                    <th>TRAN_ID</th>
                    <td>
                        <span>{{ model.tbComSms.tranId }}</span>
                    </td>
                </tr>
                <tr>
                    <th>메시지 제목</th>
                    <td colspan="3">
                        <span>{{ model.tbComSms.tranEtc1 }}</span>
                    </td>
                </tr>
                <tr>
                    <th>송신자 전화번호</th>
                    <td>
                        <span>{{ model.tbComSms.tranCallback }}</span>
                    </td>
                    <th>메시지 유형</th>
                    <td>
                        <span>{{ model.tbComSms.tranTypeNm }}</span>
                    </td>
                </tr>
                <tr>
                    <th>수신자 전화번호</th>
                    <td>
                        <span>{{ model.tbComSms.tranPhone }}</span>
                    </td>
                    <th>수신자 이름</th>
                    <td>
                        <span>{{ model.tbComSms.tranEtc3 }}</span>
                    </td>
                </tr>
                <tr>
                    <th>전송 메시지</th>
                    <td colspan="3">
                        <pre>{{ model.tbComSms.tranMsg }}</pre>
                    </td>
                </tr>
                <tr>
                    <th>메시지 상태</th>
                    <td>
                        <span>{{ model.tbComSms.tranStatusNm }}</span>
                    </td>
                    <th>전송 일시</th>
                    <td>
                        <span>{{ formatter.datetime(model.tbComSms.tranDate) }}</span>
                    </td>
                </tr>
                <tr>
                    <th>핸드폰에 전달된 시간</th>
                    <td>
                        <span>{{ formatter.datetime(model.tbComSms.tranRsltdate) }}</span>
                    </td>
                    <th>결과 수신 시간</th>
                    <td>
                        <span>{{ formatter.datetime(model.tbComSms.tranReportdate) }}</span>
                    </td>
                </tr>
                <tr>
                    <th>전송 결과</th>
                    <td>
                        <span>{{ model.tbComSms.tranRsltNm }}</span>
                    </td>
                    <th>TRAN_NET</th>
                    <td>
                        <span>{{ model.tbComSms.tranNet }}</span>
                    </td>
                </tr>
                <tr>
                    <th>타 시스템 유일키</th>
                    <td>
                        <span>{{ model.tbComSms.tranEtc2 }}</span>
                    </td>
                    <th>TRAN_ETC4</th>
                    <td>
                        <span>{{ model.tbComSms.tranEtc4 }}</span>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['read'](smsId);">새로고침</ui-button>
            <ui-button class="page" v-on:click="service['updateReSend']();" v-if="reSendAvail">재발송</ui-button>
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
            tbComSms: {}
        }
    },
    computed: {
        service: function() {
            return new SmsLogManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        // 재발송 가능 여부
        reSendAvail: function() {
            return this.model.tbComSms.tranStatus != "1";
        }
    },
    methods: {
        // 초기화
        init: function(smsId) {
            this.service.read(smsId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
