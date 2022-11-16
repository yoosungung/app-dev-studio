import React from 'react';

import ButtonContainer from '../../../../common/component/button/ButtonContainer';
import { Card, Elevation, Divider } from '@blueprintjs/core';

export function FormPageContainer(props: {
    children?: React.ReactNode;
}) {
    return (
        <div className="contents-container-form">
            {props.children}
        </div>
    );
}

export function FormPageHeader(props: {
    title: string;
    buttons: React.ReactElement;
}) {
    return (
        <div className="flex-0">
            <div className="page-header" style={{ padding: "10px 10px 0px 10px" }}>
                <h3>{props.title}</h3>
                <div style={{ flexGrow: 1 }}></div>
                <ButtonContainer>
                    {props.buttons}
                </ButtonContainer>
            </div>
        </div>
    );
}

export function FormContentsGroup(props: {
    children?: React.ReactNode;
}) {
    return (
        <div className="flex-1" style={{ overflow: "auto", padding: "0px 10px 7px 10px" }}>
            <div style={{ marginTop: "-9px" }}>
                {props.children}
            </div>
        </div>
    );
}

export function FormContents(props: {
    children?: React.ReactNode;
    title?: string;
}) {
    let title;

    if (props.title) {
        title = (
            <>
            <h4>{props.title}</h4>
            <Divider />
            </>
        );
    }

    return (
        <div className="page-middle">
            <Card interactive={false} elevation={Elevation.TWO} style={{ padding: "5px" }}>
                {title}
                {props.children}
            </Card>
        </div>
    );
}

export function FormPageFooter(props: {
    buttons: React.ReactElement;
}) {
    return (
        <div className="flex-0 page-footer" style={{ padding: "0px 10px 10px 10px" }}>
            <ButtonContainer>
                {props.buttons}
            </ButtonContainer>
        </div>
    );
}
