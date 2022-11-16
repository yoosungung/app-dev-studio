import React from 'react';
import useReactRouter from 'use-react-router';
import { AxiosResponse } from 'axios';
import { Intent, ButtonGroup, Divider } from '@blueprintjs/core';

import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormPageContainer, FormPageHeader, FormPageFooter } from '../../../common/layout/contents/AdminFormPageLayout';
import { BaseButton, ListButton } from '../../../../common/component/button/Buttons';
import BbsManageModel from '../model/BbsManageModel';
import BbsManageDetailFormCU from './BbsManageDetailFormCU';
import BbsManageService from '../service/BbsManageService';

export default function BbsManageDetailC(props: {
    model: BbsManageModel;
    codeDataSource: CodeDataSource;
}) {
    const { history } = useReactRouter();
    const { messageStore: message, listStore, validStore } = StoreUtil.useStores();
    const [tbComBbs, setTbComBbs] = React.useState(props.model.tbComBbs);
    const [tbComBbsAuthorToAuthorList, setTbComBbsAuthorToAuthorList] = React.useState(props.model.tbComBbsAuthorToAuthorList);
    const bbsManageService = new BbsManageService();

    React.useEffect(() => {
    }, []);

    const save = () => {
        const validatorsMap = BbsManageModel.getValidatorsMap(tbComBbs);

        if (validatorsMap.invalidCount > 0) {
            validStore.setValidatorsMap(validatorsMap);
            return;
        }

        bbsManageService.create({
            tbComBbs: tbComBbs,
            tbComBbsAuthorToAuthorList: tbComBbsAuthorToAuthorList
        }, (response: AxiosResponse<any>) => {
            listStore.setReloadList(true);
            history.replace(response.data);
        });
    };

    const buttons = (
        <ButtonGroup minimal={false} vertical={false}>
            <BaseButton intent={Intent.PRIMARY} icon="floppy-disk" text={message.get("common.label.save", "저장")} onClick={save} />
            <Divider />
            <ListButton />
        </ButtonGroup>
    );

    return (
        <FormPageContainer>
            <FormPageHeader
                title={message.get("admin.appmanage.bbsmanage.title.create", "게시판 생성")}
                buttons={buttons}
            />
            <BbsManageDetailFormCU
                model={{
                    tbComBbs: tbComBbs,
                    tbComBbsAuthorToAuthorList: tbComBbsAuthorToAuthorList,
                }}
                codeDataSource={props.codeDataSource}
                setTbComBbs={setTbComBbs}
                setTbComBbsAuthorToAuthorList={setTbComBbsAuthorToAuthorList}
            />
            <FormPageFooter
                buttons={buttons}
            />
        </FormPageContainer>
    );
}
