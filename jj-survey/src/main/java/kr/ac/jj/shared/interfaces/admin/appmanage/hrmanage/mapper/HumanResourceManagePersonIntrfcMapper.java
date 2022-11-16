package kr.ac.jj.shared.interfaces.admin.appmanage.hrmanage.mapper;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;

/**
 * 인사정보 관리 - 사람 인터페이스 Mapper
 */
@SharedMainSqlMapper
public interface HumanResourceManagePersonIntrfcMapper {

    /**
     * 목록 생성
     *
     * @return
     */
    public int insertList();

    /**
     * 목록 수정
     *
     * @return
     */
    public int updateList();

    /**
     * 목록 수정 - 재직 구분 퇴직 처리
     *
     * @return
     */
    public int updateListHffcSttusToT();

}
