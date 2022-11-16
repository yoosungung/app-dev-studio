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
import DataGrid from '../../../../common/component/datagrid/DataGrid';
import BbsManageService from '../service/BbsManageService';

export default function BbsManageList() {
    const { match } = useReactRouter();
    const { messageStore: message, listStore } = StoreUtil.useStores();
    const [codeDataSource, setCodeDataSource] = React.useState<CodeDataSource>();
    const [gridRequest, setGridRequest] = React.useState<GridRequest>(GridUtil.getRequestWithPaging());
    const [gridResult, setGridResult] = React.useState<GridResult>(GridUtil.getResultForEmpty());
    const bbsManageService = new BbsManageService();

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

        setGridResult(GridUtil.getResultForEmpty());

        bbsManageService.readList(gridRequest, (gridResult) => {
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
                title={message.get("admin.appmanage.bbsmanage.title.list", "게시판 관리")}
                buttons={
                    <ButtonGroup minimal={false} vertical={false}>
                        <LinkButton intent={Intent.PRIMARY} icon="add" text={message.get("common.label.regist", "등록")} to="create"></LinkButton>
                        <Divider />
                        <BaseButton intent={Intent.SUCCESS} icon="search" text={message.get("common.label.read", "조회")} onClick={readList}></BaseButton>
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
                            <th>{message.get("domain.main.tbComBbs.bbsCode", "게시판 코드")}</th>
                            <td>
                                <InputGroup
                                    name="bbsCode"
                                    onChange={handleSearchConditionValues}
                                />
                            </td>
                            <th>{message.get("domain.main.tbComBbs.bbsNm", "게시판 이름")}</th>
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
                                key: "bbsCode",
                                name: `${message.get("domain.main.tbComBbs.bbsCode", "게시판 코드")}`,
                                width: 200,
                                resizable: true
                            },
                            {
                                key: "bbsNm",
                                name: `${message.get("domain.main.tbComBbs.bbsNm", "게시판 이름")}`,
                                resizable: true,
                                formatter: ({ value, row }) => <Link to={`${row.bbsId}`}>{value}</Link>
                            },
                            {
                                key: "answerPosblYn",
                                name: `${message.get("domain.main.tbComBbs.answerPosblYn", "답글 가능 여부")}`,
                                width: 120,
                                resizable: true,
                                formatter: DataGridCodeDataYesNoFormatter
                            },
                            {
                                key: "rdcntIndictYn",
                                name: `${message.get("domain.main.tbComBbs.rdcntIndictYn", "조회수 표시 여부")}`,
                                width: 120,
                                resizable: true,
                                formatter: DataGridCodeDataYesNoFormatter
                            },
                            {
                                key: "noticeBbsYn",
                                name: `${message.get("domain.main.tbComBbs.noticeBbsYn", "공지게시판 여부")}`,
                                width: 120,
                                resizable: true,
                                formatter: DataGridCodeDataYesNoFormatter
                            },
                            {
                                key: "editrApplcYn",
                                name: `${message.get("domain.main.tbComBbs.editrApplcYn", "에디터 적용 여부")}`,
                                width: 120,
                                resizable: true,
                                formatter: DataGridCodeDataYesNoFormatter
                            },
                            {
                                key: "atchFilePolicy",
                                name: `${message.get("domain.main.tbComBbs.atchFilePolicy", "첨부파일 정책")}`,
                                width: 200,
                                resizable: true,
                                formatter: ({ value }) => {
                                    return (
                                        <>{codeDataSource.getCodeName("/admin.appmgt.bbsmgt/atchFilePolicy", value)}</>
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
