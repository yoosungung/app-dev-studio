import TbSysTitle from '../../../../../domain/main/model/sys/title/TbSysTitle';
import ValidStore from '../../../../common/validation/store/ValidStore';
import ValidatorsMap from '../../../../common/validation/ValidatorsMap';
import ValidatorForRequired from '../../../../common/validation/validator/ValidatorForRequired';
import CommonUtil from '../../../../common/util/CommonUtil';

/**
 * 명칭 관리 Model
 */
export default class TitleManageModel {
    public editable?: boolean;
    public titleCode: string | undefined;
    public tbSysTitleList: TbSysTitle[] | undefined;

    public static getValidatorsMap(tbSysTitleList: TbSysTitle[]): ValidatorsMap {
        const validatorsMap = new ValidatorsMap();

        for (let i = 0; i < tbSysTitleList.length; i++) {
            if (tbSysTitleList[i].titleLocale === CommonUtil.defaultLocale) {
                validatorsMap.add(`tbSysTitle.${CommonUtil.defaultLocale}.titleCn`, new ValidatorForRequired(tbSysTitleList[i].titleCn));
                break;
            }
        }

        return validatorsMap;
    }

    public static updateValidators(validStore: ValidStore, tbSysTitleList: TbSysTitle[], key: string): void {
        const validatorsMap = TitleManageModel.getValidatorsMap(tbSysTitleList);

        validStore.update(key, validatorsMap.getValidators(key));
    }
}
