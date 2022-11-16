<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<sec:authorize access="hasAnyRole('ROLE_SUPER')" var="isRoleSuper" />
<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SURVEY_ADMIN')" var="isRoleAdmin" />

<c:choose>
<c:when test="${isRoleSuper}">
    <script>
    window.location.replace("${pageContext.request.contextPath}/admin/appmanage/personmanage/PersonManage/");
    </script>
</c:when>
<c:when test="${isRoleAdmin}">
    <script>
    window.location.replace("${pageContext.request.contextPath}/qestnrmanage/QuestionnaireManage/");
    </script>
</c:when>
<c:otherwise>
    <script>
    window.location.replace("${pageContext.request.contextPath}/qestnr/Questionnaire/");
    </script>
</c:otherwise>
</c:choose>
