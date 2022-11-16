import MainEntity from "../../MainEntity";
import TbSysTitle from "../title/TbSysTitle";

export default class TbSysMenu extends MainEntity {
    public menuId: string | undefined;
    public menuKnd: string | undefined;
    public upperMenuId: string | undefined;
    public menuLevel: number | undefined;
    public menuNmTitle: string | undefined;
    public menuOrdr: number | undefined;
    public menuPath: string | undefined;
    public useYn: string | undefined;

    public menuNmTitleList: TbSysTitle[] | undefined;
}
