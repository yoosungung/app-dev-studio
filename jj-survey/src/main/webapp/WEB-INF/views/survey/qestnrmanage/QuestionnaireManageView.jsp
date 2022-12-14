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

<script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/vue-jstree/dist/vue-jstree.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/ckeditor4/ckeditor.js"></script>

<%@ include file="QuestionnaireManageViewList.jsp" %>
<%@ include file="QuestionnaireManageViewFormRead.jsp" %>
<%@ include file="QuestionnaireManageViewFormEdit.jsp" %>
<%@ include file="QuestionnaireManageViewFormSurvey.jsp" %>
<%@ include file="QuestionnaireManageViewTemplateSearchList.jsp" %>
<%@ include file="QuestionnaireManageViewPrevSurveySearchList.jsp" %>
<%@ include file="QuestionnaireManageViewPersonList.jsp" %>
<%@ include file="QuestionnaireManageViewPersonGroupList.jsp" %>
<%@ include file="QuestionnaireManageViewPersonSearchList.jsp" %>
<%@ include file="QuestionnaireManageViewPersonDeptTree.jsp" %>
<%@ include file="QuestionnaireManageViewPublishMediaSelect.jsp" %>

<script>
CommonUtil.enableHashRouter("list");
</script>
