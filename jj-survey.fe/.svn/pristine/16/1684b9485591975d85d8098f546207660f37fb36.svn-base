import Validator from './Validator';

export default class ValidatorForEmailAddress implements Validator {
    private _isValid: boolean;

    constructor(value: any) {
        this._isValid = (() => {
            if (!value || (value + "").length === 0) {
                return true;
            }

            var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

            return regExp.test(value);
        })();
    }

    public get isValid(): boolean {
        return this._isValid;
    }

    public get message(): string {
        return '이메일주소 형식이 올바르지 않습니다.';
    }
}
