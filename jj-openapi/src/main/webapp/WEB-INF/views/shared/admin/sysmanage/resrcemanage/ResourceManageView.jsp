<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<%@ include file="ResourceManageViewList.jsp" %>
<%@ include file="ResourceManageViewSortList.jsp" %>
<%@ include file="ResourceManageViewFormRead.jsp" %>
<%@ include file="ResourceManageViewFormEdit.jsp" %>

<script>
CommonUtil.enableHashRouter("list");
</script>
