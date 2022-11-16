<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="deptTreeList" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <!-- 결과목록 영역 시작 -->
        <div class="box" style="height: 490px; overflow: auto;">
            <ui-jstree ref="deptTree"
                v-bind:data="treeData"
                v-bind:columns="{ id: 'deptId', parent: 'upperDeptId', text: 'deptNm' }"
                v-bind:options="{ initiallyOpenLevel: 0 }"
                v-model="deptId"
            ></ui-jstree>
        </div>
        <!-- 결과목록 영역 시작 -->

        <div class="page-space tar">
            <ui-button class="page" v-on:click="addDeptList();">추가</ui-button>
            <ui-button class="list" v-on:click="closePopup();">닫기</ui-button>
        </div>
    </div>
</div>

<script>
vues.deptTreeList = new Vue({
    el: '#deptTreeList',
    data: {
        deptId: null,
        treeData: []
    },
    computed: {
        service: function() {
            return new AuthorityManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function() {
            this.treeData = [];

            this.service.readDeptTreeList();

            $(this.$el).dialog({
                title: "조직 트리",
                modal: true,
                width: 500,
                height: 600
            });
        },
        // 부서 목록 추가
        addDeptList: function() {
            var node = this.$refs.deptTree.getNodeById(this.deptId);

            if (node.children != null && node.children.length > 0) {
                alert("최하위 조직만 추가할 수 있습니다.");
                return;
            }

            var data = this.$refs.deptTree.getDataById(this.deptId);

            this.$emit("selectRow", data);
        },
        // 닫기
        closePopup: function() {
            $(this.$el).dialog("close");
        }
    }
});
</script>
