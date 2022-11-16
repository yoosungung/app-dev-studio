import React from 'react';
import styled from 'styled-components';
import { ResizeSensor, IResizeEntry, Button, Classes } from '@blueprintjs/core';
import ReactDataGrid from 'react-data-grid';

import GridRequest, { GridPaging } from '../../../../infrastructure/grid/model/GridRequest';
import GridResult from '../../../../infrastructure/grid/model/GridResult';
import { CodeData } from '../../codedata/CodeDataSource';
import StoreUtil from '../../util/StoreUtil';
import Select from '../select/Select';

const GridContainer = styled.div`
    position: relative;
    overflow: hidden;
`;

const GridTitleBar = styled.div`
`;

const GridBox = styled.div`
    position: absolute;
    overflow: hidden;
    width: 100%;
`;

const GridPagingBar = styled.div`
    position: absolute;
    bottom: 3px;
    width: 100%;
    text-align: center;
`;

const PaginationButtons = styled.div`
    margin-right: 10px;
    display: inline;
`;

const CurrentPageNo = styled.input`
    text-align: center;
    width: 80px;
`;

const RecordCountPerPage = styled.div`
    display: inline;
`;

export default function DataGrid(props: {
    grid;
    request: GridRequest;
    result: GridResult;
    readList?: () => void;
    option?: {
        titleBar?: boolean;
        paging?: boolean;
    };
}) {
    const { listStore } = StoreUtil.useStores();
    const [gridHeight, setGridHeight] = React.useState<number>(100);
    const [gridPaging, setGridPaging] = React.useState<GridPaging>(props.request.paging!);

    const titleBarHeight = (props.option?.titleBar ? 25 : 0);
    const pagingBarHeight = (props.option?.paging ? 40 : 0);

    const recordCountPerPages: number[] = [10, 20, 50, 100, 200, 500];
    const recordCountPerPageList: CodeData[] = [];

    recordCountPerPages.forEach((value) => {
        recordCountPerPageList.push({ code: value, name: value + "개씩 보기" });
    });

    const changeRecordCountPerPage = (newRecordCountPerPage: number) => {
        if (props.request.paging?.recordCountPerPage === newRecordCountPerPage) {
            return;
        }

        setGridPaging({ ...gridPaging, recordCountPerPage: newRecordCountPerPage, currentPageNo: 1 });

        if (props.request.paging) {
            props.request.paging.recordCountPerPage = newRecordCountPerPage;
            props.request.paging.currentPageNo = 1;
        }

        if (props.readList) {
            props.readList();
        }
    }

    const changePageNo = (newPageNo: number) => {
        if (props.request.paging?.currentPageNo === newPageNo) {
            return;
        }

        setGridPaging({ ...gridPaging, currentPageNo: newPageNo });

        if (props.request.paging) {
            props.request.paging.currentPageNo = newPageNo;
        }

        if (props.readList) {
            props.readList();
        }
    }

    let titleMessage: string;

    if (props.result.error === true) {
        titleMessage = `(Error)`;
        props.result.list = [];
    } else if (props.result.paging?.totalRecordCount != null) {
        titleMessage = `총 ${props.result.paging?.totalRecordCount} 건`
    } else if (listStore.loadStarted) {
        titleMessage = `Loading...`;
    } else {
        titleMessage = `(Ready)`;
    }

    return (
        <ResizeSensor onResize={(entries: IResizeEntry[]) => {
            setGridHeight(entries[0].contentRect.height - titleBarHeight - pagingBarHeight);
        }}>
            <GridContainer className="flex-column">
                {props.option?.titleBar &&
                <GridTitleBar className="flex-0">
                    {titleMessage}
                </GridTitleBar>
                }
                <GridBox className="flex-1" style={{ marginTop: `${titleBarHeight}px`, marginBottom: `${pagingBarHeight}px` }}>
                    <ReactDataGrid
                        rowGetter={(i: number) => {
                            return props.result.list[i];
                        }}
                        rowsCount={props.result.list.length}
                        minHeight={gridHeight}
                        {...props.grid}
                    />
                </GridBox>
                {props.option?.paging &&
                <GridPagingBar className="flex-0">
                    <PaginationButtons>
                        <Button className={Classes.MINIMAL} icon="double-chevron-left"
                            disabled={gridPaging.currentPageNo === 1}
                            onClick={(event) => {
                                changePageNo(1);
                            }}
                        />
                        <Button className={Classes.MINIMAL} icon="chevron-left"
                            disabled={gridPaging.currentPageNo === 1}
                            onClick={(event) => {
                                changePageNo(Math.max(gridPaging.currentPageNo! - 1, 1));
                            }}
                        />
                        <CurrentPageNo type="text" className="bp3-input"
                            value={gridPaging.currentPageNo || 1}
                            onFocus={(event) => {
                                event.target.select();
                            }}
                            onChange={(event) => {
                                let newPageNo = event.target.value;

                                if (newPageNo && (newPageNo < 1 || newPageNo > props.result.paging!.totalPageCount)) {
                                    newPageNo = props.result.paging!.currentPageNo;
                                }

                                setGridPaging({ ...gridPaging, currentPageNo: newPageNo });
                            }}
                            onKeyUp={(event) => {
                                if (event.keyCode === 13) {
                                    changePageNo(event.target.value);
                                }
                            }}
                            onBlur={(event) => {
                                event.target.value = props.request.paging?.currentPageNo;
                            }}
                        />
                        &nbsp;/&nbsp;{props.result.paging?.totalPageCount || 0} 페이지
                        <Button className={Classes.MINIMAL} icon="chevron-right"
                            disabled={gridPaging.currentPageNo === props.result.paging?.totalPageCount}
                            onClick={(event) => {
                                changePageNo(Math.min(gridPaging.currentPageNo! + 1, props.result.paging!.totalPageCount));
                            }}
                        />
                        <Button className={Classes.MINIMAL} icon="double-chevron-right"
                            disabled={gridPaging.currentPageNo === props.result.paging?.totalPageCount}
                            onClick={(event) => {
                                changePageNo(props.result.paging!.totalPageCount);
                            }}
                        />
                    </PaginationButtons>
                    <RecordCountPerPage>
                        <Select
                            options={recordCountPerPageList}
                            value={gridPaging.recordCountPerPage + ""}
                            changeValue={(value) => {
                                changeRecordCountPerPage(value);
                            }}
                        />
                    </RecordCountPerPage>
                </GridPagingBar>
                }
            </GridContainer>
        </ResizeSensor>
    );
}
