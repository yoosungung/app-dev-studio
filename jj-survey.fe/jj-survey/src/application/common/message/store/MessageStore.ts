import { toast } from 'react-toastify';

import CommonUtil from '../../util/CommonUtil';

export default class MessageStore {
    private readonly _cacheDuration: number = 60000;
    private _messageMap: MessageMap = {};

    public readAll(callback?: Function): void {
        CommonUtil.axios({ progressBar: false })
        .get("/common/message/Message/readAllMessage.do")
        .then((response) => {
            this._messageMap = response.data;

            if (callback) {
                callback();
            }

            window.setTimeout(() => {
                this.readAll();
            }, this._cacheDuration);
        })
        .catch((error) => {
            toast.error(error.response.data.message, {
                position: toast.POSITION.TOP_CENTER,
                autoClose: 5000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
            });
        });
    }

    public get(code: string, text?: string, args?: any[]): string {
        let message;

        if (this._messageMap) {
            message = this._messageMap[code];
        }

        if (!message) {
            message = "#" + (text || code);
        }

        if (args) {
            args.forEach((arg) => {
                message = message.replace("{}", arg);
            });
        }

        return message;
    }
}

interface MessageMap {
    [code: string]: string
}
