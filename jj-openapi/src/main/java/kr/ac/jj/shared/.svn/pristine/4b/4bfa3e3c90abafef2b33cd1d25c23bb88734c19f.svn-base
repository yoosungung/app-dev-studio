package kr.ac.jj.shared.domain.main.mapper.com.sms;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.com.sms.TbComSms;
import kr.ac.jj.shared.infrastructure.framework.mybatis.persistence.dao.handler.DataResultHandler;

/**
 * 공통 - 문자메시지 Mapper
 */
@SharedMainSqlMapper
public interface TbComSmsMapper extends TbComSmsEntityMapper {

    /**
     * 결과 업데이트 대상 목록 조회
     *
     * @param limitDateTime
     * @param resultHandler
     */
    public void selectForResultUpdateList(@Param("limitDateTime") Date limitDateTime,
            DataResultHandler<TbComSms> resultHandler);

    /**
     * 발송 대상 목록 조회
     *
     * @param resultHandler
     */
    public void selectForSendList(DataResultHandler<TbComSms> resultHandler);

    /**
     * 수정 - TRAN_PR
     *
     * @param smsId
     * @param tranPr
     * @return
     */
    public int updateTranPr(@Param("smsId") String smsId, @Param("tranPr") Long tranPr);

    /**
     * 수정 - 전송 결과
     *
     * @param tbComSms
     * @return
     */
    public int updateResult(@Param("tbComSms") TbComSms tbComSms);

    /**
     * 수정 - 재발송 처리
     *
     * @param smsId
     * @return
     */
    public int updateReSend(@Param("smsId") String smsId);

}
