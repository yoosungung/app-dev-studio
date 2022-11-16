import React from 'react';
import { Link } from 'react-router-dom';
import { observer } from 'mobx-react-lite';
import { Alignment, Button, Classes, Navbar, NavbarDivider, NavbarGroup, NavbarHeading, Menu, MenuItem, MenuDivider, Popover, Position, Tooltip } from "@blueprintjs/core";

import CommonUtil from '../../../../common/util/CommonUtil';
import StoreUtil from '../../../../common/util/StoreUtil';
import AdminLayoutHeaderMenu from './AdminLayoutHeaderMenu';
import UserService from '../../../../common/user/service/UserService';

export default observer(function AdminLayoutHeader() {
    const { menuStore, userStore } = StoreUtil.useStores();
    const userService = new UserService();

    const logout = () => {
        userService.logout();
    };

    return (
        <>
        <Navbar>
            <NavbarGroup align={Alignment.LEFT}>
                <NavbarHeading>
                    <Link to="/admin/">
                        <h1>설문관리 시스템 Admin</h1>
                    </Link>
                </NavbarHeading>
                <NavbarDivider />
                {menuStore.menuList && menuStore.menuList.map((menu) => {
                    if (menu.menuLevel === 1) {
                        return (
                            <Popover key={menu.menuId} position={Position.BOTTOM} content={
                                <Menu>
                                    <MenuItem disabled hidden />
                                    <AdminLayoutHeaderMenu upperMenu={menu} />
                                </Menu>
                            }>
                                <Button
                                    icon="control"
                                    className={Classes.MINIMAL}
                                    text={menu.menuNm}
                                    active={menu.menuId === menuStore.topMenuId}
                                />
                            </Popover>
                        );
                    }
                })}
            </NavbarGroup>
            <NavbarGroup align={Alignment.RIGHT}>
                <Link to="/">
                    <Tooltip content="홈으로">
                        <Button className={Classes.MINIMAL} icon="home" />
                    </Tooltip>
                </Link>
                <Popover position={Position.BOTTOM} content={
                    <Menu>
                        <MenuItem icon="person" text={`${userStore.loginUser.personNm} 님`} />
                        <MenuItem icon="key" text="비밀번호 변경" />
                        <MenuDivider />
                        <MenuItem icon="log-out" text="로그아웃" onClick={() => {
                            logout();
                        }} />
                    </Menu>
                }>
                    <Tooltip content="사용자">
                        <Button className={Classes.MINIMAL} icon="user" />
                    </Tooltip>
                </Popover>
                <Tooltip content="설정">
                    <Button className={Classes.MINIMAL} icon="cog" />
                </Tooltip>
            </NavbarGroup>
        </Navbar>
        </>
    );
});
