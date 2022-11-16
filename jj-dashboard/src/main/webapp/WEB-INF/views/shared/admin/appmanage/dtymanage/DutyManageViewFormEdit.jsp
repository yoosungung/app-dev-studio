<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageHeader">
        직무 {{ dtyId == null ? "등록" : "수정" }}
    </div>

    <div class="pageContents">
        <h3>직무 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>직무 구분</th>
                    <td>
                        <ui-radios v-model="model.tbComDty.dtySe" code-data="/domain.main.tbComDty/dtySe"></ui-radios>
                        <ui-valid-checker v-bind:value="model.tbComDty.dtySe" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>직무 코드</th>
                    <td>
                        <ui-input v-model="model.tbComDty.dtyCode" type="text" v-bind:max-byte="10" auto-focus></ui-input>
                        <ui-valid-checker v-bind:value="model.tbComDty.dtyCode" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>직무 이름</th>
                    <td>
                        <ui-input v-model="model.tbComDty.dtyNm" type="text" v-bind:max-byte="300"></ui-input>
                        <ui-valid-checker v-bind:value="model.tbComDty.dtyNm" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>사용 여부</th>
                    <td>
                        <ui-radios v-model="model.tbComDty.useYn" code-data="/common/useYn"></ui-radios>
                        <ui-valid-checker v-bind:value="model.tbComDty.useYn" check="required"></ui-valid-checker>
                    </td>
                </tr>
            </tbody>
        </table>

        <h3>직무별 권한</h3>
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
                <tr v-for="row in model.tbComDtyAuthorToAuthorList" v-bind:key="row.authorId">
                    <td class="tal" v-bind:style="{ color: row.dtyAuthorYn ? 'blue' : '' }">{{ row.authorCode }}</td>
                    <td class="tal" v-bind:style="{ color: row.dtyAuthorYn ? 'blue' : '' }">{{ row.authorNm }}</td>
                    <td class="tac" v-bind:style="{ color: row.dtyAuthorYn ? 'blue' : '' }">{{ row.useYnNm }}</td>
                    <td class="tac">
                        <ui-checkbox v-model="row.dtyAuthorYn"></ui-checkbox>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="dtyId != null">삭제</ui-button>
            <ui-button class="page" v-on:click="viewRead();" v-if="dtyId != null">취소</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        dtyId: null,
        model: {
            tbComDty: {},
            tbComDtyAuthorToAuthorList: []
        }
    },
    computed: {
        service: function() {
            return new DutyManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(dtyId) {
            this.service.read(dtyId, function(model) {
                if (dtyId != null && model.editable !== true) {
                    CommonUtil.replaceVue("read", dtyId);
                }
            });
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.dtyId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.replaceVue("list");
        }
    }
});
</script>
