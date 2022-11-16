export default interface GridRequest {
    search: any;
    paging?: GridPaging;
    excel?: any;
}

export interface GridPaging {
    recordCountPerPage?: number;
    pageListCount?: number;
    currentPageNo?: number;
    orderBy?: {
        columns?: { [key: string]: OrderByDirection };
        toSnakeCase?: boolean;
    }
}

export enum OrderByDirection {
    ASC, DESC
}
