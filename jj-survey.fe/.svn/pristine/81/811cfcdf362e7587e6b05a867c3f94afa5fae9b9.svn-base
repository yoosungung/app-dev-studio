import React from 'react';
import useReactRouter from 'use-react-router';

import StoreUtil from '../../../../common/util/StoreUtil';
import MenuInfo from '../../../../common/menu/model/MenuInfo';

export default function AdminLayoutSidebarMenu(props: {
    upperMenu: MenuInfo;
}) {
    const { history } = useReactRouter();
    const { menuStore } = StoreUtil.useStores();

    const menuClick = () => {
        if (props.upperMenu.menuPath) {
            history.push(props.upperMenu.menuPath);
            menuStore.deleteSelectMenuId();
        } else {
            menuStore.setSelectMenuId(props.upperMenu.select ? undefined : props.upperMenu.menuId);
        }
    };

    if (!menuStore.menuList) {
        return null;
    }

    let subMenuCount = 0;

    const subMenuList = menuStore.menuList.map((menu) => {
        if (menu.upperMenuId === props.upperMenu.menuId) {
            subMenuCount++;

            return (
                <AdminLayoutSidebarMenu key={menu.menuId} upperMenu={menu} />
            );
        }
    });

    const liClassNames: string[] = [];

    if (props.upperMenu.active) {
        liClassNames.push("active");
    }

    if (props.upperMenu.select) {
        liClassNames.push("select");
    }

    return (
        <li className={liClassNames.join(" ")}>
            <label onClick={menuClick}>{props.upperMenu.menuNm}</label>
            {subMenuCount > 0 &&
            <ul>
                {subMenuList}
            </ul>
            }
        </li>
    );
}
