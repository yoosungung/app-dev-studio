import React from 'react';
import useReactRouter from 'use-react-router';

import BlankPage from '../../../../common/view/BlankPage';
import ViewError from '../../../../common/error/ViewError';
import CodeDataSource, { CodeDataUtil } from '../../../../common/codedata/CodeDataSource';
import TbSysTitle from '../../../../../domain/main/model/sys/title/TbSysTitle';
import TitleManageModel from '../model/TitleManageModel';
import TitleManageService from '../service/TitleManageService';
import TitleManageDetailU from './TitleManageDetailU';
import TitleManageDetailRD from './TitleManageDetailR';

enum ViewMode {
    NONE, U, R
}

export default function TitleManageDetail() {
    const { location, match } = useReactRouter();
    const [data, setData] = React.useState({ viewMode: ViewMode.NONE } as { model: TitleManageModel, viewMode: ViewMode, codeDataSource: CodeDataSource });
    const titleManageService = new TitleManageService();
    const titleCode = match.params["titleCode"];
    const mode = location.hash;

    React.useEffect(() => {
    }, []);

    let _viewMode: ViewMode;

    if (mode === "#U") {
        _viewMode = ViewMode.U;
    } else {
        _viewMode = ViewMode.R;
    }

    if (data.viewMode !== _viewMode) {
        (async () => {
            const model: TitleManageModel = await titleManageService.read(titleCode);

            const codeDataSource = await CodeDataUtil.getCodeDataSource([
                { key: "/common/systemLocale" },
            ]);

            if (!model.tbSysTitleList) {
                model.tbSysTitleList = [];
            }

            const titleLocales: any[] = [];

            model.tbSysTitleList.forEach((tbSysTitle) => {
                titleLocales.push(tbSysTitle.titleLocale);
            });

            codeDataSource.get("/common/systemLocale").list.forEach((systemLocale) => {
                if (model.tbSysTitleList && titleLocales.indexOf(systemLocale.code) === -1) {
                    model.tbSysTitleList.push({
                        titleCode: titleCode,
                        titleLocale: systemLocale.code,
                    } as TbSysTitle);
                }
            });

            setData({ model: model, viewMode: _viewMode, codeDataSource: codeDataSource });
        })();

        return <BlankPage />;
    }

    if (!data.model) {
        return (
            <ViewError buttons={{ home: true, list: true }} />
        );
    }

    if (data.viewMode === ViewMode.U) {
        if (!data.model.editable) {
            return (
                <ViewError buttons={{ home: true, list: true, backLink: `${titleCode}` }} />
            );
        }

        return (
            <TitleManageDetailU model={data.model} codeDataSource={data.codeDataSource} />
        );
    }

    return (
        <TitleManageDetailRD model={data.model} codeDataSource={data.codeDataSource} />
    );
}
