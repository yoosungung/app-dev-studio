package kr.ac.jj.dashboard.application.supercompetence.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.dashboard.application.supercompetence.mapper.SuperCompetenceMapper;

/**
 * 교양교과 핵심역량 Service
 */
@Service
public class SuperCompetenceServiceImpl {

    private @Autowired SuperCompetenceMapper superCompetenceMapper;

    /**
     * 조회 - 대학 전체 평균
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readTotalAvgData(Map<String, Object> search) {
        return superCompetenceMapper.readTotalAvgData(search);
    }

    /**
     * 조회 - 특정 대학 전체 평균
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readTotalAvgDaehakData(Map<String, Object> search) {
        return superCompetenceMapper.readTotalAvgDaehakData(search);
    }

    /**
     * 조회 - 대학 별 평균
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readAvgDaehakData(Map<String, Object> search) {
        return superCompetenceMapper.readAvgDaehakData(search);
    }

    /**
     * 조회 - 대학 내 학년 별 평균
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readDaehakYearData(Map<String, Object> search) {
        return superCompetenceMapper.readDaehakYearData(search);
    }

    /**
     * 조회 - 학부 내 학년 별 평균
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readHakbuData(Map<String, Object> search) {
        return superCompetenceMapper.readHakbuData(search);
    }

}
