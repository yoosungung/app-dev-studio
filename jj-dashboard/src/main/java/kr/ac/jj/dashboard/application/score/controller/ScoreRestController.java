package kr.ac.jj.dashboard.application.score.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.dashboard.application.score.service.ScoreServiceImpl;

/**
 * 성적 RestController
 */
@RestController
@RequestMapping(path = "/score/Score", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class ScoreRestController {

    private @Autowired ScoreServiceImpl scoreService;

    /**
     * 조회 - 연도별 취득학점
     * 
     * @param search
     * @return
     */
    @PostMapping(path = "/readScoreData")
    public List<Map<String, Object>> readScoreData(@RequestBody Map<String, Object> search) {
        return scoreService.readScoreData(search);
    }

    /**
     * 조회 - 성적별 퍼센트
     * 
     * @param search
     * @return
     */
    @PostMapping(path = "/readPercentage")
    public List<Map<String, Object>> readPercentage(@RequestBody Map<String, Object> search) {
        return scoreService.readPercentage(search);
    }

    /**
     * 조회 - 학년 별 취득학점
     * 
     * @param search
     * @return
     */
    @PostMapping(path = "/readYearScoreData")
    public List<Map<String, Object>> readYearScoreData(@RequestBody Map<String, Object> search) {
        return scoreService.readYearScoreData(search);
    }

    /**
     * 조회 - 전체 취득학점
     * 
     * @param search
     * @return
     */
    @PostMapping(path = "/readTotalYearScoreData")
    public List<Map<String, Object>> readTotalYearScoreData(@RequestBody Map<String, Object> search) {
        return scoreService.readTotalYearScoreData(search);
    }

}
