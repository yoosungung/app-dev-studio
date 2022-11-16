package kr.ac.jj.shared.domain.main.mapper.sys.intrfc;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;

/**
 * 시스템 - 인터페이스 Mapper
 */
@SharedMainSqlMapper
public interface TbSysIntrfcMapper extends TbSysIntrfcEntityMapper {

    /**
     * 전체 삭제
     *
     * @param tableNm
     * @return
     */
    public int deleteAll(@Param("tableNm") String tableNm);

}
