import { trackPromise } from 'react-promise-tracker';

import GridRequest from '../../../../../infrastructure/grid/model/GridRequest';
import CommonUtil from '../../../../common/util/CommonUtil';
import StoreUtil from '../../../../common/util/StoreUtil';
import TitleManageModel from '../model/TitleManageModel';

/**
 * 명칭 관리 Service
 */
export default class TitleManageService {
    private readonly path = "/admin/sysmanage/titlemanage/TitleManage";

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @param callback
     */
    public readList(gridRequest: GridRequest, callback: Function) {
        trackPromise(
            CommonUtil.axios()
            .post(this.path + "/readList", gridRequest)
            .then((response) => {
                callback(response.data.result);
            })
            .catch((error) => {
                CommonUtil.showAxiosError(error);
                callback({ error: true });
            })
        );
    }

    /**
     * 조회
     *
     * @param titleCode
     */
    public async read(titleCode: string) {
        return await trackPromise(
            CommonUtil.axios()
            .get(this.path, {
                params: {
                    titleCode: titleCode
                }
            })
            .then((response) => {
                return response.data;
            })
            .catch((error) => {
                CommonUtil.showAxiosError(error);
            })
        );
    }

    /**
     * 수정
     *
     * @param model
     * @param callback
     */
    public update(model: TitleManageModel, callback: Function) {
        const { messageStore: message, notifyStore } = StoreUtil.useStores();

        notifyStore.confirm({
            contents: message.get("common.message.save.confirm", "저장하시겠습니까?"),
            width: "500px",
            onConfirm: () => {
                trackPromise(
                    CommonUtil.axios()
                    .put(this.path, model)
                    .then((response) => {
                        notifyStore.alert({
                            contents: message.get("common.message.save.success", "저장되었습니다."),
                            width: "500px",
                            onClose: () => {
                                if (callback) {
                                    callback(response);
                                }
                            }
                        });
                    })
                    .catch((error) => {
                        CommonUtil.showAxiosError(error);
                    })
                );
            }
        });
    }

    /**
     * 삭제
     *
     * @param titleCode
     */
    public delete(titleCode: string, callback: Function) {
        const { messageStore: message, notifyStore } = StoreUtil.useStores();

        notifyStore.confirm({
            contents: message.get("common.message.delete.confirm", "정말로 삭제하시겠습니까?"),
            width: "500px",
            onConfirm: () => {
                trackPromise(
                    CommonUtil.axios()
                    .delete(this.path, {
                        data: titleCode
                    })
                    .then((response) => {
                        notifyStore.alert({
                            contents: message.get("common.message.delete.success", "삭제되었습니다."),
                            width: "500px",
                            onClose: () => {
                                callback(response);
                            },
                        });
                    })
                    .catch((error) => {
                        CommonUtil.showAxiosError(error);
                    })
                );
            }
        });
    }
}
