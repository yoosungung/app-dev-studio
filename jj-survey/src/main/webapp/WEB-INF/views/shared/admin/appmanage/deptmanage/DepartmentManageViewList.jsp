<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        부서 관리
    </div>

    <div class="pageContents">
        <div class="table">
            <div class="box">
                <ui-jstree ref="deptTree"
                    v-bind:data="treeData"
                    v-bind:columns="{ id: 'deptId', parent: 'upperDeptId', text: 'deptNm', sort: 'deptCode' }"
                    v-bind:options="{ initiallyOpenLevel: 1 }"
                    v-model="deptId"
                    v-on:item-click="treeItemClick"
                ></ui-jstree>
            </div>
            <div style="padding-left: 20px;">
                <h3 style="margin-top: 0px;">부서 정보</h3>
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

                <h3 v-if="deptId != null">부서 권한</h3>
                <table class="bbs_list" v-if="deptId != null">
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
                            <td class="tac" v-bind:style="{ color: row.deptAuthorYn ? 'blue' : '' }">{{ row.deptAuthorYnNm }}</td>
                        </tr>
                    </tbody>
                </table>

                <div class="page-space tar">
                    <ui-button class="page" v-on:click="viewEdit();" v-if="model.editable">수정</ui-button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
vues.list = new Vue({
    el: '#list',
    data: {
        deptId: null,
        treeData: [],
        tbComDeptRoot: {
            deptNm: "전주대학교"
        },
        model: {
            tbComDept: {},
            tbComDeptAuthorToAuthorList: []
        },
        maxLevel: 2
    },
    computed: {
        service: function() {
            return new DepartmentManageViewService(this);
        }
    },
    mounted: function() {
        this.service.readTree();
        this.service.read();
    },
    methods: {
        // 수정 화면
        viewEdit: function() {
            CommonUtil.popupVue("edit", this.deptId);
        },
        // 트리 항목 클릭시
        treeItemClick: function(vue, node, data, event) {
            this.service.read(data == null ? null : data.deptId);
        }
    }
});
</script>
