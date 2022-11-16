package com.jd.survey.com.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jd.survey.zeus.domain.TbAccessLog;
import com.jd.survey.zeus.service.ZeusService;

/**
 * <b>교류정보수집</b>
 * <p>
 * 1. URL을 기준으로 사용자 교류정보를 수집합니다.
 * <p>
 * 2. URL을 기준으로 화면 공통정보를 모델에 맵핑합니다. (페이지 네비게이션, 화면명 등)
 */
@Service
public class ExchangeIntercepter implements HandlerInterceptor {

    public Logger log = LoggerFactory.getLogger(getClass());

    /** 접속URL */
    private String uri;

    @Autowired
    private ZeusService zeusService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        this.uri = request.getRequestURI();
        /* 운영서버에만 적용
         * 메뉴별 해당 uri 접속 시 zeus.tb_access_log에 조회수 증가 처리*/
        // 제우스 접속 로그
//        accesLogCount(request);

        /*방문자수*/
        TbAccessLog visitLog = zeusService.getTbAccessLogCnt();
        request.setAttribute("accessLogCnt", visitLog.getAccessCnt());
        request.setAttribute("accessLogTodayCnt", visitLog.getAccessTodayCnt());
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) {

    }

    /**
     * 매뉴별 해당 uri 접속시 zeus 접속 로그 조회수 처리
     *
     * @param request
     */
    private void accesLogCount(HttpServletRequest request) {
        /* 운영서버에만 적용
         * 메뉴별 해당 uri 접속 시 zeus.tb_access_log에 조회수 증가 처리*/
        String code = "";

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        Date currentTime = new Date();
        String mDate = mSimpleDateFormat.format(currentTime);

        // 접속로그 제외할 IP
        String[] ipArray = { "66.249.77.8", "66.249.77.17", "66.249.77.107", "66.249.74.192", "61.33.53.210",
                "211.243.66.148", "192.168.201.94", "172.16.244.182", "147.43.148.34" };
        String ip = request.getRemoteAddr();

        boolean excludeIp = false;
        for (int i = 0; i < ipArray.length; i++) {
            if (ip.equals(ipArray[i])) {
                excludeIp = true;
                // log.debug("접속로그 제외할 IP 출현!! ({})", ip);
                break;
            }
        }

        if (excludeIp != true) {

            TbAccessLog accessLog = new TbAccessLog();

            if (uri.startsWith("/equip/market") || uri.startsWith("/equip/glossary/network")) { // 제품
                code = "M022";
            } else if (uri.startsWith("/equip/organ") || uri.startsWith("/equip/inst")) { // 관리
                code = "M024";
            } else if (uri.startsWith("/equip")) { // 등록
                code = "M023";
            } else if (uri.startsWith("/oper")) { // 인력
                code = "M025";
            } else if (uri.startsWith("/stat") || uri.startsWith("/main/stat")) { // 통계
                code = "M026";
            } else {// 기타
                code = "M027";
            }
            accessLog.setAccessCode(code);
            accessLog.setAccessDt(mDate);
            zeusService.addTbAccessLog(accessLog);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) {
        // TODO Auto-generated method stub
        this.uri = null;
    }

}
