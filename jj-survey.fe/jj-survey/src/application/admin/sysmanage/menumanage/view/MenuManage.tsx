import React from 'react';
import { Route } from 'react-router-dom';

import MenuManageList from './MenuManageList';
// import MenuManageDetail from './MenuManageDetail';

export default function MenuManage() {
    return (
        <>
        <Route path={`/admin/sysmanage/menumanage/MenuManage/`} component={MenuManageList} />
        {/* <Route path={`/admin/sysmanage/menumanage/MenuManage/:menuId`} component={MenuManageDetail} /> */}
        </>
    );
}
