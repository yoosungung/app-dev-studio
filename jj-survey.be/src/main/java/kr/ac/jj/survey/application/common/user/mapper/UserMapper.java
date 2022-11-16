package kr.ac.jj.survey.application.common.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.BaseMap;

/**
 * 사용자 Mapper
 */
@MainSqlMapper
public interface UserMapper {
    /**
     * 검색 목록 조회
     *
     * @param search
     */
    public void selectSearchList(@Param("search") BaseMap search);

    /**
     * USER_ID 조회 - 로그인 이름
     *
     * @param loginNm
     * @return
     */
    public String selectUserIdByLoginNm(@Param("loginNm") String loginNm);

    /**
     * USER_ID 조회 - SSO 키(사번 등)
     *
     * @param ssoKey
     * @return
     */
    public String selectUserIdBySsoKey(@Param("ssoKey") String ssoKey);

    /**
     * 권한코드 목록 조회
     *
     * @param userId
     * @return
     */
    public List<String> selectAuthorCodeList(@Param("userId") String userId);
}
