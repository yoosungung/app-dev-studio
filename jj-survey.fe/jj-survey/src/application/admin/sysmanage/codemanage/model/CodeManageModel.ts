import TbSysCodeGroup from '../../../../../domain/main/model/sys/code/group/TbSysCodeGroup';
import TbSysCodeValue from '../../../../../domain/main/model/sys/code/value/TbSysCodeValue';
import ValidStore from '../../../../common/validation/store/ValidStore';
import ValidatorsMap from '../../../../common/validation/ValidatorsMap';
import ValidatorForRequired from '../../../../common/validation/validator/ValidatorForRequired';
import ValidatorForRequiredList from '../../../../common/validation/validator/ValidatorForRequiredList';
import ValidatorForRequiredTitleList from '../../../../common/validation/validator/ValidatorForRequiredTitleList';

/**
 * 코드 관리 Model
 */
export default class CodeManageModel {
    public editable?: boolean;
    public tbSysCodeGroup: TbSysCodeGroup = {} as TbSysCodeGroup;
    public tbSysCodeValueList: TbSysCodeValue[] = [];

    public static getValidatorsMap(tbSysCodeGroup: TbSysCodeGroup, tbSysCodeValueList: TbSysCodeValue[]): ValidatorsMap {
        const validatorsMap = new ValidatorsMap();

        validatorsMap.add("tbSysCodeGroup.codeGroup", new ValidatorForRequired(tbSysCodeGroup.codeGroup));
        validatorsMap.add("tbSysCodeGroup.codeGroupNmTitleList", new ValidatorForRequiredTitleList(tbSysCodeGroup.codeGroupNmTitleList || []));
        validatorsMap.add("tbSysCodeGroup.useYn", new ValidatorForRequired(tbSysCodeGroup.useYn));
        validatorsMap.add("tbSysCodeGroup.tbSysCodeValueList", new ValidatorForRequiredList(tbSysCodeValueList || []));

        tbSysCodeValueList.forEach((tbSysCodeValue) => {
            validatorsMap.add(`tbSysCodeValue.${tbSysCodeValue._KEY}.codeValue`, new ValidatorForRequired(tbSysCodeValue.codeValue));
            validatorsMap.add(`tbSysCodeValue.${tbSysCodeValue._KEY}.codeValueNmTitleList`, new ValidatorForRequiredTitleList(tbSysCodeValue.codeValueNmTitleList || []));
            validatorsMap.add(`tbSysCodeValue.${tbSysCodeValue._KEY}.useYn`, new ValidatorForRequired(tbSysCodeValue.useYn));
        });

        return validatorsMap;
    }

    public static updateValidators(validStore: ValidStore, tbSysCodeGroup: TbSysCodeGroup, tbSysCodeValueList: TbSysCodeValue[], key: string): void {
        const validatorsMap = CodeManageModel.getValidatorsMap(tbSysCodeGroup, tbSysCodeValueList);

        validStore.update(key, validatorsMap.getValidators(key));
    }
}
