package kr.ac.jj.shared.application.admin.sysmanage.titlemanage.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.admin.sysmanage.titlemanage.mapper.TitleManageMapper;
import kr.ac.jj.shared.application.admin.sysmanage.titlemanage.model.TitleManageModel;
import kr.ac.jj.shared.domain.main.mapper.sys.title.TbSysTitleMapper;
import kr.ac.jj.shared.domain.main.model.sys.title.TbSysTitle;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 타이틀 관리 Service
 */
@Service
public class TitleManageServiceImpl {

    private @Autowired TitleManageMapper titleManageMapper;
    private @Autowired TbSysTitleMapper tbSysTitleMapper;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        String titleLocalesParam = (String) gridRequest.getSearch().get("titleLocales");
        String[] titleLocales = (StringUtils.isEmpty(titleLocalesParam) ? null : titleLocalesParam.split(","));
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        titleManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch(), titleLocales);

        return resultHandler.getResultList();
    }

    /**
     * 조회
     *
     * @param titleCode
     * @return
     */
    public TitleManageModel read(String titleCode) {
        TitleManageModel model = new TitleManageModel();

        model.setTitleCode(titleCode);
        model.setTbSysTitleList(tbSysTitleMapper.selectListByTitleCode(titleCode));

        return model;
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(TitleManageModel model) {
        List<TbSysTitle> tbSysTitleList = model.getTbSysTitleList();

        tbSysTitleMapper.deleteListByTitleCode(model.getTitleCode());

        for (TbSysTitle tbSysTitle : tbSysTitleList) {
            tbSysTitle.setTitleCode(model.getTitleCode());

            if (StringUtils.isNotEmpty(tbSysTitle.getTitleCn())) {
                tbSysTitleMapper.insert(tbSysTitle);
            }
        }
    }

}
