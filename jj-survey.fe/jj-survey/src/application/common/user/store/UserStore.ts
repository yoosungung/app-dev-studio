import { observable, action } from 'mobx';
import Axios from 'axios';

import CommonUtil from '../../util/CommonUtil';
import ErrorInfo from '../../error/ErrorInfo';
import LoginUser from '../model/LoginUser';

export default class UserStore {
    @observable private _authenticated: boolean = false;
    private _loginUser: LoginUser = {} as LoginUser;
    private _userLocale: string = "";

    public get authenticated(): boolean {
        return this._authenticated;
    }

    @action public setAuthenticated(authenticated: boolean): void {
        this._authenticated = authenticated;
    }

    public get loginUser(): LoginUser {
        return this._loginUser;
    }

    public get userLocale(): string {
        return this._userLocale;
    }

    public readUser(successCallback: () => void, errorCallback: (error: ErrorInfo) => void): void {
        CommonUtil.axios()
        .get("/common/user/readUser.do")
        .then((response) => {
            if (!response.data || !response.data.loginUser) {
                this.setAuthenticated(false);
            } else {
                this._loginUser = response.data.loginUser;
                this._userLocale = response.data.userLocale;

                this.setAuthenticated(true);
            }

            if (response.data.csrfToken) {
                Axios.defaults.headers.common[response.data.csrfToken.headerName] = response.data.csrfToken.token;
            }

            if (successCallback != null) {
                successCallback();
            }
        })
        .catch((error) => {
            const errorInfo = new ErrorInfo();

            if (error.response === undefined) {
                errorInfo.message = error;
            } else if (typeof(error.response.data) === "object") {
                errorInfo.message = error.response.data.message;
            } else {
                errorInfo.message = error.response.data;
            }

            if (errorCallback != null) {
                errorCallback(errorInfo);
            }
        });
    }
}
