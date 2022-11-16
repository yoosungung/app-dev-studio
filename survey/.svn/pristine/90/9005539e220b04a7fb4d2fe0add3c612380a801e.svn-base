package com.jd.survey.zeus.mapper;

import java.io.Serializable;
import java.util.List;

import com.jd.survey.zeus.domain.ZeusUser;
import com.jd.survey.zeus.web.cmd.ZeusUserCmd;

/**
 * 사용자
 */
public interface ZeusUserMapper<T extends Serializable> {
	
	/**
	 * 사용자 건수조회
	 */
	int getUserCount(String userId);

	/**
	 * 사용자 조회
	 */
	ZeusUser getUser(String userId);
	
	/**
	 * 사용자 검색건수
	 */
	int searchUserCount(ZeusUserCmd cmd);
	
	/**
	 * 사용자 검색결과
	 */
	List<ZeusUser> searchUserList(ZeusUserCmd cmd);
	
	/**
	 * 사용자 전체 목록 조회 
	 */
	List<ZeusUser> getAllUserList();
	
}