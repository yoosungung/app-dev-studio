package kr.ac.jj.survey.infrastructure.idgen.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;

/**
 * ID ์์ฑ Mapper
 */
@MainSqlMapper
public interface IdGenerationMapper {
    /**
     * UID ์กฐํ
     *
     * @param type
     * @return
     */
    public String selectUid(@Param("type") char type);
}
