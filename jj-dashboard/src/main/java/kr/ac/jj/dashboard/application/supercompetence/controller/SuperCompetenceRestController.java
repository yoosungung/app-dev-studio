package kr.ac.jj.dashboard.application.supercompetence.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.jj.dashboard.application.supercompetence.service.SuperCompetenceServiceImpl;

/**
 * 교양교과 핵심역량 RestController
 */
@RestController
@RequestMapping(path = "/supercompetence/SuperCompetence", consumes = { MediaType.APPLICATION_JSON_VALUE })
public class SuperCompetenceRestController {

    private @Autowired SuperCompetenceServiceImpl superCompetenceService;

    /**
     * 조회 - 대학 전체 평균
     *
     * @param search
     * @return
     */
    @PostMapping(path = "/readTotalAvgData")
    public List<Map<String, Object>> readTotalAvgData(@RequestBody Map<String, Object> search) {
        return superCompetenceService.readTotalAvgData(search);
    }

    /**
     * 조회 - 특정 대학 전체 평균
     *
     * @param search
     * @return
     */
    @PostMapping(path = "/readTotalAvgDaehakData")
    public List<Map<String, Object>> readTotalAvgDaehakData(@RequestBody Map<String, Object> search) {
        return superCompetenceService.readTotalAvgDaehakData(search);
    }


    /**
     * 조회 - 대학 별 평균
     *
     * @param search
     * @return
     */
    @PostMapping(path = "/readAvgDaehakData")
    public List<Map<String, Object>> readAvgDaehakData(@RequestBody Map<String, Object> search) {
        return superCompetenceService.readAvgDaehakData(search);
    }

    /**
     * 조회 - 대학 내 학년 별 평균
     *
     * @param search
     * @return
     */
    @PostMapping(path = "/readDaehakYearData")
    public List<Map<String, Object>> readDaehakYearData(@RequestBody Map<String, Object> search) {
        return superCompetenceService.readDaehakYearData(search);
    }

    /**
     * 조회 - 학부 내 학년 별 평균
     *
     * @param search
     * @return
     */
    @PostMapping(path = "/readHakbuData")
    public List<Map<String, Object>> readHakbuData(@RequestBody Map<String, Object> search) {
        return superCompetenceService.readHakbuData(search);
    }

}
