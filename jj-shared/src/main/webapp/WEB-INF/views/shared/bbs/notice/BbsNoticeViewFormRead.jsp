<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf"%>

<div id="read" style="display: none;">

<!--
    <div class="pageHeader" v-if="bbscttId == null">FAQ 등록</div>
    <div class="pageHeader" v-if="bbscttId != null">FAQ 수정</div> -->

    <div class="pageContents">
        <h3>공지 상세</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>제목</th>
                    <td>
                        <span>{{ model.tbBbsNotice.bbscttSj }}</span>
                    </td>
                    <th>작성자</th>
                    <td>
                        <span>{{ model.tbBbsNotice.writngPsnNm}}</span>
                    </td>

                <tr>
                <th>공지 시작일</th>
                    <td>
                        <span>{{formatter.datetime( model.tbBbsNotice.noticeBeginDe)}}</span>
                    </td>
                <th>공지 종료일</th>
                    <td>
                        <span>{{ formatter.datetime(model.tbBbsNotice.noticeEndDe)}}</span>
                    </td>
                </tr>
                <tr>

                    <th>내용</th>
                    <td colspan="3">
                        <pre>{{ model.tbBbsNotice.bbscttCn}}</pre>
                    </td>
                </tr>

            </tbody>
        </table>

        <!-- <div class="table full">
            <h3>코드값 목록</h3>
        </div>
        <table class="list">
            <thead>
                <tr>
                    <th width="">코드값</th>
                    <th width="">코드값 이름</th>
                    <th width="80px">정렬번호</th>
                    <th width="80px">사용여부</th>
                </tr>
            </thead>

        </table>
-->
        <div class="page-space tar">
            <ui-button class="page" v-on:click="viewEdit();" v-if="model.editable">수정</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="model.editable">삭제</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>



<script>
    vues.read = new Vue({
        el : '#read',
        data : {
            bbscttId : null,
            model : {
                tbBbsNotice : {}
    //,
                // 이데이터를 찍어볼라고하는데 어떻게찍는지?
                //tbSysCodeValueList : []
            }
        },
        computed : {
             service : function() {
                 return new BbsNoticeViewService(this);
            },
            formatter : function() {
                return CommonUtil.formatter;
            }
        },
        methods : {
              // 수정 화면
            viewEdit : function() {
                vues.edit.service.read(this.bbscttId, function(model) {
                    if (model.editable === true) {
                        CommonUtil.setViewMode(vues, "edit");
                    }
                });
            },
            // 조회 화면
            viewRead : function() {
               CommonUtil.setViewMode(vues, "read");
                vues.read.service.read(this.bbscttId);
            },
            // 목록 화면
            viewList : function() {
                CommonUtil.setViewMode(vues, "list");
            }
        }
    });
</script>
