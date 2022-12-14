package kr.ac.jj.openapi.application.servicestatus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.openapi.application.servicemanage.model.ServiceManageModel;
import kr.ac.jj.openapi.application.servicestatus.mapper.ServiceStatusMapper;
import kr.ac.jj.openapi.domain.main.mapper.api.svc.TbApiSvcMapper;
import kr.ac.jj.openapi.domain.main.mapper.api.svc.mapng.TbApiSvcMapngMapper;
import kr.ac.jj.openapi.domain.main.mapper.api.svc.result.TbApiSvcResultMapper;
import kr.ac.jj.openapi.domain.main.model.api.svc.TbApiSvc;
import kr.ac.jj.openapi.domain.main.model.api.svc.mapng.TbApiSvcMapng;
import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.DataJobTypes;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.framework.web.context.support.MessageUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 서비스 관리 Service
 */
@Service
public class ServiceStatusServiceImpl {

    private @Autowired ServiceStatusMapper serviceStatusMapper;
    private @Autowired TbApiSvcMapper tbApiSvcMapper;
    private @Autowired TbApiSvcMapngMapper tbApiSvcMapngMapper;
    private @Autowired TbApiSvcResultMapper tbApiSvcResultMapper;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        LoginUser loginUser = SessionContextUtil.getLoginUser();
        resultHandler.getGridRequest().getSearch().put("personSe", loginUser.getTbComPerson().getPersonSe());

        serviceStatusMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    public ServiceManageModel read() {
        ServiceManageModel model = new ServiceManageModel();

        TbApiSvc tbApiSvc = new TbApiSvc();

        model.setTbApiSvc(tbApiSvc);

        return model;
    }

    /**
     * 조회
     *
     * @param svcId
     * @return
     */
    public ServiceManageModel read(String svcId) {
        ServiceManageModel model = new ServiceManageModel();

        TbApiSvc tbApiSvc = tbApiSvcMapper.select(svcId);

        model.setTbApiSvc(tbApiSvc);

        List<TbApiSvcMapng> tbApiSvcMapngList = new ArrayList<TbApiSvcMapng>();

        TbApiSvcMapng tbApiSvcMapng = new TbApiSvcMapng();

        tbApiSvcMapng.set_JOB_TYPES(DataJobTypes.CREATE);
        tbApiSvcMapng.setVriabl("currentPageNo");
        tbApiSvcMapng.setVriablNm("페이지 번호");
        tbApiSvcMapng.setDc("페이지 번호 ex)1, 5, 11");
        tbApiSvcMapng.setEssntlYn(true);
        tbApiSvcMapngList.add(0, tbApiSvcMapng);

        tbApiSvcMapng = new TbApiSvcMapng();
        tbApiSvcMapng.set_JOB_TYPES(DataJobTypes.CREATE);
        tbApiSvcMapng.setVriabl("recordCountPerPage");
        tbApiSvcMapng.setVriablNm("페이지당 건수");
        tbApiSvcMapng.setDc("페이지당 건수 ex)10, 20, 100");
        tbApiSvcMapng.setEssntlYn(true);
        tbApiSvcMapngList.add(1, tbApiSvcMapng);

        tbApiSvcMapng = new TbApiSvcMapng();
        tbApiSvcMapng.set_JOB_TYPES(DataJobTypes.CREATE);
        tbApiSvcMapng.setVriabl("dataFormat");
        tbApiSvcMapng.setVriablNm("데이터 포맷");
        tbApiSvcMapng.setDc("json or xml 선택");
        tbApiSvcMapng.setInputVal("json");
        tbApiSvcMapng.setEssntlYn(true);
        tbApiSvcMapngList.add(2, tbApiSvcMapng);

        tbApiSvcMapng = new TbApiSvcMapng();
        tbApiSvcMapng.set_JOB_TYPES(DataJobTypes.CREATE);
        tbApiSvcMapng.setVriabl("apiKey");
        tbApiSvcMapng.setVriablNm("API 키");
        tbApiSvcMapng.setDc("신청 후 승인 받은 키 입력");
        tbApiSvcMapng.setEssntlYn(true);
        tbApiSvcMapngList.add(3, tbApiSvcMapng);

        tbApiSvcMapngList.addAll(tbApiSvcMapngMapper.selectListBySvcId(svcId));

        model.setTbApiSvcMapngList(tbApiSvcMapngList);
        model.setTbApiSvcResultList(tbApiSvcResultMapper.selectListBySvcId(svcId));

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(ServiceManageModel model) {
        TbApiSvc tbApiSvc = model.getTbApiSvc().newId();
        List<TbApiSvcMapng> tbApiSvcMapngList = model.getTbApiSvcMapngList();

        if (tbApiSvcMapper.insert(tbApiSvc) == 0) {
            throw new BizException("서비스를 등록 할 수 없습니다.");
        }

        for (TbApiSvcMapng tbApiSvcMapng : tbApiSvcMapngList) {
            tbApiSvcMapng.setSvcId(tbApiSvc.getSvcId());
            if (tbApiSvcMapngMapper.insert(tbApiSvcMapng.newId()) == 0) {
                if (serviceStatusMapper.selectVariable(tbApiSvc) > 0) {
                    throw new BizException("중복된 변수 입니다.");
                }
                throw new BizException("변수를 추가 할 수 없습니다.");
            }
        }

        return tbApiSvc.getSvcId();
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(ServiceManageModel model) {
        if (!this.read(model.getTbApiSvc().getSvcId()).isEditable()) {
            throw new BizException(MessageUtil.getMessage("common.message.notAvail.update", "수정할 수 없는 데이터입니다."));
        }

        TbApiSvc tbApiSvc = model.getTbApiSvc();
        List<TbApiSvcMapng> tbApiSvcMapngList = model.getTbApiSvcMapngList();

        tbApiSvcMapper.update(tbApiSvc);

        for (TbApiSvcMapng tbApiSvcMapng : tbApiSvcMapngList) {
            DataJobTypes jobType = tbApiSvcMapng.get_JOB_TYPES();

            if (jobType == DataJobTypes.CREATE) {
                tbApiSvcMapng.setSvcId(tbApiSvc.getSvcId());
                tbApiSvcMapngMapper.insert(tbApiSvcMapng.newId());
            } else if (jobType == DataJobTypes.UPDATE) {
                tbApiSvcMapngMapper.update(tbApiSvcMapng);
            } else if (jobType == DataJobTypes.DELETE) {
                tbApiSvcMapngMapper.delete(tbApiSvcMapng.getSvcMapngId());
            }
        }
    }

    /**
     * 삭제
     *
     * @param svcId
     */
    public void delete(String svcId) {
        if (!this.read(svcId).isEditable()) {
            throw new BizException(MessageUtil.getMessage("common.message.notAvail.delete", "삭제할 수 없는 데이터입니다."));
        }

        tbApiSvcMapngMapper.deleteListBySvcId(svcId);
        tbApiSvcMapngMapper.delete(svcId);
    }

}
