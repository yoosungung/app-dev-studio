import React from 'react';
import { InputGroup } from '@blueprintjs/core';

import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormContentsGroup, FormContents } from '../../../common/layout/contents/AdminFormPageLayout';
import CodeManageModel from '../model/TitleManageModel';

export default function TitleManageDetailFormR(props: {
    model: CodeManageModel;
    codeDataSource: CodeDataSource;
}) {
    const getLocaleNm = (locale) => {
        return props.codeDataSource.getCodeName("/common/systemLocale", locale);
    };

    return (
        <FormContentsGroup>
            <FormContents>
                <table className="form">
                    <colgroup>
                        <col style={{ width: "300px" }} />
                        <col />
                    </colgroup>
                    <tbody>
                        {props.model.tbSysTitleList && props.model.tbSysTitleList.map((tbSysTitle) => {
                            return (
                                <tr key={tbSysTitle.titleLocale}>
                                    <th>{`${getLocaleNm(tbSysTitle.titleLocale)} : ${tbSysTitle.titleLocale}`}</th>
                                    <td>
                                        <InputGroup
                                            value={tbSysTitle.titleCn || ""}
                                            readOnly
                                        />
                                    </td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            </FormContents>
        </FormContentsGroup>
    );
}
