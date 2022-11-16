<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageHeader">
        서비스 {{ svcId == null ? "등록" : "수정" }}
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
                        <ui-input type="text" v-model="model.tbApiSvc.svcNm"></ui-input>
                        <ui-valid-checker v-bind:value="model.tbApiSvc.svcNm" check="required"></ui-valid-checker>
                    </td>
                    <th>URL</th>
                    <td>
                        <ui-input type="text" v-model="model.tbApiSvc.url"></ui-input>
                        <ui-valid-checker v-bind:value="model.tbApiSvc.svcNm" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>공개 여부</th>
                    <td>
                        <ui-radios code-data="/common/yesNo" v-model="model.tbApiSvc.othbcTy"></ui-radios>
                        <ui-valid-checker v-bind:value="model.tbApiSvc.othbcTy" check="required"></ui-valid-checker>
                    </td>
                    <th>공개 기간</th>
                    <td>
                        <ui-date v-model="model.tbApiSvc.othbcPdBegin"></ui-date> ~
                        <ui-valid-checker v-bind:value="model.tbApiSvc.othbcPdBegin" check="required"></ui-valid-checker>
                        <ui-date v-model="model.tbApiSvc.othbcPdEnd"></ui-date>
                        <ui-valid-checker v-bind:value="model.tbApiSvc.othbcPdEnd" check="required"></ui-valid-checker>
                    </td>
                </tr>
            </tbody>
        </table><br>

        <h3>공개 사용자</h3>
        <table class="form">
            <tbody>
                <tr>
                    <th>교수</th>
                    <td><ui-checkbox v-model="model.tbApiSvcGubun.A"></ui-checkbox></td>
                    <th>직원</th>
                    <td><ui-checkbox v-model="model.tbApiSvcGubun.D"></ui-checkbox></td>
                    <th>조교</th>
                    <td><ui-checkbox v-model="model.tbApiSvcGubun.K"></ui-checkbox></td>
                    <th>산단</th>
                    <td><ui-checkbox v-model="model.tbApiSvcGubun.I"></ui-checkbox></td>
                    <th>대학원생</th>
                    <td><ui-checkbox v-model="model.tbApiSvcGubun.G"></ui-checkbox></td>
                    <th>학생</th>
                    <td><ui-checkbox v-model="model.tbApiSvcGubun.S"></ui-checkbox></td>
                </tr>
            </tbody>
        </table><br>

        <table class="list">
        <div style="margin-bottom:4px;" class="table full">
            <h3>서비스 변수</h3>
            <div class="tar">&nbsp;
                <ui-list-button-append v-bind:data="model.tbApiSvcMapngList"
                    v-bind:new-row-data="{ }"
                    id-column-name="svcMapngId"
                    sort-column-name="sortOrdr"
                ></ui-list-button-append>
                <ui-list-button-delete v-bind:data="model.tbApiSvcMapngList"
                    sort-column-name="sortOrdr"
                ></ui-list-button-delete>
            </div>
        </div>
            <thead>
                <tr>
                    <th width="40px">
                        <ui-checkbox-head v-bind:data="model.tbApiSvcResultList"></ui-checkbox-head>
                    </th>
                    <th width="150px">변수</th>
                    <th width="200px">변수명</th>
                    <th width="120px">필수여부</th>
                    <th width="">설명</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="tbApiSvcMapng in tbApiSvcMapngListFiltered" v-bind:key="tbApiSvcMapng.svcMapngId">
                    <td class="tac">
                        <ui-checkbox-row v-bind:data="tbApiSvcMapng"></ui-checkbox-row>
                    </td>
                    <td class="tal">
                        <ui-input type="text" v-model="tbApiSvcMapng.vriabl" v-bind:max-byte="100" v-bind:byte-indicator="false"></ui-input>
                        <ui-valid-checker v-bind:value="tbApiSvcMapng.vriabl" check="required"></ui-valid-checker>
                        <ui-valid-checker-duplicate v-bind:data="model.tbApiSvcMapngList" v-bind:row-data="tbApiSvcMapng" column-name="vriabl"></ui-valid-checker-duplicate>
                    </td>
                    <td class="tal">
                        <ui-input type="text" v-model="tbApiSvcMapng.vriablNm" v-bind:max-byte="100" v-bind:byte-indicator="false"></ui-input>
                        <ui-valid-checker v-bind:value="tbApiSvcMapng.vriablNm" check="required"></ui-valid-checker>
                        <ui-valid-checker-duplicate v-bind:data="model.tbApiSvcMapngList" v-bind:row-data="tbApiSvcMapng" column-name="vriablNm"></ui-valid-checker-duplicate>
                    </td>
                    <td class="tac">
                        <ui-radios code-data="/common/yesNo" v-model="tbApiSvcMapng.essntlYn"></ui-radios>
                        <ui-valid-checker v-bind:value="tbApiSvcMapng.essntlYn" check="required"></ui-valid-checker>
                    </td>
                    <td class="tac">
                        <ui-textArea v-model="tbApiSvcMapng.dc" v-bind:max-byte="500" v-bind:byte-indicator="true"></ui-input>
                    </td>
                </tr>
            </tbody>
        </table><br>

        <div style="margin-bottom:4px;" class="table full">
            <h3>출력 변수</h3>
            <div class="tar">&nbsp;
                <ui-list-button-append v-bind:data="model.tbApiSvcResultList"
                    v-bind:new-row-data="{ }"
                    id-column-name="svcResultId"
                    sort-column-name="sortOrdr"
                ></ui-list-button-append>
                <ui-list-button-delete v-bind:data="model.tbApiSvcResultList"
                    sort-column-name="sortOrdr"
                ></ui-list-button-delete>
            </div>
        </div>

        <table class="list">
             <thead>
                <tr>
                    <th width="40px">
                        <ui-checkbox-head v-bind:data="model.tbApiSvcResultList"></ui-checkbox-head>
                    </th>
                    <th width="150px">결과변수</th>
                    <th width="200px">설명</th>
                </tr>
            </thead>

            <tbody>
                <tr v-for="tbApiSvcResult in tbApiSvcResultListFiltered" v-bind:key="tbApiSvcResult.svcResultId">
                    <td class="tac">
                        <ui-checkbox-row v-bind:data="tbApiSvcResult"></ui-checkbox-row>
                    </td>
                    <td class="tal">
                        <ui-input type="text" v-model="tbApiSvcResult.result" v-bind:max-byte="100" v-bind:byte-indicator="false"></ui-input>
                        <ui-valid-checker v-bind:value="tbApiSvcResult.result" check="required"></ui-valid-checker>
                        <ui-valid-checker-duplicate v-bind:data="model.tbApiSvcResultList" v-bind:row-data="tbApiSvcResult" column-name="result"></ui-valid-checker-duplicate>
                    </td>
                    <td class="tac">
                        <ui-textArea v-model="tbApiSvcResult.dc" v-bind:max-byte="500" v-bind:byte-indicator="true"></ui-input>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="svcId != null">삭제</ui-button>
            <ui-button class="page" v-on:click="viewRead();" v-if="svcId != null">취소</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        svcId: null,
        model: {
            tbApiSvc: {},
            tbApiSvcMapngList: [],
            tbApiSvcResultList: [],
            tbApiSvcGubun: {}
        }
    },
    computed: {
        service: function() {
            return new ServiceManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        tbApiSvcMapngListFiltered: function() {
            return CommonUtil.getFilteredList(this.model.tbApiSvcMapngList, "sortOrdr");
        },
        tbApiSvcResultListFiltered: function() {
            return CommonUtil.getFilteredList(this.model.tbApiSvcResultList, "sortOrdr");
        }
    },
    methods: {
        // 초기화
        init: function(svcId) {
            this.service.read(svcId, function(model) {
                if (svcId != null && model.editable !== true) {
                    CommonUtil.replaceVue("read", svcId);
                }
            });
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.svcId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.replaceVue("list");
        }
    }
});
</script>
