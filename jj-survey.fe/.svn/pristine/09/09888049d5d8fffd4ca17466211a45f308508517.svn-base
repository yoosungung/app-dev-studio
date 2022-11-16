import React from 'react';
import useReactRouter from 'use-react-router';
import { Intent, ButtonGroup, Divider } from '@blueprintjs/core';

import CommonUtil from '../../../../common/util/CommonUtil';
import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormPageContainer, FormPageHeader, FormPageFooter } from '../../../common/layout/contents/AdminFormPageLayout';
import { BaseButton, ListButton, LinkButton } from '../../../../common/component/button/Buttons';
import CodeManageModel from '../model/CodeManageModel';
import CodeManageService from '../service/CodeManageService';
import CodeManageDetailFormR from './CodeManageDetailFormR';

export default function CodeManageDetailRD(props: {
    model: CodeManageModel;
    codeDataSource: CodeDataSource;
}) {
    const { history, match } = useReactRouter();
    const { messageStore: message, listStore } = StoreUtil.useStores();
    const [tbSysCodeGroup] = React.useState(props.model.tbSysCodeGroup);
    const [tbSysCodeValueList] = React.useState(props.model.tbSysCodeValueList);
    const codeManageService = new CodeManageService();

    React.useEffect(() => {
    }, []);

    const _delete = () => {
        if (tbSysCodeGroup.codeGroupId) {
            codeManageService.delete(tbSysCodeGroup.codeGroupId, () => {
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
                title={message.get("admin.sysmanage.codemanage.title.detail", "코드 상세")}
                buttons={buttons}
            />
            <CodeManageDetailFormR
                model={{
                    tbSysCodeGroup: tbSysCodeGroup,
                    tbSysCodeValueList: tbSysCodeValueList,
                }}
                codeDataSource={props.codeDataSource}
            />
            <FormPageFooter
                buttons={buttons}
            />
        </FormPageContainer>
    );
}
