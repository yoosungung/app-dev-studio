import React from 'react';
import { Callout, Intent } from '@blueprintjs/core';

import ErrorInfo from './ErrorInfo';
import ButtonContainer from '../component/button/ButtonContainer';

export default function CommonError(props: {
    error: ErrorInfo;
    buttons?: React.ReactElement;
}) {
    return (
        <div className="center-box" style={{ height: "80vh" }}>
            <div style={{ width: "50vw" }}>
                <Callout intent={Intent.DANGER} title={props.error.title || "System Error!"}>
                    <div style={{ marginTop: "10px" }}>
                        {props.error.message}
                    </div>
                </Callout>
                {props.buttons &&
                <ButtonContainer>
                    <div style={{ textAlign: "center", marginTop: "20px" }}>
                        {props.buttons}
                    </div>
                </ButtonContainer>
                }
            </div>
        </div>
    );
}
