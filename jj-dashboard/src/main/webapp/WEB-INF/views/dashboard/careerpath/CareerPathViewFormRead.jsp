<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf"%>

<div id="read" style="display: none;">
    <div class="pageHeader">선도학생 조회</div>
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
                        <span v-if="isEditable() && student.hakbun != null">{{ student.hakbun }}</span>
                        <span v-if="isEditable() == false">{{ student.hakbun.substring(0,4).concat("*****") }}</span>
                    </td>
                    <th>단과대학</th>
                    <td><span>{{ student.daehakName }}</span></td>
                    <th>학과</th>
                    <td><span>{{ student.hakbuName }}</span></td>
                    <th>취업처</th>
                    <td><span>{{ student.remark }}</span></td>
                </tr>
            </tbody>
        </table>
        <table class="form">
            <colgroup>
                <col width="123px">
                <col width="">
                <col width="123px">
                <col width="">
                <col width="123px">
                <col width="">
                <col width="123px">
                <col width="">
                <col width="123px">
                <col width="">
            </colgroup>
            <tr>
                <th>전공필수</th>
                <td><ui-checkbox v-model="search.jeonpil"></ui-checkbox></td>
                <th>전공선택</th>
                <td><ui-checkbox v-model="search.jeonsun"></ui-checkbox></td>
                <th>전공기초</th>
                <td><ui-checkbox v-model="search.gicho"></ui-checkbox></td>
                <th>교양필수</th>
                <td><ui-checkbox v-model="search.kyopil"></ui-checkbox></td>
                <th>교양선택</th>
                <td><ui-checkbox v-model="search.kyosun"></ui-checkbox></td>
                <th>교직</th>
                <td><ui-checkbox v-model="search.kyogic"></ui-checkbox></td>
            </tr>
        </table>
        <div class="page-space tar">
            <ui-button class="page" v-if="isEditable()" v-on:click="viewUpdate();">수정</ui-button>
            <ui-button class="page" v-if="isEditable()" v-on:click="service['delete']();">삭제</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
        <div class="panel-main">
            <div class="panel-height-helper" style="height:1300px">
                <div class="panel-container">
                    <div class="panel-header">
                        <p>선도학생 수강과목</p>
                    </div>
                    <div class="panel-content">
                        <div id="sugangGraph" class="chart-container"></div>
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
        hakbun: null,
        search: {
            jeonpil: true,
            jeonsun: true,
            kyopil: true,
            kyosun: true,
            gicho: true,
            kyogic: true
        },
        student: {}
    },
    computed: {
        service: function() {
            return new CareerPathViewService(this);
        }
    },
    mounted: function() {
    },
    updated: function() {
        this.service.readChartData_sugang();
    },
    methods: {
        // 초기화
        init: function(hakbun) {
            this.hakbun = hakbun;
            this.service.readStudent();
            this.service.readChartData_sugang();
        },
        // 목록 화면
        viewList: function() {
            this.hakbun = null;
            this.student = {};
            vues.list.service.readList();
            CommonUtil.changeVue("list");
        },
        viewUpdate : function() {
            CommonUtil.changeVue("edit", this.hakbun);
        },
        isEditable : function() {
            return this.student.isEditable;
        }
    }
});
</script>
