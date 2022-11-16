<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        메뉴 관리
    </div>

    <div class="pageContents">
        <div class="table">
            <div class="box">
                <ui-jstree ref="menuTree"
                    v-bind:data="treeData"
                    v-bind:columns="{ id: 'menuId', parent: 'upperMenuId', text: 'menuNm', sort: 'menuOrdr' }"
                    v-bind:options="{ initiallyOpenLevel: 2 }"
                    v-model="menuId"
                    v-on:item-click="treeItemClick"
                ></ui-jstree>
            </div>
            <div style="padding-left: 20px;">
                <h3 style="margin-top: 0px;">메뉴 정보</h3>
                <table class="form">
                    <colgroup>
                        <col width="120px">
                        <col width="">
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>상위메뉴 이름</th>
                            <td>
                                <span>{{ model.tbSysMenu.upperMenuNm }}</span>
                            </td>
                        </tr>
                        <tr>
                            <th>메뉴 이름</th>
                            <td>
                                <span>{{ model.tbSysMenu.menuNm }}</span>
                            </td>
                        </tr>
                        <tr>
                            <th>메뉴 경로</th>
                            <td>
                                <span>{{ model.tbSysMenu.menuPath }}</span>
                            </td>
                        </tr>
                        <tr>
                            <th>사용 여부</th>
                            <td>
                                <span>{{ model.tbSysMenu.useYnNm }}</span>
                            </td>
                        </tr>
                    </tbody>
                </table>

                <h3 v-if="menuId != null">메뉴 권한</h3>
                <table class="bbs_list" v-if="menuId != null">
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
                            <td class="tac" v-bind:style="{ color: row.menuAuthorYn ? 'blue' : '' }">{{ row.menuAuthorYnNm }}</td>
                        </tr>
                    </tbody>
                </table>

                <div class="page-space tar">
                    <ui-button class="page" v-on:click="moveMenuOrdr(-1);" v-if="model.editable">위로</ui-button>
                    <ui-button class="page" v-on:click="moveMenuOrdr(+1);" v-if="model.editable">아래로</ui-button>
                    <ui-button class="page" v-on:click="createSubMenu();" v-if="childMenuAddable">하위메뉴 추가</ui-button>
                    <ui-button class="page" v-on:click="changeUpperMenu();" v-if="upperMenuChangeable">상위메뉴 변경</ui-button>
                    <ui-button class="page" v-on:click="viewEdit();" v-if="model.editable">수정</ui-button>
                    <ui-button class="page" v-on:click="service['delete']();" v-if="menuDeleteable">삭제</ui-button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
vues.list = new Vue({
    el: '#list',
    data: {
        menuKnd: "MAIN",
        menuId: null,
        treeData: [],
        tbSysMenuRoot: {
            menuNm: document.title
        },
        model: {
            tbSysMenu: {},
            tbSysMenuAuthorToAuthorList: []
        },
        maxLevel: 2
    },
    computed: {
        service: function() {
            return new MenuManageViewService(this);
        },
        childMenuAddable: function() {
            return this.model.tbSysMenu.menuLevel == null || this.model.tbSysMenu.menuLevel < this.maxLevel;
        },
        upperMenuChangeable: function() {
            if (!this.model.editable || this.model.tbSysMenu.menuLevel == null) {
                return false;
            }

            if (this.model.tbSysMenu.menuLevel >= this.maxLevel) {
                return true;
            }

            if (this.model.childMenuCount == 0) {
                return true;
            }
        },
        menuDeleteable: function() {
            return this.model.editable && this.model.childMenuCount == 0;
        }
    },
    mounted: function() {
        this.service.readTree();
        this.service.read();
    },
    methods: {
        // 수정 화면
        viewEdit: function() {
            CommonUtil.popupVue("edit", this.menuId);
        },
        // 트리 항목 클릭시
        treeItemClick: function(node, item, data, event) {
            this.service.read(data == null ? null : data.menuId);
        },
        // 메뉴 순서 이동
        moveMenuOrdr: function(direction) {
            var tbSysMenuList = this.$refs.menuTree.moveNode(direction);
            this.service.updateMenuOrdrList(tbSysMenuList);
        },
        // 하위메뉴 추가
        createSubMenu: function() {
            CommonUtil.popupVue("edit.initForCreate", this.menuId);
        },
        // 상위메뉴 변경
        changeUpperMenu: function() {
            CommonUtil.popupVue("editUpperMenu", this.menuId);
        }
    }
});
</script>
