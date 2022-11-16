import React from 'react';
import { InputGroup, TextArea, Radio } from '@blueprintjs/core';

import CommonUtil from '../../../../common/util/CommonUtil';
import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormContentsGroup, FormContents } from '../../../common/layout/contents/AdminFormPageLayout';
import UserManageModel from '../model/UserManageModel';
import TitleInputGroup from '../../../../common/component/title/TitleInputGroup';
import Radios from '../../../../common/component/radios/Radios';

export default function BbsManageDetailFormR(props: {
    model: UserManageModel;
    codeDataSource: CodeDataSource;
}) {
    const { messageStore: message } = StoreUtil.useStores();
    const tbComPerson = props.model.tbComPerson;

    return (
        <FormContentsGroup>
            <FormContents title={message.get("admin.appmanage.bbsmanage.label.baseInfo", "기본 정보")}>
                <table className="form">
                    <colgroup>
                        <col style={{ width: "150px" }} />
                        <col />
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>{message.get("domain.main.tbComPerson.bbsCode", "키")}</th>
                            <td>
                                <InputGroup
                                    value={tbComPerson.personId || ""}
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbComPerson.bbsNm", "이름")}</th>
                            <td>
                                <InputGroup
                                    value={tbComPerson.koreanNm || ""}
                                    readOnly
                                />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </FormContents>
        >
        </FormContentsGroup>
    );
}
