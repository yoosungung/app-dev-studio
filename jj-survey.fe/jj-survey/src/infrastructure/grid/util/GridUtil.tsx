import React from 'react';

import StoreUtil from '../../../application/common/util/StoreUtil';
import GridRequest from '../model/GridRequest';
import GridResult from '../model/GridResult';

export default class GridUtil {
    private static _columns: { rn };

    public static get columns() {
        if (GridUtil._columns != null) {
            return GridUtil._columns;
        }

        const { messageStore: message } = StoreUtil.useStores();

        GridUtil._columns = {
            rn: {
                key: "rn",
                name: `${message.get("common.label.rn", "No")}`,
                width: 50,
                resizable: false,
                formatter: ({ value }) => {
                    return (
                        <div style={{ textAlign: "center" }}>{value}</div>
                    );
                }
            },
        };

        return GridUtil._columns;
    }

    public static getRequestWithPaging(search?) {
        const gridRequest: GridRequest = {
            search: search || {},
            paging: {
                recordCountPerPage: 20,
                pageListCount: 5,
                currentPageNo: 1,
                orderBy: {
                    toSnakeCase: true
                },
            },
        };

        return gridRequest;
    }

    public static getResultForEmpty() {
        const gridResult: GridResult = {
            list: [],
        };

        return gridResult;
    }
}
