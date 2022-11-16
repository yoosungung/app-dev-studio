package kr.ac.jj.shared.domain.main.mapper.sys.user;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.sys.user.TbSysUser;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.handler.DataResultHandler;

/**
 * 시스템 - 사용자 Mapper
 */
@SharedMainSqlMapper
public interface TbSysUserMapper extends TbSysUserEntityMapper {

    /**
     * 삭제 목록 조회
     *
     * @param userPersonSes
     * @param resultHandler
     */
    public void selectDeleteList(@Param("userPersonSes") String[] userPersonSes,
            DataResultHandler<TbSysUser> resultHandler);

    /**
     * 수정 - 로그인 일시
     *
     * @param userId
     * @return
     */
    public int updateLoginDt(@Param("userId") String userId);

}
