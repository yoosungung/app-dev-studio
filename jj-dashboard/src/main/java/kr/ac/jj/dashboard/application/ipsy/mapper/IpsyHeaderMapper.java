package kr.ac.jj.dashboard.application.ipsy.mapper;

import java.util.Map;

import kr.ac.jj.dashboard.config.datasources.DataSourceDwConfig.DwSqlMapper;

/**
 * 입학 분석 데이터 Header Mapper
 *
 */
@DwSqlMapper
public interface IpsyHeaderMapper {

    /**
     * 조회 - 입학 분석 데이터 Header
     *
     * @param search
     * @return
     */
    public Map<String, Object> readData();

}
