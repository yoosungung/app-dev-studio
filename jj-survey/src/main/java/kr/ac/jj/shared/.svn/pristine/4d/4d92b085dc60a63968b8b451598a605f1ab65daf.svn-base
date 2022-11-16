package kr.ac.jj.shared.infrastructure.title.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.jj.shared.domain.main.model.sys.title.TbSysTitle;
import kr.ac.jj.shared.infrastructure.title.service.TitleSource;
import kr.ac.jj.shared.infrastructure.title.util.TitleUtil;

@Component
public class TitleSourceReloadInterceptor implements HandlerInterceptor {

    private @Autowired TitleSource titleSource;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        List<String> deleteCodeList = TitleUtil.getDeleteCodeList(request, false);

        if (deleteCodeList != null) {
            for (String deleteCode : deleteCodeList) {
                titleSource.removeTitleCn(deleteCode);
            }
        }

        List<TbSysTitle> udpateTitleList = TitleUtil.getUdpateTitleList(request, false);

        if (udpateTitleList != null) {
            for (TbSysTitle tbSysTitle : udpateTitleList) {
                titleSource.setTitleCn(tbSysTitle);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
