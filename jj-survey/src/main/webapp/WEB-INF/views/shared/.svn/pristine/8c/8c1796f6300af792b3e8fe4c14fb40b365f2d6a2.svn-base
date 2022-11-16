<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="hierarchy" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <div class="table full">
            <h3>권한계층 목록</h3>
            <div class="tar">
                <ui-list-button-append v-bind:data="model.tbSysRoleHierarchyList"
                    v-bind:new-row-data="{}"
                    id-column-name="roleHierarchyId"
                    sort-column-name="sortOrdr"
                ></ui-list-button-append>
                <ui-list-button-delete v-bind:data="model.tbSysRoleHierarchyList"
                    sort-column-name="sortOrdr"
                ></ui-list-button-delete>
            </div>
        </div>
        <table class="list">
            <thead>
                <tr>
                    <th width="50px">번호</th>
                    <th width="40px">
                        <ui-checkbox-head v-bind:data="model.tbSysCodeValueList"></ui-checkbox-head>
                    </th>
                    <th width="">부모 역할 코드</th>
                    <th width="">자식 역할 코드</th>
                    <th width="100px">정렬</th>
                </tr>
            </thead>
            <tbody v-cloak>
                <tr v-for="row in tbSysRoleHierarchyFilteredList" v-bind:key="row.roleHierarchyId">
                    <td class="tac">{{ row.sortOrdr }}</td>
                    <td class="tac">
                        <ui-checkbox-row v-bind:data="row"></ui-checkbox-row>
                    </td>
                    <td class="tal">
                        <ui-select code-data="admin.sysmanage.authorCode" v-model="row.parntsRoleCode"></ui-select>
                        <ui-valid-checker v-bind:value="row.parntsRoleCode" check="required"></ui-valid-checker>
                    </td>
                    <td class="tal">
                        <ui-select code-data="admin.sysmanage.authorCode" v-model="row.chldrnRoleCode"></ui-select>
                        <ui-valid-checker v-bind:value="row.chldrnRoleCode" check="required"></ui-valid-checker>
                    </td>
                    <td class="tac">
                        <ui-button v-on:click="moveRow(row, -1);">▲</ui-button>
                        <ui-button v-on:click="moveRow(row, +1);">▼</ui-button>
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

<script src="${pageContext.request.contextPath }/shared/admin/sysmanage/authormanage/AuthorityHierarchyManageViewService.js"></script>

<script>
vues.hierarchy = new Vue({
    el: '#hierarchy',
    data: {
        model: {
            tbSysRoleHierarchyList: []
        }
    },
    computed: {
        service: function() {
            return new AuthorityHierarchyManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        tbSysRoleHierarchyFilteredList: function() {
            return CommonUtil.getFilteredList(this.model.tbSysRoleHierarchyList, "sortOrdr");
        }
    },
    methods: {
        // 초기화
        init: function() {
            var vue = this;

            CommonUtil.loadCodeData("admin.sysmanage.authorCode", function() {
                vue.service.read();

                $(vue.$el).dialog({
                    title: "권한계층 편집",
                    modal: true,
                    width: 900,
                    height: 600
                });
            }, true);
        },
        // 행 이동
        moveRow: function(row, direction) {
            CommonUtil.setListSortOrder(this.model.tbSysRoleHierarchyList, "sortOrdr", row, direction);
        },
        // 닫기
        closePopup: function() {
            $(this.$el).dialog("close");
        }
    }
});
</script>
