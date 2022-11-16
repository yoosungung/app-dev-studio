package kr.ac.jj.survey.infrastructure.idgen.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;

/**
 * ID 생성 Mapper
 */
@MainSqlMapper
public interface IdGenerationMapper {
    /**
     * UID 조회
     *
     * @param type
     * @return
     */
    public String selectUid(@Param("type") char type);
}
