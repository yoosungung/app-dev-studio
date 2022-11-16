<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="list">
    <div class="pageHeader">
        <c:choose>
        <c:when test="${bbsCode eq 'QNA'}">
        Q&amp;A 목록
        </c:when>
        </c:choose>
        <div class="btn_area">
            <ui-button class="btn_write" onclick="vues.list.viewCreate();">Q&amp;A 등록</ui-button>
        </div>
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
                        <ui-input type="text"   v-model="gridRequest.search.searchText"></ui-input>
                        <ui-valid-checker v-bind:value="gridRequest.search.searchText" check="required"></ui-valid-checker>
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
                        <th width="100px" column-name="bbscttNo">글번호</th>
                        <th width="" column-name="answerOrdr">답글순서</th>
                        <th width="" column-name="answerLevel">답글레벨</th>
                        <th width="" column-name="bbscttSj">제목</th>
                        <th width="" column-name="writngPsnNm">작성자</th>
                        <th width="" column-name="writngDt">작성일시</th>
                        <th width="" column-name="rdcnt">조회수</th>
                    </tr>
                </thead>
                <tbody v-cloak>
                    <tr v-for="row in gridResult.list" v-bind:key="row.personId">
                        <td class="tac">{{ row.bbscttNo }}</td>
                        <td class="tac">{{ row.answerOrdr }}</td>
                        <td class="tac">{{ row.answerLevel }}</td>
                        <td class="tac">
                            <a href="javascript:void(0);" v-on:click="viewDetail(row.bbscttId);">{{ row.bbscttSj }}</a>
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
        gridRequest: GridUtil.getRequestWithPaging({}, { answerOrdr: "ASC", bbscttNo: "DESC" }),
        gridResult: {}
    },
    computed: {
        service: function() {
            return new BbsGeneralViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.readList();
    },
    methods: {
        viewCreate: function() {
            CommonUtil.setViewMode(vues, "edit");

            vues.edit.service.read();
        },
        viewDetail: function(bbscttId) {
            CommonUtil.setViewMode(vues, "read");

            vues.read.service.read(bbscttId);
        }
    }
});
</script>
