<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/ckeditor4/ckeditor.js"></script>

<%@ include file="BbsNoticeViewList.jsp" %>
<%@ include file="BbsNoticeViewFormRead.jsp"%>
<%@ include file="BbsNoticeViewFormEdit.jsp" %>

<script>
CommonUtil.enableHashRouter("list");
</script>
