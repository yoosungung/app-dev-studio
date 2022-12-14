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
                        <span>{{ model.tbApiSvc.othbcTy == '1' ? '공개' : '비공개' }}</span>
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

        <div class="table full">
            <h3>서비스 변수</h3>
        </div>
        <table class="list">
            <thead>
                <tr>
                    <th width="150px">변수</th>
                    <th width="200px">변수명</th>
                    <th width="120px">필수여부</th>
                    <th width="">설명</th>
                    <th width="">입력값</th>
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
                    <td class="tal">
                        <span>{{ tbApiSvcMapng.dc }}</span>
                    </td>
                    <td class="tac" v-if="tbApiSvcMapng.vriabl == 'dataFormat'">
                        <ui-select code-data="/servicestatus/dataFormat" v-model="tbApiSvcMapng.inputVal"></ui-select>
                    </td>
                    <td class="tac" v-if="tbApiSvcMapng.vriabl != 'dataFormat'">
                        <ui-input type="text" v-model="tbApiSvcMapng.inputVal" v-bind:max-byte="100" v-bind:byte-indicator="false"></ui-input>
                        <ui-valid-checker v-if="tbApiSvcMapng.essntlYn" v-bind:value="tbApiSvcMapng.inputVal" check="required" ></ui-valid-checker>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="table full">
            <h3>출력 결과</h3>
        </div>
        <table class="list">
            <thead>
                <tr>
                    <th width="150px">출력 결과</th>
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


        <div class="table full">
            <div>
                <h3>요청 데이터</h3>
            </div>
            <div class="tar">
                <ui-checkbox v-model="option.requestFormatterEnable">포맷터 적용</ui-checkbox>
            </div>
        </div>

        <table class="list">
            <thead>
                <tr>
                    <th style="text-align: left;">
                        요청 URL : {{ url }}<br>
                        요청 Content Type : application/json<br>
                        요청 Method : POST
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="tal overflowviwer">
                        <span v-if="!option.requestFormatterEnable" style="color: #0000ff; font-weight: bold" >{{ apiRequest }}</span>
                        <ui-data-viewer v-model="apiRequest" v-if="option.requestFormatterEnable" data-format="json"></ui-data-viewer>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="table full">
            <div>
                <h3>결과 데이터</h3>
            </div>
            <div class="tar">
                <ui-checkbox v-model="option.resultFormatterEnable">포맷터 적용</ui-checkbox>
            </div>
        </div>

        <table class="list">
            <tbody>
                <tr>
                    <td class="tal overflowviwer">
                        <span v-if="!option.resultFormatterEnable" style="color: #ff4433; font-weight: bold">{{ apiResult }}</span>
                        <ui-data-viewer v-model="apiResult" v-if="option.resultFormatterEnable" v-bind:data-format="apiRequest == null ? 'json' : apiRequest.dataFormat"></ui-data-viewer>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="page-space tar">
            <a href="${pageContext.request.contextPath}/openapi/servicestatus/ApiRequest.java" download><ui-button>샘플코드</ui-button></a>
            <ui-button class="page" v-on:click="service['download'](svcId);">CSV 다운로드</ui-button>
            <ui-button class="page" v-on:click="service['api']();">테스트</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared/common/lib/jquery.json-viewer/json-viewer/jquery.json-viewer.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/jquery.json-viewer/json-viewer/jquery.json-viewer.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared/common/lib/tree-xml-viewer-formatter/css/simpleXML.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/tree-xml-viewer-formatter/js/simpleXML.js"></script>

<script>
Vue.component('ui-data-viewer', {
    props: {
        value: {
        },
        dataFormat: {
            type: String
        }
    },
    template: ' \
        <div> \
            <div v-bind:data-value="value"></div> \
            <div ref="viewer"></div> \
        </div> \
    ',
    mounted: function() {
        this.formatValue();
    },
    updated: function() {
        this.formatValue();
    },
    methods: {
        formatValue: function() {
            var $viewer = $(this.$refs.viewer);
            $viewer.empty();

            if (this.dataFormat == "xml") {
                $viewer.simpleXML({ xmlString: this.value });
            } else {
                $viewer.jsonViewer(this.value);
            }
        }
    }
});

vues.read = new Vue({
    el: '#read',
    data: {
        svcId: null,
        apiRequest: null,
        apiResult: null,
        option: {
            requestFormatterEnable: false,
            resultFormatterEnable: false
        },
        model: {
            tbApiSvc: {},
            tbApiSvcMapngList: [],
            tbApiSvcResultList: []
        }

    },
    computed: {
        service: function() {
            return new ServiceStatusViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        url: function() {
            return window.location.origin + CommonUtil.contextPath + "/apilist/ApiList" + this.model.tbApiSvc.url;
        }
    },
    methods: {
        // 초기화
        init: function(svcId) {
            this.service.read(svcId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
