import Validator from './Validator';

export default class ValidatorForRequired implements Validator {
    private _isValid: boolean;

    constructor(value: any) {
        this._isValid = (value && (value + "").length > 0);
    }

    public get isValid(): boolean {
        return this._isValid;
    }

    public get message(): string {
        return '필수 항목입니다.';
    }
}
