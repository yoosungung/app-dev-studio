<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        교양교과 핵심역량 분석
    </div>
    <div class="pageContents">
        ※2015년 이후 전체평균
        <div class="searchArea">
            <table class="searchBox" v-on:keyup.enter="service.readList(true);">
                <colgroup>
                    <col width="7%">
                    <col width="25%">
                    <col width="7%">
                    <col width="25%">
                    <col width="7%">
                    <col width="25%">
                </colgroup>
                <tr>
                    <th>대학</th>
                    <td>
                        <ui-select v-model="search.daehakName" code-data="supercompetence.readDaehakCode" first-name="전체"></ui-select>
                    </td>

                    <th>학과</th>
                    <td>
                        <ui-select v-model="search.hakbuName" v-bind:code-data="hakbuCodeData" first-name="전체"></ui-select>
                    </td>

                    <th>기준학년</th>
                    <td>
                        <ui-select v-model="search.hakgYear" v-bind:code-data="yearData" first-name="전체"></ui-select>
                    </td>
                </tr>
            </table>
        </div>

        <div id="grid"></div>
        <div class="panel-main">
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p v-if="!search.hakbuName">{{search.daehakName==""  ? '대학별 핵심역량' : search.daehakName+"&nbsp학년별 핵심역량"}}</p>
                            <p v-if="search.hakbuName">{{search.hakbuName==""  ? '학과별 핵심역량' : search.hakbuName+"&nbsp학년별 핵심역량"}}</p>
                        </div>
                        <div class="panel-content">
                            <div id="dataGraph" class="chart-container"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>{{search.daehakName==""  ? '핵심역량 전체평균' : search.daehakName + "&nbsp 핵심역량 평균"}}</p>
                        </div>
                        <div class="panel-content">
                            <div id="avgGraph" class="chart-container"></div>
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
            daehakName : "",
            hakbuName : "",
            hakgYear : ""
        }
    },
    computed: {
        service: function() {
            return new SuperCompetenceViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        hakbuCodeData: function() {
            return "supercompetence.readHakbuCode," + this.search.daehakName;
        },
        yearData: function() {
            return "supercompetence.readYearCode," + this.search.daehakName + "," + this.search.hakbuName;
        }
    },
    mounted: function() {
        this.drawGraph();
    },
    updated: function() {
        this.drawGraph();
    },
    methods: {
        drawGraph: function() {
            this.service.drawGraph["dataGraph"](this.search);
            this.service.drawGraph["avgGraph"](this.search);
        }
    }
});
</script>
