package com.jd.survey.zeus.mapper;

import java.io.Serializable;

import com.jd.survey.zeus.domain.TbAccessLog;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;

/**
 * Zeus SQLMapper
 */
@MainSqlMapper
public interface ZeusMapper<T extends Serializable> {

    /** 접속 통계 */
    TbAccessLog getTbAccessCnt();

    /** 접속로그 등록 */
    int addTbAccessLog(TbAccessLog accessLog);

}
