package com.jd.survey.zeus.mapper;

import java.io.Serializable;

import com.jd.survey.zeus.domain.TbAccessLog;

/**
 * Zeus SQLMapper
 */
public interface ZeusMapper<T extends Serializable> {
	
	 /** 접속 통계 */
    TbAccessLog getTbAccessCnt();
    
	 /**접속로그 등록   */
	int addTbAccessLog(TbAccessLog accessLog);
	
}