import React from 'react';
import useReactRouter from 'use-react-router';

import { DataJobTypes } from '../../../../../infrastructure/framework/core/support/collection/BaseEntity';
import BlankPage from '../../../../common/view/BlankPage';
import ViewError from '../../../../common/error/ViewError';
import CodeDataSource, { CodeDataUtil } from '../../../../common/codedata/CodeDataSource';
import TbSysMenu from '../../../../../domain/main/model/sys/menu/TbSysMenu';
import TbSysMenuAuthorToAuthor from '../../../../../domain/main/model/sys/menu/author/TbSysMenuAuthorToAuthor';
import MenuManageModel from '../model/MenuManageModel';
import MenuManageService from '../service/MenuManageService';
import MenuManageDetailC from './MenuManageDetailC';
import MenuManageDetailU from './MenuManageDetailU';
import MenuManageDetailRD from './MenuManageDetailRD';

enum ViewMode {
    NONE, C, U, RD
}

export default function MenuManageDetail(props: {
    menuId?: string;
}) {
    const { location, match } = useReactRouter();
    const [data, setData] = React.useState({ viewMode: ViewMode.NONE } as { model: MenuManageModel, viewMode: ViewMode, codeDataSource: CodeDataSource });
    const menuManageService = new MenuManageService();
    const menuId = props.menuId;
    const mode = location.hash;

    React.useEffect(() => {
    }, []);

    let _viewMode: ViewMode;

    if (menuId === "create") {
        _viewMode = ViewMode.C;
    } else if (mode === "#U") {
        _viewMode = ViewMode.U;
    } else {
        _viewMode = ViewMode.RD;
    }

    if (data.viewMode !== _viewMode) {
        if (menuId) {
            (async () => {
                const model: MenuManageModel = await menuManageService.read(_viewMode === ViewMode.C ? null : menuId);

                if (!model.tbSysMenu) {
                    model.tbSysMenu = { useYn: "1" } as TbSysMenu;
                }

                if (!model.tbSysMenuAuthorToAuthorList) {
                    model.tbSysMenuAuthorToAuthorList = [{
                        _JOB_TYPE: DataJobTypes.CREATE,
                        useYn: "1"
                    }] as TbSysMenuAuthorToAuthor[];
                } else {
                    for (let i = 0; i < model.tbSysMenuAuthorToAuthorList.length; i++) {
                        const tbSysCodeValue = model.tbSysMenuAuthorToAuthorList[i];
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
        }

        return <BlankPage />;
    }

    if (!data.model) {
        return (
            <ViewError buttons={{ home: true, list: true }} />
        );
    }

    if (data.viewMode === ViewMode.C) {
        return (
            <MenuManageDetailC
                model={{
                    tbSysMenu: data.model.tbSysMenu || {},
                    tbSysMenuAuthorToAuthorList: data.model.tbSysMenuAuthorToAuthorList || [],
                }}
                codeDataSource={data.codeDataSource}
            />
        );
    }

    if (data.viewMode === ViewMode.U) {
        if (!data.model.editable) {
            return (
                <ViewError buttons={{ home: true, list: true, backLink: `${menuId}` }} />
            );
        }

        return (
            <MenuManageDetailU model={data.model} codeDataSource={data.codeDataSource} />
        );
    }

    return (
        <MenuManageDetailRD model={data.model} codeDataSource={data.codeDataSource} />
    );
}
