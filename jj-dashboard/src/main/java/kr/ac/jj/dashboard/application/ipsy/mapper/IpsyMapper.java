package kr.ac.jj.dashboard.application.ipsy.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.dashboard.config.datasources.DataSourceDwConfig.DwSqlMapper;

/**
 * 입학 분석 데이터 Mapper
 *
 */
@DwSqlMapper
public interface IpsyMapper {

    /**
     * 조회 - 입학 분석 데이터 (1/2)
     *
     * @return
     */
    public List<Map<String, Object>> readList(@Param("search") Map<String, Object> search);

    /**
     * 조회 - 입학 분석 차트 데이터 (1/2)
     *
     * @return
     */
    public List<Map<String, Object>> readChartList();

    /**
     * 조회 - 입학 분석 데이터 (2/2)
     *
     * @return
     */
    public List<Map<String, Object>> readList2(@Param("search") Map<String, Object> search);

    /**
     * 조회 - 입학 분석 차트 데이터 (2/2)
     *
     * @return
     */
    public List<Map<String, Object>> readChartList2(@Param("search") Map<String, Object> search);

}
