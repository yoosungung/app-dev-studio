import './polyfill';

import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'mobx-react';

import './style.scss';

import StoreUtil from './application/common/util/StoreUtil';
import MessageStore from './application/common/message/store/MessageStore';
import NotifyStore from './application/common/notify/store/NotifyStore';
import MenuStore from './application/common/menu/store/MenuStore';
import UserStore from './application/common/user/store/UserStore';
import ListStore from './application/common/list/store/ListStore';
import ValidStore from './application/common/validation/store/ValidStore';
import CommonError from './application/common/error/CommonError';
import ErrorInfo from './application/common/error/ErrorInfo';
import App from './App';

const messageStore = new MessageStore();
const notifyStore = new NotifyStore();
const menuStore = new MenuStore();
const userStore = new UserStore();
const listStore = new ListStore();
const validStore = new ValidStore();

StoreUtil.initStates({
    messageStore,
    notifyStore,
    menuStore,
    userStore,
    listStore,
    validStore
});

messageStore.readAll(() => {
    userStore.readUser(() => {
        ReactDOM.render(
            <Provider
                messageStore={messageStore}
                notifyStore={notifyStore}
                menuStore={menuStore}
                userStore={userStore}
                listStore={listStore}
                validStore={validStore}
            >
                <App />
            </Provider>,
            document.getElementById('root')
        );
    }, (error: ErrorInfo) => {
        ReactDOM.render(
            <CommonError error={error} />,
            document.getElementById('root')
        );
    });
});
