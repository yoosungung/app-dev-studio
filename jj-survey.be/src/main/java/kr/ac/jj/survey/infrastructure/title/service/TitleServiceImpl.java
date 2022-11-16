package kr.ac.jj.survey.infrastructure.title.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.survey.domain.main.mapper.sys.title.TbSysTitleMapper;
import kr.ac.jj.survey.domain.main.model.sys.title.TbSysTitle;
import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.survey.infrastructure.title.util.TitleUtil;

/**
 * 타이틀 Service
 */
@Service
public class TitleServiceImpl {
    private @Autowired TbSysTitleMapper tbSysTitleMapper;

    /**
     * 목록 조회
     *
     * @param titleCode
     * @return
     */
    public List<TbSysTitle> readList(String titleCode) {
        return tbSysTitleMapper.selectListByTitleCode(titleCode);
    }

    /**
     * 목록 수정
     *
     * @param titleCode
     * @param tbSysTitleList
     */
    public void updateList(String titleCode, List<TbSysTitle> tbSysTitleList) {
        tbSysTitleMapper.deleteListByTitleCode(titleCode);

        List<TbSysTitle> udpateTitleList = TitleUtil.getUdpateTitleList(RequestContextUtil.getRequest(), true);

        for (TbSysTitle tbSysTitle : tbSysTitleList) {
            if (StringUtils.isNotEmpty(tbSysTitle.getTitleCn())) {
                tbSysTitle.setTitleCode(titleCode);
                tbSysTitleMapper.insert(tbSysTitle);
                udpateTitleList.add(tbSysTitle);
            }
        }
    }

    /**
     * 목록 삭제
     *
     * @param titleCode
     */
    public void deleteList(String titleCode) {
        tbSysTitleMapper.deleteListByTitleCode(titleCode);

        List<String> deleteCodeList = TitleUtil.getDeleteCodeList(RequestContextUtil.getRequest(), true);

        deleteCodeList.add(titleCode);
    }
}
