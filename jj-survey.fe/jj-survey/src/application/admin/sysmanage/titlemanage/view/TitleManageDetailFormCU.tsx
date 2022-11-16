import React from 'react';
import { observer } from 'mobx-react-lite';
import { InputGroup } from '@blueprintjs/core';

import CommonUtil from '../../../../common/util/CommonUtil';
import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormContentsGroup, FormContents } from '../../../common/layout/contents/AdminFormPageLayout';
import ValidationMessage from '../../../../common/validation/message/ValidationMessage';
import TitleManageModel from '../model/TitleManageModel';
import TextUtil from '../../../../common/util/TextUtil';
import TbSysTitle from '../../../../../domain/main/model/sys/title/TbSysTitle';

export default observer(function TitleManageDetailFormCU(props: {
    model: TitleManageModel;
    codeDataSource: CodeDataSource;
    setTbSysTitleList;
}) {
    const { validStore } = StoreUtil.useStores();
    const validatorsMap = validStore.validatorsMap;

    React.useEffect(() => {
        validStore.reset();
    }, []);

    const handleInputChange = (event, tbSysTitle: TbSysTitle, maxByte: number) => {
        if (!props.model.tbSysTitleList) {
            return;
        }

        const tbSysTitleListNew = props.model.tbSysTitleList.map((tbSysTitle2) => {
            if (tbSysTitle2.titleLocale === tbSysTitle.titleLocale) {
                tbSysTitle2.titleCn = TextUtil.getTruncatedValue(event.target.value, maxByte);
            }

            return tbSysTitle2;
        });

        TitleManageModel.updateValidators(validStore, tbSysTitleListNew, `tbSysTitle.${CommonUtil.defaultLocale}.titleCn`);

        if (props.setTbSysTitleList) {
            props.setTbSysTitleList(tbSysTitleListNew);
        }
    };

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
                                    <th className={tbSysTitle.titleLocale === CommonUtil.defaultLocale ? "required" : ""}>{`${getLocaleNm(tbSysTitle.titleLocale)} : ${tbSysTitle.titleLocale}`}</th>
                                    <td>
                                        <InputGroup
                                            value={tbSysTitle.titleCn || ""}
                                            maxLength={TextUtil.getMaxByteLength(tbSysTitle.titleCn, 500)}
                                            onChange={event => handleInputChange(event, tbSysTitle, 500)}
                                        />
                                        {tbSysTitle.titleLocale === CommonUtil.defaultLocale &&
                                            <ValidationMessage validatorsMap={validatorsMap} validatorsKey={`tbSysTitle.${CommonUtil.defaultLocale}.titleCn`} />
                                        }
                                    </td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            </FormContents>
        </FormContentsGroup>
    );
});
