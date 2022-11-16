package kr.ac.jj.shared.domain.main.mapper.com.person;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.person.TbComPerson;

/**
 * 공통 - 사람 Entity Mapper
 */
abstract interface TbComPersonEntityMapper {

    /**
     * 조회
     *
     * @param personId
     * @return
     */
    public TbComPerson select(@Param("personId") String personId);

    /**
     * 생성
     *
     * @param tbComPerson
     * @return
     */
    public int insert(@Param("tbComPerson") TbComPerson tbComPerson);

    /**
     * 수정
     *
     * @param tbComPerson
     * @return
     */
    public int update(@Param("tbComPerson") TbComPerson tbComPerson);

    /**
     * 삭제
     *
     * @param personId
     * @return
     */
    public int delete(@Param("personId") String personId);

}
