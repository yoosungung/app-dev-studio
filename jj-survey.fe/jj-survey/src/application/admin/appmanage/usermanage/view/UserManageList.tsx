import React from 'react';
import useReactRouter from 'use-react-router';
import { Divider, Intent, InputGroup, ButtonGroup } from '@blueprintjs/core';
import { Link } from 'react-router-dom';

import GridRequest from '../../../../../infrastructure/grid/model/GridRequest';
import GridResult from '../../../../../infrastructure/grid/model/GridResult';
import GridUtil from '../../../../../infrastructure/grid/util/GridUtil';
import CommonUtil from '../../../../common/util/CommonUtil';
import BlankPage from '../../../../common/view/BlankPage';
import CodeDataSource, { CodeDataUtil } from '../../../../common/codedata/CodeDataSource';
import { ListPageContainer, ListPageHeader, ListSearchForm, ListGridContainer } from '../../../common/layout/contents/AdminListPageLayout';
import { LinkButton, BaseButton } from '../../../../common/component/button/Buttons';
import DataGrid from '../../../../common/component/datagrid/DataGrid';
import UserManageService from '../service/UserManageService';
import StoreUtil from '../../../../common/util/StoreUtil';

export default function UserManageList() {
    const { match } = useReactRouter();
    const { messageStore: message, listStore } = StoreUtil.useStores();
    const [codeDataSource, setCodeDataSource] = React.useState<CodeDataSource>();
    const [gridRequest, setGridRequest] = React.useState<GridRequest>(GridUtil.getRequestWithPaging());
    const [gridResult, setGridResult] = React.useState<GridResult>(GridUtil.getResultForEmpty);
    const userManageService = new UserManageService();

    React.useEffect(() => {
        listStore.reset();
    }, []);

    if (!codeDataSource) {
        (async () => {
            const codeDataSource = await CodeDataUtil.getCodeDataSource([
                { key: "/common/yesNo" },
                { key: "/admin.appmgt.bbsmgt/atchFilePolicy" },
            ]);

            setCodeDataSource(codeDataSource);
        })();

        return <BlankPage />;
    }

    const readList = () => {
        if (!match.isExact) {
            return;
        }

        listStore.loadStart();

        setGridResult(GridUtil.getResultForEmpty);

        userManageService.readList(gridRequest, (gridResult) => {
            setGridResult(gridResult);
        });
    };

    if (!listStore.loadStarted || listStore.reloadList) {
        readList();
    }

    const handleSearchConditionValues = (event) => {
        const name = event.target.name;
        const value = CommonUtil.getElementValue(event.target);

        gridRequest.search[name] = value;

        setGridRequest({ ...gridRequest });
    };

    const DataGridCodeDataYesNoFormatter = ({ value }) => {
        return (
            <div style={{ textAlign: "center" }}>{codeDataSource.getCodeName("/common/yesNo", value)}</div>
        );
    };

    return (
        <ListPageContainer listExact={match.isExact}>
            <ListPageHeader
                title={message.get("admin.appmanage.usermanage.title.list", "????????? ??????")}
                buttons={
                    <ButtonGroup minimal={false} vertical={false}>
                        <LinkButton intent={Intent.PRIMARY} icon="add" text={message.get("common.label.regist", "??????")} to="create"></LinkButton>
                        <Divider />
                        <BaseButton intent={Intent.SUCCESS} icon="search" text={message.get("common.label.read", "??????")} onClick={readList}></BaseButton>
                    </ButtonGroup>
                }
            />
            <ListSearchForm readList={readList}>
                <table className="form">
                    <colgroup>
                        <col style={{ width: "120px" }} />
                        <col />
                        <col style={{ width: "120px" }} />
                        <col />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>{message.get("domain.main.tbComPerson.koreanNm.normal", "????????????")}</th>
                            <td>
                                <InputGroup
                                    name="bbsCode"
                                    onChange={handleSearchConditionValues}
                                />
                            </td>
                            <th>{message.get("domain.main.tbComPerson.emplNo.normal", "???/??????")}</th>
                            <td>
                                <InputGroup
                                    name="bbsNm"
                                    onChange={handleSearchConditionValues}
                                />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </ListSearchForm>
            <ListGridContainer>
                <DataGrid
                    grid={{
                        columns: [
                            GridUtil.columns.rn,
                            {
                                key: "KOREAN_NM",
                                name: `${message.get("domain.main.tbComPerson.koreanNm.normal", "????????????")}`,
                                width: 200,
                                resizable: true,
                                formatter: ({ value, row }) => <Link to={`${row.personId}`}>{value}</Link>
                            },
                            {
                                key: "emplNo",
                                name: `${message.get("domain.main.tbComPerson.emplNo.normal", "??????")}`,
                                resizable: true
                            },
                            {
                                key: "emailAdres",
                                name: `${message.get("domain.main.tbComPerson.emailAdres.normal", "???????????????")}`,
                                width: 120,
                                resizable: true
                            },
                        ],
                    }}
                    request={gridRequest}
                    result={gridResult}
                    option={{
                        titleBar: true,
                        paging: true,
                    }}
                />
            </ListGridContainer>
        </ListPageContainer>
    );
}