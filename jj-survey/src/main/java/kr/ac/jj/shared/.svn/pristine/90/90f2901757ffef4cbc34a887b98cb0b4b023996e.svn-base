package kr.ac.jj.shared.infrastructure.idgen.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;

/**
 * ID 생성 Mapper
 */
@SharedMainSqlMapper
public interface IdGenerationMapper {

    /**
     * UID 조회
     *
     * @param type
     * @return
     */
    public String selectUid(@Param("type") char type);

}
