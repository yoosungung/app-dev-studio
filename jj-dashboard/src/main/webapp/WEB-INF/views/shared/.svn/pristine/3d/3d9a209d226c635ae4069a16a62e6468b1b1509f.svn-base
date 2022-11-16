<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="editUpperMenu" style="display: none;">
    <div class="pageContents">
        <div class="box">
            <ui-jstree ref="menuTree"
                v-bind:data="treeData"
                v-bind:columns="{ id: 'menuId', parent: 'upperMenuId', text: 'menuNm', sort: 'menuOrdr' }"
                v-bind:options="{ initiallyOpenLevel: 2 }"
                v-model="upperMenuId"
            ></ui-jstree>
        </div>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['updateUpperMenu'](upperMenuId);">저장</ui-button>
            <ui-button class="list" v-on:click="closePopup();">닫기</ui-button>
        </div>
    </div>
</div>

<script>
vues.editUpperMenu = new Vue({
    el: '#editUpperMenu',
    data: {
        menuId: null,
        upperMenuId: null,
        treeData: [],
        tbSysMenuRoot: {
            menuNm: document.title
        }
    },
    computed: {
        service: function() {
            return new MenuManageViewService(this);
        }
    },
    methods: {
        // 초기화
        init: function(menuId) {
            var vue = this;

            vue.menuId = menuId;

            vue.service.readTreeForUpperMenuSelect();

            $(vue.$el).dialog({
                title: "상위메뉴 변경",
                modal: true,
                width: 400,
                height: 400
            });
        },
        // 닫기
        closePopup: function() {
            $(this.$el).dialog("close");
        }
    }
});
</script>
