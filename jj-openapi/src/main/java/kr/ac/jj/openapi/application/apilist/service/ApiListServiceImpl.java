package kr.ac.jj.openapi.application.apilist.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.ac.jj.openapi.application.apilist.handler.ApiDataResultHandler;
import kr.ac.jj.openapi.application.apilist.model.ApiRequest;
import kr.ac.jj.openapi.domain.main.mapper.api.svc.key.hist.TbApiSvcKeyHistMapper;
import kr.ac.jj.openapi.domain.main.model.api.svc.key.hist.TbApiSvcKeyHist;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.support.io.util.IOUtil;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.handler.DataResultHandler;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;

/**
 * api 리스트 Service
 */
@Service
public class ApiListServiceImpl {

    private @Autowired @Qualifier("sqlSession.dw") SqlSession dwSqlSession;
    private @Autowired SqlSession sqlSession;
    private @Autowired TbApiSvcKeyHistMapper tbApiSvcKeyHistMapper;

    /**
     * api 테스트
     *
     * @param category
     * @param name
     * @param apiRequest
     * @return
     */
    public List<Map<String, Object>> api(String category, String name, ApiRequest apiRequest) {
        // api result
        ApiDataResultHandler resultHandler = new ApiDataResultHandler(apiRequest);

        String apiKey = apiRequest.getApiKey();
        String dataFormat = apiRequest.getDataFormat();

        Map<String, Object> parameter;

        // svcId 조회 by url
        parameter = new LinkedHashMap<String, Object>();
        parameter.put("url", "/" + category + "/" + name);
        String svcId = sqlSession.selectOne("kr.ac.jj.openapi.application.apilist.selectSvcId", parameter);

        // 사용자 조회 by ApiKey

        parameter = new LinkedHashMap<String, Object>();
        parameter.put("apiKey", apiKey);
        String personId = sqlSession.selectOne("kr.ac.jj.openapi.application.apilist.selectPersonId",
                parameter);

        // svcKeyId 조회 by ApiKey
        parameter = new LinkedHashMap<String, Object>();
        parameter.put("apiKey", apiKey);

        String svcKeyId = sqlSession.selectOne("kr.ac.jj.openapi.application.apilist.selectSvcKeyId",
                parameter);

        // api 유효성 체크
        if (!apiValidCheck(category, name, svcKeyId, dataFormat, svcId, personId)) {
            return null;
        }

        String statement = "kr.ac.jj.openapi.application.apilist." + category + "." + name;

        parameter = new LinkedHashMap<String, Object>();
        parameter.put("paging", apiRequest.getPaging());
        parameter.put("search", apiRequest.getSearch());

        dwSqlSession.select(statement, parameter, resultHandler);

        // 로그 생성
        insertCallLog(svcKeyId, svcId, personId, "000");

        return resultHandler.getResultList();
    }
    /**
     * csv 다운로드
     *
     * @param category
     * @param name
     * @param tempFile
     */
    public List<Map<String, Object>> download(String category, String name, ApiRequest apiRequest, File tempFile) {
        // api result

        Map<String, Object> parameter;
        String apiKey = apiRequest.getApiKey();
        String dataFormat = apiRequest.getDataFormat();

        // svcId 조회 by url
        parameter = new LinkedHashMap<String, Object>();
        parameter.put("url", "/" + category + "/" + name);
        String svcId = sqlSession.selectOne("kr.ac.jj.openapi.application.apilist.selectSvcId", parameter);

        // 사용자 조회 by ApiKey
        parameter = new LinkedHashMap<String, Object>();
        parameter.put("apiKey", apiKey);
        String personId = sqlSession.selectOne("kr.ac.jj.openapi.application.apilist.selectPersonId",
                parameter);

        // svcKeyId 조회 by ApiKey
        parameter = new LinkedHashMap<String, Object>();
        parameter.put("apiKey", apiKey);

        String svcKeyId = sqlSession.selectOne("kr.ac.jj.openapi.application.apilist.selectSvcKeyId",
                parameter);

        // api 유효성 체크
        if (!apiValidCheck(category, name, svcKeyId, dataFormat, svcId, personId)) {
            return null;
        }

        String statement = "kr.ac.jj.openapi.application.apilist." + category + "." + name;
        parameter = new LinkedHashMap<String, Object>();
        parameter.put("search", apiRequest.getSearch());

        FileWriter fw = null;

        try {
             fw = new FileWriter(tempFile);
        } catch (IOException e) {
            throw new BaseException(e);
        }

        BufferedWriter bw = new BufferedWriter(fw);

        dwSqlSession.select(statement, parameter, new DataResultHandler<Map<String, Object>>() {

        boolean firstRow = true;

            @Override
            public void handleResult(ResultContext<? extends Map<String, Object>> resultContext) {
                Map<String, Object> map = resultContext.getResultObject();

                int index = 1;
                if(firstRow) {
                    for(Map.Entry<String, Object> elem : map.entrySet()) {

                        if(StringUtils.equals(map.keySet().stream().findFirst().get(), elem.getKey())) {
                            try {
                                bw.write(elem.getKey());
                            } catch (IOException e) {
                                throw new BaseException(e);
                            }
                        } else if(index == map.keySet().size()){
                            try {
                                bw.write("," + elem.getKey());
                                bw.newLine();
                            } catch (IOException e) {
                                throw new BaseException(e);
                            }
                        } else {
                            try {
                                bw.write("," + elem.getKey());
                            } catch (IOException e) {
                                throw new BaseException(e);
                            }
                        }
                        index++;
                    }
                    firstRow = false;
                }

                index = 1;
                for(Map.Entry<String, Object> elem : map.entrySet()) {
                    if(StringUtils.equals(map.keySet().stream().findFirst().get(), elem.getKey())) {
                        try {
                            bw.write("" + elem.getValue());
                        } catch (IOException e) {
                            throw new BaseException(e);
                        }
                    } else if(index == map.keySet().size()){
                        try {
                            bw.write("," + elem.getValue());
                            bw.newLine();
                        } catch (IOException e) {
                            throw new BaseException(e);
                        }
                    } else {
                        try {
                            bw.write("," + elem.getValue());
                        } catch (IOException e) {
                            throw new BaseException(e);
                        }
                    }
                    index++;
                }
            }

        });

        try {
            bw.flush();
        } catch (IOException e) {
            tempFile.delete();
            throw new BaseException(e);
        }finally {
            IOUtil.closeQuietly(bw, fw);
        }
        return null;
    }

