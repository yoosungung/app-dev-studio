import React from 'react';
import useReactRouter from 'use-react-router';

import BlankPage from '../../../../common/view/BlankPage';
import ViewError from '../../../../common/error/ViewError';
import CodeDataSource, { CodeDataUtil } from '../../../../common/codedata/CodeDataSource';
import UserManageModel from '../model/UserManageModel';
import UserManageService from '../service/UserManageService';
import BbsManageDetailRD from './UserManageDetailRD';

enum ViewMode {
    NONE, C, U, RD
}

export default function BbsManageDetail() {
    const { location, match } = useReactRouter();
    const [data, setData] = React.useState({ viewMode: ViewMode.NONE } as { model: UserManageModel, viewMode: ViewMode, codeDataSource: CodeDataSource });
    const personId = match.params["personId"];
    const mode = location.hash;
    const bbsManageService = new UserManageService();

    React.useEffect(() => {
    }, []);

    let _viewMode: ViewMode;

    if (personId === "create") {
        _viewMode = ViewMode.C;
    } else if (mode === "#U") {
        _viewMode = ViewMode.U;
    } else {
        _viewMode = ViewMode.RD;
    }

    if (data.viewMode !== _viewMode) {
        (async () => {
            const model: UserManageModel = await bbsManageService.read(_viewMode === ViewMode.C ? null : personId);

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

    return (
        <BbsManageDetailRD model={data.model} codeDataSource={data.codeDataSource} />
    );
}
