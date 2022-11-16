<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>


<div id="read" style="display: none;">
    <div class="pageHeader">
        Q&A
    </div>

    <div class="pageContents">
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>제목</th>
                    <td>
                        <span>{{ model.tbBbsGnrl.bbscttSj}}</span>
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>
<!--작성자 디비 function말고 화면상에서 호출하는 방법? -->
                        <span>{{ model.tbBbsGnrl.writngPsnId }}</span>
                    </td>
                </tr>
                <tr>
                    <th>등록일</th>
                    <td>
                        <span>{{ formatter.datetime(model.tbBbsGnrl.writngDt )}}</span>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <span>{{ model.tbBbsGnrl.bbscttCn }}</span>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="page-space tar">
        <!--현재유저가 같은 유저인지 찾는 방법? -->
            <ui-button class="page" v-on:click="viewEditAns();" >답변</ui-button>
            <ui-button class="page" v-on:click="viewEdit();" >수정</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" >삭제</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.read = new Vue({
    el: '#read',
    data: {
        bbscttId: null,
        model: {
            tbBbsGnrl: {}
        }
    },
    computed: {
        service: function() {
            return new BbsGeneralViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 수정 화면
        viewEdit: function() {
            vues.edit.service.read(this.bbscttId, function(model) {
                CommonUtil.setViewMode(vues, "edit");
            });
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.setViewMode(vues, "list");
        },
        // 답변 화면
        viewEditAns: function() {
            vues.edit.service.readAns(this.bbscttId, function(model) {
                CommonUtil.setViewMode(vues, "edit");
            });
        }
    }
});
</script>
