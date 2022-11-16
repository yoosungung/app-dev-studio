import React from 'react';
import { RadioGroup, Radio } from '@blueprintjs/core';

import { CodeInfoMap } from '../../codedata/CodeDataSource';

export default function Radios(props: {
    name?: string;
    codeData: CodeInfoMap;
    value?: string;
    onChange?: (event) => void;
    changeValue?: (value) => void;
    inline?: boolean;
    readOnly?: boolean;
}) {
    if (props.readOnly) {
        return (
            <>
            {props.codeData.list.map((codeData) => {
                return (
                    <Radio
                        key={codeData.code}
                        value={codeData.code}
                        label={codeData.name}
                        checked={codeData.code === props.value}
                        disabled={codeData.code !== props.value}
                        inline={props.inline}
                        readOnly
                    />
                );
            })}
            </>
        );
    }

    return (
        <RadioGroup
            name={props.name}
            selectedValue={props.value}
            className={`${props.inline ? "inline" : ""}`}
            onChange={(event) => {
                if (props.onChange) {
                    props.onChange(event);
                }

                if (props.changeValue) {
                    props.changeValue(event.currentTarget.value);
                }
            }}
            inline={props.inline}
        >
            {props.codeData.list.map((codeData) => {
                return (
                    <Radio
                        key={codeData.code}
                        value={codeData.code}
                        label={codeData.name}
                    />
                );
            })}
        </RadioGroup>
    );
}
