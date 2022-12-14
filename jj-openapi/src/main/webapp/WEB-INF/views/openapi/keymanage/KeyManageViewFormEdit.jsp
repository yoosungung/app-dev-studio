<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageHeader">
        키 발급 {{ examinationModeName }}
    </div>

    <div class="pageContents">
        <h3>키 신청 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>키</th>
                    <td colspan="3">
                        <span>{{ model.tbApiSvcKey.apiKey }}</span>
                    </td>
                </tr>
                <tr>
                    <th>사용자</th>
                    <td>
                        <span>{{ model.applcntTbComPerson.personNm }}</span>
                    </td>
                    <th>학과/부서</th>
                    <td>
                        <span>{{ model.applcntTbComPerson.deptNames }}</span>
                    </td>
                </tr>
                <tr>
                    <th>학/교번</th>
                    <td>
                        <span>{{ model.applcntTbComPerson.emplNo }}</span>
                    </td>
                    <th>핸드폰</th>
                    <td>
                        <span>{{ model.applcntTbComPerson.tlphonNo }}</span>
                    </td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td>
                        <span>{{ model.applcntTbComPerson.emailAdres }}</span>
                    </td>
                </tr>
                <tr>
                    <th>요청사항</th>
                    <td colspan="3">
                        <span>{{ model.tbApiSvcKey.requstMatter }}</span>
                    </td>
                </tr>
            </tbody>
        </table>


        <h3>{{ examinationModeName }} 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>검토자</th>
                    <td>
                        <ui-input type="text" v-model="model.tbComPerson.personNm" v-bind:max-byte="200" v-bind:byte-indicator="false" disabled></ui-input>
                    </td>
                    <th>학/교번</th>
                    <td>
                        <ui-input type="text" v-model="model.tbComPerson.emplNo" v-bind:max-byte="500" v-bind:byte-indicator="false" disabled></ui-input>
                    </td>
                </tr>
                <tr v-if="examinationMode == 'C'">
                    <th>호출횟수/일</th>
                    <td>
                        <ui-input type="text" v-model="model.tbApiSvcKey.callCoPday"></ui-input>
                        <ui-valid-checker v-bind:value="model.tbApiSvcKey.callCoPday" check="number"></ui-valid-checker>
                    </td>
                    <th>사용기간</th>
                    <td>
                        <ui-date v-model="model.tbApiSvcKey.keyUsePdBegin" disabled></ui-date> ~
                        <ui-date v-model="model.tbApiSvcKey.keyUsePdEnd"></ui-date>
                    </td>
                </tr>
                </v-if>
                <tr>
                    <th>검토결과</th>
                    <td colspan="3">
                        <ui-textarea rows="3" v-model="model.tbApiSvcKey.exmntResult" v-bind:max-byte="2000" v-bind:byte-indicator="true" auto-focus></ui-textarea>
                        <ui-valid-checker v-bind:value="model.tbApiSvcKey.exmntResult" check="required"></ui-valid-checker>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">{{ examinationMode == 'C' ? "승인" : "반려"}}</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        svcKeyId: null,
        examinationMode: null,
        model: {
            tbApiSvcKey: {},
            applcntTbComPerson: {},
            tbComPerson: {}
        }
    },
    computed: {
        service: function() {
            return new KeyManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        examinationModeName: function() {
            if (this.examinationMode == "C") {
                return "승인";
            }

            return "반려";
        }
    },
    methods: {
        // 초기화
        init: function(svcKeyId, examinationMode) {
            this.examinationMode = examinationMode;

            this.service.read(svcKeyId, function(model) {
                if (svcKeyId != null && model.editable !== true) {
                    CommonUtil.replaceVue("read", svcKeyId);
                }
            });
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.svcKeyId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.replaceVue("list");
        }
    }
});
</script>
