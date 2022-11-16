<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <h3>부서 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>상위부서 이름</th>
                    <td>
                        <span>{{ model.tbComDept.upperDeptNm }}</span>
                    </td>
                </tr>
                <tr>
                    <th>부서 코드</th>
                    <td>
                        <span>{{ model.tbComDept.deptCode }}</span>
                    </td>
                </tr>
                <tr>
                    <th>부서 이름</th>
                    <td>
                        <span>{{ model.tbComDept.deptNm }}</span>
                    </td>
                </tr>
                <tr>
                    <th>사용 여부</th>
                    <td>
                        <span>{{ model.tbComDept.useYnNm }}</span>
                    </td>
                </tr>
            </tbody>
        </table>

        <h3>부서 권한</h3>
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
                <tr v-for="row in model.tbComDeptAuthorToAuthorList" v-bind:key="row.authorId">
                    <td class="tal" v-bind:style="{ color: row.deptAuthorYn ? 'blue' : '' }">{{ row.authorCode }}</td>
                    <td class="tal" v-bind:style="{ color: row.deptAuthorYn ? 'blue' : '' }">{{ row.authorNm }}</td>
                    <td class="tac" v-bind:style="{ color: row.deptAuthorYn ? 'blue' : '' }">{{ row.useYnNm }}</td>
                    <td class="tac">
                        <ui-checkbox v-model="row.deptAuthorYn"></ui-checkbox>
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
        deptId: null,
        upperDeptId: null,
        model: {
            tbComDept: {},
            tbComDeptAuthorToAuthorList: []
        }
    },
    computed: {
        service: function() {
            return new DepartmentManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(deptId) {
            var vue = this;

            this.service.read(deptId, function(model) {
                if (deptId != null && model.editable !== true) {
                    vue.closePopup();
                } else {
                    $(vue.$el).dialog({
                        title: "부서 수정",
                        modal: true,
                        width: 900,
                        height: 600
                    });
                }
            });
        },
        // 초기화 - 생성용
        initForCreate: function(upperDeptId) {
            var vue = this;

            this.service.readForCreate(upperDeptId, function(model) {
                $(vue.$el).dialog({
                    title: "부서 등록",
                    modal: true,
                    width: 900,
                    height: 600
                });
            });
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.deptId);
        },
        // 닫기
        closePopup: function() {
            $(this.$el).dialog("close");
        }
    }
});
</script>
