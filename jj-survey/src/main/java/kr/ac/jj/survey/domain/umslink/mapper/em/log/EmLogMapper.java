package kr.ac.jj.survey.domain.umslink.mapper.em.log;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceUmslinkConfig.UmslinkSqlMapper;
import kr.ac.jj.survey.domain.umslink.model.em.log.EmLog;

@UmslinkSqlMapper
public interface EmLogMapper {

    /**
     * 조회
     *
     * @param yyyyMM
     * @param tranPr
     * @return
     */
    public EmLog select(@Param("yyyyMM") String yyyyMM, @Param("tranPr") Long tranPr);

    /**
     * 조회 - TRAN_ETC2로
     *
     * @param yyyyMM
     * @param tranEtc2
     * @return
     */
    public EmLog selectByTranEtc2(@Param("yyyyMM") String yyyyMM, @Param("tranEtc2") String tranEtc2);

}
