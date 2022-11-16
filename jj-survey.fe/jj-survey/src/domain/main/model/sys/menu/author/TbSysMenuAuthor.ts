import MainEntity from '../../../MainEntity';

/**
 * 시스템 - 메뉴별 권한
 */
export default class TbSysMenuAuthor extends MainEntity {
    public menuId: string | undefined;
    public authorId: string | undefined;
}
