package kr.ac.jj.shared.infrastructure.idgen.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.SharedDataSourceMapperConfig.SharedSqlMapper;

/**
 * ID ์์ฑ Mapper
 */
@SharedSqlMapper
public interface IdGenerationMapper {

    /**
     * UID ์กฐํ
     *
     * @param type
     * @return
     */
    public String selectUid(@Param("type") char type);

}
