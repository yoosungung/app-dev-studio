import React from 'react';
import useReactRouter from 'use-react-router';
import { AxiosResponse } from 'axios';
import { Intent, ButtonGroup, Divider } from '@blueprintjs/core';

import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormPageContainer, FormPageHeader, FormPageFooter } from '../../../common/layout/contents/AdminFormPageLayout';
import { BaseButton, ListButton } from '../../../../common/component/button/Buttons';
import CodeManageModel from '../model/CodeManageModel';
import CodeManageService from '../service/CodeManageService';
import CodeManageDetailFormCU from './CodeManageDetailFormCU';

export default function CodeManageDetailC(props: {
    model: CodeManageModel;
    codeDataSource: CodeDataSource;
}) {
    const { history } = useReactRouter();
    const { messageStore: message, listStore: listStore, validStore } = StoreUtil.useStores();
    const [tbSysCodeGroup, setTbSysCodeGroup] = React.useState(props.model.tbSysCodeGroup);
    const [tbSysCodeValueList, setTbSysCodeValueList] = React.useState(props.model.tbSysCodeValueList);
    const codeManageService = new CodeManageService();

    React.useEffect(() => {
    }, []);

    const save = () => {
        const validatorsMap = CodeManageModel.getValidatorsMap(tbSysCodeGroup, tbSysCodeValueList);

        if (validatorsMap.invalidCount > 0) {
            validStore.setValidatorsMap(validatorsMap);
            return;
        }

        codeManageService.create({
            tbSysCodeGroup: tbSysCodeGroup,
            tbSysCodeValueList: tbSysCodeValueList
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
                title={message.get("admin.sysmanage.codemanage.title.create", "코드 생성")}
                buttons={buttons}
            />
            <CodeManageDetailFormCU
                model={{
                    tbSysCodeGroup: tbSysCodeGroup,
                    tbSysCodeValueList: tbSysCodeValueList,
                }}
                codeDataSource={props.codeDataSource}
                setTbSysCodeGroup={setTbSysCodeGroup}
                setTbSysCodeValueList={setTbSysCodeValueList}
            />
            <FormPageFooter
                buttons={buttons}
            />
        </FormPageContainer>
    );
}
