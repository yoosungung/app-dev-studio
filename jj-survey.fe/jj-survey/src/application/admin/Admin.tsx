import React from 'react';
import useReactRouter from 'use-react-router';
import { observer } from 'mobx-react-lite';
import { Spinner, Intent } from '@blueprintjs/core';

import './Admin.scss';

import StoreUtil from '../common/util/StoreUtil';
import AdminLayoutHeader from './common/layout/header/AdminLayoutHeader';
import AdminLayoutSidebar from './common/layout/sidebar/AdminLayoutSidebar';
import AdminRouter from './AdminRouter';

export default observer(function Admin() {
    const { history, location } = useReactRouter();
    const { menuStore } = StoreUtil.useStores();

    if (!menuStore.menuList) {
        menuStore.readAllList("ADMIN");

        return (
            <div className={`center-box`} data-menu-loaded={menuStore.loaded}>
                <Spinner intent={Intent.PRIMARY} size={50} />
            </div>
        );
    }

    menuStore.reset(location);

    if (!menuStore.currentMenuId) {
        const firstPathMenu = menuStore.firstPathMenu;

        if (firstPathMenu && firstPathMenu.menuPath) {
            location.pathname = firstPathMenu.menuPath;
            history.replace(firstPathMenu.menuPath);
            return null;
        }
    }

    return (
        <div className="admin">
            <div className="header">
                <AdminLayoutHeader />
            </div>
            <div className="body">
                <div className="sidebar">
                    <AdminLayoutSidebar />
                </div>
                <div className="contents">
                    <React.Suspense fallback={<div>loading...</div>}>
                        <AdminRouter />
                    </React.Suspense>
                </div>
            </div>
        </div>
    );
});
