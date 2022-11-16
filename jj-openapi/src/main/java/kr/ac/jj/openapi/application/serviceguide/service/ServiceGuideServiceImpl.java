package kr.ac.jj.openapi.application.serviceguide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.openapi.application.serviceguide.model.ServiceGuideModel;
import kr.ac.jj.openapi.domain.main.mapper.serviceguide.TbServiceGuideMapper;
import kr.ac.jj.openapi.domain.main.model.serviceguide.TbServiceGuide;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.web.context.support.MessageUtil;

/**
 * 서비스 이용안내 Service
 */
@Service
public class ServiceGuideServiceImpl {

    private @Autowired TbServiceGuideMapper tbServiceGuideMapper;

    /**
     * 조회
     *
     * @return
     */
    public ServiceGuideModel read() {
        ServiceGuideModel model = new ServiceGuideModel();

        TbServiceGuide tbServiceGuide = tbServiceGuideMapper.select();

        model.setTbServiceGuide(tbServiceGuide);

        return model;
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(ServiceGuideModel model) {
        if (!model.isEditable()) {
            throw new BizException(MessageUtil.getMessage("common.message.notAvail.update", "수정할 수 없는 데이터입니다."));
        }

        TbServiceGuide tbServiceGuide = model.getTbServiceGuide();

        if (tbServiceGuideMapper.update(tbServiceGuide) == 0) {
            tbServiceGuideMapper.insert(tbServiceGuide);
        }
    }

}
