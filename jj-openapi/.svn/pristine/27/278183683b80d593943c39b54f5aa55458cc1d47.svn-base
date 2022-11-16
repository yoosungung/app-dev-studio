<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        서비스 상세
    </div>

    <div class="pageContents">
        <h3>서비스 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>서비스명</th>
                    <td>
                        <span>{{ model.tbApiSvc.svcNm }}</span>
                    </td>
                    <th>URL</th>
                    <td>
                        <span>{{ model.tbApiSvc.url }}</span>
                    </td>
                </tr>
                <tr>
                    <th>공개 여부</th>
                    <td>
                        <span>{{ model.tbApiSvc.othbcTy == 1 ? '공개' : '비공개' }}</span>
                    </td>
                    <th>공개 기간</th>
                    <td>
                        <span>
                        {{ formatter.date(model.tbApiSvc.othbcPdBegin) }} ~ {{ formatter.date(model.tbApiSvc.othbcPdEnd) }}
                        </span>
                    </td>
                </tr>
            </tbody>
        </table>

        <h3>공개 사용자</h3>
        <table class="form">
            <tbody>
                <tr>
                    <th>교수</th>
                    <!-- 읽기 전용 수정 방법? -->
                    <td><ui-checkbox v-model="model.tbApiSvcGubun.A" read-only2></ui-checkbox></td>
                    <th>직원</th>
                    <td><ui-checkbox v-model="model.tbApiSvcGubun.D" read-only2></ui-checkbox></td>
                    <th>조교</th>
                    <td><ui-checkbox v-model="model.tbApiSvcGubun.K" read-only2></ui-checkbox></td>
                    <th>산단</th>
                    <td><ui-checkbox v-model="model.tbApiSvcGubun.I" read-only2></ui-checkbox></td>
                    <th>대학원생</th>
                    <td><ui-checkbox v-model="model.tbApiSvcGubun.G" read-only2></ui-checkbox></td>
                    <th>학생</th>
                    <td><ui-checkbox v-model="model.tbApiSvcGubun.S" read-only2></ui-checkbox></td>
                </tr>
            </tbody>
        </table><br>

        <h3>서비스 변수</h3>
        <table class="list">
            <thead>
                <tr>
                    <th width="150px">변수</th>
                    <th width="200px">변수명</th>
                    <th width="120px">필수여부</th>
                    <th width="">설명</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="tbApiSvcMapng in model.tbApiSvcMapngList" v-bind:key="tbApiSvcMapng.svcMapngId">
                    <td class="tal">
                        <span>{{ tbApiSvcMapng.vriabl }}</span>
                    </td>
                    <td class="tal">
                        <span>{{ tbApiSvcMapng.vriablNm }}</span>
                    </td>
                    <td class="tac">
                        <span>{{ tbApiSvcMapng.essntlYnNm }}</span>
                    </td>
                    <td class="tac">
                        <span>{{ tbApiSvcMapng.dc }}</span>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="table full">
            <h3>출력 변수</h3>
        </div>
        <table class="list">
            <thead>
                <tr>
                    <th width="100px">변수</th>
                    <th width="200px">설명</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="tbApiSvcResult in model.tbApiSvcResultList" v-bind:key="tbApiSvcResult.svcResultId">
                    <td class="tac">
                        <span>{{ tbApiSvcResult.result }}</span>
                    </td>
                    <td class="tac">
                        <span>{{ tbApiSvcResult.dc }}</span>
                    </td>
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
        svcId: null,
        model: {
            tbApiSvc: {},
            tbApiSvcMapngList: [],
            tbApiSvcGubun: {}
        }
    },
    computed: {
        service: function() {
            return new ServiceManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(svcId) {
            this.service.read(svcId);
        },
        // 수정 화면
        viewEdit: function() {
            CommonUtil.changeVue("edit", this.svcId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
