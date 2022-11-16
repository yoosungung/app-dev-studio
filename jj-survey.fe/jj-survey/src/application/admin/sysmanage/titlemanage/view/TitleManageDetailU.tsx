import React from 'react';
import useReactRouter from 'use-react-router';
import { AxiosResponse } from 'axios';
import { Intent, ButtonGroup, Divider } from '@blueprintjs/core';

import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormPageContainer, FormPageHeader, FormPageFooter } from '../../../common/layout/contents/AdminFormPageLayout';
import { BaseButton, ListButton, RedirectButton } from '../../../../common/component/button/Buttons';
import TitleManageModel from '../model/TitleManageModel';
import TitleManageService from '../service/TitleManageService';
import TitleManageDetailFormCU from './TitleManageDetailFormCU';

export default function TitleManageDetailU(props: {
    model: TitleManageModel;
    codeDataSource: CodeDataSource;
}) {
    const { history } = useReactRouter();
    const { messageStore: message, listStore, validStore } = StoreUtil.useStores();
    const [tbSysTitleList, setTbSysTitleList] = React.useState(props.model.tbSysTitleList);
    const codeManageService = new TitleManageService();

    React.useEffect(() => {
    }, []);

    const save = () => {
        const validatorsMap = TitleManageModel.getValidatorsMap(tbSysTitleList || []);

        if (validatorsMap.invalidCount > 0) {
            validStore.setValidatorsMap(validatorsMap);
            return;
        }

        codeManageService.update({
            titleCode: props.model.titleCode,
            tbSysTitleList: tbSysTitleList,
        }, (response: AxiosResponse<any>) => {
            listStore.setReloadList(true);
            history.replace(`${props.model.titleCode}`);
        });
    };

    const buttons = (
        <ButtonGroup minimal={false} vertical={false}>
            <BaseButton intent={Intent.PRIMARY} icon="floppy-disk" text={message.get("common.label.save", "저장")} onClick={save} />
            <RedirectButton intent={Intent.PRIMARY} icon="reset" text={message.get("common.label.cancel", "취소")} to={`${props.model.titleCode}`} />
            <Divider />
            <ListButton />
        </ButtonGroup>
    );

    return (
        <FormPageContainer>
            <FormPageHeader
                title={message.get("admin.sysmanage.codemanage.title.update", "코드 수정")}
                buttons={buttons}
            />
            <TitleManageDetailFormCU
                model={{
                    titleCode: props.model.titleCode,
                    tbSysTitleList: tbSysTitleList,
                }}
                codeDataSource={props.codeDataSource}
                setTbSysTitleList={setTbSysTitleList}
            />
            <FormPageFooter
                buttons={buttons}
            />
        </FormPageContainer>
    );
}
