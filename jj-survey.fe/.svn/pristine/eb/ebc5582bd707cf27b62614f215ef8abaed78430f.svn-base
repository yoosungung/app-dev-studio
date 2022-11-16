import { observable, action } from 'mobx';
import { IconName, MaybeElement, Intent } from '@blueprintjs/core';

export default class NotifyStore {
    @observable private _notifyList: NotifyInfo[] = [];
    private _notifyKey: number = 0;

    public get notifyList(): NotifyInfo[] {
        return this._notifyList;
    }

    @action public alert(notifyInfo: NotifyInfo): void {
        notifyInfo.key = ++this._notifyKey;
        notifyInfo.type = NotifyType.ALERT;
        notifyInfo.icon = "info-sign";
        this._notifyList = this._notifyList.concat(notifyInfo);
    }

    @action public error = (notifyInfo: NotifyInfo): void => {
        notifyInfo.key = ++this._notifyKey;
        notifyInfo.type = NotifyType.ALERT;
        notifyInfo.icon = "error";
        notifyInfo.intent = Intent.DANGER;
        this._notifyList = this._notifyList.concat(notifyInfo);
    }

    @action public confirm(notifyInfo: NotifyInfo): void {
        notifyInfo.key = ++this._notifyKey;
        notifyInfo.type = NotifyType.CONFIRM;
        notifyInfo.icon = "confirm",
        this._notifyList = this._notifyList.concat(notifyInfo);
    }

    @action public close(notifyKey?: number): void {
        this._notifyList = this._notifyList.filter(
            (notifyInfo) => {
                return notifyInfo.key !== notifyKey;
            }
        );
    }
}

export enum NotifyType {
    ALERT,
    CONFIRM
}

interface NotifyInfo /* extends IAlertProps */ {
    key?: number;
    type?: NotifyType;
    contents: string;
    width?: string;
    canEscapeKeyCancel?: boolean;
    cancelButtonText?: string;
    confirmButtonText?: string;
    icon?: IconName | MaybeElement;
    intent?: Intent;
    isOpen?: boolean;
    style?: React.CSSProperties;
    transitionDuration?: number;
    portalContainer?: HTMLElement;
    onCancel?(evt?: React.SyntheticEvent<HTMLElement>): void;
    onConfirm?(evt?: React.SyntheticEvent<HTMLElement>): void;
    onClose?(confirmed: boolean, evt?: React.SyntheticEvent<HTMLElement>): void;
}
