<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/tiles/shared/include/PageHeader.jspf" %>

    <div class="pageHeader">
        FAQ 목록
    </div>

    <div class="pageContents" id="list">
        <table class="form" v-on:keyup.enter="readList(true);">
            <colgroup>
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>제목</th>
                    <td>
                        <input type="text" v-model="gridRequest.search.name">
                        <div class="fr">
                            <ui-button type="search" v-on:click="readList(true);">조회</ui-button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="dataTbl">
            <ui-grid-paging v-bind:request="gridRequest" v-bind:result="gridResult" v-bind:read-list="readList">
                <table class="list">
                    <thead>
                        <tr>
                            <th width="100px" data-sort-name="emplNo">글번호</th>
                            <th width="" data-sort-name="koreanNm">제목</th>
                            <th width="" data-sort-name="ofcpsNm">작성자</th>
                            <th width="" data-sort-name="emailAdres">작성일시</th>
                            <th width="" data-sort-name="deptNm">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="row in gridResult.list" v-bind:key="row.personId">
                            <td class="tac">{{ row.emplNo }}</td>
                            <td class="tac">{{ row.koreanNm }}</td>
                            <td class="tac">{{ row.ofcpsNm }}</td>
                            <td class="tac">{{ row.emailAdres }}</td>
                            <td class="tac">{{ row.deptNm }}</td>
                        </tr>
                    </tbody>
                </table>
            </ui-grid-paging>
        </div>
    </div>

    <script>
    var list = new Vue({
        el: '#list',
        data: {
            gridRequest: GridUtil.getRequestWithPaging({}, { emplNo: "ASC" }),
            gridResult: {}
        },
        computed: {
            formatter: function() {
                return CommonUtil.formatter;
            }
        },
        mounted: function() {
            this.readList();
        },
        methods: {
            // 목록 조회
            readList: function(firstPage) {
                var vue = this;

                GridUtil.setLoadStart(vue, firstPage);

                CommonUtil.axios()
                .post("readList", vue.gridRequest)
                .then(function(response) {
                    GridUtil.loadData(vue, response.data.result);
                })
                ["catch"](function(error) {
                    CommonUtil.showAxiosError(error);
                    GridUtil.setError(vue, error);
                });
            }
        }
    });
    </script>
