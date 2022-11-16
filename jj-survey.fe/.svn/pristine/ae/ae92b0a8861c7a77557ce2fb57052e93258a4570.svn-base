import TbComBbs from '../../../../../domain/main/model/com/bbs/TbComBbs';
import TbComBbsAuthorToAuthor from '../../../../../domain/main/model/com/bbs/author/TbComBbsAuthorToAuthor';
import ValidStore from '../../../../common/validation/store/ValidStore';
import ValidatorsMap from '../../../../common/validation/ValidatorsMap';
import ValidatorForRequired from '../../../../common/validation/validator/ValidatorForRequired';
import ValidatorForRequiredTitleList from '../../../../common/validation/validator/ValidatorForRequiredTitleList';

/**
 * 게시판 관리 Model
 */
export default class BbsManageModel {
    public editable?: boolean;
    public tbComBbs: TbComBbs = {} as TbComBbs;
    public tbComBbsAuthorToAuthorList: TbComBbsAuthorToAuthor[] = [];

    public static getValidatorsMap(tbComBbs: TbComBbs): ValidatorsMap {
        const validatorsMap = new ValidatorsMap();

        validatorsMap.add("tbComBbs.bbsCode", new ValidatorForRequired(tbComBbs.bbsCode));
        validatorsMap.add("tbComBbs.bbsNmTitleList", new ValidatorForRequiredTitleList(tbComBbs.bbsNmTitleList || []));
        validatorsMap.add("tbComBbs.answerPosblYn", new ValidatorForRequired(tbComBbs.answerPosblYn));
        validatorsMap.add("tbComBbs.rdcntIndictYn", new ValidatorForRequired(tbComBbs.rdcntIndictYn));
        validatorsMap.add("tbComBbs.noticeBbsYn", new ValidatorForRequired(tbComBbs.noticeBbsYn));
        validatorsMap.add("tbComBbs.editrApplcYn", new ValidatorForRequired(tbComBbs.editrApplcYn));

        return validatorsMap;
    }

    public static updateValidators(validStore: ValidStore, tbComBbs: TbComBbs, key: string): void {
        const validatorsMap = BbsManageModel.getValidatorsMap(tbComBbs);

        validStore.update(key, validatorsMap.getValidators(key));
    }
}
