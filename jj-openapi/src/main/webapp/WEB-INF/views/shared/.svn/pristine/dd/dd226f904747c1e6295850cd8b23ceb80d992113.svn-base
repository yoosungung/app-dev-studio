<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <h3>메뉴 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>상위 메뉴</th>
                    <td>
                        <span>{{ model.tbSysMenu.upperMenuNm }}</span>
                    </td>
                </tr>
                <tr>
                    <th>메뉴 이름</th>
                    <td>
                        <ui-input type="text" v-model="model.tbSysMenu.menuNm" v-bind:max-byte="500" v-bind:byte-indicator="false" auto-focus></ui-input>
                        <ui-valid-checker v-bind:value="model.tbSysMenu.menuNm" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>메뉴 경로</th>
                    <td>
                        <ui-input type="text" v-model="model.tbSysMenu.menuPath" v-bind:max-byte="1000" v-bind:byte-indicator="false"></ui-input>
                    </td>
                </tr>
                <tr>
                    <th>사용 여부</th>
                    <td>
                        <ui-radios v-model="model.tbSysMenu.useYn" code-data="/common/useYn"></ui-radios>
                        <ui-valid-checker v-bind:value="model.tbSysMenu.useYn" check="required"></ui-valid-checker>
                    </td>
                </tr>
            </tbody>
        </table>

        <h3>메뉴 권한</h3>
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
                <tr v-for="row in model.tbSysMenuAuthorToAuthorList" v-bind:key="row.authorId">
                    <td class="tal" v-bind:style="{ color: row.menuAuthorYn ? 'blue' : '' }">{{ row.authorCode }}</td>
                    <td class="tal" v-bind:style="{ color: row.menuAuthorYn ? 'blue' : '' }">{{ row.authorNm }}</td>
                    <td class="tac" v-bind:style="{ color: row.menuAuthorYn ? 'blue' : '' }">{{ row.useYnNm }}</td>
                    <td class="tac">
                        <ui-checkbox v-model="row.menuAuthorYn"></ui-checkbox>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="list" v-on:click="closePopup();">닫기</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        menuId: null,
        upperMenuId: null,
        model: {
            tbSysMenu: {},
            tbSysMenuAuthorToAuthorList: []
        }
    },
    computed: {
        service: function() {
            return new MenuManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(menuId) {
            var vue = this;

            this.service.read(menuId, function(model) {
                if (menuId != null && model.editable !== true) {
                    vue.closePopup();
                } else {
                    $(vue.$el).dialog({
                        title: "메뉴 수정",
                        modal: true,
                        width: 900,
                        height: 600
                    });
                }
            });
        },
        // 초기화 - 생성용
        initForCreate: function(upperMenuId) {
            var vue = this;

            this.service.readForCreate(upperMenuId, function(model) {
                $(vue.$el).dialog({
                    title: "메뉴 등록",
                    modal: true,
                    width: 900,
                    height: 600
                });
            });
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.menuId);
        },
        // 닫기
        closePopup: function() {
            $(this.$el).dialog("close");
        }
    }
});
</script>
