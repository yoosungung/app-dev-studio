<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageHeader">
        프로그램 {{ resrceId == null ? "등록" : "수정" }}
    </div>

    <div class="pageContents">
        <h3>프로그램 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>프로그램 유형</th>
                    <td>
                        <ui-radios v-model="model.tbSysResrce.resrceTy" code-data="/domain.main.tbSysResrce/resrceTy"></ui-radios>
                        <ui-valid-checker v-bind:value="model.tbSysResrce.resrceTy" check="required"></ui-valid-checker>
                    </td>
                    <th>프로그램 패턴</th>
                    <td>
                        <ui-input v-model="model.tbSysResrce.resrcePttrn" type="text" v-bind:max-byte="200" v-bind:byte-indicator="false" auto-focus></ui-input>
                        <ui-valid-checker v-bind:value="model.tbSysResrce.resrcePttrn" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>정렬 순서</th>
                    <td>
                        <ui-input v-model="model.tbSysResrce.sortOrdr" type="number" min="1" max="999"></ui-input>
                        <ui-valid-checker v-bind:value="model.tbSysResrce.sortOrdr" check="required"></ui-valid-checker>
                    </td>
                    <th>사용 여부</th>
                    <td>
                        <ui-radios v-model="model.tbSysResrce.useYn" code-data="/common/useYn"></ui-radios>
                        <ui-valid-checker v-bind:value="model.tbSysResrce.useYn" check="required"></ui-valid-checker>
                    </td>
                </tr>
            </tbody>
        </table>

        <h3>프로그램 권한</h3>
        <table class="bbs_list">
            <thead>
                <tr>
                    <th width="">권한 코드</th>
                    <th width="">권한 이름</th>
                    <th width="100px">사용 여부</th>
                    <th width="120px">권한 부여</th>
                </tr>
            </thead>
            <tbody v-cloak>
                <tr v-for="row in model.tbSysResrceAuthorToAuthorList" v-bind:key="row.authorId">
                    <td class="tal" v-bind:style="{ color: row.resrceAuthorYn ? 'blue' : '' }">{{ row.authorCode }}</td>
                    <td class="tal" v-bind:style="{ color: row.resrceAuthorYn ? 'blue' : '' }">{{ row.authorNm }}</td>
                    <td class="tac" v-bind:style="{ color: row.resrceAuthorYn ? 'blue' : '' }">{{ row.useYnNm }}</td>
                    <td class="tac">
                        <ui-checkbox v-model="row.resrceAuthorYn"></ui-checkbox>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="resrceId != null">삭제</ui-button>
            <ui-button class="page" v-on:click="viewRead();" v-if="resrceId != null">취소</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        resrceId: null,
        model: {
            tbSysResrce: {},
            tbSysResrceAuthorToAuthorList: []
        }
    },
    computed: {
        service: function() {
            return new ResourceManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(resrceId) {
            this.service.read(resrceId, function(model) {
                if (resrceId != null && model.editable !== true) {
                    CommonUtil.replaceVue("read", resrceId);
                }
            });
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.resrceId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.replaceVue("list");
        }
    }
});
</script>
