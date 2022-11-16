import React from 'react';
import { Checkbox, ButtonGroup, Intent, Button } from '@blueprintjs/core';

import BaseEntity, { DataJobTypes } from '../../../../infrastructure/framework/core/support/collection/BaseEntity';
import CommonUtil from '../../util/CommonUtil';
import StoreUtil from '../../util/StoreUtil';
import { BaseButton } from '../button/Buttons';

export default function ListTable(props: {
    data: any[];
    thead: React.ReactNode;
    sortColumnName?: string;
    tbodyRow: (index: number, rowNumber: number) => React.ReactNode;
    addButtonClick?: (data: any) => void;
    changeData?: (data: any[]) => void;
    readOnly?: boolean;
}) {
    const { messageStore: message } = StoreUtil.useStores();

    let buttons;

    if (props.readOnly === true) {
        buttons = null;
    } else {
        buttons = (
            <ButtonGroup minimal={false} vertical={false}>
                <BaseButton intent={Intent.NONE} small icon="add" text={message.get("common.label.add", "추가")} onClick={() => {
                    let data = { _JOB_TYPE: DataJobTypes.CREATE };

                    if (props.addButtonClick) {
                        props.addButtonClick(data);
                    }

                    if (props.changeData) {
                        props.changeData(props.data.concat(data));
                    }
                }} />
                <BaseButton intent={Intent.NONE} small icon="delete" text={message.get("common.label.delete", "삭제")} onClick={() => {
                    let data = props.data.map((rowData) => {
                        if (rowData._rowCheckYn_ === "1" && rowData._JOB_TYPE === DataJobTypes.UPDATE) {
                            rowData._JOB_TYPE = DataJobTypes.DELETE;

                            if (props.sortColumnName) {
                                rowData[props.sortColumnName] = 0;
                            }
                        }

                        return rowData;
                    }).filter((rowData) => {
                        return !(rowData._rowCheckYn_ === "1" && rowData._JOB_TYPE === DataJobTypes.CREATE);
                    });

                    if (props.sortColumnName) {
                        data = data.sort((a, b) => {
                            if (props.sortColumnName) {
                                return Number(a[props.sortColumnName]) - Number(b[props.sortColumnName]);
                            }

                            return 0;
                        });
                    }

                    if (props.sortColumnName) {
                        resetSortRowNumber(data, props.sortColumnName);
                    }

                    if (props.changeData) {
                        props.changeData(data);
                    }
                }} />
            </ButtonGroup>
        );
    }

    let rowNumber = 1;

    return (
        <>
        {buttons}
        <table
            className="list"
            onClick={(event) => {
                const tbody = event.currentTarget.getElementsByTagName("TBODY")[0];
                const trs = tbody.getElementsByTagName("TR");

                for (let i = 0; i < trs.length; i++) {
                    trs[i].className = "";
                }
            }}
        >
            <thead>
                {props.thead}
            </thead>
            <tbody>
                {props.data && props.data.map((rowData, index) => {
                    if (!rowData._KEY) {
                        rowData._KEY = CommonUtil.getUid();
                    }

                    if (rowData._JOB_TYPE === DataJobTypes.DELETE) {
                        return null;
                    }

                    return (
                        <tr key={rowData._KEY}>
                            {props.tbodyRow(index, rowNumber++)}
                        </tr>
                    );
                })}
            </tbody>
        </table>
        </>
    );
}

export function ListTableHeaderRowNumber(props: {
    colSpan?: number;
    rowSpan?: number;
    width?: number;
}) {
    const { messageStore: message } = StoreUtil.useStores();

    return (
        <th
            colSpan={props.colSpan}
            rowSpan={props.rowSpan}
            style={{ width: `${props.width || 50}px` }}
        >
            {message.get("common.label.no", "No")}
        </th>
    );
}

