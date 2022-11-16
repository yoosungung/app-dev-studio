package kr.ac.jj.shared.domain.main.mapper.sys.schdul;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;

/**
 * 시스템 - 스케줄 Mapper
 */
@SharedMainSqlMapper
public interface TbSysSchdulMapper extends TbSysSchdulEntityMapper {

    /**
     * ID 조회 - 스케줄 Key로
     *
     * @param schdulKey
     * @return
     */
    public String selectIdBySchdulKey(@Param("schdulKey") String schdulKey);

}
