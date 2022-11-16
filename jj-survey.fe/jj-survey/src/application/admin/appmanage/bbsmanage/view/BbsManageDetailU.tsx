import React from 'react';
import useReactRouter from 'use-react-router';
import { AxiosResponse } from 'axios';
import { Intent, ButtonGroup, Divider } from '@blueprintjs/core';

import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormPageContainer, FormPageHeader, FormPageFooter } from '../../../common/layout/contents/AdminFormPageLayout';
import { BaseButton, ListButton, RedirectButton } from '../../../../common/component/button/Buttons';
import BbsManageModel from '../model/BbsManageModel';
import BbsManageDetailFormCU from './BbsManageDetailFormCU';
import BbsManageService from '../service/BbsManageService';

export default function BbsManageDetailU(props: {
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

        bbsManageService.update({
            tbComBbs: tbComBbs,
            tbComBbsAuthorToAuthorList: tbComBbsAuthorToAuthorList
        }, (response: AxiosResponse<any>) => {
            listStore.setReloadList(true);
            history.replace(`${tbComBbs.bbsId}`);
        });
    };

    const buttons = (
        <ButtonGroup minimal={false} vertical={false}>
            <BaseButton intent={Intent.PRIMARY} icon="floppy-disk" text={message.get("common.label.save", "저장")} onClick={save} />
            <RedirectButton intent={Intent.PRIMARY} icon="reset" text={message.get("common.label.cancel", "취소")} to={`${tbComBbs.bbsId}`} />
            <Divider />
            <ListButton />
        </ButtonGroup>
    );

    return (
        <FormPageContainer>
            <FormPageHeader
                title={message.get("admin.appmanage.bbsmanage.title.update", "게시판 수정")}
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
