package kr.ac.jj.shared.domain.main.mapper.com.sms;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.sms.TbComSms;

/**
 * 공통 - 문자메시지 Entity Mapper
 */
abstract interface TbComSmsEntityMapper {

    /**
     * 조회
     *
     * @param smsId
     * @return
     */
    public TbComSms select(@Param("smsId") String smsId);

    /**
     * 생성
     *
     * @param tbComSms
     * @return
     */
    public int insert(@Param("tbComSms") TbComSms tbComSms);

    /**
     * 수정
     *
     * @param tbComSms
     * @return
     */
    public int update(@Param("tbComSms") TbComSms tbComSms);

    /**
     * 삭제
     *
     * @param smsId
     * @return
     */
    public int delete(@Param("smsId") String smsId);

}
