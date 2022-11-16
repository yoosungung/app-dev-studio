<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf"%>

<div id="edit" style="display: none;">
    <div class="pageHeader">
        공지 {{ bbscttId == null ? "등록" : "수정" }}
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
                        <ui-input type="text" v-model="model.tbBbsNotice.bbscttSj" v-bind:max-byte="200" v-bind:byte-indicator="false" auto-focus></ui-input>
                        <ui-valid-checker v-bind:value="model.tbBbsNotice.bbscttSj" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>공지 내용</th>
                    <td>
                        <ui-ckeditor4 v-model="model.tbBbsNotice.bbscttCn"></ui-ckeditor4>
                        <ui-valid-checker v-bind:value="model.tbBbsNotice.bbscttCn" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td>
                        <ui-filepond v-model="model.tbBbsNotice.atchFileList" multiple policy="bbs.notice"></ui-filepond>
                        <div style="font-size: x-small;">
                            <div>※ 업로드 가능한 파일의 총 용량은 20MB까지 업로드 가능합니다.</div>
                            <div>※ 첨부가능 파일 확장자: zip, rar, doc(*), xls(*), ppt(*), pdf, txt, jpg, gif, bmp, png</div>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="bbscttId != null">삭제</ui-button>
            <ui-button class="page" v-on:click="viewRead();" v-if="bbscttId != null">취소</ui-button>
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
            tbBbsNotice: {}
        }
    },
    computed: {
        service: function() {
            return new BbsNoticeViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(bbscttId) {
            this.service.read(bbscttId, false, function(model) {
                if (bbscttId != null && model.editable !== true) {
                    CommonUtil.replaceVue("read", bbscttId);
                }
            });
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.bbscttId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.replaceVue("list");
        }
    }
});
</script>
