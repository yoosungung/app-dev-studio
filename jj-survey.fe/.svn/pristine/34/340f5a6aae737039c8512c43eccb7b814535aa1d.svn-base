import React from 'react';
import useReactRouter from 'use-react-router';
import { Intent, ButtonGroup, Divider } from '@blueprintjs/core';

import CommonUtil from '../../../../common/util/CommonUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormPageContainer, FormPageHeader, FormPageFooter } from '../../../common/layout/contents/AdminFormPageLayout';
import { BaseButton, ListButton, LinkButton } from '../../../../common/component/button/Buttons';
import UserManageModel from '../model/UserManageModel';
import UserManageDetailFormR from './UserManageDetailFormR';
import UserManageService from '../service/UserManageService';
import StoreUtil from '../../../../common/util/StoreUtil';

export default function BbsManageDetailRD(props: {
    model: UserManageModel;
    codeDataSource: CodeDataSource;
}) {
    const { history, match } = useReactRouter();
    const { messageStore: message, listStore } = StoreUtil.useStores();
    const [tbComPerson] = React.useState(props.model.tbComPerson);
    const bbsManageService = new UserManageService();

    React.useEffect(() => {
    }, []);

    const _delete = () => {
        if (tbComPerson.personId) {
            bbsManageService.delete(tbComPerson.personId, () => {
                listStore.setReloadList(true);
                CommonUtil.goList(history, match, true);
            });
        }
    };

    const buttons = (
        <ButtonGroup minimal={false} vertical={false}>
            {props.model.editable && <>
            <LinkButton intent={Intent.PRIMARY} icon="edit" text={message.get("common.label.modify", "수정")} to="#U" />
            <BaseButton intent={Intent.DANGER} icon="delete" text={message.get("common.label.delete", "삭제")} onClick={_delete} />
            <Divider />
            </>}
            <ListButton />
        </ButtonGroup>
    );

    return (
        <FormPageContainer>
            <FormPageHeader
                title={message.get("admin.appmanage.bbsmanage.title.detail", "게시판 상세")}
                buttons={buttons}
            />
            <UserManageDetailFormR
                model={{
                    tbComPerson: tbComPerson,
                }}
                codeDataSource={props.codeDataSource}
            />
            <FormPageFooter
                buttons={buttons}
            />
        </FormPageContainer>
    );
}
