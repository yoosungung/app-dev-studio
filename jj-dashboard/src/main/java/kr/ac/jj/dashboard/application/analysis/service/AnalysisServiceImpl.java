package kr.ac.jj.dashboard.application.analysis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.dashboard.application.analysis.mapper.AnalysisMapper;

/**
 * 학습 분석 Service
 *
 */
@Service
public class AnalysisServiceImpl {

    private @Autowired AnalysisMapper analysisMapper;

    /**
     * 조회 - 학습 분석
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readList(Map<String, Object> search) {
        return analysisMapper.readList(search);
    }

    /**
     * 조회 - 학습 분석 (선이수 과목)
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readList2(Map<String, Object> search) {
        return analysisMapper.readList2(search);
    }

    /**
     * 조회 - 학습 분석 (장학금)
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readList3(Map<String, Object> search) {
        return analysisMapper.readList3(search);
    }

}
