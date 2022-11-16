import React from 'react';
import useReactRouter from 'use-react-router';
import { AxiosResponse } from 'axios';
import { Intent, ButtonGroup, Divider } from '@blueprintjs/core';

import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormPageContainer, FormPageHeader, FormPageFooter } from '../../../common/layout/contents/AdminFormPageLayout';
import { BaseButton, ListButton } from '../../../../common/component/button/Buttons';
import MenuManageModel from '../model/MenuManageModel';
import MenuManageService from '../service/MenuManageService';
import MenuManageDetailFormCU from './MenuManageDetailFormCU';

export default function MenuManageDetailC(props: {
    model: MenuManageModel;
    codeDataSource: CodeDataSource;
}) {
    const { history } = useReactRouter();
    const { messageStore: message, listStore: listStore, validStore } = StoreUtil.useStores();
    const [tbSysMenu, setTbSysMenu] = React.useState(props.model.tbSysMenu);
    const [tbSysMenuAuthorToAuthorList, setTbSysMenuAuthorToAuthorList] = React.useState(props.model.tbSysMenuAuthorToAuthorList);
    const codeManageService = new MenuManageService();

    React.useEffect(() => {
    }, []);

    const save = () => {
        const validatorsMap = MenuManageModel.getValidatorsMap(tbSysMenu, tbSysMenuAuthorToAuthorList);

        if (validatorsMap.invalidCount > 0) {
            validStore.setValidatorsMap(validatorsMap);
            return;
        }

        codeManageService.create({
            tbSysMenu: tbSysMenu,
            tbSysMenuAuthorToAuthorList: tbSysMenuAuthorToAuthorList
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
            <MenuManageDetailFormCU
                model={{
                    tbSysMenu: tbSysMenu,
                    tbSysMenuAuthorToAuthorList: tbSysMenuAuthorToAuthorList,
                }}
                codeDataSource={props.codeDataSource}
                setTbSysMenu={setTbSysMenu}
                setTbSysMenuAuthorToAuthorList={setTbSysMenuAuthorToAuthorList}
            />
            <FormPageFooter
                buttons={buttons}
            />
        </FormPageContainer>
    );
}
