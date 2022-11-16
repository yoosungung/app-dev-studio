import React from 'react';
import { observer } from 'mobx-react';

import StoreUtil from '../../util/StoreUtil';
import UserLogin from '../view/UserLogin';

export default observer(function UserRoute({ component: Component, ...props }) {
    const { userStore } = StoreUtil.useStores();

    if (userStore.authenticated) {
        return (
            <Component {...props} />
        );
    }

    return (
        <UserLogin />
    );
});
