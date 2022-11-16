<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf"%>

<div id="edit" style="display: none;">
    <div class="pageHeader" v-if="bbscttId == null">공지 등록</div>
    <div class="pageHeader" v-if="bbscttId != null">공지 수정</div>

    <div class="pageContents">
        <h3>공지 입력</h3>
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
                    <td  colspan="2"><ui-input type="text"
                            v-model="model.tbBbsNotice.bbscttSj" v-bind:max-byte="200"
                            v-bind:byte-indicator="false" auto-focus></ui-input> <ui-valid-checker
                            v-bind:value="model.tbBbsNotice.bbscttSj" check="required"></ui-valid-checker>
                    </td>

                </tr>
                 <tr>

                    <th>등록일</th>
                    <td colspan="2">
                    <ui-date v-model="model.tbBbsNotice.registrydate" v-bind:max-byte="2000"
                            v-bind:byte-indicator="true"></ui-date>
                    </td>
                </tr>
                <tr>

                    <th>첨부파일</th>
                    <td colspan="2"><ui-input type="text"
                            v-model="model.tbBbsNotice.atchFileId" v-bind:max-byte="500"
                            v-bind:byte-indicator="false"></ui-input> <ui-valid-checker
                            v-bind:value="model.tbBbsNotice.atchFileId" ></ui-valid-checker>
                    </td>
                </tr>

                <tr>
                    <th>공지 내용</th>
                    <td colspan="3"><ui-textarea rows="3"
                            v-model="model.tbBbsNotice.bbscttCn" v-bind:max-byte="2000"
                            v-bind:byte-indicator="true" check="required"></ui-textarea></td>
                </tr>

            </tbody>
        </table>



  <ui-valid-checker v-bind:value="model.tbBbsNotice"
            check="required"></ui-valid-checker>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="service['update']();">저장</ui-button>
            <ui-button class="page" v-on:click="service['delete']();"
                v-if="bbscttId != null">삭제</ui-button>
            <ui-button class="page" v-on:click="viewRead();"
                v-if="bbscttId != null">취소</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>



<script>
    vues.edit = new Vue({
        el : '#edit',
        data : {
            bbscttId : null,
            model : {
                tbBbsNotice : { }
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
