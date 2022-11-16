package kr.ac.jj.shared.domain.main.mapper.com.person.dept;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.com.person.dept.TbComPersonDept;

/**
 * 공통 - 사람별 부서 Mapper
 */
@SharedMainSqlMapper
public interface TbComPersonDeptMapper extends TbComPersonDeptEntityMapper {

    /**
     * 생성 - 존재하지 않는 경우
     *
     * @param tbComPersonDept
     * @return
     */
    public int insertNotExists(@Param("tbComPersonDept") TbComPersonDept tbComPersonDept);

    /**
     * 목록 삭제 - 미사용 건
     *
     * @return
     */
    public int deleteListNotUsed();

}
