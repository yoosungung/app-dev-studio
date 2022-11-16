package com.jd.survey.zeus.service;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jd.survey.zeus.domain.TbAccessLog;
import com.jd.survey.zeus.mapper.ZeusMapper;



@Transactional(readOnly = true)
@Service("ZeusService")
public class ZeusService {
		
	@Autowired private ZeusMapper<Serializable> zeusMapper;
		
	/** 방문자카운트 조회 */
	public TbAccessLog getTbAccessLogCnt() {
		return new TbAccessLog();
		//return zeusMapper.getTbAccessCnt();
	}



	/**제우스 매뉴별 접속 카운트*/
	public void addTbAccessLog(TbAccessLog accessLog) {
		//zeusMapper.addTbAccessLog(accessLog);
	}


}
