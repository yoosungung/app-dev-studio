<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<%@ include file="CareerPathViewList.jsp" %>
<%@ include file="CareerPathViewFormEdit.jsp" %>
<%@ include file="CareerPathViewFormRead.jsp" %>
<%@ include file="CareerPathViewExcelUpload.jsp" %>

<script>
CommonUtil.enableHashRouter("list");
</script>
