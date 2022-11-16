import React from 'react';
import Validator from '../validator/Validator';
import ValidatorsMap from '../ValidatorsMap';

export default function ValidationMessage(props: {
    validatorsMap: ValidatorsMap;
    validatorsKey: string;
    inline?: boolean;
}) {
    if (!props.validatorsMap || !props.validatorsKey) {
        return null;
    }

    const validators: Validator[] = props.validatorsMap.getValidators(props.validatorsKey);

    if (!validators) {
        return null;
    }

    for (let i = 0; i < validators.length; i++) {
        const validator: Validator = validators[i];

        if (!validator.isValid) {
            return (
                <div style={{ display: `${props.inline === true ? "inline" : "block"}`, color: "#ff0000" }}>{validator.message}</div>
            );
        }
    }

    return null;
}
