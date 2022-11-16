<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="excelUpload" style="display: none;">
    <div class="pageContents" style="margin-top: 10px;">
        <!-- 영역 시작 -->
        <div class="searchArea">
            <table class="searchBox">
                <colgroup>
                    <col width="50%">
                    <col width="25%">
                    <col width="25%">
                </colgroup>
                <tr>
                    <td>
                        <input type="file" id="file" ref="file" v-on:change="fileUpload();" accept=".xls, .xlsx">
                    </td>
                    <td>
                        <div class="btnArea_center">
                            <ui-button class="btn_write" v-on:click="excelUpload();">업로드</ui-button>
                            <a href="${pageContext.request.contextPath}/dashboard/careerpath/선도학생양식.xlsx" download><ui-button class="btn_excel">양식 다운로드</ui-button></a>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <!-- 영역 끝 -->
    </div>
</div>

<script>
vues.excelUpload = new Vue({
    el: '#excelUpload',
    data: {
        surveyGroupId: null,
        file: ''
    },
    computed: {
        service: function() {
            return new CareerPathViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        }
    },
    methods: {
        // 초기화
        init: function() {
            $(this.$el).dialog({
                title: "엑셀 업로드",
                modal: true,
                width: 800,
                height: 250,
                close: function() {
                    vues.list.service.readList();
                }
            });
        },
        // 파일 선택
        fileUpload: function() {
            this.file = this.$refs.file.files[0];
        },
        // 엑셀 업로드
        excelUpload: function() {
            var vue = this;

            this.service.excelUpload(function() {
                vues.list.service.readList();

                $(vue.$el).dialog("close");
            });
        }
    }
});
</script>
