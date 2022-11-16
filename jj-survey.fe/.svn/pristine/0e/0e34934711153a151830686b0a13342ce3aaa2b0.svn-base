import React from 'react';
import { InputGroup, TextArea } from '@blueprintjs/core';

import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import ListTable, { ListTableHeaderRowNumber, ListTableBodyRowNumber } from '../../../../common/component/listtable/ListTable';
import { FormContentsGroup, FormContents } from '../../../common/layout/contents/AdminFormPageLayout';
import CodeManageModel from '../model/CodeManageModel';
import TitleInputGroup from '../../../../common/component/title/TitleInputGroup';
import Radios from '../../../../common/component/radios/Radios';

export default function CodeManageDetailFormR(props: {
    model: CodeManageModel;
    codeDataSource: CodeDataSource;
}) {
    const { messageStore: message } = StoreUtil.useStores();
    const tbSysCodeGroup = props.model.tbSysCodeGroup;
    const tbSysCodeValueList = props.model.tbSysCodeValueList;

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
                            <th>{message.get("domain.main.tbSysCodeGroup.codeGroup", "코드 그룹")}</th>
                            <td>
                                <InputGroup
                                    value={tbSysCodeGroup.codeGroup || ""}
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbSysCodeGroup.codeGroupNm", "코드 그룹 이름")}</th>
                            <td>
                                <TitleInputGroup
                                    systemLocaleList={props.codeDataSource.get("/common/systemLocale").list}
                                    tbSysTitleList={tbSysCodeGroup.codeGroupNmTitleList}
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbSysCodeGroup.codeGroupDc", "코드 그룹 설명")}</th>
                            <td>
                                <TextArea
                                    value={tbSysCodeGroup.codeGroupDc || ""}
                                    fill
                                    rows={5}
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("admin.sysmanage.codemanage.title.valueList", "코드값 목록")}</th>
                            <td>
                                <ListTable
                                    data={tbSysCodeValueList}
                                    thead={
                                        <>
                                        <tr>
                                            <ListTableHeaderRowNumber />
                                            <th style={{ width: "150px" }}>{message.get("domain.main.tbSysCodeValue.codeValue", "코드 값")}</th>
                                            <th style={{ width: "300px" }}>{message.get("domain.main.tbSysCodeValue.codeValueNm", "코드 값 이름")}</th>
                                            <th style={{ width: "*", minWidth: "200px" }}>{message.get("domain.main.tbSysCodeValue.codeValueDc", "코드 값 설명")}</th>
                                            <th style={{ width: "100px" }}>{message.get("common.label.useYn", "사용 여부")}</th>
                                        </tr>
                                        </>
                                    }
                                    tbodyRow={(index, rowNumber) => {
                                        const tbSysCodeValue = tbSysCodeValueList[index];

                                        return (
                                            <>
                                            <ListTableBodyRowNumber index={index} rowNumber={rowNumber} />
                                            <td>
                                                <InputGroup
                                                    value={tbSysCodeValue.codeValue || ""}
                                                    style={{ textAlign: "center" }}
                                                    readOnly
                                                />
                                            </td>
                                            <td>
                                                <TitleInputGroup
                                                    systemLocaleList={props.codeDataSource.get("/common/systemLocale").list}
                                                    tbSysTitleList={tbSysCodeValue.codeValueNmTitleList}
                                                    readOnly
                                                />
                                            </td>
                                            <td>
                                                <InputGroup
                                                    value={tbSysCodeValue.codeValueDc || ""}
                                                    readOnly
                                                />
                                            </td>
                                            <td>
                                                <InputGroup
                                                    value={props.codeDataSource.getCodeName("/common/useYn", tbSysCodeValue.useYn)}
                                                    style={{ textAlign: "center", color: `${tbSysCodeValue.useYn === "1" ? "blue" : "gray"}` }}
                                                    readOnly
                                                />
                                            </td>
                                            </>
                                        );
                                    }}
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("common.label.useYn", "사용 여부")}</th>
                            <td>
                                <Radios
                                    codeData={props.codeDataSource.get("/common/useYn")}
                                    value={tbSysCodeGroup.useYn}
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
