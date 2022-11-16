import Validator from './Validator';

export default class ValidatorForCellularPhoneNumber implements Validator {
    private _isValid: boolean;

    constructor(value: any) {
        this._isValid = (() => {
            if (!value || (value + "").length === 0) {
                return true;
            }

            var regExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;

            return regExp.test(value);
        })();
    }

    public get isValid(): boolean {
        return this._isValid;
    }

    public get message(): string {
        return '휴대폰번호가 올바르지 않습니다. 하이픈(-)을 포함한 숫자만 입력하세요.';
    }
}
