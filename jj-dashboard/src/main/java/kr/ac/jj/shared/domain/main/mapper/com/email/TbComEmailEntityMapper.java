package kr.ac.jj.shared.domain.main.mapper.com.email;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.email.TbComEmail;

/**
 * 공통 메일 Entity Mapper
 */
abstract interface TbComEmailEntityMapper {

    /**
     * 조회
     *
     * @param emailId
     * @return
     */
    public TbComEmail select(@Param("emailId") String emailId);

    /**
     * 생성
     *
     * @param tbComEmail
     * @return
     */
    public int insert(@Param("tbComEmail") TbComEmail tbComEmail);

    /**
     * 수정
     *
     * @param tbComEmail
     * @return
     */
    public int update(@Param("tbComEmail") TbComEmail tbComEmail);

    /**
     * 삭제
     *
     * @param emailId
     * @return
     */
    public int delete(@Param("emailId") String emailId);

}
