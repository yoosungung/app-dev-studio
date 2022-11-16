<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<style>
div.cke_contents_ltr blockquote {
    font-style: italic;
    font-family: Georgia, Times, "Times New Roman", serif;
    padding: 2px 0;
    border-style: solid;
    border-color: #ccc;
    border-width: 0;
    padding-left: 20px;
    padding-right: 8px;
    border-left-width: 5px;
    overflow-x: auto;
}
</style>

<div id="read" style="display: none;">
    <div class="pageHeader">
        Q&A 상세
    </div>

    <div class="pageContents">
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>제목</th>
                    <td colspan="5">
                        <span>{{ model.tbBbsGnrl.bbscttSj }}</span>
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>
                        <span>{{ model.tbBbsGnrl.writngPsnNm }}</span>
                    </td>
                    <th>등록일</th>
                    <td>
                        <span>{{ formatter.date(model.tbBbsGnrl.writngDt) }}</span>
                    </td>
                    <th>조회수</th>
                    <td>
                        <span>{{ model.tbBbsGnrl.rdcnt}}</span>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="5">
                        <div v-html="model.tbBbsGnrl.bbscttCn" class="cke_contents_ltr"></div>
                    </td>
                </tr>
                <tr>
                    <th>파일</th>
                    <td colspan="5">
                        <ui-filedownload v-model="model.tbBbsGnrl.atchFileList"></ui-filedownload>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-if="model.tbBbsGnrl.deleteYn == '0'" v-on:click="viewReply();">답변</ui-button>
            <ui-button class="page" v-if="model.editable" v-on:click="viewEdit();">수정</ui-button>
            <ui-button class="page" v-if="model.editable" v-on:click="service['delete']();">삭제</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.read = new Vue({
    el: '#read',
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
        }
    },
    methods: {
        // 초기화
        init: function(bbscttId) {
            this.service.read(bbscttId, true);
        },
        // 수정 화면
        viewEdit: function() {
            CommonUtil.changeVue("edit", this.bbscttId);
        },
        // 답변 화면
        viewReply: function() {
            CommonUtil.changeVue("edit", this.bbscttId, "reply");
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
