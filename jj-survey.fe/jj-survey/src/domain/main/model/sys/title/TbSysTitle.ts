import MainEntity from "../../MainEntity";

/**
 * 시스템 - 타이틀 Entity
 */
export default class TbSysTitle extends MainEntity {
    public titleCode: string | undefined;
    public titleLocale: string | undefined;
    public titleCn: string | undefined;
}
