<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/vue-jstree/dist/vue-jstree.js"></script>

<%@ include file="MenuManageViewList.jsp" %>
<%@ include file="MenuManageViewFormEdit.jsp" %>
<%@ include file="MenuManageViewFormEditUpperMenu.jsp" %>

<script>
CommonUtil.enableHashRouter("list");
</script>
