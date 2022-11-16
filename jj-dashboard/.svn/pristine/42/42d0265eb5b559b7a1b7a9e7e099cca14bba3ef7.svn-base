<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:importAttribute toName="servletPath" name="content" scope="request" />
<% out.clear(); %><!DOCTYPE html>
<html lang="ko">
<head>
    <title>전주대학교 학생성공지원 시스템</title>

    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <meta name="X-CSRF-Parameter-Name" content="<c:out value="${_csrf.parameterName}" />" />
    <meta name="X-CSRF-Header-Name" content="<c:out value="${_csrf.headerName}" />" />
    <meta name="X-CSRF-Token" content="<c:out value="${_csrf.token}" />" />
    <meta name="X-Servlet-Path" content="<c:out value="${servletPath}" />" />
    <meta name="X-Context-Path" content="<c:out value="${pageContext.request.contextPath}" />" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/babel-polyfill/polyfill.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/bluebird/bluebird.min.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/jquery/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/vue/vue.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/axios/axios.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared/common/lib/axios/nprogress.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/axios/loadProgressBar.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared/common/ui/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/ui/common.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/util/CommonUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/util/TextUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/util/ValidationUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/vue/VueComponents.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dashboard/common/ui/layout.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dashboard/common/ui/style.css">

    <style>
    #nprogress .bar {
        background: red !important;
    }

    #nprogress .peg {
        box-shadow: 0 0 10px red, 0 0 5px red !important;
    }

    #nprogress .spinner {
        left: calc((100% - 18px) / 2);
    }

    #nprogress .spinner-icon {
        border-top-color: red !important;
        border-left-color: red !important;
    }
    </style>
</head>
<body>
    <div id="wrap">
        <!-- id=wrap 값이 있는 div에 pageWide클래스를 넣었을 경우 100%화면 구현 -->

        <div id="header">
            <%@ include file="/WEB-INF/layouts/dashboard/include/TilesIncludeHeader.jspf" %>
        </div>
        <script src="${pageContext.request.contextPath}/shared/common/menu/MenuLoad.js"></script>
        <div id="container">
            <!-- 오른쪽 컨텐츠 영역 시작 -->
            <div class="pageCon">
                <script src="${pageContext.request.contextPath}<tiles:getAsString name="service" defaultValue="/shared/common/BlankViewService.js" />"></script>
                <script>var vues = {};</script>
                <tiles:insertAttribute name="content" />
            </div>
            <!-- 오른쪽 컨텐츠 영역 끝 -->
        </div>
    </div>
</body>
</html>
