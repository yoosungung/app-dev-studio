<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/vue-jstree/dist/vue-jstree.js"></script>

<%@ include file="DepartmentManageViewList.jsp" %>
<%@ include file="DepartmentManageViewFormEdit.jsp" %>

<script>
CommonUtil.enableHashRouter("list");
</script>
