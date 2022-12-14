<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<style>
table {
    width: 100%;
    border-collapse: collapse;
}
td {
    padding: 5px;
}
.line {
    border: 1px solid #444444;
    height: 100%;
}
.location {
    border: 1px solid #444444;
}
.location td, .location th {
    border: 1px solid #444444;
    padding: 3px;
}
</style>

<div class="pageHeader">
    입학 - 출신지역별 분포
</div>

<%@ include file="../ipsyHeader/IpsyHeaderView.jsp" %>

<br/><br/>

<div id="map">
<div style="text-align:right; font-weight: bold; font-size: 15px"><span>단위 : %</span></div>
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

    <table>
        <colgroup>
            <col width="33%">
            <col width="*">
            <col width="33%">
        </colgroup>
        <tr>
            <td style="display:table-cell;vertical-align:top;">
                <table>
                    <tr><td colspan="2"><strong>수시-학생부종합</strong></td></tr>
                </table>
                <table class="line">
                    <tr><td>전국</td><td>전북</td></tr>
                    <tr>
                        <td><div id="map_jong" style="height:300px;"></div></td>
                        <td><div id="map_jong_jb" style="height:250px;"></div></td>
                    </tr>
                </table>
            </td>
            <td rowspan="2">
                <table>
                    <tr><td><strong>전체</strong></td></tr>
                </table>
                <table class="line">
                    <tr>
                        <td>
                            <div id="map_all" style="height:780px;"></div>
                        </td>
                    </tr>
                </table>
            </td>
            <td style="display:table-cell;vertical-align:top;">
                <table>
                    <tr><td colspan="2"><strong>정시</strong></td></tr>
                </table>
                <table class="line">
                    <tr><td>전국</td><td>전북</td></tr>
                    <tr>
                        <td><div id="map_jung" style="height:300px;"></div></td>
                        <td><div id="map_jung_jb" style="height:250px;"></div></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td style="display:table-cell;vertical-align:bottom;">
                <table>
                    <tr><td colspan="2"><strong>수시-학생부교과</strong></td></tr>
                </table>
                <table class="line">
                    <tr><td>전국</td><td>전북</td></tr>
                    <tr>
                        <td><div id="map_gyo" style="height:300px;"></div></td>
                        <td><div id="map_gyo_jb" style="height:250px;"></div></td>
                    </tr>
                </table>
            </td>
            <td style="display:table-cell;vertical-align:center;">
                <div style="width:100%;height:420px;">
                    <table class="location">
                        <thead id="thead" style="background-color:#e5e5e5;"></thead>
                        <tbody id="tbody"></tbody>
                        <tfoot id="tfoot"></tfoot>
                    </table>
                </div>
            </td>
        </tr>
    </table>
</div>
<!-- <div id="map" style="width:100%;height:350px;"></div> -->

<script>
vues.read = new Vue({
    el: '#map',
    data: {
        search : {
            yearData: new Date().getFullYear()
        }
    },
    computed: {
        service: function() {
            return new SpreadViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
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
