export default interface GridResult {
    list: any[];
    paging?: {
        totalRecordCount: number;
        totalPageCount: number;
        currentPageNo: number;
    };
    error?: boolean;
}
