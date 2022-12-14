<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="edit" style="display: none;">
    <div class="pageHeader">
        키 {{ svcKeyId == null ? "신청" : "수정" }}
    </div>

    <div class="pageContents">
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr v-if="svcKeyId != null">
                    <th>키</th>
                    <td colspan="3">
                        <span>{{ model.tbApiSvcKey.apiKey }}</span>
                    </td>
                </tr>
                <tr>
                    <th>사용자</th>
                    <td>
                        <span>{{ model.tbComPerson.personNm }}</span>
                    </td>
                    <th>학과/부서</th>
                    <td>
                        <span>{{ model.tbComPerson.deptNames }}</span>
                    </td>
                </tr>
                <tr>
                    <th>학/교번</th>
                    <td>
                        <span>{{ model.tbComPerson.emplNo }}</span>
                    </td>
                    <th>핸드폰</th>
                    <td>
                        <span>{{ model.tbComPerson.tlphonNo }}</span>
                    </td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td>
                        <span>{{ model.tbComPerson.emailAdres }}</span>
                    </td>
                    <th>사용 기간</th>
                    <td>
                        <span>{{ formatter.date(model.tbApiSvcKey.keyUsePdBegin) }}</span>
                        <span v-if="!(model.tbApiSvcKey.keyUsePdBegin == null && model.tbApiSvcKey.keyUsePdEnd == null)">~</span>
                        <span>{{ formatter.date(model.tbApiSvcKey.keyUsePdEnd) }}</span>
                    </td>
                </tr>
                <tr>
                    <th>요청사항</th>
                    <td colspan="3">
                        <ui-textarea v-model="model.tbApiSvcKey.requstMatter" rows="5" v-bind:max-byte="2000" v-bind:byte-indicator="true" auto-focus></ui-textarea>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="svcKeyId != null">삭제</ui-button>
            <ui-button class="page" v-on:click="viewRead();" v-if="svcKeyId != null">취소</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        svcKeyId: null,
        model: {
            tbApiSvcKey: {},
            tbComPerson: {}
        }
    },
    computed: {
        service: function() {
            return new KeyStatusViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(svcKeyId) {
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
