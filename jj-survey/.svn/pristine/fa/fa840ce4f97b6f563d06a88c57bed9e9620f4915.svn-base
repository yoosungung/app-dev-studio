<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="personDeptTree" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <ui-radios v-model="deptSe" code-data="/domain.main.tbComDept/deptSe" style="margin-bottom: 5px;"></ui-radios>

        <!-- 결과목록 영역 시작 -->
        <div class="box" style="height: 490px; overflow: auto;">
            <ui-jstree ref="deptTree"
                v-bind:data="treeData"
                v-bind:columns="{ id: 'deptId', parent: 'upperDeptId', text: 'deptNm', selected: 'selectedYn' }"
                v-bind:options="{ initiallyOpenLevel: 0, nodeText: getNodeText }"
                show-checkbox
                multiple
            ></ui-jstree>
        </div>
        <!-- 결과목록 영역 시작 -->

        <div class="page-space tar">
            <ui-button class="page" v-on:click="createDeptPersonList();">확인</ui-button>
            <ui-button class="list" v-on:click="closePopup();">취소</ui-button>
        </div>
    </div>
</div>

<script>
vues.personDeptTree = new Vue({
    el: '#personDeptTree',
    data: {
        surveyGroupId: null,
        deptSe: "EMPLOYEE",
        treeData: []
    },
    computed: {
        service: function() {
            return new GroupManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    updated: function() {
        if (this.service.currentDeptSe != this.deptSe) {
            this.service.readDeptTree();
        }
    },
    methods: {
        // 초기화
        init: function(surveyGroupId) {
            this.surveyGroupId = surveyGroupId;
            this.treeData = [];

            this.service.readDeptTree();

            $(this.$el).dialog({
                title: "조직 트리",
                modal: true,
                width: 500,
                height: 620
            });
        },
        // 트리 노드 텍스트 반환
        getNodeText: function(node, data) {
            if (node.children == null || node.children.length == 0) {
                return data.deptNm + " (" + data.aditPersonCo + "/" + data.totPersonCo + " 추가됨)"
            }
        },
        // 조직별 사람 목록 생성
        createDeptPersonList: function() {
            var dataList = [];

            for (var i = 0; i < this.treeData.length; i++) {
                if (this.treeData[i].selectedYn == true) {
                    dataList.push(this.treeData[i]);
                }
            }

            var vue = this;

            this.service.createDeptPersonList(dataList, function() {
                vues.read.service.readPersonList();

                $(vue.$el).dialog("close");
            });
        },
        // 닫기
        closePopup: function() {
            $(this.$el).dialog("close");
        }
    }
});
</script>
