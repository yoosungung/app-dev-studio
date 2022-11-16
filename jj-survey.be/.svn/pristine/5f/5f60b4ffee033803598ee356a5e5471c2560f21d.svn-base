package kr.ac.jj.survey.domain.main.mapper.sys.user;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;

/**
 * 시스템 - 사용자 Mapper
 */
@MainSqlMapper
public interface TbSysUserMapper extends TbSysUserEntityMapper {
    /**
     * 수정 - 로그인 일시
     *
     * @param userId
     * @return
     */
    public int updateLoginDt(@Param("userId") String userId);
}
