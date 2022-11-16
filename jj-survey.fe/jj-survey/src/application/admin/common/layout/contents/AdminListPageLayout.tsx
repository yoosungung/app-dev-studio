import React from 'react';

import ButtonContainer from '../../../../common/component/button/ButtonContainer';
import { Card, Elevation } from '@blueprintjs/core';

export function ListPageContainer(props: {
    children?: React.ReactNode;
    listExact?: boolean;
}) {
    return (
        <div className={`contents-container-list${props.listExact === false ? " hidden-list" : ""}`}>
            {props.children}
        </div>
    );
}

export function ListPageHeader(props: {
    title: React.ReactNode;
    buttons: React.ReactElement;
}) {
    return (
        <div className="flex-0">
            <div className="page-header">
                <h3>{props.title}</h3>
                <div style={{ flexGrow: 1 }}></div>
                <ButtonContainer>
                    {props.buttons}
                </ButtonContainer>
            </div>
        </div>
    );
}

export function ListSearchForm(props: {
    children?: React.ReactNode;
    readList: Function;
}) {
    return (
        <div className="flex-0" style={{ marginTop: "1px", marginBottom: "10px" }}>
            <Card interactive={false} elevation={Elevation.TWO} style={{ padding: "5px" }} onKeyUp={(event) => {
                if (event.keyCode == 13) {
                    props.readList();
                }
            }}>
                {props.children}
            </Card>
        </div>
    );
}

export function ListGridContainer(props: {
    children?: React.ReactNode;
}) {
    return (
        <div className="flex-1">
            {props.children}
        </div>
    );
}
