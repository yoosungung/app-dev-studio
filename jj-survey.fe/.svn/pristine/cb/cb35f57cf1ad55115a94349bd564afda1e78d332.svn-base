import React from 'react';
import useReactRouter from 'use-react-router';
import { Intent, InputGroup, ButtonGroup } from '@blueprintjs/core';
import { Link } from 'react-router-dom';

import GridRequest from '../../../../../infrastructure/grid/model/GridRequest';
import GridResult from '../../../../../infrastructure/grid/model/GridResult';
import GridUtil from '../../../../../infrastructure/grid/util/GridUtil';
import CommonUtil from '../../../../common/util/CommonUtil';
import StoreUtil from '../../../../common/util/StoreUtil';
import MultiSelect from '../../../../common/component/multiselect/MultiSelect';
import BlankPage from '../../../../common/view/BlankPage';
import CodeDataSource, { CodeDataUtil } from '../../../../common/codedata/CodeDataSource';
import { ListPageContainer, ListPageHeader, ListSearchForm, ListGridContainer } from '../../../common/layout/contents/AdminListPageLayout';
import { BaseButton } from '../../../../common/component/button/Buttons';
import DataGrid from '../../../../common/component/datagrid/DataGrid';
import TitleManageService from '../service/TitleManageService';

export default function TitleManageList() {
    const { match } = useReactRouter();
    const { messageStore: message, listStore } = StoreUtil.useStores();
    const [codeDataSource, setCodeDataSource] = React.useState<CodeDataSource>();
    const [gridRequest, setGridRequest] = React.useState<GridRequest>(GridUtil.getRequestWithPaging());
    const [gridResult, setGridResult] = React.useState<GridResult>(GridUtil.getResultForEmpty());
    const titleManageService = new TitleManageService();

    React.useEffect(() => {
        listStore.reset();
    }, []);

    if (!codeDataSource) {
        (async () => {
            const codeDataSource = await CodeDataUtil.getCodeDataSource([
                { key: "/common/systemLocale" },
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

        titleManageService.readList(gridRequest, (gridResult) => {
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

    const titleLocalesVisible = (codeDataSource.get("/common/systemLocale").list.length > 1);

    return (
        <ListPageContainer listExact={match.isExact}>
            <ListPageHeader
                title={message.get("admin.sysmanage.titlemanage.title.list", "명칭 관리")}
                buttons={
                    <ButtonGroup minimal={false} vertical={false}>
                        <BaseButton intent={Intent.SUCCESS} icon="search" text={message.get("common.label.read", "조회")} onClick={readList}></BaseButton>
                    </ButtonGroup>
                }
            />
            <ListSearchForm readList={readList}>
                <table className="form">
                    <colgroup>
                        {titleLocalesVisible &&
                        <>
                        <col style={{ width: "120px" }} />
                        <col />
                        </>
                        }
                        <col style={{ width: "120px" }} />
                        <col />
                    </colgroup>
                    <tbody>
                        <tr>
                            {titleLocalesVisible &&
                            <>
                            <th>{message.get("common.label.langCode", "언어코드")}</th>
                            <td>
                                <MultiSelect
                                    codeData={codeDataSource.get("/common/systemLocale")}
                                    changeValue={(value) => {
                                        // 조회 시작시 그리드 컬럼이 바뀌도록 상태 변경 안 함
                                        gridRequest.search.titleLocales = value;
                                    }}
                                />
                            </td>
                            </>
                            }
                            <th>{message.get("common.label.searchText", "검색어")}</th>
                            <td>
                                <InputGroup
                                    name="searchText"
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
                        columns: (() => {
                            const gridColumns: any[] = [];

                            gridColumns.push(GridUtil.columns.rn);

                            gridColumns.push({
                                key: "titleCode",
                                name: `${message.get("domain.main.tbSysTitle.titleCode", "명칭 코드")}`,
                                width: 300,
                                resizable: true,
                                formatter: ({ value, row }) => <Link to={`${row.titleCode}`}>{value}</Link>
                            });

                            if (gridRequest.search.titleLocales) {
                                const titleLocales: string[] = gridRequest.search.titleLocales.split(",");

                                titleLocales.forEach((locale) => {
                                    const key = ["title"];

                                    key.push(locale.substr(0, 1).toUpperCase());
                                    key.push(locale.substr(1, 1).toLowerCase());
                                    key.push(locale.substr(3, 1).toUpperCase());
                                    key.push(locale.substr(4, 1).toLowerCase());

                                    gridColumns.push({
                                        key: key.join(""),
                                        name: codeDataSource.getCodeName("/common/systemLocale", locale),
                                        resizable: true
                                    });
                                });
                            } else {
                                gridColumns.push({
                                    key: "titleCn",
                                    name: `${message.get("domain.main.tbSysTitle.titleCn", "명칭 내용")}`,
                                    resizable: true
                                });
                            }

                            return gridColumns;
                        })(),
                        minColumnWidth: 300,
                    }}
                    request={gridRequest}
                    result={gridResult}
                    readList={readList}
                    option={{
                        titleBar: true,
                        paging: true,
                    }}
                />
            </ListGridContainer>
        </ListPageContainer>
    );
}
