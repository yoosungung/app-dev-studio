import React from 'react';
import { Switch } from 'react-router';
import { HashRouter, Route } from 'react-router-dom';

import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

import 'normalize.css';
import 'axios-progress-bar/dist/nprogress.css'
import 'bootstrap/dist/css/bootstrap.css'
import '@blueprintjs/core/lib/css/blueprint.css';
import '@blueprintjs/icons/lib/css/blueprint-icons.css';
import '@blueprintjs/table/lib/css/table.css';

import LoadSpinner from './application/common/spinner/LoadSpinner';
import NotifyContainer from './application/common/notify/NotifyContainer';
import UserRoute from './application/common/user/route/UserRoute';
import UserLogin from './application/common/user/view/UserLogin';
import Main from './application/main/Main';
import Admin from './application/admin/Admin';

export default function App() {
    return (
        <HashRouter>
            <Switch>
                <Route path="/" exact component={Main} />
                <Route path="/login/" exact component={UserLogin} />
                <UserRoute path="/admin/" component={Admin} />
            </Switch>

            <NotifyContainer />

            <ToastContainer
                position="top-right"
                autoClose={5000}
                hideProgressBar={false}
                newestOnTop={true}
                closeOnClick={true}
                draggable={true}
                pauseOnHover={true}
                rtl={false}
            />

            <LoadSpinner />
        </HashRouter>
    );
}
