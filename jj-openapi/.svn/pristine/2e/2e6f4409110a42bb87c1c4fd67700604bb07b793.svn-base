<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>
<% out.clear(); %><!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>전주대학교 OpenAPI 관리시스템</title>
    <meta content="Dashboard" name="description" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="IE=edge" http-equiv="X-UA-Compatible" />
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/shared/common/lib/bootstrap/css/bootstrap.min.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/shared/common/lib/fontawesome/css/all.min.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/openapi/common/error/beyond.min.css" />
    <style type="text/css">
    body, h1 {
        font-family: "맑은 고딕", "굴림", "돋움", "gulim", "dotum", "Arial", "Helvetica", "sans-serif";
    }
    </style>
    <script>
    var Page = {
        goBack: function() {
            if (document.referrer && document.referrer.indexOf(location.origin) != -1) {
                location.href = document.referrer;
            } else {
                Page.goMain();
            }
        },
        goMain: function() {
            location.href = "${pageContext.request.contextPath}/";
        }
    };
    </script>
</head>
<c:choose>
<c:when test="${status eq 500}">
    <c:set var="classCode" value="500" />
</c:when>
<c:otherwise>
    <c:set var="classCode" value="404" />
</c:otherwise>
</c:choose>
<body class="body-<c:out value="${classCode}" />">
    <div class="body-<c:out value="${classCode}" />">
        <div class="error-header"></div>
        <div class="container ">
            <section class="error-container text-center">
                <h1><c:out value="${status}" /></h1>
                <div class="error-divider">
                    <p style="margin-top: 50px;" class="description"><c:out value="${message}" /></p>
                </div>
                <a href="javascript:Page.goBack();" class="return-btn"><i class="fa fa-arrow-left"></i> 이전 페이지</a>
                <a href="javascript:Page.goMain();" class="return-btn"><i class="fa fa-home"></i> 메인 페이지</a>
            </section>
        </div>
    </div>
</body>
</html>
