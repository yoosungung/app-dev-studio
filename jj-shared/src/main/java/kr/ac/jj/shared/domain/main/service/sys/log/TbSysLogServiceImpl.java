package kr.ac.jj.shared.domain.main.service.sys.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.domain.main.mapper.sys.log.TbSysLogMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.log.error.TbSysLogErrorMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.log.login.TbSysLogLoginMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.log.menu.TbSysLogMenuMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.log.query.TbSysLogQueryMapper;

/**
 * 시스템 - 로그 Service
 */
@Service
public class TbSysLogServiceImpl {

    private @Autowired TbSysLogMapper tbSysLogMapper;
    private @Autowired TbSysLogMenuMapper tbSysLogMenuMapper;
    private @Autowired TbSysLogLoginMapper tbSysLogLoginMapper;
    private @Autowired TbSysLogErrorMapper tbSysLogErrorMapper;
    private @Autowired TbSysLogQueryMapper tbSysLogQueryMapper;

    public void delete(String logId) {
        tbSysLogQueryMapper.deleteListByLogId(logId);
        tbSysLogErrorMapper.deleteListByLogId(logId);
        tbSysLogLoginMapper.deleteListByLogId(logId);
        tbSysLogMenuMapper.deleteListByLogId(logId);
        tbSysLogMapper.delete(logId);
    }

}
