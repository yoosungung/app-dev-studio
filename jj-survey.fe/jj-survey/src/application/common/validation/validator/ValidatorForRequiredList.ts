import { DataJobTypes } from '../../../../infrastructure/framework/core/support/collection/BaseEntity';
import Validator from './Validator';

export default class ValidatorForRequiredList implements Validator {
    private _isValid: boolean;

    constructor(list: any[], key?: string) {
        this._isValid = false;

        for (let i = 0; i < list.length; i++) {
            let value;

            if (key) {
                value = list[i][key];
            } else {
                value = list[i];
            }

            if (value === undefined || value === null) {
                continue;
            }

            if (typeof(value) === "object") {
                if (value._JOB_TYPE !== DataJobTypes.DELETE) {
                    this._isValid = true;
                    break;
                }
                continue;
            }

            if ((value + "").length > 0) {
                this._isValid = true;
                break;
            }
        }
    }

    public get isValid(): boolean {
        return this._isValid;
    }

    public get message(): string {
        return '필수 항목입니다.';
    }
}
