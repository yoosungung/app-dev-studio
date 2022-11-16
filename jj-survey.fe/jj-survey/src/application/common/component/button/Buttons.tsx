import React from 'react';
import { Link } from 'react-router-dom';
import { LocationDescriptor, LocationState } from 'history';
import useReactRouter from 'use-react-router';
import { Button, IButtonProps, Intent } from '@blueprintjs/core';

import CommonUtil from '../../util/CommonUtil';
import StoreUtil from '../../util/StoreUtil';

interface BaseButtonProps extends IButtonProps {
    children?: React.ReactNode;
}

export function BaseButton(props: BaseButtonProps) {
    return (
        <span className="button-wrap">
            <Button {...props}>{props.children}</Button>
        </span>
    );
}

interface LinkButtonProps extends BaseButtonProps {
    to: LocationDescriptor<LocationState>;
}

export function LinkButton(props: LinkButtonProps) {
    return (
        <span className="button-wrap">
            <Link to={props.to}>
                <Button {...props}>{props.children}</Button>
            </Link>
        </span>
    );
}

export function RedirectButton(props: LinkButtonProps) {
    return (
        <span className="button-wrap">
            <Link to={props.to} replace>
                <Button {...props}>{props.children}</Button>
            </Link>
        </span>
    );
}

export function ListButton(props: BaseButtonProps) {
    const { match } = useReactRouter();

    const linkTo = CommonUtil.getListPath(match);
    const { messageStore: message } = StoreUtil.useStores();

    return (
        <span className="button-wrap">
            <Link to={linkTo}>
                <Button {...props} intent={Intent.SUCCESS} icon="list">{message.get("common.label.list", "목록")}</Button>
            </Link>
        </span>
    );
}
