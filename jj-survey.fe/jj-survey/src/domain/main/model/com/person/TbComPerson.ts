import MainEntity from "../../MainEntity";

/**
 * 공통 - 사람
 */
export default class TbComPerson extends MainEntity {
    public personId: string | undefined;
    public emplNo: string | undefined;
    public emailAdres: string | undefined;
    public writngPsnId: string | undefined;
    public writngDt: number | undefined;
    public changePsnId: string | undefined;
    public changeDt: number | undefined;
    public koreanNm: string | undefined;
    public englNm: string | undefined;
    public chcrtNm: string | undefined;
    public deptId: string | undefined;
    public hffcSttus: string | undefined;
    public ecnyDe: number | undefined;
    public retireDe: number | undefined;
    public ofcpsNm: string | undefined;
    public clsfNm: string | undefined;
    public rspofcNm: string | undefined;
    public tlphonNo: string | undefined;
    public atchFileId: string | undefined;
    public rm: string | undefined;
}
