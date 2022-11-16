<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        프로그램 상세
    </div>

    <div class="pageContents">
        <h3>프로그램 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>프로그램 유형</th>
                    <td>
                        <span>{{ model.tbSysResrce.resrceTyNm }}</span>
                    </td>
                    <th>프로그램 패턴</th>
                    <td>
                        <span>{{ model.tbSysResrce.resrcePttrn }}</span>
                    </td>
                </tr>
                <tr>
                    <th>정렬 순서</th>
                    <td>
                        <span>{{ model.tbSysResrce.sortOrdr }}</span>
                    </td>
                    <th>사용 여부</th>
                    <td>
                        <span>{{ model.tbSysResrce.useYnNm }}</span>
                    </td>
                </tr>
            </tbody>
        </table>

        <h3>프로그램 권한</h3>
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
                <tr v-for="row in model.tbSysResrceAuthorToAuthorList" v-bind:key="row.authorId">
                    <td class="tal" v-bind:style="{ color: row.resrceAuthorYn ? 'blue' : '' }">{{ row.authorCode }}</td>
                    <td class="tal" v-bind:style="{ color: row.resrceAuthorYn ? 'blue' : '' }">{{ row.authorNm }}</td>
                    <td class="tac" v-bind:style="{ color: row.resrceAuthorYn ? 'blue' : '' }">{{ row.useYnNm }}</td>
                    <td class="tac" v-bind:style="{ color: row.resrceAuthorYn ? 'blue' : '' }">{{ row.resrceAuthorYnNm }}</td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="viewEdit();" v-if="model.editable">수정</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="model.editable">삭제</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.read = new Vue({
    el: '#read',
    data: {
        resrceId: null,
        model: {
            tbSysResrce: {},
            tbSysResrceAuthorToAuthorList: []
        }
    },
    computed: {
        service: function() {
            return new ResourceManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(resrceId) {
            this.service.read(resrceId);
        },
        // 수정 화면
        viewEdit: function() {
            CommonUtil.changeVue("edit", this.resrceId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