export function TableGirdHeaderRowCheck(props: {
    data: any[];
    changeData: (data: any[]) => void;
    colSpan?: number;
    rowSpan?: number;
    width?: number;
}) {
    const [rowCheckAll, setRowCheckAll] = React.useState(false);

    let rowCount = 0;

    props.data.forEach((rowData) => {
        if (rowData._JOB_TYPE !== DataJobTypes.DELETE) {
            rowCount++;
        }
    });

    const allChecked = (
        rowCount > 0 && props.data.find((rowData) => {
            return rowData._rowCheckYn_ !== "1";
        }) === undefined
    );

    return (
        <th
            colSpan={props.colSpan}
            rowSpan={props.rowSpan}
            style={{ width: `${props.width || 50}px` }}
        >
            <Checkbox
                checked={rowCheckAll && allChecked}
                onChange={(event) => {
                    props.changeData(props.data.map((rowData) => {
                        return { ...rowData, _rowCheckYn_: event.currentTarget.checked ? "1" : "0" };
                    }));

                    setRowCheckAll(event.currentTarget.checked);
                }}
            />
        </th>
    );
}

export function ListTableHeaderRowSort(props: {
    colSpan?: number;
    rowSpan?: number;
    width?: number;
}) {
    const { messageStore: message } = StoreUtil.useStores();

    return (
        <th
            colSpan={props.colSpan}
            rowSpan={props.rowSpan}
            style={{ width: `${props.width || 100}px` }}
        >
            {message.get("common.label.sort", "정렬")}
        </th>
    );
}

export function ListTableBodyRowNumber(props: {
    index: number;
    rowNumber: number;
}) {
    return (
        <th>{props.rowNumber}</th>
    );
}

export function ListTableBodyRowCheck(props: {
    data: any[];
    index: number;
    paddingTop?: number;
    changeData: (data: any[]) => void;
}) {
    const rowData = props.data[props.index];

    return (
        <td style={{ textAlign: "center" }}>
            <Checkbox
                name="_rowCheckYn_"
                checked={rowData._rowCheckYn_ === "1"}
                style={{ paddingTop: `${props.paddingTop || 5}px` }}
                onChange={(event) => {
                    props.changeData(props.data.map((rowData2) => {
                        if (rowData2 === rowData) {
                            rowData2._rowCheckYn_ = (rowData2._rowCheckYn_ === "1" ? "0" : "1");
                        }

                        return rowData2;
                    }));
                }}
            />
        </td>
    );
}

