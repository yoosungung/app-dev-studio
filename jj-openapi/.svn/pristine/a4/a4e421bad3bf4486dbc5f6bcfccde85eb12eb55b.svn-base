<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<sec:authorize access="hasAnyRole('ROLE_SUPER')" var="isRoleSuper" />

<c:choose>
<c:when test="${isRoleSuper}">
    <script>
    window.location.replace("${pageContext.request.contextPath}/admin/appmanage/personmanage/PersonManage/");
    </script>
</c:when>
<c:otherwise>
    <%@ include file="MainViewHome.jsp" %>
</c:otherwise>
</c:choose>
