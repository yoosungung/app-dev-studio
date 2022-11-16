<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list" style="display: none;">
    <div class="pageHeader">
        공지사항 목록
        <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
        <div class="btn_area">
            <ui-button class="btn_write" onclick="vues.list.viewCreate();">공지사항 등록</ui-button>
        </div>
        </sec:authorize>
    </div>

    <div class="pageContents">
        <!-- 검색조건 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readList(true);">
                <colgroup>
                    <col width="10%">
                    <col>
                    <col width="7%">
                </colgroup>
                <tr>
                    <th>제목</th>
                    <td>
                        <ui-input type="text" v-model="gridRequest.search.bbscttSj"></ui-input>
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
                    <!-- 나중에 수정할것 -->
                        <th width="100px" column-name="bbscttNo">글번호</th>
                        <th width="" column-name="bbscttSj">제목</th>
                        <th width="" column-name="writngPsnNm">작성자</th>
                        <th width="" column-name="writngDt">작성일시</th>
                        <th width="" column-name="rdcnt">조회수</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.personId">
                        <td class="tac">{{ row.bbscttNo }}</td>
                        <td class="tal">
                            <a href="javascript:void(0);" v-on:click="viewDetail(row.bbscttId);"> {{ row.bbscttSj }}</a>
                        </td>
                        <td class="tac">{{ row.writngPsnNm }}</td>
                        <td class="tac">{{ formatter.datetime(row.writngDt) }}</td>
                        <td class="tac">{{ row.rdcnt }}</td>
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
        gridRequest: GridUtil.getRequestWithPaging({}, { bbscttNo: "DESC" }),
        gridResult: {}
    },
    computed: {
        service: function() {
            return new BbsNoticeViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function() {
            this.service.readList();
        },
        <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
         // 생성 화면
        viewCreate: function() {
            CommonUtil.changeVue("edit");
        },
        </sec:authorize>
        // 상세 화면
        viewDetail: function(bbscttId) {
            CommonUtil.changeVue("read", bbscttId);
        }
    }
});
</script>
