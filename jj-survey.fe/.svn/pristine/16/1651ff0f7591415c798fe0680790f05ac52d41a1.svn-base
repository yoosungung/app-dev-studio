import React from 'react';
import { Intent, ButtonGroup } from '@blueprintjs/core';

import CommonError from './CommonError';
import { ListButton, RedirectButton } from '../component/button/Buttons';

export default function ViewError(props: {
    buttons?: {
        home?: boolean;
        backLink?: string;
        list?: boolean;
    };
}) {
    return (
        <CommonError
            error={{
                message: "잘못된 요청입니다."
            }}
            buttons={
                <ButtonGroup minimal={false} vertical={false}>
                    {(props.buttons && props.buttons.home) && <RedirectButton intent={Intent.NONE} icon="home" text="홈으로" to="/" />}
                    {(props.buttons && props.buttons.backLink) && <RedirectButton intent={Intent.NONE} icon="reset" text="뒤로" to={`${props.buttons.backLink}`} />}
                    {(props.buttons && props.buttons.list) && <ListButton />}
                </ButtonGroup>
            }
        />
    );
}
