package kr.ac.jj.survey.infrastructure.title.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.ac.jj.survey.domain.main.model.sys.title.TbSysTitle;
import kr.ac.jj.survey.infrastructure.title.service.TitleSource;

/**
 * 타이틀 Util
 */
@Component
public class TitleUtil {
    private static TitleSource titleSource;

    private TitleUtil(@Autowired TitleSource titleSource) {
        TitleUtil.titleSource = titleSource;
    }

    public static String getTitleCn(String titleCode) {
        return TitleUtil.titleSource.getTitleCn(titleCode);
    }

    public static List<TbSysTitle> getUdpateTitleList(HttpServletRequest request, boolean create) {
        @SuppressWarnings("unchecked")
        List<TbSysTitle> udpateTitleList = (List<TbSysTitle>) request.getAttribute("TbSysTitle.udpateTitleList");

        if (udpateTitleList == null && create) {
            udpateTitleList = new ArrayList<TbSysTitle>();
            request.setAttribute("TbSysTitle.udpateTitleList", udpateTitleList);
        }

        return udpateTitleList;
    }

    public static List<String> getDeleteCodeList(HttpServletRequest request, boolean create) {
        @SuppressWarnings("unchecked")
        List<String> deleteCodeList = (List<String>) request.getAttribute("TbSysTitle.deleteCodeList");

        if (deleteCodeList == null && create) {
            deleteCodeList = new ArrayList<String>();
            request.setAttribute("TbSysTitle.deleteCodeList", deleteCodeList);
        }

        return deleteCodeList;
    }
}
