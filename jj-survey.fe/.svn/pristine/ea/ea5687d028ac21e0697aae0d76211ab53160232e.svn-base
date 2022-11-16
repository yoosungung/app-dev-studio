import React from 'react';
import useReactRouter from 'use-react-router';

import { DataJobTypes } from '../../../../../infrastructure/framework/core/support/collection/BaseEntity';
import BlankPage from '../../../../common/view/BlankPage';
import ViewError from '../../../../common/error/ViewError';
import CodeDataSource, { CodeDataUtil } from '../../../../common/codedata/CodeDataSource';
import TbSysCodeGroup from '../../../../../domain/main/model/sys/code/group/TbSysCodeGroup';
import TbSysCodeValue from '../../../../../domain/main/model/sys/code/value/TbSysCodeValue';
import CodeManageModel from '../model/CodeManageModel';
import CodeManageService from '../service/CodeManageService';
import CodeManageDetailC from './CodeManageDetailC';
import CodeManageDetailU from './CodeManageDetailU';
import CodeManageDetailRD from './CodeManageDetailRD';

enum ViewMode {
    NONE, C, U, RD
}

export default function CodeManageDetail() {
    const { location, match } = useReactRouter();
    const [data, setData] = React.useState({ viewMode: ViewMode.NONE } as { model: CodeManageModel, viewMode: ViewMode, codeDataSource: CodeDataSource });
    const codeManageService = new CodeManageService();
    const codeGroupId = match.params["codeGroupId"];
    const mode = location.hash;

    React.useEffect(() => {
    }, []);

    let _viewMode: ViewMode;

    if (codeGroupId === "create") {
        _viewMode = ViewMode.C;
    } else if (mode === "#U") {
        _viewMode = ViewMode.U;
    } else {
        _viewMode = ViewMode.RD;
    }

    if (data.viewMode !== _viewMode) {
        (async () => {
            const model: CodeManageModel = await codeManageService.read(_viewMode === ViewMode.C ? null : codeGroupId);

            if (!model.tbSysCodeGroup) {
                model.tbSysCodeGroup = { useYn: "1" } as TbSysCodeGroup;
            }

            if (!model.tbSysCodeValueList) {
                model.tbSysCodeValueList = [{
                    _JOB_TYPE: DataJobTypes.CREATE,
                    useYn: "1"
                }] as TbSysCodeValue[];
            } else {
                for (let i = 0; i < model.tbSysCodeValueList.length; i++) {
                    const tbSysCodeValue = model.tbSysCodeValueList[i];
                    tbSysCodeValue._JOB_TYPE = DataJobTypes.UPDATE;
                    tbSysCodeValue.sortOrdr = i + 1;
                }
            }

            const codeDataSource = await CodeDataUtil.getCodeDataSource([
                { key: "/common/systemLocale" },
                { key: "/common/useYn" },
            ]);

            setData({ model: model, viewMode: _viewMode, codeDataSource: codeDataSource });
        })();

        return <BlankPage />;
    }

    if (!data.model) {
        return (
            <ViewError buttons={{ home: true, list: true }} />
        );
    }

    if (data.viewMode === ViewMode.C) {
        return (
            <CodeManageDetailC
                model={{
                    tbSysCodeGroup: data.model.tbSysCodeGroup || {},
                    tbSysCodeValueList: data.model.tbSysCodeValueList || [],
                }}
                codeDataSource={data.codeDataSource}
            />
        );
    }

    if (data.viewMode === ViewMode.U) {
        if (!data.model.editable) {
            return (
                <ViewError buttons={{ home: true, list: true, backLink: `${codeGroupId}` }} />
            );
        }

        return (
            <CodeManageDetailU model={data.model} codeDataSource={data.codeDataSource} />
        );
    }

    return (
        <CodeManageDetailRD model={data.model} codeDataSource={data.codeDataSource} />
    );
}
