import React from 'react';
import { InputGroup, TextArea } from '@blueprintjs/core';

import StoreUtil from '../../../../common/util/StoreUtil';
import CodeDataSource from '../../../../common/codedata/CodeDataSource';
import { FormContentsGroup, FormContents } from '../../../common/layout/contents/AdminFormPageLayout';
import BbsManageModel from '../model/BbsManageModel';
import TitleInputGroup from '../../../../common/component/title/TitleInputGroup';
import Radios from '../../../../common/component/radios/Radios';

export default function BbsManageDetailFormR(props: {
    model: BbsManageModel;
    codeDataSource: CodeDataSource;
}) {
    const { messageStore: message } = StoreUtil.useStores();
    const tbComBbs = props.model.tbComBbs;
    const tbComBbsAuthorToAuthorList = props.model.tbComBbsAuthorToAuthorList;

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
                            <th>{message.get("domain.main.tbComBbs.bbsCode", "게시판 코드")}</th>
                            <td>
                                <InputGroup
                                    value={tbComBbs.bbsCode || ""}
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("admin.appmanage.bbsmanage.label.bbsPath", "게시판 경로")}</th>
                            <td>
                                <InputGroup
                                    value={`/common/bbsctt/BbsContents/${tbComBbs.bbsCode}/`}
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbComBbs.bbsNm", "게시판 이름")}</th>
                            <td>
                                <TitleInputGroup
                                    systemLocaleList={props.codeDataSource.get("/common/systemLocale").list}
                                    tbSysTitleList={tbComBbs.bbsNmTitleList}
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbComBbs.bbsDc", "게시판 설명")}</th>
                            <td>
                                <TextArea
                                    value={tbComBbs.bbsDc || ""}
                                    fill={true}
                                    rows={5}
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbComBbs.answerPosblYn", "답글 가능 여부")}</th>
                            <td>
                                <Radios
                                    codeData={props.codeDataSource.get("/common/yesNo")}
                                    value={tbComBbs.answerPosblYn}
                                    inline
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbComBbs.rdcntIndictYn", "조회수 표시 여부")}</th>
                            <td>
                                <Radios
                                    codeData={props.codeDataSource.get("/common/yesNo")}
                                    value={tbComBbs.rdcntIndictYn}
                                    inline
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbComBbs.noticeBbsYn", "공지게시판 여부")}</th>
                            <td>
                                <Radios
                                    codeData={props.codeDataSource.get("/common/yesNo")}
                                    value={tbComBbs.noticeBbsYn}
                                    inline
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbComBbs.editrApplcYn", "에디터 적용 여부")}</th>
                            <td>
                                <Radios
                                    codeData={props.codeDataSource.get("/common/yesNo")}
                                    value={tbComBbs.editrApplcYn}
                                    inline
                                    readOnly
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>{message.get("domain.main.tbComBbs.atchFilePolicy", "첨부파일 정책")}</th>
                            <td>
                                <InputGroup
                                    value={props.codeDataSource.getCodeName("/admin.appmgt.bbsmgt/atchFilePolicy", tbComBbs.atchFilePolicy)}
                                    readOnly
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
                                    <td style={{ textAlign: "center", color: `${tbComBbsAuthorToAuthor.writngPosblYn === "1" ? "blue" : "gray"}` }}>
                                        {props.codeDataSource.getCodeName("/common/yesNo", tbComBbsAuthorToAuthor.writngPosblYn)}
                                    </td>
                                    <td style={{ textAlign: "center", color: `${tbComBbsAuthorToAuthor.inqirePosblYn === "1" ? "blue" : "gray"}` }}>
                                        {props.codeDataSource.getCodeName("/common/yesNo", tbComBbsAuthorToAuthor.inqirePosblYn)}
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
