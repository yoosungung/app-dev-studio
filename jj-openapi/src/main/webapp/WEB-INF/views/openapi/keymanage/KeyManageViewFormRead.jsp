<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        키 신청 상세
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
                        <span v-if="!(model.tbApiSvcKey.keyUsePdBegin==null && model.tbApiSvcKey.keyUsePdEnd==null)">~</span>
                        <span>{{ formatter.date(model.tbApiSvcKey.keyUsePdEnd) }}</span>
                    </td>
                </tr>
                <tr>
                    <th>요청사항</th>
                    <td colspan="3">
                        <pre>{{ model.tbApiSvcKey.requstMatter}}</pre>
                    </td>
                </tr>

            </tbody>
        </table>

        <h3 v-if="model.tbApiSvcKey.sttus!='A'" >{{ model.tbApiSvcKey.sttus == 'C' ? '승인' : '반려' }} 정보</h3>
        <table v-if="model.tbApiSvcKey.sttus!='A'"  class="form">
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
                        <span>{{ model.tbComPerson.personNm }}</span>
                    </td>
                    <th>학/교번</th>
                    <td>
                        <span>{{ model.tbComPerson.emplNo }}</span>
                    </td>
                </tr>
                <tr v-if="model.tbApiSvcKey.sttus == 'C'">
                    <th>호출횟수/일</th>
                    <td>
                        <span>{{ model.tbApiSvcKey.callCoPday }}</span>
                    </td>
                    <th>사용기간</th>
                    <td>
                        <span>{{ formatter.date(model.tbApiSvcKey.keyUsePdBegin) }}</span>~
                        <span>{{ formatter.date(model.tbApiSvcKey.keyUsePdEnd) }}</span>
                    </td>
                </tr>
                </v-if>
                <tr>
                    <th>검토결과</th>
                    <td colspan="3">
                        <pre>{{ model.tbApiSvcKey.exmntResult }}</pre>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.read = new Vue({
    el: '#read',
    data: {
        svcKeyId: null,
        model: {
            tbApiSvcKey: {},
            tbComPerson: {}
        }
    },
    computed: {
        service: function() {
            return new KeyManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function(svcKeyId) {
            this.service.read(svcKeyId);
        },
        // 수정 화면
        viewEdit: function() {
            CommonUtil.changeVue("edit", this.svcKeyId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
