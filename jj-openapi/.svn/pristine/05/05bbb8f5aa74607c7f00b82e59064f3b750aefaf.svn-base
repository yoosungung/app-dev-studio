<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf"%>

<div id="read" style="padding-top: 33px;">
    <div class="pageHeader">
        이용 안내
    </div>

    <div class="pageContents">
        <div v-html="model.tbServiceGuide.serviceContent" style="overflow-x: auto;"></div>
    </div>
</div>

<script>
vues.read = new Vue({
    el : '#read',
    data : {
       model : {
           tbServiceGuide: {}
       }
    },
    computed : {
        service : function() {
             return new ServiceGuideViewFormReadService(this);
        },
        formatter : function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.read();
    },
    methods : {
        // 초기화
        init: function() {
            this.service.read();
        }
    }
});
</script>
