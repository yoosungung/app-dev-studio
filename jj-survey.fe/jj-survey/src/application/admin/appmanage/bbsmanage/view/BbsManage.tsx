import React from 'react';
import { Route } from 'react-router-dom';

import BbsManageList from './BbsManageList';
import BbsManageDetail from './BbsManageDetail';

export default function BbsManage() {
    return (
        <>
        <Route path={`/admin/appmanage/bbsmanage/BbsManage/`} component={BbsManageList} />
        <Route path={`/admin/appmanage/bbsmanage/BbsManage/:bbsId`} component={BbsManageDetail} />
        </>
    );
}
