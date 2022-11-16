package kr.ac.jj.shared.interfaces.admin.appmanage.hrmanage.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept.DeptSeEnums;

/**
 * 인사정보 관리 - 사람별 부서 인터페이스 Mapper
 */
@SharedMainSqlMapper
public interface HumanResourceManagePersonDeptIntrfcMapper {

    /**
     * 목록 생성
     *
     * @param deptSe
     * @return
     */
    public int insertList(@Param("deptSe") DeptSeEnums deptSe);

    /**
     * 목록 수정
     *
     * @param deptSe
     * @return
     */
    public int updateList(@Param("deptSe") DeptSeEnums deptSe);

    /**
     * 목록 삭제
     *
     * @param deptSe
     * @return
     */
    public int deleteList(@Param("deptSe") DeptSeEnums deptSe);

}
