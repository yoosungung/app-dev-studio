<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<style>
td, td span {
    font-size: 15px;
    font-weight: bold;
}
</style>

<div id="list" style="display: none;">
    <div class="pageHeader">
        중도탈락 분석
        <div class="btn_area">
            <ui-button class="btn_excel" v-on:click="viewExcel();">대상자 추가(엑셀)</ui-button>
        </div>
    </div>
    <div class="pageContents">
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
                    <th>년도</th>
                    <td>
                        <ui-select code-data="dropout.readYearCode" v-model="search.ipsyYy" first-name="전체"></ui-select>
                    </td>
                    <th>이름</th>
                    <td>
                        <ui-input type="text" v-model="search.ipsyName"></ui-input>
                    </td>
                    <th>학번</th>
                    <td>
                        <ui-input type="text" v-model="search.ipsyHakbun"></ui-input>
                    </td>
                    <td>
                        <div class="btnArea_center">
                            <ui-button class="btn_total_search" v-on:click="service.readList();">조회</ui-button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>

         <div class="panel-main">
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>학생 목록 </p>
                        </div>
                        <div class="panel-content">
                            <div id="grid"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel-wrapper">
                <div class="panel-height-helper">
                    <div class="panel-container">
                        <div class="panel-header">
                            <p>중도탈락 고위험군 예측</p>
                        </div>
                        <div class="panel-content">
                            <table class="bbs_list">
                                <colgroup>
                                    <col width="17%">
                                    <col width="33%">
                                    <col width="17%">
                                    <col width="33%">
                                </colgroup>
                                <tr>
                                    <th>이름/학번</th>
                                    <td class="tac"><span> {{student.ipsyName}} / {{student.ipsyHakbun}}</span></td>
                                    <th>학년/성별/생년</th>
                                    <td class="tac"><span> {{student.ipsyYear}} / {{student.ipsySex}} / {{student.ipsyBirth}}</span></td>
                                </tr>
                                <tr>
                                    <th>대학</th>
                                    <td class="tac"><span> {{student.ipsyDaehak}}</span></td>
                                    <th>학과</th>
                                    <td class="tac"><span> {{student.ipsyHapgyHakkwaName}}</span></td>
                                </tr>
                                    <th>입학년도</th>
                                    <td class="tac"><span> {{student.ipsyYy}}</span></td>
                                    <th>입시구분</th>
                                    <td class="tac"><span> {{student.ipsyGubunName}}</span></td>
                                </tr>
                                <tr>
                                    <th>고교구분</th>
                                    <td class="tac"><span> {{student.ihLclass}}({{student.hsClass}})</span></td>
                                    <th>출신고교</th>
                                    <td class="tac"><span> {{student.ihName}}</span></td>
                                </tr>
                                <tr>
                                    <th>지역</th>
                                    <td class="tac"><span> {{student.schoNpostDong}}</span></td>
                                    <th>입시전체점수</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyTotalJumsu1"> {{student.ipsyTotalJumsu1}}</span></td>
                                <tr>
                                <tr>
                                    <th>서류평가점수(적성)</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyPaper21"> {{student.ipsyPaper21}}</span></td>
                                    <th>봉사시간</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyBongsaTime"> {{student.ipsyBongsaTime}}</span></td>
                                </tr>
                                <tr>
                                    <th>서류점수</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyPaperAve"> {{student.ipsyPaperAve}}</span></td>
                                    <th>합격후보번호</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyHuboSeq"> {{student.ipsyHuboSeq}}</span></td>
                                </tr>
                                <tr>
                                    <th>검정고시점수</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyKumjung"> {{student.ipsyKumjung}}</span></td>
                                    <th>내신점수</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyHigh100"> {{student.ipsyHigh100}}</span></td>
                                </tr>
                                <tr>
                                    <th>고교졸업 후 기간</th>
                                    <td class="tac"><span v-bind:style="textColor.graduatedYy"> {{student.graduatedYy}}</span></td>
                                    <th>가산점1</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyGasanJumsu11"> {{student.ipsyGasanJumsu11}}</span></td>
                                </tr>
                                <tr>
                                    <th>가산점4</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyGasanJumsu23"> {{student.ipsyGasanJumsu23}}</span></td>
                                    <th>가산점5</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyGasanJumsu24"> {{student.ipsyGasanJumsu24}}</span></td>
                                </tr>
                                <tr>
                                    <th>면접점수</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyMeunjubAve"> {{student.ipsyMeunjubAve}}</span></td>
                                    <th>서류평가점수(인성)</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyPaper11"> {{student.ipsyPaper11}}</span></td>
                                </tr>
                                <tr>
                                    <th>입학료장학금</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyJangIphak"> {{student.ipsyJangIphak}}</span></td>
                                    <th>수업료장학금</th>
                                    <td class="tac"><span v-bind:style="textColor.ipsyJangSuup"> {{student.ipsyJangSuup}}</span></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
vues.list = new Vue({
    el: '#list',
    data: {
        search: {},
        student: {},
        textColor: {}
    },
    computed: {
        service: function() {
            return new DropOutViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
    },
    mounted: function() {
        this.readList();
    },
    methods: {
        readList: function() {
            this.service.readList(this.search);
        },
        // 엑셀 화면
        viewExcel : function() {
            CommonUtil.popupVue('excelUpload');
        }
    }
});
</script>
