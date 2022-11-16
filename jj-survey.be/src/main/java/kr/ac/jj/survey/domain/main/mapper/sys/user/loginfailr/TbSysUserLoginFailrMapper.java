package kr.ac.jj.survey.domain.main.mapper.sys.user.loginfailr;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;

/**
 * 시스템 - 사용자 로그인 실패 Mapper
 */
@MainSqlMapper
public interface TbSysUserLoginFailrMapper extends TbSysUserLoginFailrEntityMapper {
    /**
     * 수정 => 0
     *
     * @param loginNm
     * @return
     */
    public int updateZero(@Param("loginNm") String loginNm);
}
