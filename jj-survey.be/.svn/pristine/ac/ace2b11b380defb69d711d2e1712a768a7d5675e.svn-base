package kr.ac.jj.survey.infrastructure.title.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.jj.survey.domain.main.model.sys.title.TbSysTitle;
import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.survey.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.survey.infrastructure.title.mapper.TitleMapper;

/**
 * 타이틀 Source
 */
@Component
public class TitleSource {
    private @Autowired TitleMapper titleMapper;

    private Map<String, String> titleSource;

    @PostConstruct
    public void reload() {
        Map<String, String> titleSource = new HashMap<String, String>();

        List<TbSysTitle> tbSysTitleList = titleMapper.selectList();

        for (TbSysTitle tbSysTitle : tbSysTitleList) {
            titleSource.put(tbSysTitle.getTitleCode() + "::" + tbSysTitle.getTitleLocale(), tbSysTitle.getTitleCn());
        }

        this.titleSource = titleSource;
    }

    public void setTitleCn(TbSysTitle tbSysTitle) {
        this.titleSource.put(tbSysTitle.getTitleCode() + "::" + tbSysTitle.getTitleLocale(), tbSysTitle.getTitleCn());
    }

    public void removeTitleCn(String titleCode) {
        String[] systemLocales = CodeDataUtil.getCodes("/common/systemLocale");

        for (String systemLocale : systemLocales) {
            this.titleSource.remove(titleCode + "::" + systemLocale);
        }
    }

    public String getTitleCn(String titleCode) {
        String titleCn = this.titleSource.get(titleCode + "::" + RequestContextUtil.getLocale());

        if (StringUtils.isNotEmpty(titleCn)) {
            return titleCn;
        }

        return this.titleSource.get(titleCode + "::ko_KR");
    }
}
