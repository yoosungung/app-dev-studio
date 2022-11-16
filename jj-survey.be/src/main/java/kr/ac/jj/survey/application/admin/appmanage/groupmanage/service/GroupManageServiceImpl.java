package kr.ac.jj.survey.application.admin.appmanage.groupmanage.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.survey.application.admin.appmanage.usermanage.mapper.UserManageMapper;
import kr.ac.jj.survey.application.admin.appmanage.usermanage.model.UserManageModel;
import kr.ac.jj.survey.domain.main.mapper.com.person.TbComPersonMapper;
import kr.ac.jj.survey.domain.main.model.com.person.TbComPerson;
import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.survey.infrastructure.framework.web.context.support.MessageUtil;
import kr.ac.jj.survey.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;

/**
 * 그룹 관리 Service
 */
@Service
public class GroupManageServiceImpl {
    private @Autowired UserManageMapper userManageMapper;
    private @Autowired TbComPersonMapper tbComPersonMapper;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        userManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회
     *
     * @param personId
     * @return
     */
    public UserManageModel read(String personId) {
        UserManageModel model = new UserManageModel();

        TbComPerson tbComPerson = tbComPersonMapper.select(personId);

        model.setTbComPerson(tbComPerson);

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(UserManageModel model) {
        TbComPerson tbComPerson = model.getTbComPerson().newId();

        if (tbComPersonMapper.insert(tbComPerson) == 0) {
            throw new BizException(MessageUtil.getMessage("admin.appmanage.bbsmanage.message.bbsCreateFail",
                    "게시판을 생성할 수 없습니다.\n\n이미 추가된 코드인지 확인하시기 바랍니다."));
        }

        return tbComPerson.getPersonId();
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(UserManageModel model) {
        TbComPerson tbComPerson = model.getTbComPerson();

        if (tbComPersonMapper.update(tbComPerson) == 0) {
            throw new BizException(MessageUtil.getMessage("admin.appmanage.bbsmanage.message.bbsUpdateFail",
                    "게시판 정보를 수정할 수 없습니다.\n\n이미 존재하는 코드인지 확인하시기 바랍니다."));
        }
    }

    /**
     * 삭제
     *
     * @param bbsId
     */
    public void delete(String personId) {
        if (tbComPersonMapper.delete(personId) == 0) {
            throw new BizException(MessageUtil.getMessage("admin.appmanage.bbsmanage.message.bbsDeleteFail",
                    "게시판을 삭제할 수 없습니다.\n\n이미 사용중인 코드인지 확인하시기 바랍니다."));
        }

    }
}
