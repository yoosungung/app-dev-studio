import { observable, action } from 'mobx';

export default class ListStore {
    private _loadStarted: boolean = false;
    @observable private _reloadList: boolean = false;

    public reset() {
        this._loadStarted = false;
        this._reloadList = false;
    }

    public loadStart() {
        this._loadStarted = true;
        this._reloadList = false;
    }

    public get loadStarted(): boolean {
        return this._loadStarted;
    }

    public set loadStarted(loadStarted: boolean) {
        this._loadStarted = loadStarted;
    }

    public get reloadList(): boolean {
        return this._reloadList;
    }

    @action public setReloadList(reloadList: boolean): void {
        this._reloadList = reloadList;
    }
}
