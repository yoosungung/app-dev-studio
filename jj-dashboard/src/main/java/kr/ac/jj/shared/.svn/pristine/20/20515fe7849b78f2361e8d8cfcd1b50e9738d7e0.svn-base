package kr.ac.jj.shared.domain.main.mapper.com.email.sndng;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.email.sndng.TbComEmailSndng;

/**
 * 공통 메일 발송 Entity Mapper
 */
abstract interface TbComEmailSndngEntityMapper {

    /**
     * 목록 조회 - EMAIL_ID(으)로
     *
     * @param emailId
     * @return
     */
    public List<TbComEmailSndng> selectListByEmailId(@Param("emailId") String emailId);

    /**
     * 조회
     *
     * @param emailSndngId
     * @return
     */
    public TbComEmailSndng select(@Param("emailSndngId") String emailSndngId);

    /**
     * 생성
     *
     * @param tbComEmailSndng
     * @return
     */
    public int insert(@Param("tbComEmailSndng") TbComEmailSndng tbComEmailSndng);

    /**
     * 수정
     *
     * @param tbComEmailSndng
     * @return
     */
    public int update(@Param("tbComEmailSndng") TbComEmailSndng tbComEmailSndng);

    /**
     * 삭제
     *
     * @param emailSndngId
     * @return
     */
    public int delete(@Param("emailSndngId") String emailSndngId);

    /**
     * 목록 삭제 - EMAIL_ID(으)로
     *
     * @param emailId
     * @return
     */
    public int deleteListByEmailId(@Param("emailId") String emailId);

}
