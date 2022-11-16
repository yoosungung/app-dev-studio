import React from 'react';
import { InputGroup, TextArea } from '@blueprintjs/core';

import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import ListTable, { ListTableHeaderRowNumber, ListTableBodyRowNumber } from '../../../../common/component/listtable/ListTable';
import { FormContentsGroup, FormContents } from '../../../common/layout/contents/AdminFormPageLayout';
import MenuManageModel from '../model/MenuManageModel';
import TitleInputGroup from '../../../../common/component/title/TitleInputGroup';
import Radios from '../../../../common/component/radios/Radios';

export default function MenuManageDetailFormR(props: {
    model: MenuManageModel;
    codeDataSource: CodeDataSource;
}) {
    const { messageStore: message } = StoreUtil.useStores();
    const tbSysMenu = props.model.tbSysMenu;
    const tbSysMenuAuthorToAuthorList = props.model.tbSysMenuAuthorToAuthorList;

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
}
