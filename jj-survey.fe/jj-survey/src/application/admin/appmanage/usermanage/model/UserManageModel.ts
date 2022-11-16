import TbComPerson from '../../../../../domain/main/model/com/person/TbComPerson';
import ValidStore from '../../../../common/validation/store/ValidStore';
import ValidatorsMap from '../../../../common/validation/ValidatorsMap';
import ValidatorForRequired from '../../../../common/validation/validator/ValidatorForRequired';
import ValidatorForEmailAddress from '../../../../common/validation/validator/ValidatorForEmailAddress';

/**
 * 사용자 관리 Model
 */
export default class UserManageModel {
    public editable?: boolean;
    public tbComPerson: TbComPerson = {} as TbComPerson;
    
    public static getValidatorsMap(tbComPerson: TbComPerson): ValidatorsMap {
        const validatorsMap = new ValidatorsMap();

        validatorsMap.add("tbComPerson.emplNo", new ValidatorForRequired(tbComPerson.emplNo));
        validatorsMap.add("tbComPerson.emailAdres", new ValidatorForRequired(tbComPerson.emailAdres));
        validatorsMap.add("tbComPerson.emailAdres", new ValidatorForEmailAddress(tbComPerson.emailAdres));
        validatorsMap.add("tbComPerson.hffcSttus", new ValidatorForRequired(tbComPerson.hffcSttus));
        validatorsMap.add("tbComPerson.ecnyDe", new ValidatorForRequired(tbComPerson.ecnyDe));

        return validatorsMap;
    }

    public static updateValidators(validStore: ValidStore, tbComPerson: TbComPerson, key: string): void {
        const validatorsMap = UserManageModel.getValidatorsMap(tbComPerson);

        validStore.update(key, validatorsMap.getValidators(key));
    }
}
