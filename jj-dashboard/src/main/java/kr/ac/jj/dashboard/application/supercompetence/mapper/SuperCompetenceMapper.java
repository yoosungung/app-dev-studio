package kr.ac.jj.dashboard.application.supercompetence.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.dashboard.config.datasources.DataSourceDwConfig.DwSqlMapper;

/**
 * 교양교과 핵심역량 Mapper
 */
@DwSqlMapper
public interface SuperCompetenceMapper {

    /**
     * 조회 - 대학 전체 평균
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readTotalAvgData(@Param("search") Map<String, Object> search);

    /**
     * 조회 - 특정 대학 전체 평균
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readTotalAvgDaehakData(@Param("search") Map<String, Object> search);

    /**
     * 조회 - 대학 별 평균
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readAvgDaehakData(@Param("search") Map<String, Object> search);

    /**
     * 조회 - 대학 내 학년 별 평균
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readDaehakYearData(@Param("search") Map<String, Object> search);

    /**
     * 조회 - 학부 내 학년 별 평균
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readHakbuData(@Param("search") Map<String, Object> search);

}
