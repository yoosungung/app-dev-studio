import TbSysMenu from '../../../../../domain/main/model/sys/menu/TbSysMenu';
import TbSysMenuAuthorToAuthor from '../../../../../domain/main/model/sys/menu/author/TbSysMenuAuthorToAuthor';
import ValidStore from '../../../../common/validation/store/ValidStore';
import ValidatorsMap from '../../../../common/validation/ValidatorsMap';
import ValidatorForRequired from '../../../../common/validation/validator/ValidatorForRequired';
import ValidatorForRequiredTitleList from '../../../../common/validation/validator/ValidatorForRequiredTitleList';

/**
 * 메뉴 관리 Model
 */
export default class MenuManageModel {
    public editable?: boolean;
    public tbSysMenu: TbSysMenu = {} as TbSysMenu;
    public tbSysMenuAuthorToAuthorList: TbSysMenuAuthorToAuthor[] = [];

    public static getValidatorsMap(tbSysMenu: TbSysMenu, tbSysMenuAuthorToAuthorList: TbSysMenuAuthorToAuthor[]): ValidatorsMap {
        const validatorsMap = new ValidatorsMap();

        validatorsMap.add("tbSysMenu.menuNmTitleList", new ValidatorForRequiredTitleList(tbSysMenu.menuNmTitleList || []));

        return validatorsMap;
    }

    public static updateValidators(validStore: ValidStore, tbSysMenu: TbSysMenu, tbSysMenuAuthorToAuthorList: TbSysMenuAuthorToAuthor[], key: string): void {
        const validatorsMap = MenuManageModel.getValidatorsMap(tbSysMenu, tbSysMenuAuthorToAuthorList);

        validStore.update(key, validatorsMap.getValidators(key));
    }
}
