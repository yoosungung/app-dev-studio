<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="create" style="display: none;">
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
                    <th>메시지 제목</th>
                    <td colspan="3">
                        <ui-input type="text" v-model="model.tbComSms.tranEtc1" v-bind:max-byte="64" v-bind:byte-indicator="false" auto-focus></ui-input>
                    </td>
                </tr>
                <tr>
                    <th>송신자 전화번호</th>
                    <td>
                        <ui-input type="text" v-model="model.tbComSms.tranCallback" v-bind:max-byte="15" v-bind:byte-indicator="false"></ui-input>
                        <ui-valid-checker v-bind:value="model.tbComSms.tranCallback" check="required"></ui-valid-checker>
                    </td>
                    <th>UMS 그룹 ID</th>
                    <td>
                        <ui-input type="text" v-model="model.umsGroupId" v-bind:max-byte="20" v-bind:byte-indicator="false"></ui-input>
                    </td>
                </tr>
                <tr>
                    <th>수신자 전화번호</th>
                    <td>
                        <ui-input type="text" v-model="model.tbComSms.tranPhone" v-bind:max-byte="15" v-bind:byte-indicator="false"></ui-input>
                        <ui-valid-checker v-bind:value="model.tbComSms.tranCallback" check="required"></ui-valid-checker>
                    </td>
                    <th>수신자 이름</th>
                    <td>
                        <ui-input type="text" v-model="model.tbComSms.tranEtc3" v-bind:max-byte="16" v-bind:byte-indicator="false"></ui-input>
                        <ui-valid-checker v-bind:value="model.tbComSms.tranEtc3" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>메시지 내용</th>
                    <td colspan="3">
                        <ui-textarea v-model="model.tbComSms.tranMsg" rows="5" style="height: 250px;"></ui-textarea>
                        <ui-valid-checker v-bind:value="model.tbComSms.tranMsg" check="required"></ui-valid-checker>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['create']('create');">저장</ui-button>
            <ui-button class="page" v-on:click="service['create']('send');">직접 발송</ui-button>
            <ui-button class="list" v-on:click="closePopup();">닫기</ui-button>
        </div>
    </div>
</div>

<script>
vues.create = new Vue({
    el: '#create',
    data: {
        model: {
            umsGroupId: null,
            tbComSms: {}
        }
    },
    computed: {
        service: function() {
            return new SmsLogManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function() {
            $(this.$el).dialog({
                title: "발송 테스트",
                modal: true,
                width: 900,
                height: 520
            });
        },
        // 닫기
        closePopup: function() {
            $(this.$el).dialog("close");
        }
    }
});
</script>