export function ListTableBodyRowSort(props: {
    data: any[];
    index: number;
    rowNumber: number;
    sortColumnName: string;
    changeData: (data: any[]) => void;
}) {
    const [enteredDataIndex, setEnteredDataIndex] = React.useState(-1);

    const resetSortOrder = (rowData, direction: number) => {
        let sortOrderCalc = false;
        let data: any[];

        if (direction === -1) {
            data = props.data;
        } else {
            data = props.data.reverse();
        }

        data = data.map((rowData2) => {
            if (rowData2 === rowData) {
                sortOrderCalc = true;
                rowData2[props.sortColumnName] = rowData2[props.sortColumnName] - direction;
                return rowData2;
            }

            if (sortOrderCalc) {
                sortOrderCalc = false;
                rowData2[props.sortColumnName] = rowData2[props.sortColumnName] + direction;
                return rowData2;
            }

            return rowData2;
        }).sort((a, b) => {
            if (props.sortColumnName) {
                return Number(a[props.sortColumnName]) - Number(b[props.sortColumnName]);
            }

            return 0;
        });

        resetSortRowNumber(data, props.sortColumnName);

        props.changeData(data);
    };

    const handleDragStart = (event) => {
        const button = getParentElement(event.target, "BUTTON", 0);

        if (!button) {
            return;
        }

        const dataIndex = Number(button.dataset["dataIndex"]);
        const rowNumber = Number(button.dataset["rowNumber"]);

        // event.dataTransfer.setData("dataIndex", String(dataIndex));
        // event.dataTransfer.setData("rowNumber", String(rowNumber));

        window["draggedDataIndex"] = dataIndex;
        window["draggedRowNumber"] = rowNumber;

        setEnteredDataIndex(-1);
    };

    const handleDragOver = (event) => {
        event.preventDefault();
    };

    const handleDragEnter = (event) => {
        const button = getParentElement(event.target, "BUTTON", 0);

        if (!button) {
            return;
        }

        const enteredDataIndex = Number(button.dataset["dataIndex"]);
        const enteredRowNumber = Number(button.dataset["rowNumber"]);

        removeRowHighlightClassName(event);

        const tbody = getParentElement(event.target, "TBODY", 0);
        const tr = tbody.getElementsByTagName("TR")[enteredRowNumber - 1];

        if (tr) {
            if (enteredRowNumber === props.rowNumber && window["draggedRowNumber"] > enteredRowNumber) {
                tr.className = "drag-enter-top";
            } else if (enteredRowNumber === props.rowNumber && window["draggedRowNumber"] < enteredRowNumber) {
                tr.className = "drag-enter-bottom";
            } else {
                tr.className = "";
            }
        }

        setEnteredDataIndex(enteredDataIndex);
    };

    const removeRowHighlightClassName = (event) => {
        const tbody = getParentElement(event.target, "TBODY", 0);
        const trs = tbody.getElementsByTagName("TR");

        for (let i = 0; i < trs.length; i++) {
            trs[i].className = "";
        }
    };

    const handleOnDrop = (event) => {
        const button = getParentElement(event.target, "BUTTON", 0);

        if (!button) {
            return;
        }

        // const draggedDataIndex = Number(event.dataTransfer.getData("dataIndex"));
        const draggedDataIndex = Number(window["draggedDataIndex"]);
        const droppedDataIndex = Number(button.dataset["dataIndex"]);
        const data = [...props.data];

        if (draggedDataIndex !== droppedDataIndex) {
            const draggedData = data.splice(draggedDataIndex, 1)[0];

            if (draggedData !== undefined && draggedData !== null) {
                data.splice(droppedDataIndex, 0, draggedData);
            }
        }

        resetSortRowNumber(data, props.sortColumnName);

        removeRowHighlightClassName(event);

        props.changeData(data);

        setEnteredDataIndex(-1);
    };

    const getParentElement = (element, tagName: string, count: number) => {
        if (!element || count > 10) {
            return;
        }

        if (element.tagName !== tagName) {
            return getParentElement(element.parentElement, tagName, ++count);
        }

        return element;
    };

    let rowCount = 0;
    let isFirstRow = true;
    let isLastRow = true;

    for (let i = 0; i < props.data.length; i++) {
        if (props.data[i]._JOB_TYPE === DataJobTypes.DELETE) {
            continue;
        }

        rowCount++;

        if (isFirstRow && i < props.index) {
            isFirstRow = false;
        }

        if (isLastRow && i > props.index) {
            isLastRow = false;
        }
    }

    return (
        <td style={{ textAlign: "center", whiteSpace: "nowrap" }}>
            <Button
                icon={isFirstRow ? "stop" : "arrow-up"}
                disabled={isFirstRow}
                onClick={(event) => {
                    resetSortOrder(props.data[props.index], 1);
                }}
            />
            <Button
                icon={isLastRow ? "stop" : "arrow-down"}
                disabled={isLastRow}
                onClick={(event) => {
                    resetSortOrder(props.data[props.index], -1);
                }}
            />
            <Button
                icon="drag-handle-vertical"
                disabled={rowCount === 1}
                draggable
                onDragStart={handleDragStart}
                onDragOver={handleDragOver}
                onDrop={handleOnDrop}
                onDragEnter={handleDragEnter}
                data-data-index={props.index}
                data-row-number={props.rowNumber}
            />
        </td>
    );
}

const resetSortRowNumber = (data: BaseEntity[], sortColumnName: string) => {
    let rowNumber = 1;

    data.forEach((rowData) => {
        if (rowData._JOB_TYPE !== DataJobTypes.DELETE) {
            rowData[sortColumnName] = rowNumber++;
        }
    });
};
