import React from 'react';
import { Switch } from 'react-router';

import UserRoute from '../common/user/route/UserRoute';

import BbsManage from './appmanage/bbsmanage/view/BbsManage';
import UserManage from './appmanage/usermanage/view/UserManage';

import CodeManage from './sysmanage/codemanage/view/CodeManage';
import TitleManage from './sysmanage/titlemanage/view/TitleManage';
import MenuManage from './sysmanage/menumanage/view/MenuManage';

export default function AdminRouter() {
    return (
        <Switch>
            <UserRoute path="/admin/appmanage/bbsmanage/BbsManage/" component={BbsManage} />
            <UserRoute path="/admin/appmanage/usermanage/UserManage/" component={UserManage} />

            <UserRoute path="/admin/sysmanage/codemanage/CodeManage/" component={CodeManage} />
            <UserRoute path="/admin/sysmanage/titlemanage/TitleManage/" component={TitleManage} />
            <UserRoute path="/admin/sysmanage/menumanage/MenuManage/" component={MenuManage} />
        </Switch>
    );
};
