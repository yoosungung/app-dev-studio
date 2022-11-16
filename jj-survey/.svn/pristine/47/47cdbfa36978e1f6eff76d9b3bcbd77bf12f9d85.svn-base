<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="publishMediaSelect" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <ui-checkbox v-model="publishModel.emailSndngYn">이메일 발송</ui-checkbox>
        <ui-checkbox v-model="publishModel.smsSndngYn">SMS 발송</ui-checkbox>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="confirm();">확인</ui-button>
            <ui-button class="list" v-on:click="closePopup();">취소</ui-button>
        </div>
    </div>
</div>

<script>
vues.publishMediaSelect = new Vue({
    el: '#publishMediaSelect',
    data: {
        callback: null,
        publishModel: {
            emailSndngYn: false,
            smsSndngYn: false
        }
    },
    computed: {
        service: function() {
            return new QuestionnaireManageViewService(this);
        }
    },
    methods: {
        // 초기화
        init: function(callback) {
            this.callback = callback;

            $(this.$el).dialog({
                title: "발송 매체 선택",
                modal: true,
                width: 300,
                height: 150
            });
        },
        // 확인
        confirm: function() {
            if (this.callback(this.publishModel) !== false) {
                this.closePopup();
            }
        },
        // 닫기
        closePopup: function() {
            $(this.$el).dialog("close");
        }
    }
});
</script>
