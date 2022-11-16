package kr.ac.jj.dashboard.application.analysis.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.dashboard.application.analysis.service.AnalysisServiceImpl;

/**
 * 학습 분석 RestController
 *
 */
@RestController
@RequestMapping(path = "/analysis/Analysis", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class AnalysisRestController {

    private @Autowired AnalysisServiceImpl analysisService;

    /**
     * 조회 - 학습 분석
     *
     * @param search
     * @return
     */
    @PostMapping(path = "/readList")
    public List<Map<String, Object>> readList(@RequestBody Map<String, Object> search) {
        return analysisService.readList(search);
    }

    /**
     * 조회 - 학습 분석 (선이수 과목)
     *
     * @param search
     * @return
     */
    @PostMapping(path = "/readList2")
    public List<Map<String, Object>> readList2(@RequestBody Map<String, Object> search) {
        return analysisService.readList2(search);
    }

    /**
     * 조회 - 학습 분석 (장학금)
     *
     * @param search
     * @return
     */
    @PostMapping(path = "/readList3")
    public List<Map<String, Object>> readList3(@RequestBody Map<String, Object> search) {
        return analysisService.readList3(search);
    }

}
