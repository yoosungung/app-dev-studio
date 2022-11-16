<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/layouts/shared/include/PageHeader.jspf" %>

<div id="read" style="display: none;">
    <div class="pageHeader">
        권한 상세
    </div>

    <div class="pageContents">
        <h3>권한 정보</h3>
        <table class="form">
            <colgroup>
                <col width="120px">
                <col width="">
                <col width="120px">
                <col width="">
            </colgroup>
            <tbody>
                <tr>
                    <th>권한 코드</th>
                    <td>
                        <span>{{ model.tbSysAuthor.authorCode }}</span>
                    </td>
                    <th>권한 이름</th>
                    <td>
                        <span>{{ model.tbSysAuthor.authorNm }}</span>
                    </td>
                </tr>
                <tr>
                    <th>권한 설명</th>
                    <td colspan="3">
                        <pre>{{ model.tbSysAuthor.authorDc }}</pre>
                    </td>
                </tr>
                <tr>
                    <th>사용자 권한 여부</th>
                    <td>
                        <span>{{ model.tbSysAuthor.userAuthorYnNm }}</span>
                    </td>
                    <th>사용 여부</th>
                    <td>
                        <span>{{ model.tbSysAuthor.useYnNm }}</span>
                    </td>
                </tr>
            </tbody>
        </table>

        <div class="table full" v-if="model.tbSysAuthor.userAuthorYn">
            <div style="width: 33%;">
                <h3>권한별 사용자</h3>
                <table class="list auto">
                    <thead>
                        <tr>
                            <th width="">로그인 이름</th>
                            <th width="">사번</th>
                            <th width="">이름</th>
                        </tr>
                    </thead>
                    <tbody v-cloak v-if="model.tbSysUserAuthorToUserList.length == 0">
                        <tr>
                            <td colspan="3" class="tac">(사용자 없음)</td>
                        </tr>
                    </tbody>
                    <tbody v-cloak v-if="model.tbSysUserAuthorToUserList.length > 0">
                        <tr v-for="row in model.tbSysUserAuthorToUserList" v-bind:key="row.userId">
                            <td class="tac">{{ row.loginNm }}</td>
                            <td class="tac">{{ row.emplNo }}</td>
                            <td class="tac">{{ row.koreanNm }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div style="padding-left: 10px;"></div>
            <div style="width: 33%;">
                <h3>권한별 부서</h3>
                <table class="list auto">
                    <thead>
                        <tr>
                            <th width="">부서 코드</th>
                            <th width="">부서 이름</th>
                        </tr>
                    </thead>
                    <tbody v-cloak v-if="model.tbComDeptAuthorToDeptList.length == 0">
                        <tr>
                            <td colspan="2" class="tac">(부서 없음)</td>
                        </tr>
                    </tbody>
                    <tbody v-cloak v-if="model.tbComDeptAuthorToDeptList.length > 0">
                        <tr v-for="row in model.tbComDeptAuthorToDeptList" v-bind:key="row.deptId">
                            <td class="tac">{{ row.deptCode }}</td>
                            <td class="tac">{{ row.deptNm }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div style="padding-left: 10px;"></div>
            <div style="width: 33%;">
                <h3>권한별 보직</h3>
                <table class="list auto">
                    <thead>
                        <tr>
                            <th width="">보직명</th>
                        </tr>
                    </thead>
                    <tbody v-cloak v-if="model.tbSysAuthorRelateCodeDtyNmList.length == 0">
                        <tr>
                            <td class="tac">(보직 없음)</td>
                        </tr>
                    </tbody>
                    <tbody v-cloak v-if="model.tbSysAuthorRelateCodeDtyNmList.length > 0">
                        <tr v-for="row in model.tbSysAuthorRelateCodeDtyNmList" v-bind:key="row.userId">
                            <td class="tac">{{ row.relateCodeValue }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="table full">
            <div style="width: 33%;">
                <h3>권한별 메뉴</h3>
                <div class="box">
                    <div v-if="menuTreeList.length == 0" class="tac">(메뉴 없음)</div>
                    <ui-jstree v-if="menuTreeList.length > 0"
                        v-bind:data="menuTreeList"
                        v-bind:columns="{ id: 'menuId', parent: 'upperMenuId', text: 'menuNm' }"
                        v-bind:options="{ initiallyOpenLevel: -1 }"
                    ></ui-jstree>
                </div>
            </div>
            <div style="padding-left: 10px;"></div>
            <div style="width: 33%;">
                <h3>권한별 프로그램</h3>
                <table class="list auto">
                    <thead>
                        <tr>
                            <th width="">프로그램 유형</th>
                            <th width="">프로그램 패턴</th>
                        </tr>
                    </thead>
                    <tbody v-cloak v-if="resrceList.length == 0">
                        <tr>
                            <td colspan="2" class="tac">(프로그램 없음)</td>
                        </tr>
                    </tbody>
                    <tbody v-cloak v-if="resrceList.length > 0">
                        <tr v-for="row in resrceList" v-bind:key="row.resrceId">
                            <td class="tac">{{ row.resrceTyNm }}</td>
                            <td class="tal">{{ row.resrcePttrn }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div style="padding-left: 10px;"></div>
            <div style="width: 33%;">
                <h3>권한별 사용자 구분</h3>
                <table class="list auto">
                    <thead>
                        <tr>
                            <th width="">구분 코드</th>
                            <th width="">구분 이름</th>
                        </tr>
                    </thead>
                    <tbody v-cloak v-if="personSeList.length == 0">
                        <tr>
                            <td colspan="2" class="tac">(사용자 구분 없음)</td>
                        </tr>
                    </tbody>
                    <tbody v-cloak v-if="personSeList.length > 0">
                        <tr v-for="row in personSeList" v-bind:key="row.authorId">
                            <td class="tac">{{ row.relateCodeValue }}</td>
                            <td class="tac">{{ row.relateCodeValueNm }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="page-space tar">
            <ui-button class="page" v-on:click="viewEdit();" v-if="model.editable">수정</ui-button>
            <ui-button class="page" v-on:click="service['delete']();" v-if="model.deletable">삭제</ui-button>
            <ui-button class="list" v-on:click="viewList();">목록</ui-button>
        </div>
    </div>
</div>

<script>
vues.read = new Vue({
    el: '#read',
    data: {
        authorId: null,
        menuKnd: "MAIN",
        model: {
            tbSysAuthor: {},
            tbSysMenuAuthorToMenuListMap: {},
            tbSysResrceAuthorToResrceList: [],
            tbComDeptAuthorToDeptList: [],
            tbSysUserAuthorToUserList: [],
            tbSysAuthorRelateCodePersonSeList: [],
            tbSysAuthorRelateCodeDtyNmList: []
        }
    },
    computed: {
        service: function() {
            return new AuthorityManageViewService(this);
        },
        formatter: function() {
            return CommonUtil.formatter;
        },
        menuTreeList: function() {
            var menuList = this.model.tbSysMenuAuthorToMenuListMap[this.menuKnd];

            if (menuList == null) {
                return [];
            }

            var result = [];

            for (var i = 0; i < menuList.length; i++) {
                if (menuList[i].menuAuthorYn == true) {
                    result.push(menuList[i])
                }
            }

            return result;
        },
        resrceList: function() {
            var resrceList = this.model.tbSysResrceAuthorToResrceList;
            var result = [];

            for (var i = 0; i < resrceList.length; i++) {
                if (resrceList[i].resrceAuthorYn == true) {
                    result.push(resrceList[i])
                }
            }

            return result;
        },
        personSeList: function() {
            var personSeList = this.model.tbSysAuthorRelateCodePersonSeList;
            var result = [];

            for (var i = 0; i < personSeList.length; i++) {
                if (personSeList[i].relateCodeAuthorYn == true) {
                    result.push(personSeList[i])
                }
            }

            return result;
        }
    },
    methods: {
        // 초기화
        init: function(authorId) {
            this.service.read(authorId);
        },
        // 수정 화면
        viewEdit: function() {
            CommonUtil.changeVue("edit", this.authorId);
        },
        // 목록 화면
        viewList: function() {
            CommonUtil.changeVue("list");
        }
    }
});
</script>
