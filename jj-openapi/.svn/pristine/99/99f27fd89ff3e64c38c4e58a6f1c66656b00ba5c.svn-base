<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/ckeditor4/ckeditor.js"></script>

<div id="edit">
    <div class="pageHeader">
        이용안내 수정
    </div>

    <div class="pageContents">
        <ui-ckeditor4 v-model="model.tbServiceGuide.serviceContent" height="550px"></ui-ckeditor4>

        <div class="page-space tar">
            <ui-button class="page" v-if="model.editable" v-on:click="service['update']();">저장</ui-button>
        </div>
    </div>
</div>

<script>
vues.edit = new Vue({
    el: '#edit',
    data: {
       model: {
           tbServiceGuide: {}
       }
    },
    computed: {
        service: function() {
            return new ServiceGuideViewFormEditService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.read();
    },
    methods: {
    }
});
</script>
