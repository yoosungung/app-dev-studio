package kr.ac.jj.dashboard.application.score.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.dashboard.config.datasources.DataSourceDwConfig.DwSqlMapper;

/**
 * 성적 Mapper
 */
@DwSqlMapper
public interface ScoreMapper {

    /**
     * 조회 - 연도별 취득학점
     * 
     * @param search
     * @return
     */
    public List<Map<String, Object>> readScoreData(@Param("search") Map<String, Object> search);

    /**
     * 조회 - 성적별 퍼센트
     * 
     * @param search
     * @return
     */
    public List<Map<String, Object>> readPercentage(@Param("search") Map<String, Object> search);

    /**
     * 조회 - 학년별 취득학점
     * 
     * @param search
     * @return
     */
    public List<Map<String, Object>> readYearScoreData(@Param("search") Map<String, Object> search);

    /**
     * 조회 - 전체 취득학점
     * 
     * @param search
     * @return
     */
    public List<Map<String, Object>> readTotalYearScoreData(@Param("search") Map<String, Object> search);

}
