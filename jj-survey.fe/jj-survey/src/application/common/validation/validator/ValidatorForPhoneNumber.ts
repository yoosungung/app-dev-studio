import Validator from './Validator';

export default class ValidatorForPhoneNumber implements Validator {
    private _isValid: boolean;

    constructor(value: any) {
        this._isValid = (() => {
            if (!value || (value + "").length === 0) {
                return true;
            }

            var regExp = /^\d{2,3}-\d{3,4}-\d{4}$/;

            return regExp.test(value);
        })();
    }

    public get isValid(): boolean {
        return this._isValid;
    }

    public get message(): string {
        return '전화번호가 올바르지 않습니다. 하이픈(-)을 포함한 숫자만 입력하세요.';
    }
}