    /**
     * api 유효성 체크
     *
     * @param category
     * @param name
     * @param svcKeyId
     * @param dataFormat
     * @param svcId
     * @param personId
     * @return
     */
    public Boolean apiValidCheck(String category, String name, String svcKeyId, String dataFormat, String svcId,
            String personId) {
        Map<String, Object> parameter;

        // 001. 서비스 공개 기간
        parameter = new LinkedHashMap<String, Object>();
        parameter.put("url", "/" + category + "/" + name);
        String othbcPdYn = sqlSession.selectOne("kr.ac.jj.openapi.application.apilist.selectOthbcPdCheck",
                parameter);

        if ("N".equals(othbcPdYn)) {
            // 로그 생성
            insertCallLog(svcKeyId, svcId, personId, "001");
            // api 에러 정보
            getError(dataFormat, "001", "사용중지된 API서비스 입니다.");
            return false;
        }

        // 002. 인증키 오류
        parameter = new LinkedHashMap<String, Object>();
        parameter.put("svcKeyId", svcKeyId);
        String keyYn = sqlSession.selectOne("kr.ac.jj.openapi.application.apilist.selectKeyCheck", parameter);
        if ("N".equals(keyYn)) {
            // 로그 생성
            insertCallLog(svcKeyId, svcId, personId, "002");
            // api 에러 정보
            getError(dataFormat, "002", "인증키값이 없습니다.");
            return false;
        }

        // 003. 유효하지 않은 인증 키
        parameter = new LinkedHashMap<String, Object>();
        parameter.put("svcKeyId", svcKeyId);
        String authYn = sqlSession.selectOne("kr.ac.jj.openapi.application.apilist.selectAuthCheck", parameter);
        if ("N".equals(authYn)) {
            // 로그 생성
            insertCallLog(svcKeyId, svcId, personId, "003");
            // api 에러 정보
            getError(dataFormat, "003", "유효하지 않은 인증키 입니다.");
            return false;
        }

        // 005. 리턴 타입
        if (!(StringUtils.equalsIgnoreCase(dataFormat, "json") || StringUtils.equalsIgnoreCase(dataFormat, "xml"))) {
            // 로그 생성
            insertCallLog(svcKeyId, svcId, personId, "005");
            // api 에러 정보
            getError(dataFormat, "005", "리턴 타입이 올바르지 않습니다.");
            return false;
        }

        // 006. 정보 존재 X
        /*
         * parameter = new LinkedHashMap<String, Object>();
         *
         * parameter.put("url", "/" + category + "/" + name);
         *
         * String svcYn = sqlSession.selectOne(
         * "kr.ac.jj.openapi.application.apilist.selectSvcCheck", parameter);
         *
         * if("N".equals(svcYn)) { // 로그 생성 insertCallLog(svcKeyId, svcId, personId,
         * "006"); // api 에러 정보 getError(dataFormat, "006", "정보가 존재하지 않습니다."); return
         * false; }
         */

        // 007. 일일 호출 횟수 초과
        parameter = new LinkedHashMap<String, Object>();

        parameter.put("svcKeyId", svcKeyId);

        // 일일 호출 횟수
        int callCoPday = sqlSession.selectOne("kr.ac.jj.openapi.application.apilist.selectCallCoPday",
                parameter);

        // 당일 svcKeyId 호출 횟수
        int todayCallCnt = sqlSession.selectOne("kr.ac.jj.openapi.application.apilist.selectTodayCallCnt",
                parameter);

        if (todayCallCnt >= callCoPday) {
            // 로그 생성
            insertCallLog(svcKeyId, svcId, personId, "007");
            // api 에러 정보
            getError(dataFormat, "007", "일일 호출 횟수 초과");
            return false;
        }

        return true;
    }

