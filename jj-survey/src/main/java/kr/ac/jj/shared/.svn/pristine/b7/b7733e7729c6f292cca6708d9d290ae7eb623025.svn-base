package kr.ac.jj.shared.interfaces.admin.appmanage.hrmanage.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;

/**
 * 인사정보 관리 - 직무 인터페이스 Mapper
 */
@SharedMainSqlMapper
public interface HumanResourceManageDtyIntrfcMapper {

    /**
     * 목록 생성
     *
     * @param dtySe
     * @return
     */
    public int insertList(@Param("dtySe") String dtySe);

    /**
     * 목록 수정
     *
     * @param dtySe
     * @return
     */
    public int updateList(@Param("dtySe") String dtySe);

    /**
     * 목록 수정 - 사용 여부 미사용 처리
     *
     * @param dtySe
     * @return
     */
    public int updateListUseYnTo0(@Param("dtySe") String dtySe);

}
