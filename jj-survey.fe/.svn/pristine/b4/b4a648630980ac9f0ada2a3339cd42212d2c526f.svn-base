import React from 'react';
import { MobXProviderContext } from 'mobx-react';

import MessageStore from '../message/store/MessageStore';
import NotifyStore from '../notify/store/NotifyStore';
import MenuStore from '../menu/store/MenuStore';
import UserStore from '../user/store/UserStore';
import ListStore from '../list/store/ListStore';
import ValidStore from '../validation/store/ValidStore';

type AllStores = {
    messageStore: MessageStore;
    notifyStore: NotifyStore;
    menuStore: MenuStore;
    userStore: UserStore;
    listStore: ListStore;
    validStore: ValidStore;
};

export default class StoreUtil {
    private static stores = {} as AllStores;

    public static initStates(stores: AllStores) {
        StoreUtil.stores = stores;
    }

    public static useStores(): AllStores {
        try {
            return StoreUtil.stores;
        } catch(e) {
            return React.useContext(MobXProviderContext);
        }
    }
}
