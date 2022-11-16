import React from 'react';

import StoreUtil from '../../util/StoreUtil';
import { CodeInfoMap, CodeData } from '../../../common/codedata/CodeDataSource';

export default function Select(props: {
    name?: string;
    codeData?: CodeInfoMap;
    options?: CodeData[];
    firstName?: string;
    value?: string;
    onChange?: (event) => void;
    changeValue?: (value) => void;
    fill?: boolean;
}) {
    const { messageStore: message } = StoreUtil.useStores();

    return (
        <div className={`bp3-select${props.fill ? " bp3-fill" : ""}`}>
            <select
                name={props.name}
                value={props.value || ""}
                onChange={(event) => {
                    if (props.onChange) {
                        props.onChange(event);
                    }

                    if (props.changeValue) {
                        props.changeValue(event.target.value);
                    }
                }}
            >
                {props.firstName && <option value="">{message.get(`common.codeData.firstName.${props.firstName}`)}</option>}
                {props.codeData && props.codeData.list.map((codeData) => {
                    return (
                        <option key={codeData.code} value={codeData.code}>{codeData.name}</option>
                    );
                })}
                {props.options && props.options.map((codeData) => {
                    return (
                        <option key={codeData.code} value={codeData.code}>{codeData.name}</option>
                    );
                })}
            </select>
        </div>
    );
}
