package kr.ac.jj.dashboard.application.analysis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.dashboard.config.datasources.DataSourceDwConfig.DwSqlMapper;

/**
 * 학습 분석 Mapper
 *
 */
@DwSqlMapper
public interface AnalysisMapper {

    /**
     * 조회 - 학습 분석
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readList(@Param("search") Map<String, Object> search);


    /**
     * 조회 - 학습 분석 (선이수 과목)
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readList2(@Param("search") Map<String, Object> search);

    /**
     * 조회 - 학습 분석 (장학금)
     *
     * @param search
     * @return
     */
    public List<Map<String, Object>> readList3(@Param("search") Map<String, Object> search);

}
