<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/ckeditor4/ckeditor.js"></script>

<%@ include file="ServiceStatusViewList.jsp" %>
<%@ include file="ServiceStatusViewFormRead.jsp" %>
<%-- <%@ include file="ServiceStatusViewFormEdit.jsp" %> --%>

<script>
CommonUtil.enableHashRouter("list");
</script>
