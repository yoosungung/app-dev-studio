<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf"%>

<div id="edit" style="display: none;">
    <div class="pageHeader">
        선도학생 등록
    </div>

    <div class="pageContents">
        <table class="form">
            <colgroup>
                <col width="100px">
                <col width="">
                <col width="100px">
                <col width="">
                <col width="100px">
                <col width="">
                <col width="100px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>학번</th>
                    <td>
                        <ui-input type="text" v-model="student.hakbun" v-bind:max-byte="10"></ui-input>
                        <ui-valid-checker v-bind:value="student.hakbun" check="required"></ui-valid-checker>
                    </td>
                    <th>단과대학</th>
                    <td>
                        <ui-select v-model="student.daehakName" code-data="careerpath.readDaehakCode" ></ui-select>
                        <ui-valid-checker v-bind:value="student.daehakName" check="required"></ui-valid-checker>
                    </td>
                    <th>학과</th>
                    <td>
                        <ui-select v-model="student.hakbuName" v-bind:code-data="hakbuCodeData" ></ui-input>
                        <ui-valid-checker v-bind:value="student.hakbuName" check="required"></ui-valid-checker>
                    </td>
                    <th>이름</th>
                    <td>
                        <ui-input type="text" v-model="student.irum" v-bind:max-byte="50"></ui-input>
                        <ui-valid-checker v-bind:value="student.irum" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>취업처</th>
                    <td>
                        <ui-input type="text" v-model="student.remark" v-bind:max-byte="200"></ui-input>
                    </td>
                    <th>이메일</th>
                    <td>
                        <ui-input type="text" v-model="student.emailAddr" v-bind:max-byte="200"></ui-input>
                    </td>
                    <th>전화번호</th>
                    <td>
                        <ui-input type="text" v-model="student.phoneNo" v-bind:max-byte="20"></ui-input>
                    </td>
                    <th>졸업구분</th>
                    <td>
                        <ui-select v-model="student.jolGbn" code-data="/common/jolGbn"></ui-select>
                    </td>
                </tr>
                <tr>
                    <th>취업일자</th>
                    <td>
                        <ui-date v-model="student.employmentDate"></ui-date>
                    </td>
                    <th>졸업년도</th>
                    <td>
                        <ui-input type="text" v-model="student.graduatedYy" v-bind:max-byte="4"></ui-input>
                    </td>
                    <th>관리부서</th>
                    <td>
                        <ui-input type="text" v-model="student.managementDept" v-bind:max-byte="40"></ui-input>
                    </td>
                    <th>관리부서번호</th>
                    <td>
                        <ui-input type="text" v-model="student.managementPhone" v-bind:max-byte="20"></ui-input>
                    </td>
                </tr>
                <tr>
                    <th>졸업년도</th>
                    <td>
                        <ui-input type="text" v-model="student.position" v-bind:max-byte="200"></ui-input>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="viewRead();" v-if="hakbun != null">취소</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
        hakbun : null,
        student : {}
    },
    computed: {
        service: function() {
            return new CareerPathViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        hakbuCodeData: function() {
            return "careerpath.readHakbuCode," + this.student.daehakName;
        }
    },
    methods: {
        // 초기화
        init: function(hakbun) {
            this.student = {};
            this.hakbun = hakbun;
            if (hakbun != null) {
                this.service.readStudent();
            }
        },
        // 조회 화면
        viewRead: function() {
            CommonUtil.replaceVue("read", this.hakbun);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.replaceVue("list");
        },
        viewTargetPersonAdd: function(vueName) {
            CommonUtil.popupVue(vueName);
        }
    }
});
</script>
