<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        직무 상세
    </div>

    <div class="pageContents">
        <h3>직무 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>직무 구분</th>
                    <td>
                        <span>{{ model.tbComDty.dtySe }}</span>
                    </td>
                </tr>
                <tr>
                    <th>직무 코드</th>
                    <td>
                        <span>{{ model.tbComDty.dtyCode }}</span>
                    </td>
                </tr>
                <tr>
                    <th>직무 이름</th>
                    <td>
                        <span>{{ model.tbComDty.dtyNm }}</span>
                    </td>
                </tr>
                <tr>
                    <th>사용 여부</th>
                    <td>
                        <span>{{ model.tbComDty.useYnNm }}</span>
                    </td>
                </tr>
            </tbody>
        </table>

        <h3>직무별 권한</h3>
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
                <tr v-for="row in model.tbComDtyAuthorToAuthorList" v-bind:key="row.authorId">
                    <td class="tal" v-bind:style="{ color: row.dtyAuthorYn ? 'blue' : '' }">{{ row.authorCode }}</td>
                    <td class="tal" v-bind:style="{ color: row.dtyAuthorYn ? 'blue' : '' }">{{ row.authorNm }}</td>
                    <td class="tac" v-bind:style="{ color: row.dtyAuthorYn ? 'blue' : '' }">{{ row.useYnNm }}</td>
                    <td class="tac" v-bind:style="{ color: row.dtyAuthorYn ? 'blue' : '' }">{{ row.dtyAuthorYnNm }}</td>
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
        dtyId: null,
        model: {
            tbComDty: {},
            tbComDtyAuthorToAuthorList: []
        }
    },
    computed: {
        service: function() {
            return new DutyManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(dtyId) {
            this.service.read(dtyId);
        },
        // 수정 화면
        viewEdit: function() {
            CommonUtil.changeVue("edit", this.dtyId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
