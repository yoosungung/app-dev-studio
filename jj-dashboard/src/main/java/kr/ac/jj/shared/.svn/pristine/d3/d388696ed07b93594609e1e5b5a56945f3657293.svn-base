package kr.ac.jj.shared.domain.main.mapper.com.email.recptn;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.email.recptn.TbComEmailRecptn;

/**
 * 공통 메일 수신 Entity Mapper
 */
abstract interface TbComEmailRecptnEntityMapper {

    /**
     * 목록 조회 - EMAIL_ID(으)로
     *
     * @param emailId
     * @return
     */
    public List<TbComEmailRecptn> selectListByEmailId(@Param("emailId") String emailId);

    /**
     * 조회
     *
     * @param emailRecptnId
     * @return
     */
    public TbComEmailRecptn select(@Param("emailRecptnId") String emailRecptnId);

    /**
     * 생성
     *
     * @param tbComEmailRecptn
     * @return
     */
    public int insert(@Param("tbComEmailRecptn") TbComEmailRecptn tbComEmailRecptn);

    /**
     * 수정
     *
     * @param tbComEmailRecptn
     * @return
     */
    public int update(@Param("tbComEmailRecptn") TbComEmailRecptn tbComEmailRecptn);

    /**
     * 삭제
     *
     * @param emailRecptnId
     * @return
     */
    public int delete(@Param("emailRecptnId") String emailRecptnId);

    /**
     * 목록 삭제 - EMAIL_ID(으)로
     *
     * @param emailId
     * @return
     */
    public int deleteListByEmailId(@Param("emailId") String emailId);

}
