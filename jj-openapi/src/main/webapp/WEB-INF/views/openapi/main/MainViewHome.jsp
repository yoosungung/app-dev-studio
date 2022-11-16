<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<!-- 메인이미지 영역 시작 -->

<div id="read">
<div id="slide_container" class="main_nav">
    <div id="slides">
    </div>
</div>

<!-- 메인이미지 영역 끝 -->
    <div class="main_content">
        <!-- Open API 인트로 시작 -->
        <div class="main_intro">
            전주대학교 보유의 진로정보를<br/>
            비롯한 다양한 컨텐츠를<br/>
            오픈API로 제공하고 있습니다.
        </div>
        <!-- Open API 인트로 끝 -->
        <!-- 메인링크배너 시작 -->
        <div class="main_banner">
            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/keystatus/KeyStatus/">
                        <div class="link_banner01">
                            <p>오픈 API 신청</p>
                            <span class="link_banner_text">
                            오픈 API를 이용하시려면<br/>
                            인증키가 필요합니다.
                            </span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/serviceguide/ServiceGuide/viewRead.do">
                        <div class="link_banner02">
                            <p>이용안내</p>
                            <span class="link_banner_text">
                            전주대학교 오픈 API 이용 안내입니다.<br/>
                            개발에 필요한 가이드를 안내해 드립니다.
                            </span>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
        <!-- 메인링크배너 끝 -->
        <!-- 공지사항/게시판/FAQ 시작 -->
        <div class="main_info">
            <ul>
                <li>
                    <!-- 공지사항 시작 -->
                    <div class="main_info_news">
                        <div class="main_info_top">
                            <p>공지사항</p>
                            <a href="${pageContext.request.contextPath}/bbs/notice/BbsNotice/"><span class="btn_more">더보기</span></a>
                        </div>
                        <table class="main_list">
                            <colgroup>
                                <col>
                                <col width="70">
                            </colgroup>
                            <tr v-for="notice in noticeList" v-bind:key="notice.bbscttId">
                                <th>
                                    <a v-bind:href="'${pageContext.request.contextPath}/bbs/notice/BbsNotice/#read:' + notice.bbscttId">
                                        <span class="main_list_title">{{ notice.bbscttSj }}</span>
                                    </a>
                                </th>
                                <td>{{ formatter.date(notice.writngDt) }}</td>
                            </tr>
                        </table>
                    </div>
                    <!-- 공지사항 끝 -->
                </li>
                <li>
                    <!-- 게시판 시작 -->
                    <div class="main_info_news">
                        <div class="main_info_top">
                            <p>Q&A</p>
                            <a href="${pageContext.request.contextPath}/bbs/gnrl/BbsGeneral/QNA/"><span class="btn_more">더보기</span></a>
                        </div>
                        <table class="main_list">
                            <colgroup>
                                <col>
                                <col width="70">
                            </colgroup>
                            <tr v-for="qna in qnaList" v-bind:key="qna.bbscttId">
                                <th>
                                    <a v-bind:href="'${pageContext.request.contextPath}/bbs/gnrl/BbsGeneral/QNA/#read:' + qna.bbscttId">
                                        <span class="main_list_title">{{ qna.bbscttSj }}</span>
                                    </a>
                                </th>
                                <td>{{ formatter.date(qna.writngDt) }}</td>
                            </tr>
                        </table>
                    </div>
                    <!-- 게시판 끝 -->
                </li>
                <li>
                    <!-- FAQ 시작 -->
                    <div class="main_info_news">
                        <div class="main_info_top">
                            <p>FAQ</p>
                            <a href="${pageContext.request.contextPath}/bbs/faq/BbsFaq/"><span class="btn_more">더보기</span></a>
                        </div>
                        <table class="main_list">
                            <colgroup>
                                <col>
                                <col width="70">
                            </colgroup>
                            <tr v-for="faq in faqList" v-bind:key="faq.bbscttId">
                                <th>
                                    <a v-bind:href="'${pageContext.request.contextPath}/bbs/faq/BbsFaq/#read:' + faq.bbscttId">
                                        <span class="main_list_title">{{ faq.bbscttSj }}</span>
                                    </a>
                                </th>
                                <td>{{ formatter.date(faq.writngDt) }}</td>
                            </tr>
                        </table>
                    </div>
                    <!-- FAQ 끝 -->
                </li>
            </ul>
        </div>
        <!-- 공지사항/게시판/FAQ 끝 -->
    </div>
</div>
<script>
vues.read = new Vue({
    el: '#read',
    data: {
        noticeList: [],
        qnaList: [],
        faqList: []
    },
    computed: {
        service: function() {
            return new MainViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    mounted: function() {
        this.service.readNoticeList();
        this.service.readQnaList();
        this.service.readFaqList();
        this.service.readBannerList();
    },
    methods: {
    }
});
</script>
