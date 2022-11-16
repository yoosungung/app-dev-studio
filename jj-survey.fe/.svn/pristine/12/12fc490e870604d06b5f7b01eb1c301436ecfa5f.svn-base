import { trackPromise } from 'react-promise-tracker';
import { toast } from 'react-toastify';

import CommonUtil from '../util/CommonUtil';

export default class CodeDataSource {
    private _codeDataMap: {
        [key: string]: CodeInfoMap
    };

    constructor(codeDataMap?: {
        [key: string]: CodeInfoMap
    }) {
        if (!codeDataMap) {
            this._codeDataMap = {};
            return;
        }

        this._codeDataMap = codeDataMap;

        for (const key in this._codeDataMap) {
            const codeInfoMap = this._codeDataMap[key];

            codeInfoMap.data = {};

            codeInfoMap.list.forEach((codeData) => {
                codeInfoMap.data[codeData.code] = codeData;
            });
        }
    }

    public get(key: string): CodeInfoMap {
        if (!this._codeDataMap || !this._codeDataMap[key]) {
            return { list: [], data: {} } as CodeInfoMap;
        }

        return this._codeDataMap[key];
    }

    public getCodeData(key: string, code: string): CodeData {
        if (!this._codeDataMap || !this._codeDataMap[key]) {
            return {} as CodeData;
        }

        if (!this._codeDataMap[key].data || !this._codeDataMap[key].data[code]) {
            return {} as CodeData;
        }

        return this._codeDataMap[key].data[code];
    }

    public getCodeName(key: string, code?: string) {
        if (!key || !code) {
            return;
        }

        const codeData = this.getCodeData(key, code);

        if (codeData) {
            return codeData.name || code;
        }
    }
}

export interface CodeInfoMap {
    list: CodeData[];
    data: {
        [code: string]: CodeData;
    };
}

export interface CodeData {
    code: any;
    name: any;
    [key: string]: any;
}

export class CodeDataUtil {
    public static async getCodeDataSource(requestCodeList: {
        key: string
    }[]) {
        return await trackPromise(
            CommonUtil.axios()
            .post("/common/codedata/CodeData", requestCodeList)
            .then((response): CodeDataSource => {
                return new CodeDataSource(response.data);
            })
            .catch((error): CodeDataSource => {
                toast.error(error.response.data.message, {
                    position: toast.POSITION.TOP_CENTER,
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                });

                return new CodeDataSource();
            })
        );
    }
}
