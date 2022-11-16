import React from 'react';
import useReactRouter from 'use-react-router';

import BlankPage from '../../../../common/view/BlankPage';
import ViewError from '../../../../common/error/ViewError';
import CodeDataSource, { CodeDataUtil } from '../../../../common/codedata/CodeDataSource';
import BbsManageModel from '../model/BbsManageModel';
import BbsManageService from '../service/BbsManageService';
import BbsManageDetailC from './BbsManageDetailC';
import BbsManageDetailU from './BbsManageDetailU';
import BbsManageDetailRD from './BbsManageDetailRD';

enum ViewMode {
    NONE, C, U, RD
}

export default function BbsManageDetail() {
    const { location, match } = useReactRouter();
    const [data, setData] = React.useState({ viewMode: ViewMode.NONE } as { model: BbsManageModel, viewMode: ViewMode, codeDataSource: CodeDataSource });
    const bbsId = match.params["bbsId"];
    const mode = location.hash;
    const bbsManageService = new BbsManageService();

    React.useEffect(() => {
    }, []);

    let _viewMode: ViewMode;

    if (bbsId === "create") {
        _viewMode = ViewMode.C;
    } else if (mode === "#U") {
        _viewMode = ViewMode.U;
    } else {
        _viewMode = ViewMode.RD;
    }

    if (data.viewMode !== _viewMode) {
        (async () => {
            const model: BbsManageModel = await bbsManageService.read(_viewMode === ViewMode.C ? null : bbsId);

            const codeDataSource = await CodeDataUtil.getCodeDataSource([
                { key: "/common/systemLocale" },
                { key: "/common/yesNo" },
                { key: "/admin.appmgt.bbsmgt/atchFilePolicy" },
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
            <BbsManageDetailC
                model={{
                    tbComBbs: data.model.tbComBbs || {},
                    tbComBbsAuthorToAuthorList: data.model.tbComBbsAuthorToAuthorList || [],
                }}
                codeDataSource={data.codeDataSource}
            />
        );
    }

    if (data.viewMode === ViewMode.U) {
        if (!data.model.editable) {
            return (
                <ViewError buttons={{ home: true, list: true, backLink: `${bbsId}` }} />
            );
        }

        return (
            <BbsManageDetailU model={data.model} codeDataSource={data.codeDataSource} />
        );
    }

    return (
        <BbsManageDetailRD model={data.model} codeDataSource={data.codeDataSource} />
    );
}
