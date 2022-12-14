<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        서비스 관리
        <div class="btn_area">
            <ui-button class="btn_write" onclick="vues.list.viewCreate();">서비스 등록</ui-button>
        </div>
    </div>

    <div class="pageContents">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readList(true);">
                <colgroup>
                    <col width="10%">
                    <col width="20%">
                    <col width="10%">
                    <col width="31%">
                    <col>
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>서비스명</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.serviceNm"></ui-input>
                    </td>
                    <th>공개 기간</th>
                    <td colspan="1">
                        <ui-date v-model="gridRequest.search.othbcPdBegin"></ui-date> ~
                        <ui-date v-model="gridRequest.search.othbcPdEnd"></ui-date>
                    </td>
                    <td>
                        <h4>
                            <ui-checkbox v-model="gridRequest.search.wholedate">
                                &nbsp;&nbsp;전체기간
                            </ui-checkbox>
                        </h4>
                    </td>
                </tr>
                <tr>
                    <th>URL</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.url"></ui-input>
                    </td>
                    <th>공개 여부</th>
                    <td colspan="2">
                        <ui-select code-data="/servicemanage/othbTy" first-name="(전체)" v-model="gridRequest.search.othbTy"></ui-select>
                    </td>
                    <td>
                        <div class="btnArea_center">
                            <ui-button class="btn_total_search" v-on:click="service.readList(true);">조회</ui-button>
                        </div>
                    </td>
                </tr>

            </table>
        </div>
        <!-- 검색조건 영역 끝 -->

        <!-- 결과목록 영역 시작 -->
        <ui-grid-paging v-bind:request="gridRequest" v-bind:result="gridResult" v-bind:read-list="service.readList">
            <table class="bbs_list">
                <thead>
                    <tr>
                        <th width="" column-name="svcNm" column-sort>서비스명</th>
                        <th width="" column-name="url" column-sort>URL</th>
                        <th width="" column-name="othbcPdBegin" column-sort>공개기간</th>
                        <th width="" column-name="othbcTy" column-sort>공개여부</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.svcId">
                        <td class="tal"><a href="javascript:void(0);" v-on:click="viewDetail(row.svcId);">{{ row.svcNm }}</a></td>
                        <td class="tal">{{ row.url }}</td>
                        <td class="tac">{{ formatter.date(row.othbcPdBegin) }} ~ {{ formatter.date(row.othbcPdEnd) }}</td>
                        <td class="tac">{{ row.othbcTy=='1' ? '공개' : '비공개'}}</td>
                    </tr>
                </tbody>
            </table>
        </ui-grid-paging>
        <!-- 결과목록 영역 시작 -->
    </div>
</div>

<script>
vues.list = new Vue({
    el: '#list',
    data: {
        gridRequest: GridUtil.getRequestWithPaging({}, { registDt: "DESC" }),
        gridResult: {}
    },
    computed: {
        service: function() {
            return new ServiceManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.readList();
    },
    methods: {
        // 생성 화면
        viewCreate: function() {
            CommonUtil.changeVue("edit");
        },
        // 상세 화면
        viewDetail: function(svcId) {
            CommonUtil.changeVue("read", svcId);
        }
    }
});
</script>
