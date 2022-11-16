import React from 'react';
import { observer } from 'mobx-react-lite';
import { InputGroup, TextArea, Checkbox } from '@blueprintjs/core';

import { DataJobTypes } from '../../../../../infrastructure/framework/core/support/collection/BaseEntity';
import CommonUtil from '../../../../common/util/CommonUtil';
import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import ListTable, { ListTableHeaderRowNumber, TableGirdHeaderRowCheck, ListTableHeaderRowSort, ListTableBodyRowNumber, ListTableBodyRowCheck, ListTableBodyRowSort } from '../../../../common/component/listtable/ListTable';
import TextUtil from '../../../../common/util/TextUtil';
import { FormContentsGroup, FormContents } from '../../../common/layout/contents/AdminFormPageLayout';
import Radios from '../../../../common/component/radios/Radios';
import TextByteIndicator from '../../../../common/component/text/TextByteIndicator';
import TitleInputGroup from '../../../../common/component/title/TitleInputGroup';
import ValidationMessage from '../../../../common/validation/message/ValidationMessage';
import MenuManageModel from '../model/MenuManageModel';
import TbSysCodeValue from '../../../../../domain/main/model/sys/code/value/TbSysCodeValue';

export default observer(function MenuManageDetailFormCU(props: {
    model: MenuManageModel;
    codeDataSource: CodeDataSource;
    setTbSysMenu;
    setTbSysMenuAuthorToAuthorList;
}) {
    const { messageStore: message, validStore } = StoreUtil.useStores();
    const tbSysMenu = props.model.tbSysMenu;
    const tbSysMenuAuthorToAuthorList = props.model.tbSysMenuAuthorToAuthorList;
    const validatorsMap = validStore.validatorsMap;

    React.useEffect(() => {
        validStore.reset();
    }, []);

    const handleTbSysMenuInputChange = (event, maxByte?: number) => {
        const name = event.target.name;
        const value = CommonUtil.getElementValue(event.target, maxByte);

        handleTbSysMenuValueChange(name, value);
    };

    const handleTbSysMenuValueChange = (name: string, value) => {
        const tbSysMenuNew = { ...tbSysMenu, [name]: value };

        MenuManageModel.updateValidators(validStore, tbSysMenuNew, tbSysMenuAuthorToAuthorList, "tbSysMenu." + name);

        props.setTbSysMenu(tbSysMenuNew);
    };

    const handleTbSysMenuAuthorToAuthorListInputChange = (event, tbSysCodeValue: TbSysCodeValue, maxByte?: number) => {
        const name = event.target.name;

        const tbSysMenuAuthorToAuthorListNew = tbSysMenuAuthorToAuthorList.map((tbSysCodeValue2) => {
            if (tbSysCodeValue2._KEY === tbSysCodeValue._KEY) {
                tbSysCodeValue2[name] = CommonUtil.getElementValue(event.currentTarget, maxByte);
            }

            return tbSysCodeValue2;
        });

        MenuManageModel.updateValidators(validStore, tbSysMenu, tbSysMenuAuthorToAuthorListNew, `tbSysCodeValue.${tbSysCodeValue._KEY}.${name}`);

        props.setTbSysMenuAuthorToAuthorList(tbSysMenuAuthorToAuthorListNew);
    };

    const handleTbSysMenuAuthorToAuthorListValueChange = (tbSysCodeValue: TbSysCodeValue, name: string, value) => {
        const tbSysMenuAuthorToAuthorListNew = tbSysMenuAuthorToAuthorList.map((tbSysCodeValue2) => {
            if (tbSysCodeValue2._KEY === tbSysCodeValue._KEY) {
                tbSysCodeValue2[name] = value;
            }

            return tbSysCodeValue2;
        });

        MenuManageModel.updateValidators(validStore, tbSysMenu, tbSysMenuAuthorToAuthorListNew, `tbSysCodeValue.${tbSysCodeValue._KEY}.${name}`);

        props.setTbSysMenuAuthorToAuthorList(tbSysMenuAuthorToAuthorListNew);
    };

    return (
        <FormContentsGroup>
            <FormContents>
                <table className="form">
                    <colgroup>
                        <col style={{ width: "150px" }} />
                        <col />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>{message.get("domain.main.tbSysMenu.upperMenu", "상위 메뉴")}</th>
                            <td>
                                <InputGroup
                                    name="codeGroup"
                                    // value={tbSysMenu.menuPath || ""}
                                    // maxLength={TextUtil.getMaxByteLength(tbSysMenu.codeGroup, 200)}
                                    // onChange={handleTbSysMenuInputChange}
                                    readOnly
                                />
                                {/* <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbSysMenu.codeGroup" /> */}
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbSysMenu.menuNm", "메뉴 이름")}</th>
                            <td>
                                <TitleInputGroup
                                    systemLocaleList={props.codeDataSource.get("/common/systemLocale").list}
                                    tbSysTitleList={tbSysMenu.menuNmTitleList}
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbSysMenu.menuPath", "메뉴 경로")}</th>
                            <td>
                                <InputGroup
                                    name="codeGroup"
                                    // value={tbSysMenu.menuPath || ""}
                                    // maxLength={TextUtil.getMaxByteLength(tbSysMenu.codeGroup, 200)}
                                    // onChange={handleTbSysMenuInputChange}
                                    readOnly
                                />
                                {/* <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbSysMenu.codeGroup" /> */}
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("common.label.useYn", "사용 여부")}</th>
                            <td>
                                <Radios
                                    codeData={props.codeDataSource.get("/common/useYn")}
                                    value={tbSysMenu.useYn}
                                    inline
                                    readOnly
                                />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </FormContents>
        </FormContentsGroup>
    );
});
