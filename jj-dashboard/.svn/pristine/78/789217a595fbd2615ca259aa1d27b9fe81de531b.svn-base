<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dashboard/common/user/UserLoginView.css" />

<!-- 로그인 시작 -->
<div class="loginArea" style="display: none;" v-on:keyup.enter="service.login();">
    <div class="loginBox">
        <div class="loginCon">
            <input type="text" v-model="model.loginNm" alt="아이디입력" tabindex="1" ref="loginNm" />
            <ui-valid-checker v-bind:value="model.loginNm" check="required" style="position: absolute; right: 5px; top: 13px;"></ui-valid-checker>

            <input type="password" v-model="model.loginPassword" alt="비밀번호입력" tabindex="2" ref="loginPassword"
                <c:if test="${adminLoginAvail eq true}">readonly</c:if>
            />
            <ui-valid-checker v-bind:value="model.loginPassword" check="required" style="position: absolute; right: 5px; top: 66px;"></ui-valid-checker>

            <ui-button class="btn_login" tabindex="3" v-on:click="service.login();">로그인</ui-button>
        </div>
        <div class="loginBottom">
            <p>
                Copyrights (C)2020 Jeonju University. All Rights Reserved.
            </p>
            <span>
                <a href="http://www.jj.ac.kr/jj/personal_information.jsp" target="_blank">개인정보처리방침</a>
            </span>
        </div>
    </div>
</div>
<!-- 로그인 끝 -->

<script>
vues.login = new Vue({
    el: '.loginArea',
    data: {
        model: {
            loginNm: "",
            loginPassword: "<c:out value="${adminLoginPassword}" escapeXml="false" />"
        },
        url: {
            loginPath: "<c:out value="${loginPath}" />",
            targetUrl: "<c:out value="${targetUrl}" />"
        }
    },
    computed: {
        service: function() {
            return new UserLoginViewService(this);
        }
    },
    mounted: function() {
        <c:choose>
        <c:when test="${empty userLogin_loginTy}">
        this.viewLogin();
        </c:when>
        <c:otherwise>
        this.service.login("<c:out value="${userLogin_loginTy}" />", "<c:out value="${userLogin_username}" />");
        </c:otherwise>
        </c:choose>
    },
    methods: {
        // 화면 보이기
        viewLogin: function() {
            $(this.$el).show();

            this.$refs.loginNm.focus();
        }
    }
});
</script>
