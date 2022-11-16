import React from 'react';
import useReactRouter from 'use-react-router';
import { Intent, ButtonGroup, Divider } from '@blueprintjs/core';

import CommonUtil from '../../../../common/util/CommonUtil';
import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormPageContainer, FormPageHeader, FormPageFooter } from '../../../common/layout/contents/AdminFormPageLayout';
import { BaseButton, ListButton, LinkButton } from '../../../../common/component/button/Buttons';
import BbsManageModel from '../model/BbsManageModel';
import BbsManageDetailFormR from './BbsManageDetailFormR';
import BbsManageService from '../service/BbsManageService';

export default function BbsManageDetailRD(props: {
    model: BbsManageModel;
    codeDataSource: CodeDataSource;
}) {
    const { history, match } = useReactRouter();
    const { messageStore: message, listStore } = StoreUtil.useStores();
    const [tbComBbs] = React.useState(props.model.tbComBbs);
    const [tbComBbsAuthorToAuthorList] = React.useState(props.model.tbComBbsAuthorToAuthorList);
    const bbsManageService = new BbsManageService();

    React.useEffect(() => {
    }, []);

    const _delete = () => {
        if (tbComBbs.bbsId) {
            bbsManageService.delete(tbComBbs.bbsId, () => {
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
            <BbsManageDetailFormR
                model={{
                    tbComBbs: tbComBbs,
                    tbComBbsAuthorToAuthorList: tbComBbsAuthorToAuthorList,
                }}
                codeDataSource={props.codeDataSource}
            />
            <FormPageFooter
                buttons={buttons}
            />
        </FormPageContainer>
    );
}
