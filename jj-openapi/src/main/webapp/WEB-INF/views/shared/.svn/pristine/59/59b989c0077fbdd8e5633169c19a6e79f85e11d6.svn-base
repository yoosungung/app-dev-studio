<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="sort" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <table class="list">
            <thead>
                <tr>
                    <th width="50px">번호</th>
                    <th width="">프로그램 유형</th>
                    <th width="">프로그램 패턴</th>
                    <th width="80px">사용 여부</th>
                    <th width="100px">정렬 순서</th>
                </tr>
            </thead>
            <tbody v-cloak>
                <tr v-for="row in sortedList" v-bind:key="row.authorId">
                    <td class="tac">{{ row.sortOrdr }}</td>
                    <td class="tal">{{ row.resrceTyNm }}</td>
                    <td class="tal">{{ row.resrcePttrn }}</td>
                    <td class="tac">{{ row.useYnNm }}</td>
                    <td class="tac">
                        <ui-button v-on:click="moveRow(row, -1);">▲</ui-button>
                        <ui-button v-on:click="moveRow(row, +1);">▼</ui-button>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['updateSortOrdrList']();">저장</ui-button>
            <ui-button class="list" v-on:click="closePopup();">닫기</ui-button>
        </div>
    </div>
</div>

<script>
vues.sort = new Vue({
    el: '#sort',
    data: {
        sortOrdrList: []
    },
    computed: {
        service: function() {
            return new ResourceManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        sortedList: function() {
            return CommonUtil.getSortedList(this.sortOrdrList, "sortOrdr");
        }
    },
    mounted: function() {
    },
    methods: {
        // 초기화
        init: function() {
            this.service.readSortOrdrList();

            $(this.$el).dialog({
                title: "프로그램 정렬",
                modal: true,
                width: 900,
                height: 600
            });
        },
        // 행 이동
        moveRow: function(row, direction) {
            CommonUtil.setListSortOrder(this.sortOrdrList, "sortOrdr", row, direction);
        },
        // 닫기
        closePopup: function() {
            $(this.$el).dialog("close");
        }
    }
});
</script>
