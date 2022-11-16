package kr.ac.jj.shared.interfaces.admin.appmanage.hrmanage.mapper;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept.DeptSeEnums;

/**
 * 인사정보 관리 - 부서 인터페이스(학생) Mapper
 */
@SharedMainSqlMapper
public interface HumanResourceManageDeptIntrfcUserMapper {

    /**
     * 목록 생성 - 레벨 1
     *
     * @param deptSe
     * @return
     */
    public int insertListLevel1(@Param("deptSe") DeptSeEnums deptSe);

    /**
     * 목록 생성 - 레벨 2
     *
     * @param deptSe
     * @return
     */
    public int insertListLevel2(@Param("deptSe") DeptSeEnums deptSe);

    /**
     * 목록 생성 - 레벨 3
     *
     * @param deptSe
     * @return
     */
    public int insertListLevel3(@Param("deptSe") DeptSeEnums deptSe);

    /**
     * 목록 수정 - 레벨 1
     *
     * @param deptSe
     * @return
     */
    public int updateListLevel1(@Param("deptSe") DeptSeEnums deptSe);

    /**
     * 목록 수정 - 레벨 2
     *
     * @param deptSe
     * @return
     */
    public int updateListLevel2(@Param("deptSe") DeptSeEnums deptSe);

    /**
     * 목록 수정 - 레벨 3
     *
     * @param deptSe
     * @return
     */
    public int updateListLevel3(@Param("deptSe") DeptSeEnums deptSe);

    /**
     * 목록 수정 - 사용 여부 미사용 처리 - 레벨 1
     *
     * @param deptSe
     * @return
     */
    public int updateListUseYnTo0Level1(@Param("deptSe") DeptSeEnums deptSe);

    /**
     * 목록 수정 - 사용 여부 미사용 처리 - 레벨 2
     *
     * @param deptSe
     * @return
     */
    public int updateListUseYnTo0Level2(@Param("deptSe") DeptSeEnums deptSe);

    /**
     * 목록 수정 - 사용 여부 미사용 처리 - 레벨 3
     *
     * @param deptSe
     * @return
     */
    public int updateListUseYnTo0Level3(@Param("deptSe") DeptSeEnums deptSe);

}
