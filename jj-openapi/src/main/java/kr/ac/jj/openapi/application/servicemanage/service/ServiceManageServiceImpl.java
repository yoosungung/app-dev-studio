package kr.ac.jj.openapi.application.servicemanage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.openapi.application.servicemanage.mapper.ServiceManageMapper;
import kr.ac.jj.openapi.application.servicemanage.model.ServiceManageModel;
import kr.ac.jj.openapi.domain.main.mapper.api.svc.TbApiSvcMapper;
import kr.ac.jj.openapi.domain.main.mapper.api.svc.mapng.TbApiSvcMapngMapper;
import kr.ac.jj.openapi.domain.main.mapper.api.svc.result.TbApiSvcResultMapper;
import kr.ac.jj.openapi.domain.main.model.api.svc.TbApiSvc;
import kr.ac.jj.openapi.domain.main.model.api.svc.mapng.TbApiSvcMapng;
import kr.ac.jj.openapi.domain.main.model.api.svc.result.TbApiSvcResult;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.DataJobTypes;
import kr.ac.jj.shared.infrastructure.framework.web.context.support.MessageUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 서비스 관리 Service
 */
@Service
public class ServiceManageServiceImpl {

    private @Autowired ServiceManageMapper serviceManageMapper;
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

        serviceManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

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
        List<TbApiSvcMapng> tbApiSvcMapngList = new ArrayList<TbApiSvcMapng>();
        List<TbApiSvcResult> tbApiSvcResultList = new ArrayList<TbApiSvcResult>();

        tbApiSvc.setOthbcPdBegin(new Date());
        tbApiSvc.setValidPdBegin(new Date());

        Map<String, Object> tbApiSvcGubun = new HashMap<>();
        String[] gubuns = {"A","D","K","I","G","S"};
        for (String gubun : gubuns) {
            tbApiSvcGubun.put(gubun, true);
        }
        model.setTbApiSvc(tbApiSvc);
        model.setTbApiSvcMapngList(tbApiSvcMapngList);
        model.setTbApiSvcResultList(tbApiSvcResultList);
        model.setTbApiSvcGubun(tbApiSvcGubun);

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

        //A: 교수, D: 직원, K: 조교, I: 산단, G: 대학원생, S: 학생
        String personSe = tbApiSvc.getPersonSe();
        String[] gubuns = personSe.split(",");

        Map<String, Object> tbApiSvcGubun = new HashMap<>();
        tbApiSvcGubun.put(gubuns[0], true);
        for (int i=0; i<gubuns.length; i++) {
            if (!gubuns[i].equals("")) {
                tbApiSvcGubun.put(gubuns[i], true);
            }
            else
                tbApiSvcGubun.put(gubuns[i], false);
        }
        model.setTbApiSvc(tbApiSvc);
        model.setTbApiSvcMapngList(tbApiSvcMapngMapper.selectListBySvcId(svcId));
        model.setTbApiSvcResultList(tbApiSvcResultMapper.selectListBySvcId(svcId));
        model.setTbApiSvcGubun(tbApiSvcGubun);

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
        List<TbApiSvcResult> tbApiSvcResultList = model.getTbApiSvcResultList();

        String personSe = "";
        Map<String, Object> tbApiSvcGubun = model.getTbApiSvcGubun();
        for (String key : tbApiSvcGubun.keySet()) {
            if ((boolean) tbApiSvcGubun.get(key)) {
                personSe += key + ",";
            }
        }
        tbApiSvc.setPersonSe(personSe);

        if (tbApiSvcMapper.selectUrl(tbApiSvc) > 0) {
            throw new BizException("이미 등록된 URL입니다.");
        }

        if (tbApiSvcMapper.insert(tbApiSvc) == 0) {
            throw new BizException("서비스를 등록할 수 없습니다.");
        }

        for (TbApiSvcMapng tbApiSvcMapng : tbApiSvcMapngList) {
            tbApiSvcMapng.setSvcId(tbApiSvc.getSvcId());
            if (tbApiSvcMapngMapper.insert(tbApiSvcMapng.newId()) == 0) {
                if (serviceManageMapper.selectVariable(tbApiSvc) > 0) {
                    throw new BizException("중복된 변수 입니다.");
                }
                throw new BizException("변수를 추가할 수 없습니다.");
            }
        }

        for (TbApiSvcResult tbApiSvcResult : tbApiSvcResultList) {
            tbApiSvcResult.setSvcId(tbApiSvc.getSvcId());
            if (tbApiSvcResultMapper.insert(tbApiSvcResult.newId()) == 0) {
                if (serviceManageMapper.selectResult(tbApiSvc) > 0) {
                    throw new BizException("중복된 변수 입니다.");
                }
                throw new BizException("변수를 추가할 수 없습니다.");
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
        List<TbApiSvcResult> tbApiSvcResultList = model.getTbApiSvcResultList();

        String personSe = "";
        Map<String, Object> tbApiSvcGubun = model.getTbApiSvcGubun();
        for (String key : tbApiSvcGubun.keySet()) {
            if ((boolean) tbApiSvcGubun.get(key)) {
                personSe += key + ",";
            }
        }
        tbApiSvc.setPersonSe(personSe);
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

        for (TbApiSvcResult tbApiSvcResult : tbApiSvcResultList) {
            DataJobTypes jobType = tbApiSvcResult.get_JOB_TYPES();

            if (jobType == DataJobTypes.CREATE) {
                tbApiSvcResult.setSvcId(tbApiSvc.getSvcId());
                tbApiSvcResultMapper.insert(tbApiSvcResult.newId());
            } else if (jobType == DataJobTypes.UPDATE) {
                tbApiSvcResultMapper.update(tbApiSvcResult);
            } else if (jobType == DataJobTypes.DELETE) {
                tbApiSvcResultMapper.delete(tbApiSvcResult.getSvcResultId());
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

        tbApiSvcResultMapper.deleteListBySvcId(svcId);
        tbApiSvcMapngMapper.deleteListBySvcId(svcId);
        tbApiSvcMapper.delete(svcId);
    }

}
