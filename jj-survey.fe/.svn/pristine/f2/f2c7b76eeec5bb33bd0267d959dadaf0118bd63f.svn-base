import { observable, action } from 'mobx';

import ValidatorsMap from '../ValidatorsMap';
import Validator from '../validator/Validator';

export default class ValidStore {
    @observable private _validatorsMap: ValidatorsMap = new ValidatorsMap();

    public reset(): void {
        this._validatorsMap = new ValidatorsMap();
    }

    public get validatorsMap(): ValidatorsMap {
        return this._validatorsMap;
    }

    @action public setValidatorsMap(validatorsMap: ValidatorsMap): void {
        this._validatorsMap = validatorsMap;
    }

    public update(name: string, validators: Validator[]): void {
        this.setValidatorsMap(this._validatorsMap.update(name, validators));
    }
}
