import React from 'react';

import TextUtil from '../../../common/util/TextUtil';

export default function TextByteIndicator(props: {
    value: string | undefined;
    maxByte: number;
}) {
    const totByte: number = TextUtil.getTotalByte(props.value);

    return (
        <div style={{ textAlign: "right" }}>
            <span style={{ color: totByte > props.maxByte ? "red" : "blue" }}>{totByte} byte</span> of {props.maxByte} byte input.
        </div>
    );
}
