<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageHeader">
        코드 {{ codeGroupId == null ? "등록" : "수정" }}
    </div>

    <div class="pageContents">
        <h3>코드그룹 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>코드그룹</th>
                    <td>
                        <ui-input type="text" v-model="model.tbSysCodeGroup.codeGroup" v-bind:max-byte="200" v-bind:byte-indicator="false" auto-focus></ui-input>
                        <ui-valid-checker v-bind:value="model.tbSysCodeGroup.codeGroup" check="required"></ui-valid-checker>
                    </td>
                    <th>코드그룹 이름</th>
                    <td>
                        <ui-input type="text" v-model="model.tbSysCodeGroup.codeGroupNm" v-bind:max-byte="500" v-bind:byte-indicator="false"></ui-input>
                        <ui-valid-checker v-bind:value="model.tbSysCodeGroup.codeGroupNm" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>코드그룹 설명</th>
                    <td colspan="3">
                        <ui-textarea rows="3" v-model="model.tbSysCodeGroup.codeGroupDc" v-bind:max-byte="2000" v-bind:byte-indicator="true"></ui-textarea>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="table full">
            <h3>코드값 목록</h3>
            <div class="tar">
                <ui-list-button-append v-bind:data="model.tbSysCodeValueList"
                    v-bind:new-row-data="{ useYn: true }"
                    id-column-name="codeValueId"
                    sort-column-name="sortOrdr"
                ></ui-list-button-append>
                <ui-list-button-delete v-bind:data="model.tbSysCodeValueList"
                    sort-column-name="sortOrdr"
                ></ui-list-button-delete>
            </div>
        </div>
        <table class="list">
            <thead>
                <tr>
                    <th width="40px">
                        <ui-checkbox-head v-bind:data="model.tbSysCodeValueList"></ui-checkbox-head>
                    </th>
                    <th width="">코드값</th>
                    <th width="">코드값 이름</th>
                    <th width="100px">정렬</th>
                    <th width="80px">사용여부</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="row in tbSysCodeValueListFiltered" v-bind:key="row.codeValueId">
                    <td class="tac">
                        <ui-checkbox-row v-bind:data="row"></ui-checkbox-row>
                    </td>
                    <td class="tal">
                        <ui-input type="text" v-model="row.codeValue" v-bind:max-byte="100" v-bind:byte-indicator="false"></ui-input>
                        <ui-valid-checker v-bind:value="row.codeValue" check="required"></ui-valid-checker>
                        <ui-valid-checker-duplicate v-bind:data="model.tbSysCodeValueList" v-bind:row-data="row" column-name="codeValue"></ui-valid-checker-duplicate>
                    </td>
                    <td class="tal">
                        <ui-input type="text" v-model="row.codeValueNm" v-bind:max-byte="1000" v-bind:byte-indicator="false"></ui-input>
                        <ui-valid-checker v-bind:value="row.codeValueNm" check="required"></ui-valid-checker>
                        <ui-valid-checker-duplicate v-bind:data="model.tbSysCodeValueList" v-bind:row-data="row" column-name="codeValueNm"></ui-valid-checker-duplicate>
                    </td>
                    <td class="tac">
                        <ui-button v-on:click="moveRow(row, -1);">▲</ui-button>
                        <ui-button v-on:click="moveRow(row, +1);">▼</ui-button>
                    </td>
                    <td class="tac">
                        <ui-checkbox v-model="row.useYn">사용</ui-checkbox>
                    </td>
                </tr>
            </tbody>
        </table>
        <ui-valid-checker v-bind:value="model.tbSysCodeValueList" check="required"></ui-valid-checker>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="codeGroupId != null">삭제</ui-button>
            <ui-button class="page" v-on:click="viewRead();" v-if="codeGroupId != null">취소</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        codeGroupId: null,
        model: {
            tbSysCodeGroup: {},
            tbSysCodeValueList: []
        }
    },
    computed: {
        service: function() {
            return new CodeManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        tbSysCodeValueListFiltered: function() {
            return CommonUtil.getFilteredList(this.model.tbSysCodeValueList, "sortOrdr");
        }
    },
    methods: {
        // 초기화
        init: function(codeGroupId) {
            this.service.read(codeGroupId, function(model) {
                if (codeGroupId != null && model.editable !== true) {
                    CommonUtil.replaceVue("read", codeGroupId);
                }
            });
        },
        // 행 이동
        moveRow: function(row, direction) {
            CommonUtil.setListSortOrder(this.model.tbSysCodeValueList, "sortOrdr", row, direction);
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.codeGroupId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.replaceVue("list");
        }
    }
});
</script>