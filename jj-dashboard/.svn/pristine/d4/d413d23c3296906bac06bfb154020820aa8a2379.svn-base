<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        성적 분포 분석
    </div>
    <div class="pageContents">
        <div class="searchArea">
            <table class="searchBox">
                <colgroup>
                    <col width="5%">
                    <col width="10%">
                    <col width="5%">
                    <col width="10%">
                    <col width="5%">
                    <col width="10%">
                    <col width="1%">
                    <col width="10%">
                    <col width="5%">
                    <col width="10%">
                    <col width="5%">
                    <col width="10%">
                    <col width="5%">
                    <col width="10%">
                </colgroup>
                <tr>
                    <th>대학</th>
                    <td>
                        <ui-select v-model="search.sutrDaehakNm" code-data="score.readDaehakCode" first-name="전체"></ui-select>
                    </td>

                    <th>학과</th>
                    <td>
                        <ui-select v-model="search.sutrHakbuNm" v-bind:code-data="hakbuCodeData" first-name="전체"></ui-select>
                    </td>

                    <th>조회년도</th>
                    <td>
                        <span>
                            <ui-select v-model="search.startStdYy" v-bind:code-data="yearData" first-name="전체"></ui-select>
                        </span>
                    </td>
                    <td> ~</td>
                    <td>
                        <span>
                            <ui-select v-model="search.endStdYy" v-bind:code-data="yearData" first-name="전체"></ui-select>
                        </span>
                    </td>
                    <th>학년</th>
                    <td>
                        <span>
                            <ui-select v-model="search.sutrYear" v-bind:code-data="sutrYearData" first-name="전체"></ui-select>
                        </span>
                    </td>
                    <th>남녀구분</th>
                    <td>
                        <span>
                            <ui-select v-model="search.hakjSex" code-data="/dashboard/sex" first-name="전체"></ui-select>
                        </span>
                    </td>
                </tr>
            </table>
        </div>

        <div class="panel-main">
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>연도별 취득학점</p>
                        </div>
                        <div class="panel-content">
                            <div id="yearScoreGraph" class="chart-container"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>학년별 평균 취득학점</p>
                        </div>
                        <div class="panel-content">
                            <div id="yearGraph" class="chart-container"></div>
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
            sutrDaehakNm : "",
            sutrHakbuNm : "",
            startStdYy : "",
            endStdYy : "",
            hakjSex : "",
            sutrYear : ""
        }
    },
    computed: {
        service: function() {
            return new ScoreViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        hakbuCodeData: function() {
            return "score.readHakbuCode," + this.search.sutrDaehakNm;
        },
        yearData: function() {
            return "score.readYearCode," + this.search.sutrDaehakNm + "," + this.search.sutrHakbuNm;
        },
        sutrYearData: function() {
            return "score.readSutrYearCode," + this.search.sutrDaehakNm + "," + this.search.sutrHakbuNm;
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
            this.service.drawGraph["yearScoreGraph"](this.search);
            this.service.drawGraph["yearGraph"](this.search);
        }
    }
});
</script>
