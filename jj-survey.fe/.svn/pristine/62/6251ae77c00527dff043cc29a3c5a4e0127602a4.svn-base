import { trackPromise } from 'react-promise-tracker';
import { ITreeNode } from '@blueprintjs/core';

import GridRequest from '../../../../../infrastructure/grid/model/GridRequest';
import CommonUtil from '../../../../common/util/CommonUtil';
import StoreUtil from '../../../../common/util/StoreUtil';
import MenuManageModel from '../model/MenuManageModel';

interface MenuInfo {
    menuId: string;
    upperMenuId: string;
    menuLevel: number;
    menuNm: string;
    menuPath: string;
    menuOrdr: number;
    nodeTypeNm: string;
    treeNode: ITreeNode;
};

/**
 * 메뉴 관리 Service
 */
export default class MenuManageService {
    private readonly path = "/admin/sysmanage/menumanage/MenuManage";

    /**
     * 트리 조회
     *
     * @param searchData
     * @param callback
     */
    public readTree(searchData, callback: Function) {
        trackPromise(
            CommonUtil.axios()
            .post(this.path + "/readTree", searchData.menuKnd)
            .then((response) => {
                callback(this.getTreeNodeList(searchData.menuKndNm, response.data));
            })
            .catch((error) => {
                CommonUtil.showAxiosError(error);
            })
        );
    }

    private getTreeNodeList(menuKndNm: string, menuList: MenuInfo[]): ITreeNode[] {
        const treeNodeList: ITreeNode[] = [];

        const getMenuNode = (menu: MenuInfo): ITreeNode => {
            menu.treeNode = {
                id: menu.menuId,
                icon: (menu.nodeTypeNm === "FOLDER" ? "folder-close" : "document"),
                label: menu.menuNm,
                isExpanded: true,
            };

            return menu.treeNode;
        };

        const getUpperNode = (upperMenuId: string): ITreeNode | undefined => {
            for (let i = 0; i < menuList.length; i++) {
                const menu = menuList[i];

                if (menu.menuId === upperMenuId) {
                    return menu.treeNode;
                }
            }
        }

        menuList.splice(0, 0, {
            menuId: "1",
            upperMenuId: "0",
            menuNm: menuKndNm,
            nodeTypeNm: "FOLDER",
        } as MenuInfo);

        for (let i = 0; i < menuList.length; i++) {
            const menu = menuList[i];

            if (menu.upperMenuId === "0") {
                treeNodeList.push(getMenuNode(menu));
            } else {
                const upperNode = getUpperNode(menu.upperMenuId || "1");

                if (!upperNode) {
                    continue;
                }

                if (!upperNode.childNodes) {
                    upperNode.childNodes = [];
                }

                upperNode.childNodes.push(getMenuNode(menu));
            }
        }

        return treeNodeList;
    }

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
     * @param menuId
     */
    public async read(menuId: string | undefined | null) {
        return await trackPromise(
            CommonUtil.axios()
            .get(this.path, {
                params: {
                    menuId: menuId
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
     * 생성
     *
     * @param model
     * @param callback
     */
    public create(model: MenuManageModel, callback: Function) {
        const { messageStore: message, notifyStore } = StoreUtil.useStores();

        notifyStore.confirm({
            contents: message.get("common.message.save.confirm", "저장하시겠습니까?"),
            width: "500px",
            onConfirm: () => {
                trackPromise(
                    CommonUtil.axios()
                    .post(this.path, model)
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
     * 수정
     *
     * @param model
     * @param callback
     */
    public update(model: MenuManageModel, callback: Function) {
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
                        notifyStore.error({
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
     * @param menuId
     */
    public delete(menuId: string, callback: Function) {
        const { messageStore: message, notifyStore } = StoreUtil.useStores();

        notifyStore.confirm({
            contents: message.get("common.message.delete.confirm", "정말로 삭제하시겠습니까?"),
            width: "500px",
            onConfirm: () => {
                trackPromise(
                    CommonUtil.axios()
                    .delete(this.path, {
                        data: menuId
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
                        notifyStore.error({
                            contents: error.response.data.message,
                            width: "500px",
                        });
                    })
                );
            }
        });
    }
}
