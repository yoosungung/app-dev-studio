package kr.ac.jj.dashboard.application.entrance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.dashboard.config.datasources.DataSourceDwConfig.DwSqlMapper;

/**
 * 입학 데이터 Mapper
 *
 */
@DwSqlMapper
public interface EntranceMapper {

    /**
     * 조회 - 입학 데이터 Sankey 차트
     * 
     * @param search
     * @return
     */
    public List<Map<String, Object>> readList(@Param("search") Map<String, Object> search);

}
