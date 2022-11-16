import React from 'react';
import { Intent, ButtonGroup, Divider } from '@blueprintjs/core';

import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormPageContainer, FormPageHeader, FormPageFooter } from '../../../common/layout/contents/AdminFormPageLayout';
import { ListButton, LinkButton } from '../../../../common/component/button/Buttons';
import TitleManageModel from '../model/TitleManageModel';
import TitleManageDetailFormR from './TitleManageDetailFormR';

export default function TitleManageDetailR(props: {
    model: TitleManageModel;
    codeDataSource: CodeDataSource;
}) {
    const { messageStore: message, listStore } = StoreUtil.useStores();
    const [tbSysTitleList] = React.useState(props.model.tbSysTitleList);

    React.useEffect(() => {
    }, []);

    const buttons = (
        <ButtonGroup minimal={false} vertical={false}>
            {props.model.editable && <>
            <LinkButton intent={Intent.PRIMARY} icon="edit" text={message.get("common.label.modify", "수정")} to="#U" />
            <Divider />
            </>}
            <ListButton />
        </ButtonGroup>
    );

    return (
        <FormPageContainer>
            <FormPageHeader
                title={message.get("admin.sysmanage.titlemanage.title.detail", "명칭 상세")}
                buttons={buttons}
            />
            <TitleManageDetailFormR
                model={{
                    titleCode: props.model.titleCode,
                    tbSysTitleList: tbSysTitleList,
                }}
                codeDataSource={props.codeDataSource}
            />
            <FormPageFooter
                buttons={buttons}
            />
        </FormPageContainer>
    );
}
