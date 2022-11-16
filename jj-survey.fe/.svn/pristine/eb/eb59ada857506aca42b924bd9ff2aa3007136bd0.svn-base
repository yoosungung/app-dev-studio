import React from 'react';
import useReactRouter from 'use-react-router';
import { MenuItem } from '@blueprintjs/core';

import StoreUtil from '../../../../common/util/StoreUtil';
import MenuInfo from '../../../../common/menu/model/MenuInfo';

export default function AdminLayoutHeaderMenu(props: {
    upperMenu: MenuInfo;
}) {
    const { history } = useReactRouter();
    const { menuStore } = StoreUtil.useStores();

    const menuClick = (menu: MenuInfo) => {
        if (menu.menuPath) {
            history.push(menu.menuPath);
        }
    };

    if (!menuStore.menuList) {
        return null;
    }

    return (
        <>
        {menuStore.menuList.map((menu) => {
            if (menu.upperMenuId === props.upperMenu.menuId) {
                let subMenuCount = 0;

                if (menuStore.menuList) {
                    menuStore.menuList.forEach((menu2) => {
                        if (menu2.upperMenuId === menu.menuId) {
                            subMenuCount++;
                        }
                    });
                }

                return (
                    <MenuItem key={menu.menuId} icon="application" text={menu.menuNm} onClick={() => { menuClick(menu); }}>
                        {subMenuCount > 0 ? <AdminLayoutHeaderMenu key={menu.menuId} upperMenu={menu} /> : undefined}
                    </MenuItem>
                );
            }
        })}
        </>
    );
}
