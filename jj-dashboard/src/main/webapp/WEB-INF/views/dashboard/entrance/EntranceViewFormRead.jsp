<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        입학 - 전형별 분석
    </div>
    <div class="pageContents">
        <div class="searchArea">
            <table class="searchBox">
                <colgroup>
                    <col width="5%">
                    <col width="10%">
                    <col width="5%">
                    <col width="10%">
                    <col width="8%">
                    <col width="12%">
                    <col width="5%">
                    <col width="15%">
                    <col width="5%">
                    <col width="25%">
                </colgroup>
                <tr>
                    <th>연도</th>
                    <td>
                        <ui-select v-model="search.yearData" code-data="entrance.readYear" first-name="전체"></ui-select>
                    </td>
                    <th>유형</th>
                    <td>
                        <ui-select v-model="search.applyKind" code-data="entrance.applyKind" first-name="전체"></ui-select>
                    </td>
                    <th>단과대학</th>
                    <td>
                        <ui-select v-model="search.daehakNm" code-data="entrance.readDaehakList" first-name="전체"></ui-select>
                    </td>
                    <th>학과</th>
                    <td>
                        <ui-select v-model="search.hakkwaNm" v-bind:code-data="hakgwaCodeData" first-name="전체"></ui-select>
                    </td>
                    <th>전형별</th>
                    <td>
                        <ui-select v-model="search.junhyungNm" code-data="entrance.readJunhyungList" first-name="전체"></ui-select>
                    </td>
                </tr>
            </table>
        </div>

        <div class="panel-main">
            <div class="panel-height-helper" style="height:850px;">
                <div class="panel-container">
                    <div class="panel-header">
                        <p>입학 데이터 분석</p>
                    </div>
                    <div class="panel-content">
                        <div id="entranceGraph" class="chart-container"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

vues.read = new Vue({
    el: '#read',
    data: {
        search : {
            applyKind: "",
            yearData: new Date().getFullYear(),
            schoNm: "",
            daehakNm: "",
            hakkwaNm: "",
            junhyungNm: "",
        }
    },
    computed: {
        service: function() {
            return new EntranceViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        hakgwaCodeData: function() {
            return "entrance.readHakkwa1nameList," + this.search.daehakNm;
        },
    },
    mounted: function() {
        this.readList();
    },
    updated: function() {
        this.readList();
    },
    methods: {
        readList: function() {
            this.service.readList(this.search);
        }
    }
});
</script>