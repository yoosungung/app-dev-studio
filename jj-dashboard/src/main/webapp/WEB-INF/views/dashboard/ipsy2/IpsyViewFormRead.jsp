<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<style type="text/css">
.panel-height-helper{
    height:350px;
}
.table {
    border: 1px solid #444444;
}
.table td, .table th {
    border: 1px solid #444444;
    padding: 3px;
    font-size: larger;
}
</style>

<div class="pageHeader">
    입학 - 고등학교 유형별 분석
</div>

<%@ include file="../ipsyHeader/IpsyHeaderView.jsp" %>

<br/>

<div id="read" style="display: none;">
    <div class="pageContents">
        <div class="searchArea">
            <table class="searchBox">
                <colgroup>
                    <col width="10%">
                    <col width="*">
                    <col width="10%">
                    <col width="*">
                </colgroup>
                <tr>
                    <th>연도</th>
                    <td>
                        <ui-select v-model="search.yearData" code-data="ipsy.readYear"></ui-select>
                    </td>
                    <th>합격여부</th>
                    <td>
                        <ui-select v-model="search.hapgyuk" code-data="ipsy.readHapgyuk" first-name="전체"></ui-select>
                    </td>
                </tr>
            </table>
        </div>

        <div class="panel-main">
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>수시-학생부종합</p>
                        </div>
                        <div class="panel-content">
                            <div id="ipsyGraphJong" class="chart-container"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>정시</p>
                        </div>
                        <div class="panel-content">
                           <div id="ipsyGraphJung" class="chart-container"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="panel-main">
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>수시-학생부교과</p>
                        </div>
                        <div class="panel-content">
                           <div id="ipsyGraphGyo" class="chart-container"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>상세 인원수</p>
                        </div>
                        <div class="panel-content">
                            <div id="ipsyTable" class="chart-container" style="overflow-y: scroll;width: 95%;height: 80%;">
                                <table class="table" style="width: 100%;height: 80%;">
                                    <thead id="thead" style="background-color:#e5e5e5;"></thead>
                                    <tbody id="tbody"></tbody>
                                </table>
                            </div>
                        </div>
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
            yearData: new Date().getFullYear()
        }
    },
    computed: {
        service: function() {
            return new IpsyViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
    },
    mounted: function() {
        this.readList();
        this.readChartList();
    },
    updated: function() {
        this.readList();
        this.readChartList();
    },
    methods: {
        readList: function() {
            this.service.readList(this.search);
        },
        readChartList: function() {
            this.service.readChartList(this.search);
        }
    }
});
</script>