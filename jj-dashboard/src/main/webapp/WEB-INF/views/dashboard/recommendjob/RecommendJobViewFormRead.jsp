<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<style>
.whetherContinue.continue { position: relative; }

.whetherContinue.continue:after {
    position: absolute;
    left: -8px;
    top: -9px;
    content: "";
    width: 20px;
    height: 20px;
    background: url('${pageContext.request.contextPath}/dashboard/common/ui/images/icon_whether_continue.png') no-repeat center;
    background-size: 20px;
}
</style>

<div id="list" style="display: none;">
    <div class="pageHeader">
        직업추천 - 채용정보
        <div class="btn_area">
        </div>
    </div>
    <div class="pageContents">
        <div class="searchArea">
            <table class="searchBox">
                <colgroup>
                    <col width="10%">
                    <col width="40%">
                    <col width="10%">
                    <col width="40%">
                </colgroup>
                <tr>
                    <th>학과</th>
                    <td><ui-select v-model="gridRequest.search.hakgwaNm" code-data="recommendjob.readHakgwaList" first-name="전체"></ui-select></td>
                    <th>지역</th>
                    <td><ui-input type="text" v-model="gridRequest.search.addr" style="width: 100%;"></ui-input></td>
                    <td>
                        <div class="btnArea_center">
                            <ui-button class="btn_total_search" v-on:click="service.readList();">조회</ui-button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <ui-grid-paging v-bind:request="gridRequest" v-bind:result="gridResult" v-bind:read-list="service.readList">
        <table class="bbs_list">
            <thead>
                <tr>
                    <th width="25%" column-name="jobsNm">직종</th>
                    <th width="20%" column-name="company">회사명</th>
                    <th width="10%" column-name="minSal">연봉</th>
                    <th width="35%" column-name="basicAddr">주소</th>
                    <th width="10%" column-name="whetherContinue">진행여부</th>
                </tr>
            </thead>
            <tbody v-cloak>
                <tr v-for="row in gridResult.list" v-bind:key="row.code_seq">
                    <td class="tal">{{ row.jobsNm }}</td>
                    <td class="tac">{{ row.company }}</td>
                    <td class="tar sal" style="padding: 3px 20px 3px 5px;">{{ comma(row.minSal) }}</td>
                    <td class="tal">{{ setAddr(row.basicAddr, row.detailAddr) }}</td>
                    <td class="tac"><i v-if="row.whetherContinue == 1" class="whetherContinue continue"></i></td>
                </tr>
            </tbody>
        </table>
        </ui-grid-paging>
    </div>
</div>

<script type="text/javascript">
$(document).ready(function() {
    window.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            $('.btn_total_search').trigger("click");
        }
    });
});

vues.list = new Vue({
    el : '#list',
    data : {
        gridRequest: GridUtil.getRequestWithPaging({}, {whetherContinue: "DESC"}),
        gridResult: {},
    },
    computed : {
        service : function() {
            return new RecommendJobViewService(this);
        },
        formatter : function() {
            return CommonUtil.formatter;
        },
    },
    mounted: function() {
        this.service.readList(this.search);
    },
    updated: function() {
        this.comma();
    },
    methods: {
        comma: function(str) {
            return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
        },
        setAddr: function(basicAddr, detailAddr) {
            if (detailAddr != null && detailAddr != "null" && detailAddr != "" && detailAddr != "noData") {
                return basicAddr + " " + detailAddr;
            }
            else
                return basicAddr;
        }
    }
});
</script>