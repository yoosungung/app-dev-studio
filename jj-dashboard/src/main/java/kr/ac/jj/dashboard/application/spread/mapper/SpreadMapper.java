package kr.ac.jj.dashboard.application.spread.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.dashboard.config.datasources.DataSourceDwConfig.DwSqlMapper;

/**
 * 출신 지역 분포 Mapper
 *
 */
@DwSqlMapper
public interface SpreadMapper {

    /**
     * 조회 - 출신 지역 분포 컬럼 목록 조회
     *
     * @return
     */
    public List<String> readJunhyungNameList();

    /**
     * 조회 - 출신 지역 분포 Clusterer
     *
     * @param junhyungNameList
     * @param search
     * @return
     */
    public List<Map<String, Object>> readList(@Param("junhyungNameList") List<String> junhyungNameList,
            @Param("search") Map<String, Object> search);

}
