import { trackPromise } from 'react-promise-tracker';

import GridRequest from '../../../../../infrastructure/grid/model/GridRequest';
import CommonUtil from '../../../../common/util/CommonUtil';
import UserManageModel from '../model/UserManageModel';
import StoreUtil from '../../../../common/util/StoreUtil';

/**
 * 사용자 관리 Service
 */
export default class UserManageService {
    private message = StoreUtil.useStores().messageStore;
    private notifyStore = StoreUtil.useStores().notifyStore;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @param callback
     */
    public readList(gridRequest: GridRequest, callback: Function) {
        trackPromise(
            CommonUtil.axios()
            .post("/admin/appmanage/usermanage/UserManage/readList", gridRequest)
            .then((response) => {
                console.log(response.data.result);
                callback(response.data.result);
            })
            .catch((error) => {
                this.notifyStore.error({
                    contents: error.response.data.message,
                    width: "500px",
                });
            })
        );
    }

    /**
     * 조회
     *
     * @param personId
     */
    public async read(personId: string) {
        return await trackPromise(
            CommonUtil.axios()
            .get("/admin/appmanage/usermanage/UserManage/read", {
                params: {
                    personId: personId
                },
                data: null
            })
            .then((response) => {
                return response.data;
            })
            .catch((error) => {
                this.notifyStore.error({
                    contents: error.response.data.message,
                    width: "500px",
                });
            })
        );
    }

    /**
     * 생성
     *
     * @param userManageModel
     * @param callback
     */
    public create(userManageModel: UserManageModel, callback: Function) {
        this.notifyStore.confirm({
            contents: this.message.get("common.message.save.confirm", "저장하시겠습니까?"),
            width: "500px",
            onConfirm: () => {
                trackPromise(
                    CommonUtil.axios()
                    .post("/admin/appmanage/usermanage/UserManage/create", userManageModel)
                    .then((response) => {
                        this.notifyStore.alert({
                            contents: this.message.get("common.message.save.success", "저장되었습니다."),
                            width: "500px",
                            onClose: () => {
                                if (callback) {
                                    callback(response);
                                }
                            }
                        });
                    })
                    .catch((error) => {
                        this.notifyStore.error({
                            contents: error.response.data.message,
                            width: "500px",
                        });
                    })
                );
            }
        });
    }

    /**
     * 수정
     *
     * @param userManageModel
     * @param callback
     */
    public update(userManageModel: UserManageModel, callback: Function) {
        this.notifyStore.confirm({
            contents: this.message.get("common.message.save.confirm", "저장하시겠습니까?"),
            width: "500px",
            onConfirm: () => {
                trackPromise(
                    CommonUtil.axios()
                    .put("/admin/appmanage/usermanage/UserManage/update", userManageModel)
                    .then((response) => {
                        this.notifyStore.alert({
                            contents: this.message.get("common.message.save.success", "저장되었습니다."),
                            width: "500px",
                            onClose: () => {
                                if (callback) {
                                    callback(response);
                                }
                            }
                        });
                    })
                    .catch((error) => {
                        this.notifyStore.error({
                            contents: error.response.data.message,
                            width: "500px",
                        });
                    })
                );
            }
        });
    }

    /**
     * 삭제
     *
     * @param personId
     */
    public delete(personId: string, callback: Function) {
        this.notifyStore.confirm({
            contents: this.message.get("common.message.delete.confirm", "삭제하시겠습니까?"),
            width: "500px",
            onConfirm: () => {
                trackPromise(
                    CommonUtil.axios()
                    .delete("/admin/appmanage/usermanage/UserManage/delete", {
                        data: personId
                    })
                    .then((response) => {
                        this.notifyStore.alert({
                            contents: this.message.get("common.message.delete.success", "삭제되었습니다."),
                            width: "500px",
                            onClose: () => {
                                callback(response);
                            },
                        });
                    })
                    .catch((error) => {
                        this.notifyStore.error({
                            contents: error.response.data.message,
                            width: "500px",
                        });
                    })
                );
            }
        });
    }
}