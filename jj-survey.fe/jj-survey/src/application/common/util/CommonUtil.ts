import { match } from 'react-router';
import { History } from 'history';
import Axios, { AxiosRequestConfig } from 'axios';

import { loadProgressBar } from 'axios-progress-bar'

import StoreUtil from './StoreUtil';
import TextUtil from './TextUtil';

(() => {
    Axios.defaults.headers.common["X-Requested-With"] = "XMLHttpRequest";
})();

export default class CommonUtil {
    public static get contextPath(): string {
        return document.getElementById('root')!.getAttribute("context-path") + "";
    }

    public static get defaultLocale(): string {
        return document.getElementById('root')!.getAttribute("default-locale") + "";
    }

    public static axios(option?: {
        original?: boolean;
        progressBar?: boolean;
    }) {
        const _axios = Axios.create({
            baseURL: CommonUtil.contextPath,
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (option === undefined || option.progressBar === undefined || option.progressBar) {
            loadProgressBar({ showSpinner: false }, _axios);
        }

        if (option && option.original) {
            return _axios;
        }

        return {
            get: (url: string, config?: AxiosRequestConfig) => {
                if (config === undefined) {
                    config = {};
                }

                if (config.data === undefined) {
                    config.data = null;
                }

                return _axios.get(url, config);
            },
            post: (url: string, data?: any, config?: AxiosRequestConfig) => {
                return _axios.post(url, data, config);
            },
            put: (url: string, data?: any, config?: AxiosRequestConfig) => {
                if (config === undefined) {
                    config = {};
                }

                if (config.headers === undefined) {
                    config.headers = {};
                }

                config.headers["X-HTTP-Method-Override"] = "PUT";

                return _axios.post(url, data, config);
            },
            delete: (url: string, config?: AxiosRequestConfig) => {
                if (config === undefined) {
                    config = {};
                }

                if (config.headers === undefined) {
                    config.headers = {};
                }

                config.headers["X-HTTP-Method-Override"] = "DELETE";

                return _axios.post(url, config.data, config);
            }
        };
    }

    public static showAxiosError(error) {
        const { messageStore, notifyStore } = StoreUtil.useStores();

        let invalidSession = false;
        let invalidToken = false;
        let message: string;

        if (!error.response) {
            message = error;
        } else {
            invalidSession = (error.response.headers["x-invalid-session"] === "true");
            invalidToken = (error.response.headers["x-invalid-token"] === "true");

            if (invalidSession) {
                message = messageStore.get("common.user.message.sessionTimeout", "오랫동안 사용하지 않아 로그아웃 되었습니다.");
            } else if (invalidToken) {
                message = messageStore.get("common.message.invalidToken", "유효하지 않은 접근정보입니다.");
            } else {
                message = error.response.data.message;
            }
        }

        notifyStore.error({
            contents: message,
            width: "600px",
            onClose: () => {
                if (invalidSession || invalidToken) {
                    window.location.reload();
                }
            }
        });
    }

    public static getListPath(match: match<{}>) {
        return match.path.substr(0, match.path.lastIndexOf("/") + 1);
    }

    public static goList(history: History<any>, match: match<{}>, replace: boolean) {
        const path = this.getListPath(match);

        if (replace) {
            history.replace(path);
        } else {
            history.push(path);
        }
    }

    public static getElementValue(element, maxByte?: number) {
        let value;

        if (element.type === "checkbox") {
            value = (element.checked ? "1" : "0");
        } else {
            value = TextUtil.getTruncatedValue(element.value, maxByte);
        }

        return value;
    }

    public static getUid() {
        if (window["UID_SEQ"] !== undefined) {
            window["UID_SEQ"] = window["UID_SEQ"] + 1;
        } else {
            window["UID_SEQ"] = 0;
        }

        return "ID" + window["UID_SEQ"];
    }
}
