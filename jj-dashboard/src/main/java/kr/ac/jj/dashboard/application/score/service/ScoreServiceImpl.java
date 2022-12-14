package kr.ac.jj.dashboard.application.score.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.dashboard.application.score.mapper.ScoreMapper;

/**
 * 성적 Service
 */
@Service
public class ScoreServiceImpl {

    private @Autowired ScoreMapper scoreMapper;

    /**
     * 조회 - 연도별 취득학점
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readScoreData(Map<String, Object> search) {
        return scoreMapper.readScoreData(search);
    }

    /**
     * 조회 - 성적별 퍼센트
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readPercentage(Map<String, Object> search) {
        return scoreMapper.readPercentage(search);
    }

    /**
     * 조회 - 학년 별 취득학점
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readYearScoreData(Map<String, Object> search) {
        if(search.get("clickedYear") != null && !search.get("clickedYear").toString().equals("")) {
            search.put("startStdYy", search.get("clickedYear"));
            search.put("endStdYy", search.get("clickedYear"));
        }
        return scoreMapper.readYearScoreData(search);
    }

    /**
     * 조회 - 전체 취득학점
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readTotalYearScoreData(Map<String, Object> search) {
        return scoreMapper.readTotalYearScoreData(search);
    }

}
