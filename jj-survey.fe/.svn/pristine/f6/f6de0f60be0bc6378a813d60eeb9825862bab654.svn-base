import { observable, action } from 'mobx';
import { Location } from 'history';

import MenuLoadService from '../service/MenuLoadService';
import MenuInfo from '../model/MenuInfo';

export default class MenuStore {
    private _menuLoadService = new MenuLoadService();
    @observable private _loaded = false;
    private _menuList: MenuInfo[] | undefined;
    private _menuMap: { [menuId: string]: { menu: MenuInfo, upperMenu?: MenuInfo } } = {};
    @observable private _currentMenuId: string | undefined;
    @observable private _topMenuId: string | undefined;
    @observable private _selectMenuId: string | undefined;

    public get loaded() {
        return this._loaded;
    }

    public get menuList(): MenuInfo[] | undefined {
        return this._menuList;
    }

    public get menuMap() {
        return this._menuMap;
    }

    public get currentMenuId() {
        return this._currentMenuId;
    }

    public get topMenuId() {
        return this._topMenuId;
    }

    public get selectMenuId() {
        return this._selectMenuId;
    }

    public get firstPathMenu() {
        return this.getFirstPathMenu();
    }

    private getFirstPathMenu = (upperMenu?: MenuInfo): MenuInfo | undefined => {
        if (!this._menuList || this._menuList.length === 0) {
            return;
        }

        if (!upperMenu) {
            return this.getFirstPathMenu(this._menuList[0]);
        }

        for (let i = 0; i < this._menuList.length; i++) {
            if (this._menuList[i].upperMenuId === upperMenu.menuId) {
                if (!this._menuList[i].menuPath) {
                    return this.getFirstPathMenu(this._menuList[i]);
                }

                return this._menuList[i];
            }
        }
    }

    public deleteSelectMenuId() {
        this._selectMenuId = undefined;
    }

    @action public setSelectMenuId(selectMenuId: string | undefined) {
        for (const key in this._menuMap) {
            this._menuMap[key].menu.select = false;
        }

        if (selectMenuId) {
            this._menuMap[selectMenuId].menu.select = true;

            let tmepMenuId = selectMenuId;

            for (let i = 0; i < 5 && this._menuMap[tmepMenuId].upperMenu; i++) {
                const upperMenu = this._menuMap[tmepMenuId].upperMenu!;
                upperMenu.select = true;
                tmepMenuId = upperMenu.menuId;
            }
        }

        this._selectMenuId = selectMenuId;
    }

    @action public readAllList(menuKnd: string): void {
        this._menuLoadService.readAllList(menuKnd, (menuList) => {
            for (let i = 0; i < menuList.length; i++) {
                const menu = menuList[i];

                this._menuMap[menu.menuId] = { menu: menu };

                if (menu.upperMenuId) {
                    this._menuMap[menu.menuId].upperMenu = this._menuMap[menu.upperMenuId].menu;
                }
            }

            this._menuList = menuList;

            this._loaded = true;
        });
    }

    public reset(location: Location) {
        for (const key in this._menuMap) {
            this._menuMap[key].menu.active = false;
            this._menuMap[key].menu.select = false;
        }

        this.deleteSelectMenuId();

        if (!this._menuList) {
            return;
        }

        this._currentMenuId = (() => {
            for (let i = this._menuList.length - 1; i >= 0; i--) {
                const menu = this._menuList[i];

                if (menu.menuPath && location.pathname.substr(0, menu.menuPath.length) === menu.menuPath) {
                    menu.active = true;
                    return menu.menuId;
                }
            }
        })();

        this._topMenuId = (() => {
            if (!this._currentMenuId) {
                return;
            }

            let tmepMenuId = this._currentMenuId;

            for (let i = 0; i < 5 && this._menuMap[tmepMenuId].upperMenu; i++) {
                const upperMenu = this._menuMap[tmepMenuId].upperMenu!;
                upperMenu.active = true;
                tmepMenuId = upperMenu.menuId;
            }

            return tmepMenuId;
        })();
    }
}
