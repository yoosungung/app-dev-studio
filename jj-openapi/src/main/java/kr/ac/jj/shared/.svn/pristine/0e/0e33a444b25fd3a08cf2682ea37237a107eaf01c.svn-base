package kr.ac.jj.shared.application.admin.logmanage.menulog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.admin.logmanage.menulog.mapper.MenuLogManageMapper;
import kr.ac.jj.shared.application.admin.logmanage.menulog.model.MenuLogManageModel;
import kr.ac.jj.shared.domain.main.mapper.sys.log.TbSysLogMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.log.query.TbSysLogQueryMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.user.TbSysUserMapper;
import kr.ac.jj.shared.domain.main.model.sys.log.TbSysLog;
import kr.ac.jj.shared.domain.main.service.sys.log.TbSysLogServiceImpl;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 메뉴 로그 관리 Service
 */
@Service
public class MenuLogManageServiceImpl {

    private @Autowired MenuLogManageMapper menuLogManageMapper;
    private @Autowired TbSysLogMapper tbSysLogMapper;
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

        menuLogManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회
     *
     * @param logId
     * @return
     */
    public MenuLogManageModel read(String logId) {
        MenuLogManageModel model = new MenuLogManageModel();

        TbSysLog tbSysLog = tbSysLogMapper.select(logId);

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
     * @param logId
     */
    public void delete(String logId) {
        tbSysLogService.delete(logId);
    }

}
