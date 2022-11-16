import React from 'react';
import { InputGroup, Button, Tag } from '@blueprintjs/core';

import CommonUtil from '../../util/CommonUtil';
import StoreUtil from '../../util/StoreUtil';
import TextUtil from '../../util/TextUtil';
import { CodeData } from '../../codedata/CodeDataSource';
import TbSysTitle from '../../../../domain/main/model/sys/title/TbSysTitle';

export default function TitleInputGroup(props: {
    systemLocaleList: CodeData[] | undefined | null;
    tbSysTitleList: TbSysTitle[] | undefined | null;
    changeList?: (tbSysTitleList: TbSysTitle[]) => void;
    readOnly?: boolean;
}) {
    const { userStore } = StoreUtil.useStores();
    const [tbSysTitleList, setTbSysTitleList] = React.useState<TbSysTitle[]>();
    const [showAllLocale, setShowAllLocale] = React.useState(false);

    const handleInputChange = (event, tbSysTitle: TbSysTitle, maxByte: number) => {
        if (!tbSysTitleList) {
            return;
        }

        const tbSysTitleListNew = tbSysTitleList.map((tbSysTitle2) => {
            if (tbSysTitle2.titleLocale === tbSysTitle.titleLocale) {
                tbSysTitle2.titleCn = TextUtil.getTruncatedValue(event.target.value, maxByte);
            }

            return tbSysTitle2;
        });

        if (props.changeList) {
            props.changeList(tbSysTitleListNew);
        } else {
            setTbSysTitleList(tbSysTitleListNew);
        }
    };

    if (!tbSysTitleList) {
        const getTitleCn = (localeCode: string) => {
            if (!props.tbSysTitleList) {
                return;
            }

            for (let i = 0; i < props.tbSysTitleList.length; i++) {
                if (props.tbSysTitleList[i].titleLocale === localeCode) {
                    return props.tbSysTitleList[i].titleCn;
                }
            }
        };

        if (props.systemLocaleList && props.systemLocaleList.length > 0) {
            setTbSysTitleList(props.systemLocaleList.map((systemLocale) => {
                return { titleLocale: systemLocale.code, titleCn: getTitleCn(systemLocale.code) } as TbSysTitle;
            }));
        }

        return null;
    }

    if (!tbSysTitleList || tbSysTitleList.length === 0) {
        return null;
    }

    if (tbSysTitleList.length === 1) {
        const tbSysTitle = tbSysTitleList[0];

        return (
            <InputGroup
                key={tbSysTitle.titleLocale}
                value={tbSysTitle.titleCn || ""}
                maxLength={TextUtil.getMaxByteLength(tbSysTitle.titleCn, 500)}
                onChange={event => handleInputChange(event, tbSysTitle, 500)}
                readOnly={props.readOnly}
            />
        );
    }

    const getLocaleNm = (localeCode) => {
        if (!props.systemLocaleList) {
            return localeCode;
        }

        for (let i = 0; i < props.systemLocaleList.length; i++) {
            if (props.systemLocaleList[i].code === localeCode) {
                return props.systemLocaleList[i].name;
            }
        }
    };

    const getInputGroup = (tbSysTitle: TbSysTitle, index: number) => {
        return (
            <div key={tbSysTitle.titleLocale}>
                {index > 0 && <div style={{ marginTop: "2px" }}></div>}
                <InputGroup
                    key={tbSysTitle.titleLocale}
                    value={tbSysTitle.titleCn || ""}
                    placeholder={props.readOnly ? "" : getLocaleNm(tbSysTitle.titleLocale)}
                    rightElement={
                        <>
                        <Tag style={{ display: "inline" }} minimal={true} title={getLocaleNm(tbSysTitle.titleLocale)}>{tbSysTitle.titleLocale}</Tag>
                        <Button icon={showAllLocale ? "arrow-up" : "arrow-down"} minimal={true} onClick={() => {
                            setShowAllLocale(!showAllLocale);
                        }} />
                        </>
                    }
                    maxLength={TextUtil.getMaxByteLength(tbSysTitle.titleCn, 500)}
                    onChange={event => handleInputChange(event, tbSysTitle, 500)}
                    readOnly={props.readOnly}
                />
            </div>
        );
    };

    if (!showAllLocale) {
        return (
            <>
            {tbSysTitleList.map((tbSysTitle, index) => {
                if (tbSysTitle.titleLocale === userStore.userLocale) {
                    return getInputGroup(tbSysTitle, index);
                }
            })}
            </>
        );
    }

    return (
        <>
        {tbSysTitleList.map((tbSysTitle, index) => {
            if (tbSysTitle.titleLocale === CommonUtil.defaultLocale) {
                return getInputGroup(tbSysTitle, index);
            }
        })}
        {tbSysTitleList.map((tbSysTitle, index) => {
            if (tbSysTitle.titleLocale !== CommonUtil.defaultLocale) {
                return getInputGroup(tbSysTitle, index);
            }
        })}
        </>
    );
}
