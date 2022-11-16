var MenuLoad = {};

MenuLoad.createHeaderMenuList = function(selector, menuList, current) {
    if ($(selector).length == 0) {
        return;
    }

    new Vue({
        el: selector,
        data: {
            menuList: menuList
        },
        methods: {
        },
        computed: {
            headerMenuList: function() {
                var list = [];

                for (var i = 0; i < this.menuList.length; i++) {
                    var menu = this.menuList[i];

                    if (menu.upperMenuId == null) {
                        list.push(menu);
                    }
                }

                return list;
            },
            topMenu: function() {
                return current.headerMenu;
            }
        }
    });
};

MenuLoad.createHeaderInnerMenuList = function(selector, menuList, current) {
    if ($(selector).length == 0) {
        return;
    }

    new Vue({
        el: selector,
        data: {
            menuList: menuList
        },
        methods: {
        },
        computed: {
            headerMenuList: function() {
                var list = [];

                for (var i = 0; i < this.menuList.length; i++) {
                    var menu = this.menuList[i];

                    if (menu.upperMenuId == null) {
                        list.push(menu);
                    }
                }

                return list;
            }
        }
    });
};

MenuLoad.createSidebarMenuList = function(selector, menuList, current) {
    if ($(selector).length == 0) {
        return;
    }

    new Vue({
        el: selector,
        data: {
            menuList: menuList
        },
        methods: {
        },
        computed: {
            sidebarMenuList: function() {
                var list = [];

                for (var i = 0; i < this.menuList.length; i++) {
                    var menu = this.menuList[i];

                    if (menu.upperMenuId != null) {
                        if (menu.menuId == current.menu.menuId) {
                            menu._className = "on";
                        }

                        list.push(menu);
                    }
                }

                return list;
            },
            upperMenu: function() {
                return current.headerMenu;
            }
        }
    });
};

MenuLoad.createSubMenuList = function(selector, menuList, current) {
    if ($(selector).length == 0) {
        return;
    }

    new Vue({
        el: selector,
        data: {
            menuList: menuList
        },
        methods: {
        },
        computed: {
            subMenuList: function() {
                var list = [];

                if (current.headerMenu == null) {
                    return list;
                }

                for (var i = 0; i < this.menuList.length; i++) {
                    var menu = this.menuList[i];

                    if (menu.upperMenuId == current.headerMenu.menuId) {
                        if (menu.menuId == current.menu.menuId) {
                            menu._className = "on";
                        }

                        list.push(menu);
                    }
                }

                return list;
            }
        }
    });
};

(function() {
    Vue.component('ui-menu-sidebar', {
        props: ["menuList", "upperMenu"],
        template: ' \
            <ul> \
                <li v-for="menu in sidebarMenuList" v-bind:key="menu.menuId" v-bind:class="menu._className"> \
                    <a v-bind:href="menu.menuPath"> \
                        <span class="gnb_left_menu">{{ menu.menuNm }}</span> \
                    </a> \
                    <ui-menu-sidebar v-bind:menu-list="menuList" v-bind:upperMenu="menu"></ui-menu-sidebar> \
                </li> \
            </ul> \
        ',
        computed: {
            sidebarMenuList: function() {
                var list = [];

                for (var i = 0; i < this.menuList.length; i++) {
                    var menu = this.menuList[i];

                    if (menu.upperMenuId == this.upperMenu.menuId) {
                        list.push(menu);
                    }
                }

                return list;
            }
        }
    });

    CommonUtil.axios()
    .get("/common/menu/MenuLoad/MAIN/readAllList.do")
    .then(function(response) {
        var menuList = response.data;
        var pathname = window.location.pathname.replace(CommonUtil.contextPath, "") + window.location.search;
        var current = {};

        current.menu = getCurrentMenu(menuList, pathname);
        current.headerMenu = getHeaderMenu(menuList, current.menu);

        if (current.menu == null) {
            current.menu = {};
        }

        if (current.headerMenu == null) {
            current.headerMenu = {};
        }

        for (var i = 0; i < menuList.length; i++) {
            var menu = menuList[i];

            if (menu.menuPath != null) {
                menu.menuPath = CommonUtil.contextPath + menu.menuPath;
            }
        }

        MenuLoad.createHeaderMenuList('#gnb_nav', menuList, current);
        //MenuLoad.createHeaderInnerMenuList('header > .subMenu > .subMenuInner', menuList, current);
        MenuLoad.createSidebarMenuList('.pageLeft_nav', menuList, current);
        //MenuLoad.createSubMenuList('.subNav > .subNavInner', menuList, current);
    })["catch"](function(error) {
        CommonUtil.showAxiosError(error);
    });

    function getCurrentMenu(menuList, pathname) {
        var menu = findMenu(function(menu) {
            return menu.menuPath == pathname;
        });

        if (menu == null) {
            menu = findMenu(function(menu) {
                return menu.menuPath.length > 1 && pathname.substr(0, menu.menuPath.length) == menu.menuPath;
            });
        }

        if (menu == null) {
            menu = findMenu(function(menu) {
                return pathname.length > 1 && menu.menuPath.substr(0, pathname.length) == pathname;
            });
        }

        return menu;

        function findMenu(compare) {
            for (var i = 0; i < menuList.length; i++) {
                var menu = menuList[i];

                if (menu.upperMenuId != null && menu.menuPath != null && compare(menu) === true) {
                    return menu;
                }
            }
        }
    }

    function getHeaderMenu(menuList, menu) {
        if (menu == null) {
            return;
        }

        for (var i = 0; i < menuList.length; i++) {
            if (menuList[i].menuId != menu.upperMenuId) {
                continue;
            }

            menuList[i]._className = "on";

            if (menuList[i].upperMenuId != null) {
                return getHeaderMenu(menuList, menuList[i]);
            }

            return menuList[i];
        }
    }
})();
