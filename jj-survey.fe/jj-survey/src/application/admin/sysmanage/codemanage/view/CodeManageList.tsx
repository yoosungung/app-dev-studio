import React from 'react';
import useReactRouter from 'use-react-router';
import { Divider, Intent, InputGroup, ButtonGroup } from '@blueprintjs/core';
import { Link } from 'react-router-dom';

import GridRequest from '../../../../../infrastructure/grid/model/GridRequest';
import GridResult from '../../../../../infrastructure/grid/model/GridResult';
import GridUtil from '../../../../../infrastructure/grid/util/GridUtil';
import CommonUtil from '../../../../common/util/CommonUtil';
import StoreUtil from '../../../../common/util/StoreUtil';
import BlankPage from '../../../../common/view/BlankPage';
import CodeDataSource, { CodeDataUtil } from '../../../../common/codedata/CodeDataSource';
import { ListPageContainer, ListPageHeader, ListSearchForm, ListGridContainer } from '../../../common/layout/contents/AdminListPageLayout';
import { LinkButton, BaseButton } from '../../../../common/component/button/Buttons';
import Select from '../../../../common/component/select/Select';
import DataGrid from '../../../../common/component/datagrid/DataGrid';
import CodeManageService from '../service/CodeManageService';

export default function CodeManageList() {
    const { match } = useReactRouter();
    const { messageStore: message, listStore } = StoreUtil.useStores();
    const [codeDataSource, setCodeDataSource] = React.useState<CodeDataSource>();
    const [gridRequest, setGridRequest] = React.useState<GridRequest>(GridUtil.getRequestWithPaging());
    const [gridResult, setGridResult] = React.useState<GridResult>(GridUtil.getResultForEmpty());
    const codeManageService = new CodeManageService();

    React.useEffect(() => {
        listStore.reset();
    }, []);

    if (!codeDataSource) {
        (async () => {
            const codeDataSource = await CodeDataUtil.getCodeDataSource([
                { key: "/common/useYn" },
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

        setGridResult(GridUtil.getResultForEmpty());

        codeManageService.readList(gridRequest, (gridResult) => {
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

    const DataGridAlignCenterFormatter = ({ value }) => {
        return (
            <div style={{ textAlign: "center" }}>{value}</div>
        );
    };

    return (
        <ListPageContainer listExact={match.isExact}>
            <ListPageHeader
                title={message.get("admin.sysmanage.codemanage.title.list", "?????? ??????")}
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
                            <th>{message.get("common.label.searchText", "?????????")}</th>
                            <td>
                                <InputGroup
                                    name="searchText"
                                    onChange={handleSearchConditionValues}
                                />
                            </td>
                            <th>{message.get("common.label.useYn", "?????? ??????")}</th>
                            <td>
                                <Select
                                    name="useYn"
                                    codeData={codeDataSource.get("/common/useYn")}
                                    firstName="all"
                                    onChange={handleSearchConditionValues}
                                    fill
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
                                key: "codeGroup",
                                name: `${message.get("domain.main.tbSysCodeGroup.codeGroup", "?????? ??????")}`,
                                width: 150,
                                resizable: true,
                                formatter: ({ value, row }) => <Link to={`${row.codeGroupId}`}>{value}</Link>
                            },
                            {
                                key: "codeGroupNm",
                                name: `${message.get("domain.main.tbSysCodeGroup.codeGroupNm", "?????? ?????? ??????")}`,
                                width: 300,
                                resizable: true
                            },
                            {
                                key: "codeValueCo",
                                name: `${message.get("admin.sysmanage.codemanage.label.codeValueCo", "????????? ??????")}`,
                                width: 100,
                                resizable: true,
                                formatter: DataGridAlignCenterFormatter
                            },
                            {
                                key: "codeGroupDc",
                                name: `${message.get("domain.main.tbSysCodeGroup.codeGroupDc", "?????? ?????? ??????")}`,
                                resizable: true
                            },
                            {
                                key: "useYn",
                                name: `${message.get("common.label.useYn", "?????? ??????")}`,
                                width: 80,
                                resizable: true,
                                formatter: ({ value }) => {
                                    return (
                                        <div style={{ textAlign: "center" }}>{codeDataSource.getCodeName("/common/useYn", value)}</div>
                                    );
                                }
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
