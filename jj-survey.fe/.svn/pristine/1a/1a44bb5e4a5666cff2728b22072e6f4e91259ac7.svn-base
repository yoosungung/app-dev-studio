import React from 'react';
import useReactRouter from 'use-react-router';
import useStateWithCallback from 'use-state-with-callback';
import { Divider, Intent, InputGroup, ButtonGroup, Tree, Classes, ITreeNode } from '@blueprintjs/core';
import { Link } from 'react-router-dom';

import StoreUtil from '../../../../common/util/StoreUtil';
import BlankPage from '../../../../common/view/BlankPage';
import CodeDataSource, { CodeDataUtil } from '../../../../common/codedata/CodeDataSource';
import { ListPageContainer, ListPageHeader, ListGridContainer } from '../../../common/layout/contents/AdminListPageLayout';
import { LinkButton, BaseButton } from '../../../../common/component/button/Buttons';
import Select from '../../../../common/component/select/Select';
import DataGrid from '../../../../common/component/datagrid/DataGrid';
import MenuManageService from '../service/MenuManageService';
import { FormContentsGroup, FormContents } from '../../../common/layout/contents/AdminFormPageLayout';
import ValidationMessage from '../../../../common/validation/message/ValidationMessage';
import TitleInputGroup from '../../../../common/component/title/TitleInputGroup';
import TbSysMenu from '../../../../../domain/main/model/sys/menu/TbSysMenu';
import TextUtil from '../../../../common/util/TextUtil';
import Radios from '../../../../common/component/radios/Radios';
import MenuManageModel from '../model/MenuManageModel';
import MenuManageDetail from './MenuManageDetail';

export default function MenuManageList() {
    const { match } = useReactRouter();
    const { messageStore: message, listStore } = StoreUtil.useStores();
    const [codeDataSource, setCodeDataSource] = React.useState<CodeDataSource>();
    const [menuKnd, setMenuKnd] = React.useState<string>("MAIN");
    const [treeNodeList, setTreeNodeList] = React.useState<ITreeNode<{}>[]>([]);
    const [searchData, setSearchData] = React.useState<any>({});
    const [resultData, setResultData] = React.useState({ list: [] });
    const menuManageService = new MenuManageService();

    React.useEffect(() => {
        listStore.reset();
    }, []);

    if (!codeDataSource) {
        (async () => {
            const codeDataSource = await CodeDataUtil.getCodeDataSource([
                { key: "/common/systemLocale" },
                { key: "/common/useYn" },
                { key: "/domain.main.tbSysMenu/menuKnd" },
            ]);

            setCodeDataSource(codeDataSource);
        })();

        return <BlankPage />;
    }

    const readTree = (_menuKnd?: string) => {
        if (!match.isExact) {
            return;
        }

        listStore.loadStart();

        setTreeNodeList([]);

        const searchData: any = {};
        searchData.menuKnd = (_menuKnd || menuKnd);
        searchData.menuKndNm = codeDataSource.getCodeName("/domain.main.tbSysMenu/menuKnd", searchData.menuKnd);

        menuManageService.readTree(searchData, (treeNodeList) => {
            setTreeNodeList(treeNodeList);
        });
    };

    const readList = () => {
        if (!match.isExact) {
            return;
        }

        listStore.loadStart();

        setResultData({ list: [] });

        menuManageService.readList(searchData, (resultData) => {
            setResultData(resultData);
        });
    };

    if (!listStore.loadStarted || listStore.reloadList) {
        readTree();
    }

    const handleMenuKndChange = (event) => {
        setMenuKnd(event.target.value);
        readTree(event.target.value);
    };

    const forEachNode = (nodes: ITreeNode[] | undefined, callback: (node: ITreeNode) => void) => {
        if (nodes == null) {
            return;
        }

        for (const node of nodes) {
            callback(node);
            forEachNode(node.childNodes, callback);
        }
    };

    const handleNodeClick = (nodeData: ITreeNode, _nodePath: number[], e: React.MouseEvent<HTMLElement>) => {
        const originallySelected = nodeData.isSelected;
        if (!e.shiftKey) {
            forEachNode(treeNodeList, n => (n.isSelected = false));
        }
        nodeData.isSelected = originallySelected == null ? true : !originallySelected;
        setTreeNodeList([...treeNodeList]);
    };

    const handleNodeCollapse = (nodeData: ITreeNode) => {
        nodeData.isExpanded = false;
        setTreeNodeList([...treeNodeList]);
    };

    const handleNodeExpand = (nodeData: ITreeNode) => {
        nodeData.isExpanded = true;
        setTreeNodeList([...treeNodeList]);
    };

    const DataGridAlignCenterFormatter = ({ value }) => {
        return (
            <div style={{ textAlign: "center" }}>{value}</div>
        );
    };
    console.log(treeNodeList);

    return (
        <ListPageContainer listExact={match.isExact}>
            <ListPageHeader
                title={
                    <>
                    {message.get("admin.sysmanage.menumanage.title.list", "메뉴 관리")} -
                    <span style={{ marginLeft: "5px" }}>
                        <Select
                            name="menuKnd"
                            codeData={codeDataSource.get("/domain.main.tbSysMenu/menuKnd")}
                            value={menuKnd}
                            onChange={handleMenuKndChange}
                        />
                    </span>
                    </>
                }
                buttons={
                    <ButtonGroup minimal={false} vertical={false}>
                        <LinkButton intent={Intent.PRIMARY} icon="add" text={message.get("common.label.regist", "등록")} to="create"></LinkButton>
                        <Divider />
                        <BaseButton intent={Intent.SUCCESS} icon="search" text={message.get("common.label.read", "조회")} onClick={() => {
                            readTree();
                        }}></BaseButton>
                    </ButtonGroup>
                }
            />
            <ListGridContainer>
                <div style={{ display: "flex", flexDirection: "row", height: "100%", marginTop: "1px" }}>
                    <div className="flex-0" style={{ width: "400px", position: "relative" }}>
                        <div className="tree-container" style={{ position: "absolute" }}>
                            <Tree
                                contents={treeNodeList}
                                onNodeClick={handleNodeClick}
                                onNodeCollapse={handleNodeCollapse}
                                onNodeExpand={handleNodeExpand}
                                className={Classes.ELEVATION_0}
                            />
                        </div>
                    </div>
                    <div className="flex-1">
                        <div style={{ paddingLeft: "10px", marginTop: "-10px" }}>
                            <MenuManageDetail
                                // menuId={}
                            />
                        </div>
                    </div>
                </div>
            </ListGridContainer>
        </ListPageContainer>
    );
}
