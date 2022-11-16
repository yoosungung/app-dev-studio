package kr.ac.jj.shared.domain.main.mapper.intrfc.dept.empl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.intrfc.dept.empl.TbIntrfcDeptEmpl;

/**
 * 인터페이스 - 부서 - 사원 Mapper
 */
@SharedMainSqlMapper
public interface TbIntrfcDeptEmplMapper extends TbIntrfcDeptEmplEntityMapper {

    /**
     * 목록 생성
     *
     * @param tbIntrfcDeptEmplList
     * @return
     */
    public int insertList(@Param("tbIntrfcDeptEmplList") List<TbIntrfcDeptEmpl> tbIntrfcDeptEmplList);

    /**
     * 전체 삭제
     *
     * @return
     */
    public int deleteAll();

}