    /**
     * api 에러 정보
     *
     * @param code
     * @param message
     * @return
     */
    public void getError(String dataFormat, String code, String message) {
        PrintWriter responseWriter;

        HttpServletResponse response = RequestContextUtil.getResponse();

        response.setCharacterEncoding("utf-8");
        response.setContentType(MappingJackson2JsonView.DEFAULT_CONTENT_TYPE);

        try {
            responseWriter = response.getWriter();
        } catch (IOException e) {
            throw new BaseException(e);
        }

        // 에러 정보 만들기
        if (StringUtils.equalsIgnoreCase(dataFormat, "xml")) {
            responseWriter
                    .println("<result><error><code>" + code + "</code><msg>" + message + "</msg></error></result>");
        } else {
            responseWriter.println("\"result\":{\"error\":{\"code\":\"" + code + "\", \"msg\":\"" + message + "\"}}}");
        }

    }

    /**
     * api 호출 로그 생성
     *
     * @param svcKeyId
     * @param svcId
     * @param personId
     * @param callResult
     * @return
     */
    public void insertCallLog(String svcKeyId, String svcId, String personId, String callResult) {
        TbApiSvcKeyHist tbApiSvcKeyHist = new TbApiSvcKeyHist().newId();
        tbApiSvcKeyHist.setSvcKeyId(svcKeyId);
        tbApiSvcKeyHist.setSvcId(svcId);
        tbApiSvcKeyHist.setCallUserId(personId);
        tbApiSvcKeyHist.setCallResult(callResult);
        tbApiSvcKeyHist.setCallDt(new Date());

        tbApiSvcKeyHistMapper.insert(tbApiSvcKeyHist);
    }
}