package kr.ac.jj.shared.application.admin.logmanage.errorlog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.admin.logmanage.errorlog.mapper.ErrorLogManageMapper;
import kr.ac.jj.shared.application.admin.logmanage.errorlog.model.ErrorLogManageModel;
import kr.ac.jj.shared.domain.main.mapper.sys.log.TbSysLogMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.log.error.TbSysLogErrorMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.log.query.TbSysLogQueryMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.user.TbSysUserMapper;
import kr.ac.jj.shared.domain.main.model.sys.log.TbSysLog;
import kr.ac.jj.shared.domain.main.model.sys.log.error.TbSysLogError;
import kr.ac.jj.shared.domain.main.service.sys.log.TbSysLogServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 에러 로그 관리 Service
 */
@Service
public class ErrorLogManageServiceImpl {

    private @Autowired ErrorLogManageMapper errorLogManageMapper;
    private @Autowired TbSysLogMapper tbSysLogMapper;
    private @Autowired TbSysLogErrorMapper tbSysLogErrorMapper;
    private @Autowired TbSysLogQueryMapper tbSysLogQueryMapper;
    private @Autowired TbSysUserMapper tbSysUserMapper;
    private @Autowired TbSysLogServiceImpl tbSysLogService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        errorLogManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회
     *
     * @param errorLogId
     * @return
     */
    public ErrorLogManageModel read(String errorLogId) {
        ErrorLogManageModel model = new ErrorLogManageModel();

        TbSysLogError tbSysLogError = tbSysLogErrorMapper.select(errorLogId);
        String logId = tbSysLogError.getLogId();
        TbSysLog tbSysLog = tbSysLogMapper.select(logId);

        model.setTbSysLogError(tbSysLogError);
        model.setTbSysLog(tbSysLog);
        model.setTbSysLogQueryList(tbSysLogQueryMapper.selectListByLogId(logId));

        String userId = tbSysLog.getUserId();

        if (userId.length() == 18) {
            model.setTbSysUser(tbSysUserMapper.select(userId));
        }

        return model;
    }

    /**
     * 삭제
     *
     * @param errorLogId
     */
    public void delete(String errorLogId) {
        TbSysLogError tbSysLogError = tbSysLogErrorMapper.select(errorLogId);
        String logId = tbSysLogError.getLogId();

        tbSysLogService.delete(logId);
    }

}
