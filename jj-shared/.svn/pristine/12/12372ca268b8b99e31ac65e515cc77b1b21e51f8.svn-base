<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageHeader" v-if="bbscttId == null">
        Q&A 등록
    </div>
    <div class="pageHeader" v-if="bbscttId != null">
        Q&A 수정
    </div>

    <div class="pageContents">
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>제목</th>
                    <td>
                        <ui-input type="text" v-model="model.tbBbsGnrl.bbscttSj" v-bind:max-byte="2000" v-bind:byte-indicator="false" auto-focus></ui-input>
                        <ui-valid-checker v-bind:value="model.tbBbsGnrl.bbscttSj" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <ui-textarea rows="3" v-model="model.tbBbsGnrl.bbscttCn"  v-bind:byte-indicator="true"></ui-textarea>
                        <ui-valid-checker v-bind:value="model.tbBbsGnrl.bbscttCn" check="required"></ui-valid-checker>
                    </td>
                </tr>
            </tbody>
        </table>
        <ui-valid-checker v-bind:value="model.tbBbsGnrl" check="required"></ui-valid-checker>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="viewRead();" v-if="bbscttId != null">취소</ui-button>
            <ui-button class="page" v-on:click="viewAnsRead();" v-if="isAnswer()">취소</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        bbscttId: null,
        model: {
            tbBbsGnrl: {}
        }
    },
    computed: {
        service: function() {
            return new BbsGeneralViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
    },
    methods: {
        //답글 여부
        isAnswer: function() {
            return (this.bbscttId == null && this.model.tbBbsGnrl.frstBbscttId != null);
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.setViewMode(vues, "read");

            vues.read.service.read(this.bbscttId);
        },
        // 조회 화면
        viewAnsRead: function() {
            CommonUtil.setViewMode(vues, "read");

            vues.read.service.read(this.model.tbBbsGnrl.frstBbscttId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.setViewMode(vues, "list");
        }
    }
});
</script>
