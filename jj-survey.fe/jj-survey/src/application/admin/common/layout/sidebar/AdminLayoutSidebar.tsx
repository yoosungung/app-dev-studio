import React from 'react';
import { observer } from 'mobx-react-lite';

import AdminLayoutSidebarMenu from './AdminLayoutSidebarMenu';
import StoreUtil from '../../../../common/util/StoreUtil';

export default observer(function AdminLayoutSidebar() {
    const { menuStore } = StoreUtil.useStores();

    return (
        <div className="sidebar-container">
            <div className="vertical-head">
                <h2>{menuStore.topMenuId && menuStore.menuMap[menuStore.topMenuId].menu.menuNm}</h2>
            </div>
            <div className="vertical-menu">
                <ul data-current-menu-id={menuStore.currentMenuId} data-select-menu-id={menuStore.selectMenuId}>
                    {menuStore.menuList && menuStore.menuList.map((menu) => {
                        if (menu.upperMenuId === menuStore.topMenuId) {
                            return (
                                <AdminLayoutSidebarMenu key={menu.menuId} upperMenu={menu} />
                            );
                        }
                    })}
                </ul>
            </div>
        </div>
    );
});
