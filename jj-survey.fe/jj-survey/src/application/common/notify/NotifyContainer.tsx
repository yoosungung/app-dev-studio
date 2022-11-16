import React from 'react';
import { observer } from 'mobx-react';
import { Alert, Intent } from "@blueprintjs/core";

import StoreUtil from '../util/StoreUtil';
import { NotifyType } from './store/NotifyStore';

export default observer(function NotifyContainer() {
    const { messageStore: message, notifyStore } = StoreUtil.useStores();

    return (
        <>
        {notifyStore.notifyList.map((notifyInfo) => {
            if (notifyInfo.type === NotifyType.ALERT) {
                return (
                    <Alert
                        key={notifyInfo.key}
                        icon={notifyInfo.icon || "info-sign"}
                        intent={notifyInfo.intent || Intent.NONE}
                        isOpen={true}
                        style={{ width: notifyInfo.width, maxWidth: notifyInfo.width }}
                        canEscapeKeyCancel={notifyInfo.canEscapeKeyCancel || true}
                        confirmButtonText={notifyInfo.confirmButtonText || message.get("common.label.confirm", "확인")}
                        onClose={(...args) => {
                            notifyStore.close(notifyInfo.key);

                            if (notifyInfo.onClose != null) {
                                notifyInfo.onClose(...args);
                            }
                        }}
                    >
                        <p>{notifyInfo.contents}</p>
                    </Alert>
                );
            }

            if (notifyInfo.type === NotifyType.CONFIRM) {
                return (
                    <Alert
                        key={notifyInfo.key}
                        icon={notifyInfo.icon || "info-sign"}
                        intent={notifyInfo.intent || Intent.NONE}
                        isOpen={true}
                        style={{ width: notifyInfo.width, maxWidth: notifyInfo.width }}
                        canEscapeKeyCancel={notifyInfo.canEscapeKeyCancel || true}
                        confirmButtonText={notifyInfo.confirmButtonText || message.get("common.label.confirm", "확인")}
                        cancelButtonText={notifyInfo.cancelButtonText || message.get("common.label.cancel", "취소")}
                        onConfirm={(...args) => {
                            notifyStore.close(notifyInfo.key);

                            if (notifyInfo.onConfirm != null) {
                                notifyInfo.onConfirm(...args);
                            }
                        }}
                        onClose={(...args) => {
                            notifyStore.close(notifyInfo.key);

                            if (notifyInfo.onClose != null) {
                                notifyInfo.onClose(...args);
                            }
                        }}
                    >
                        <p>{notifyInfo.contents}</p>
                    </Alert>
                );
            }

            return null;
        })}
        </>
    );
});
