<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:importAttribute toName="servletPath" name="content" scope="request" />
<% out.clear(); %><!DOCTYPE html>
<html lang="ko">
<head>
    <title>전주대학교 OpenAPI 관리시스템</title>

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

    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/jquery.scrollTo/jquery.scrollTo.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared/common/lib/axios/nprogress.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/axios/loadProgressBar.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/moment/moment.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/lib/moment/locale/ko.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/shared/common/ui/common.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/ui/common.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/util/CommonUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/util/TextUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/util/ValidationUtil.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/shared/common/vue/VueComponents.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/openapi/common/ui/layout.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/openapi/common/ui/style.css">

    <!--메인화면 추가 -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/openapi/main/main.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/openapi/main/js/jquery.alsEN-1.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/openapi/main/js/jquery.slides.min.js"></script>
    <!--메인화면 추가 -->

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
    <!-- 메인이미지 영역 시작 -->
    <div id="wrap">
        <div id="header">
            <%@ include file="/WEB-INF/layouts/openapi/include/TilesIncludeHeader.jspf" %>
        </div>
        <script src="${pageContext.request.contextPath}/shared/common/menu/MenuLoad.js"></script>
        <div id="container">
            <div class="pageContainer">
                <!-- 메인 컨텐츠 영역 시작 -->
                <div class="pageCon">
                    <script src="${pageContext.request.contextPath}<tiles:getAsString name="service" defaultValue="/shared/common/BlankViewService.js" />"></script>
                    <script>var vues = {};</script>
                    <tiles:insertAttribute name="content" />
                </div>
                <!-- 메인 컨텐츠 영역 끝 -->
            </div>
            <%@ include file="/WEB-INF/layouts/openapi/include/TilesIncludeFooter.jspf" %>
        </div>
    </div>
</body>
</html>
