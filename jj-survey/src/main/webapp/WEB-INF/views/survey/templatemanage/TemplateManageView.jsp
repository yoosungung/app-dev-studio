<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<style>
label.color-selector {
    display: inline-block;
    width: 60px;
    height: 60px;
    text-align: center;
    line-height: 60px;
}

label.color-selector:not(:first-child) {
    margin-left: 2px;
}
</style>

<script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/ckeditor4/ckeditor.js"></script>

<%@ include file="TemplateManageViewList.jsp" %>
<%@ include file="TemplateManageViewFormRead.jsp" %>
<%@ include file="TemplateManageViewFormEdit.jsp" %>
<%@ include file="TemplateManageViewFormSurvey.jsp" %>

<script>
CommonUtil.enableHashRouter("list");
</script>
