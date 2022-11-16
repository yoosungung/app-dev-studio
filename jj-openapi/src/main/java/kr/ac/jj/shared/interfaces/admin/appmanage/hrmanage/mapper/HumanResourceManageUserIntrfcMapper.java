package kr.ac.jj.shared.interfaces.admin.appmanage.hrmanage.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;

/**
 * 인사정보 관리 - 사용자 인터페이스 Mapper
 */
@SharedMainSqlMapper
public interface HumanResourceManageUserIntrfcMapper {

    /**
     * 목록 생성
     *
     * @param userPersonSes
     * @return
     */
    public int insertList(@Param("userPersonSes") String[] userPersonSes);

    /**
     * 목록 수정 - 사용 여부 미사용 처리
     *
     * @param userPersonSes
     * @return
     */
    public int updateListUseYnTo0(@Param("userPersonSes") String[] userPersonSes);

    /**
     * 목록 수정
     *
     * @return
     */
    public int updateList();

}
