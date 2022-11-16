import Validator from './validator/Validator';

export default class ValidatorsMap {
    private _validatorsMap: { [key: string]: Validator[] } = {};

    public add(key: string, validator: Validator): void {
        if (!this._validatorsMap[key]) {
            this._validatorsMap[key] = [];
        }

        this._validatorsMap[key].push(validator);
    }

    public set(key: string, validator: Validator[]): void {
        this._validatorsMap[key] = validator;
    }

    public getValidators(key: string): Validator[] {
        return this._validatorsMap[key];
    }

    public get invalidCount(): number {
        let invalidCount: number = 0;

        Object.keys(this._validatorsMap).forEach((key: string) => {
            const validators: Validator[] = this._validatorsMap[key];

            validators.forEach((validator: Validator) => {
                if (!validator.isValid) {
                    invalidCount++;
                }
            });
        });

        return invalidCount;
    }

    public update(key: string, validators: Validator[]): ValidatorsMap {
        const validatorsMap = new ValidatorsMap();

        Object.keys(this._validatorsMap).forEach((key2: string) => {
            if (key !== key2) {
                validatorsMap.set(key2, this._validatorsMap[key2]);
            }
        });

        validatorsMap.set(key, validators);

        return validatorsMap;
    }
}
