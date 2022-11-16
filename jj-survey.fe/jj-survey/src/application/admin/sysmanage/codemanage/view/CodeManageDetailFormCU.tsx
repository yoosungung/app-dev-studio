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
import CodeManageModel from '../model/CodeManageModel';
import TbSysCodeValue from '../../../../../domain/main/model/sys/code/value/TbSysCodeValue';

export default observer(function CodeManageDetailFormCU(props: {
    model: CodeManageModel;
    codeDataSource: CodeDataSource;
    setTbSysCodeGroup;
    setTbSysCodeValueList;
}) {
    const { messageStore: message, validStore } = StoreUtil.useStores();
    const tbSysCodeGroup = props.model.tbSysCodeGroup;
    const tbSysCodeValueList = props.model.tbSysCodeValueList;
    const validatorsMap = validStore.validatorsMap;

    React.useEffect(() => {
        validStore.reset();
    }, []);

    const handleTbSysCodeGroupInputChange = (event, maxByte?: number) => {
        const name = event.target.name;
        const value = CommonUtil.getElementValue(event.target, maxByte);

        handleTbSysCodeGroupValueChange(name, value);
    };

    const handleTbSysCodeGroupValueChange = (name: string, value) => {
        const tbSysCodeGroupNew = { ...tbSysCodeGroup, [name]: value };

        CodeManageModel.updateValidators(validStore, tbSysCodeGroupNew, tbSysCodeValueList, "tbSysCodeGroup." + name);

        props.setTbSysCodeGroup(tbSysCodeGroupNew);
    };

    const handleTbSysCodeValueListInputChange = (event, tbSysCodeValue: TbSysCodeValue, maxByte?: number) => {
        const name = event.target.name;

        const tbSysCodeValueListNew = tbSysCodeValueList.map((tbSysCodeValue2) => {
            if (tbSysCodeValue2._KEY === tbSysCodeValue._KEY) {
                tbSysCodeValue2[name] = CommonUtil.getElementValue(event.currentTarget, maxByte);
            }

            return tbSysCodeValue2;
        });

        CodeManageModel.updateValidators(validStore, tbSysCodeGroup, tbSysCodeValueListNew, `tbSysCodeValue.${tbSysCodeValue._KEY}.${name}`);

        props.setTbSysCodeValueList(tbSysCodeValueListNew);
    };

    const handleTbSysCodeValueListValueChange = (tbSysCodeValue: TbSysCodeValue, name: string, value) => {
        const tbSysCodeValueListNew = tbSysCodeValueList.map((tbSysCodeValue2) => {
            if (tbSysCodeValue2._KEY === tbSysCodeValue._KEY) {
                tbSysCodeValue2[name] = value;
            }

            return tbSysCodeValue2;
        });

        CodeManageModel.updateValidators(validStore, tbSysCodeGroup, tbSysCodeValueListNew, `tbSysCodeValue.${tbSysCodeValue._KEY}.${name}`);

        props.setTbSysCodeValueList(tbSysCodeValueListNew);
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
                            <th className="required">{message.get("domain.main.tbSysCodeGroup.codeGroup", "코드 그룹")}</th>
                            <td>
                                <InputGroup
                                    name="codeGroup"
                                    value={tbSysCodeGroup.codeGroup || ""}
                                    maxLength={TextUtil.getMaxByteLength(tbSysCodeGroup.codeGroup, 200)}
                                    onChange={handleTbSysCodeGroupInputChange}
                                    autoFocus
                                />
                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbSysCodeGroup.codeGroup" />
                            </td>
                        </tr>
                        <tr>
                            <th className="required">{message.get("domain.main.tbSysCodeGroup.codeGroupNm", "코드 그룹 이름")}</th>
                            <td>
                                <TitleInputGroup
                                    systemLocaleList={props.codeDataSource.get("/common/systemLocale").list}
                                    tbSysTitleList={tbSysCodeGroup.codeGroupNmTitleList}
                                    changeList={(codeGroupNmTitleList) => {
                                        handleTbSysCodeGroupValueChange("codeGroupNmTitleList", codeGroupNmTitleList);
                                    }}
                                />
                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbSysCodeGroup.codeGroupNmTitleList" />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbSysCodeGroup.codeGroupDc", "코드 그룹 설명")}</th>
                            <td>
                                <TextArea
                                    name="codeGroupDc"
                                    value={tbSysCodeGroup.codeGroupDc || ""}
                                    maxLength={TextUtil.getMaxByteLength(tbSysCodeGroup.codeGroupDc, 2000)}
                                    onChange={event => handleTbSysCodeGroupInputChange(event, 2000) }
                                    fill
                                    rows={5}
                                />
                                <TextByteIndicator value={tbSysCodeGroup.codeGroupDc} maxByte={2000} />
                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbSysCodeGroup.codeGroupDc" />
                            </td>
                        </tr>
                        <tr>
                            <th className="required">{message.get("admin.sysmanage.codemanage.title.valueList", "코드값 목록")}</th>
                            <td>
                                <ListTable
                                    data={tbSysCodeValueList}
                                    thead={
                                        <>
                                        <tr>
                                            <ListTableHeaderRowNumber />
                                            <TableGirdHeaderRowCheck data={tbSysCodeValueList} changeData={props.setTbSysCodeValueList} />
                                            <th style={{ width: "150px" }} className="required">{message.get("domain.main.tbSysCodeValue.codeValue", "코드 값")}</th>
                                            <th style={{ width: "300px" }} className="required">{message.get("domain.main.tbSysCodeValue.codeValueNm", "코드 값 이름")}</th>
                                            <th style={{ width: "*", minWidth: "200px" }}>{message.get("domain.main.tbSysCodeValue.codeValueDc", "코드 값 설명")}</th>
                                            <th style={{ width: "100px" }} className="required">{message.get("common.label.useYn", "사용 여부")}</th>
                                            <ListTableHeaderRowSort />
                                        </tr>
                                        </>
                                    }
                                    tbodyRow={(index, rowNumber) => {
                                        const tbSysCodeValue = tbSysCodeValueList[index];

                                        return (
                                            <>
                                            <ListTableBodyRowNumber index={index} rowNumber={rowNumber} />
                                            <ListTableBodyRowCheck data={tbSysCodeValueList} index={index} changeData={props.setTbSysCodeValueList} />
                                            <td>
                                                <InputGroup
                                                    name="codeValue"
                                                    value={tbSysCodeValue.codeValue || ""}
                                                    maxLength={TextUtil.getMaxByteLength(tbSysCodeValue.codeValue, 100)}
                                                    style={{ textAlign: "center" }}
                                                    onChange={(event) => {
                                                        handleTbSysCodeValueListInputChange(event, tbSysCodeValue, 100);
                                                    }}
                                                />
                                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey={`tbSysCodeValue.${tbSysCodeValue._KEY}.codeValue`} />
                                            </td>
                                            <td>
                                                <TitleInputGroup
                                                    systemLocaleList={props.codeDataSource.get("/common/systemLocale").list}
                                                    tbSysTitleList={tbSysCodeValue.codeValueNmTitleList}
                                                    changeList={(codeValueNmTitleList) => {
                                                        handleTbSysCodeValueListValueChange(tbSysCodeValue, "codeValueNmTitleList", codeValueNmTitleList);
                                                    }}
                                                />
                                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey={`tbSysCodeValue.${tbSysCodeValue._KEY}.codeValueNmTitleList`} />
                                            </td>
                                            <td>
                                                <InputGroup
                                                    name="codeValueDc"
                                                    value={tbSysCodeValue.codeValueDc || ""}
                                                    maxLength={TextUtil.getMaxByteLength(tbSysCodeValue.codeValueDc, 2000)}
                                                    onChange={(event) => {
                                                        handleTbSysCodeValueListInputChange(event, tbSysCodeValue, 2000);
                                                    }}
                                                />
                                            </td>
                                            <td style={{ textAlign: "center" }}>
                                                <Checkbox
                                                    name="useYn"
                                                    checked={tbSysCodeValue.useYn === "1"}
                                                    style={{ paddingTop: "5px" }}
                                                    onChange={(event) => {
                                                        handleTbSysCodeValueListInputChange(event, tbSysCodeValue);
                                                    }}
                                                />
                                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey={`tbSysCodeValue.${tbSysCodeValue._KEY}.useYn`} />
                                            </td>
                                            <ListTableBodyRowSort data={tbSysCodeValueList} index={index} rowNumber={rowNumber} sortColumnName="sortOrdr" changeData={props.setTbSysCodeValueList} />
                                            </>
                                        );
                                    }}
                                    sortColumnName="sortOrdr"
                                    addButtonClick={(tbSysCodeValueNew: TbSysCodeValue) => {
                                        let sortOrdrMax = 0;

                                        tbSysCodeValueList.forEach((tbSysCodeValue) => {
                                            if (tbSysCodeValue._JOB_TYPE != DataJobTypes.DELETE) {
                                                sortOrdrMax = Math.max(tbSysCodeValue.sortOrdr!, sortOrdrMax);
                                            }
                                        });

                                        tbSysCodeValueNew.sortOrdr = sortOrdrMax + 1;
                                        tbSysCodeValueNew.useYn = "1";
                                    }}
                                    changeData={(tbSysCodeValueListNew) => {
                                        CodeManageModel.updateValidators(validStore, tbSysCodeGroup, tbSysCodeValueListNew, "tbSysCodeGroup.tbSysCodeValueList");
                                        props.setTbSysCodeValueList(tbSysCodeValueListNew);
                                    }}
                                />
                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbSysCodeGroup.tbSysCodeValueList" />
                            </td>
                        </tr>
                        <tr>
                            <th className="required">{message.get("common.label.useYn", "사용 여부")}</th>
                            <td>
                                <Radios
                                    name="useYn"
                                    codeData={props.codeDataSource.get("/common/useYn")}
                                    value={tbSysCodeGroup.useYn}
                                    onChange={handleTbSysCodeGroupInputChange}
                                    inline
                                />
                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbSysCodeGroup.useYn" inline={true} />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </FormContents>
        </FormContentsGroup>
    );
});
