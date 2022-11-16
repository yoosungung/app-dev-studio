import CommonUtil from '../../../common/util/CommonUtil';
import Validator from './Validator';

export default class ValidatorForRequiredTitleList implements Validator {
    private _isValid: boolean;

    constructor(list: any[]) {
        this._isValid = false;

        for (let i = 0; i < list.length; i++) {
            const titleLocale = list[i]["titleLocale"];
            const titleCn = list[i]["titleCn"];

            if (titleLocale !== CommonUtil.defaultLocale) {
                continue;
            }

            if (titleCn === undefined || titleCn === null) {
                continue;
            }

            if ((titleCn + "").length > 0) {
                this._isValid = true;
                break;
            }
        }
    }

    public get isValid(): boolean {
        return this._isValid;
    }

    public get message(): string {
        return `필수 항목입니다.(${CommonUtil.defaultLocale})`;
    }
}
