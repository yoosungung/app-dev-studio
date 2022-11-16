import React from 'react';
import { observer } from 'mobx-react-lite';
import { InputGroup, TextArea, Checkbox } from '@blueprintjs/core';

import CommonUtil from '../../../../common/util/CommonUtil';
import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import TextUtil from '../../../../common/util/TextUtil';
import { FormContentsGroup, FormContents } from '../../../common/layout/contents/AdminFormPageLayout';
import TextByteIndicator from '../../../../common/component/text/TextByteIndicator';
import Select from '../../../../common/component/select/Select';
import Radios from '../../../../common/component/radios/Radios';
import TitleInputGroup from '../../../../common/component/title/TitleInputGroup';
import ValidationMessage from '../../../../common/validation/message/ValidationMessage';
import BbsManageModel from '../model/BbsManageModel';
import TbComBbsAuthorToAuthor from '../../../../../domain/main/model/com/bbs/author/TbComBbsAuthorToAuthor';

export default observer(function BbsManageDetailFormCU(props: {
    model: BbsManageModel;
    codeDataSource: CodeDataSource;
    setTbComBbs;
    setTbComBbsAuthorToAuthorList;
}) {
    const { messageStore: message, validStore } = StoreUtil.useStores();
    const tbComBbs = props.model.tbComBbs;
    const tbComBbsAuthorToAuthorList = props.model.tbComBbsAuthorToAuthorList;
    const validatorsMap = validStore.validatorsMap;

    React.useEffect(() => {
        validStore.reset();
    }, []);

    const handleTbComBbsInputChange = (event, maxByte?: number) => {
        const name = event.target.name;
        const value = CommonUtil.getElementValue(event.target, maxByte);

        handleTbComBbsValueChange(name, value);
    };

    const handleTbComBbsValueChange = (name: string, value) => {
        const tbComBbsNew = { ...tbComBbs, [name]: value };

        BbsManageModel.updateValidators(validStore, tbComBbsNew, "tbComBbs." + name);

        props.setTbComBbs(tbComBbsNew);
    };

    const handleTbComBbsAuthorToAuthorListInputChange = (event, tbComBbsAuthorToAuthor: TbComBbsAuthorToAuthor) => {
        const name = event.target.name;

        props.setTbComBbsAuthorToAuthorList(tbComBbsAuthorToAuthorList.map((tbComBbsAuthorToAuthor2) => {
            if (tbComBbsAuthorToAuthor2.authorId === tbComBbsAuthorToAuthor.authorId) {
                tbComBbsAuthorToAuthor2[name] = (event.currentTarget.checked ? "1" : "0");
            }

            return tbComBbsAuthorToAuthor2;
        }));
    };

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
                            <th className="required">{message.get("domain.main.tbComBbs.bbsCode", "게시판 코드")}</th>
                            <td>
                                <InputGroup
                                    name="bbsCode"
                                    value={tbComBbs.bbsCode || ""}
                                    maxLength={TextUtil.getMaxByteLength(tbComBbs.bbsCode, 10)}
                                    onChange={handleTbComBbsInputChange}
                                    autoFocus
                                />
                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbComBbs.bbsCode" />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("admin.appmanage.bbsmanage.label.bbsPath", "게시판 경로")}</th>
                            <td>
                                <InputGroup
                                    value={tbComBbs.bbsCode && `/common/bbsctt/BbsContents/${tbComBbs.bbsCode}/`}
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th className="required">{message.get("domain.main.tbComBbs.bbsNm", "게시판 이름")}</th>
                            <td>
                                <TitleInputGroup
                                    systemLocaleList={props.codeDataSource.get("/common/systemLocale").list}
                                    tbSysTitleList={tbComBbs.bbsNmTitleList}
                                    changeList={(bbsNmTitleList) => {
                                        handleTbComBbsValueChange("bbsNmTitleList", bbsNmTitleList);
                                    }}
                                />
                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbComBbs.bbsNmTitleList" />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbComBbs.bbsDc", "게시판 설명")}</th>
                            <td>
                                <TextArea
                                    name="bbsDc"
                                    value={tbComBbs.bbsDc || ""}
                                    maxLength={TextUtil.getMaxByteLength(tbComBbs.bbsDc, 1000)}
                                    onChange={event => handleTbComBbsInputChange(event, 1000) }
                                    fill={true}
                                    rows={5}
                                />
                                <TextByteIndicator value={tbComBbs.bbsDc} maxByte={1000} />
                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbComBbs.bbsDc" />
                            </td>
                        </tr>
                        <tr>
                            <th className="required">{message.get("domain.main.tbComBbs.answerPosblYn", "답글 가능 여부")}</th>
                            <td>
                                <Radios
                                    name="answerPosblYn"
                                    codeData={props.codeDataSource.get("/common/yesNo")}
                                    value={tbComBbs.answerPosblYn}
                                    onChange={handleTbComBbsInputChange}
                                    inline
                                />
                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbComBbs.answerPosblYn" inline={true} />
                            </td>
                        </tr>
                        <tr>
                            <th className="required">{message.get("domain.main.tbComBbs.rdcntIndictYn", "조회수 표시 여부")}</th>
                            <td>
                                <Radios
                                    name="rdcntIndictYn"
                                    codeData={props.codeDataSource.get("/common/yesNo")}
                                    value={tbComBbs.rdcntIndictYn}
                                    onChange={handleTbComBbsInputChange}
                                    inline
                                />
                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbComBbs.rdcntIndictYn" inline={true} />
                            </td>
                        </tr>
                        <tr>
                            <th className="required">{message.get("domain.main.tbComBbs.noticeBbsYn", "공지게시판 여부")}</th>
                            <td>
                                <Radios
                                    name="noticeBbsYn"
                                    codeData={props.codeDataSource.get("/common/yesNo")}
                                    value={tbComBbs.noticeBbsYn}
                                    onChange={handleTbComBbsInputChange}
                                    inline
                                />
                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbComBbs.noticeBbsYn" inline={true} />
                            </td>
                        </tr>
                        <tr>
                            <th className="required">{message.get("domain.main.tbComBbs.editrApplcYn", "에디터 적용 여부")}</th>
                            <td>
                                <Radios
                                    name="editrApplcYn"
                                    codeData={props.codeDataSource.get("/common/yesNo")}
                                    value={tbComBbs.editrApplcYn}
                                    onChange={handleTbComBbsInputChange}
                                    inline
                                />
                                <ValidationMessage validatorsMap={validatorsMap} validatorsKey="tbComBbs.editrApplcYn" inline={true} />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbComBbs.atchFilePolicy", "첨부파일 정책")}</th>
                            <td>
                                <Select
                                    name="atchFilePolicy"
                                    codeData={props.codeDataSource.get("/admin.appmgt.bbsmgt/atchFilePolicy")}
                                    firstName="none"
                                    value={tbComBbs.atchFilePolicy}
                                    onChange={handleTbComBbsInputChange}
                                />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </FormContents>
            <FormContents title={message.get("admin.appmanage.bbsmanage.label.authList", "사용 권한")}>
                <table className="list bp3-html-table bp3-html-table-bordered bp3-html-table-condensed bp3-small">
                    <thead>
                        <tr>
                            <th style={{ width: "150px" }}>{message.get("common.label.no", "No")}</th>
                            <th style={{ width: "*" }}>{message.get("domain.main.tbSysAuthor.authorNm", "권한 이름")}</th>
                            <th style={{ width: "100px" }}>{message.get("domain.main.tbComBbsAuthor.writngPosblYn", "작성 가능")}</th>
                            <th style={{ width: "100px" }}>{message.get("domain.main.tbComBbsAuthor.inqirePosblYn", "조회 가능")}</th>
                        </tr>
                    </thead>
                    <tbody>
                        {tbComBbsAuthorToAuthorList && tbComBbsAuthorToAuthorList.map((tbComBbsAuthorToAuthor, index) => {
                            return (
                                <tr key={tbComBbsAuthorToAuthor.authorId}>
                                    <td style={{ textAlign: "center" }}>
                                        {index + 1}
                                    </td>
                                    <td>
                                        {tbComBbsAuthorToAuthor.authorNm}
                                    </td>
                                    <td style={{ textAlign: "center" }}>
                                        <Checkbox
                                            name="writngPosblYn"
                                            checked={tbComBbsAuthorToAuthor.writngPosblYn === "1"}
                                            onChange={(event) => {
                                                handleTbComBbsAuthorToAuthorListInputChange(event, tbComBbsAuthorToAuthor);
                                            }}
                                        />
                                    </td>
                                    <td style={{ textAlign: "center" }}>
                                        <Checkbox
                                            name="inqirePosblYn"
                                            checked={tbComBbsAuthorToAuthor.inqirePosblYn === "1"}
                                            onChange={(event) => {
                                                handleTbComBbsAuthorToAuthorListInputChange(event, tbComBbsAuthorToAuthor);
                                            }}
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
});
