<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div class="pageHeader">
    배너 관리
</div>
<div id="list">
    <div class="pageContents">
        <h3>배너 추가</h3>
        <table class="form">
            <colgroup>
                <col width="100px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>배너 제목</th>
                    <td>
                        <ui-input type="text" v-model="model.tbApiBanner.bannerSj" auto-focus></ui-input>
                        <ui-valid-checker v-bind:value="model.tbApiBanner.bannerSj" check="required"></ui-valid-checker>
                    </td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td>
                        <ui-filepond v-model="model.tbApiBanner.atchFileList" policy="banner"></ui-filepond>
                        <div style="font-size: x-small;">
                            <div>※ 업로드 가능한 파일의 총 용량은 20MB까지 업로드 가능합니다.</div>
                            <div>※ 첨부가능 파일 확장자: jpg, gif, bmp, png</div>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['create']();">저장</ui-button>
        </div>

        <h3>배너 목록</h3>
        <table class="list">
            <thead>
                <tr>
                    <th width="40px">
                        <ui-checkbox-head v-bind:data="bannerList"></ui-checkbox-head>
                    </th>
                    <th width="">배너제목</th>
                    <th width="">첨부파일</th>
                    <th width="80px">사용여부</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="banner in bannerList" v-bind:key="banner.bannerId">
                    <td class="tac">
                        <ui-checkbox-row v-bind:data="banner">
                    </ui-checkbox-row></td>
                    <td class="tac">{{ banner.bannerSj }}</td>
                    <td class="tac"><ui-filedownload v-model="banner.atchFileList"></ui-filedownload></td>

                    <td class="tac" v-on:click="service['update'](banner);" >
                        <ui-checkbox v-bind:checked="useYn(banner.bannerSttus)">사용</ui-checkbox>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<script>
vues.list = new Vue({
    el: '#list',
    data: {
        bannerList : [],
        model: {
            tbApiBanner: {}
        }
    },
    computed: {
        service: function() {
            return new BannerManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.model.tbApiBanner.atchFileList = [];
        this.service.readList();
    },
    methods: {
        useYn: function(bannerSttus) {
            return bannerSttus == "1" ? true : false;
        }
    }
});
</script>
