import React from 'react';
import ReactMultiSelectCheckboxes from 'react-multiselect-checkboxes';

import StoreUtil from '../../util/StoreUtil';
import { CodeInfoMap } from '../../../common/codedata/CodeDataSource';

interface MultiSelectValueList {
    value: any;
    label: any;
}

export default function MultiSelect(props: {
    codeData: CodeInfoMap;
    value?: string;
    changeValue?: (value: string) => void;
    defaultLabel?: string;
}) {
    const { messageStore: message } = StoreUtil.useStores();
    const [valueList, setValueList] = React.useState<MultiSelectValueList[]>([]);

    React.useEffect(() => {
        if (props.value) {
            valueList.length = 0;

            props.value.split(",").forEach((val) => {
                valueList.push({ value: val, label: props.codeData.data[val].name });
            });
        }
    }, []);

    return (
        <div className="multiselect-checkboxes">
            <ReactMultiSelectCheckboxes
                options={props.codeData.list.map((codeData) => {
                    return { value: codeData.code, label: codeData.name };
                })}
                value={valueList}
                placeholderButtonLabel={`${props.defaultLabel || message.get("common.codeData.firstName.sel", "(선택)")}`}
                getDropdownButtonLabel={({placeholderButtonLabel, value}) => {
                    if (!value || value.length === 0) {
                        return placeholderButtonLabel;
                    }

                    if (value.length === 1) {
                        return value[0].label;
                    }

                    return `${value[0].label} 외 ${value.length - 1}`;
                }}
                onChange={(valueList) => {
                    const values = valueList.map((val) => {
                        return val.value;
                    });

                    const valueListOrdered: MultiSelectValueList[] = [];

                    props.codeData.list.forEach((codeData) => {
                        if (values.indexOf(codeData.code) !== -1) {
                            valueListOrdered.push({ value: codeData.code, label: codeData.name });
                        }
                    });

                    setValueList(valueListOrdered);

                    if (props.changeValue) {
                        props.changeValue(valueListOrdered.map((val) => {
                            return val.value;
                        }).join(","));
                    }
                }}
            />
        </div>
    );
}
