package kr.ac.jj.survey.application.groupmanage.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.common.user.model.LoginUser;
import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept;
import kr.ac.jj.shared.domain.main.model.com.dept.TbComDept.DeptSeEnums;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.web.context.session.SessionContextUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.application.groupmanage.mapper.GroupManageMapper;
import kr.ac.jj.survey.application.groupmanage.model.GroupManageModel;
import kr.ac.jj.survey.domain.main.mapper.survey.group.TbSurveyGroupMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.group.person.TbSurveyGroupPersonMapper;
import kr.ac.jj.survey.domain.main.mapper.survey.group.person.TbSurveyGroupPersonToPersonMapper;
import kr.ac.jj.survey.domain.main.model.survey.group.TbSurveyGroup;
import kr.ac.jj.survey.domain.main.model.survey.group.person.TbSurveyGroupPerson;

/**
 * 그룹 관리 Service
 */
@Service
public class GroupManageServiceImpl {

    private @Autowired GroupManageMapper groupManageMapper;
    private @Autowired TbSurveyGroupMapper tbSurveyGroupMapper;
    private @Autowired TbSurveyGroupPersonMapper tbSurveyGroupPersonMapper;
    private @Autowired TbSurveyGroupPersonToPersonMapper tbSurveyGroupPersonToPersonMapper;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        groupManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    public GroupManageModel read() {
        GroupManageModel model = new GroupManageModel();

        TbSurveyGroup tbSurveyGroup = new TbSurveyGroup();

        model.setTbSurveyGroup(tbSurveyGroup);

        return model;
    }

    /**
     * 조회
     *
     * @param surveyGroupId
     * @return
     */
    public GroupManageModel read(String surveyGroupId) {
        GroupManageModel model = new GroupManageModel();

        TbSurveyGroup tbSurveyGroup = tbSurveyGroupMapper.select(surveyGroupId);

        model.setTbSurveyGroup(tbSurveyGroup);

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(GroupManageModel model) {
        TbSurveyGroup tbSurveyGroup = model.getTbSurveyGroup().newId();
        LoginUser loginUser = SessionContextUtil.getLoginUser();

        tbSurveyGroup.setRegistPsnId(loginUser.getPersonId());
        tbSurveyGroup.setRegistDt(new Date());

        if (tbSurveyGroupMapper.insert(tbSurveyGroup) == 0) {
            throw new BizException("그룹을 생성할 수 없습니다.");
        }

        return tbSurveyGroup.getSurveyGroupId();
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(GroupManageModel model) {
        TbSurveyGroup tbSurveyGroup = model.getTbSurveyGroup();

        tbSurveyGroupMapper.update(tbSurveyGroup);
    }

    /**
     * 삭제
     *
     * @param surveyGroupId
     */
    public void delete(String surveyGroupId) {
        tbSurveyGroupPersonMapper.deleteListBySurveyGroupId(surveyGroupId);
        tbSurveyGroupMapper.delete(surveyGroupId);
    }

    /**
     * 사람(대상자) 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readPersonList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        tbSurveyGroupPersonToPersonMapper.selectGridListBySurveyGroupId(resultHandler, gridRequest.getPaging(),
                gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 사람(대상자) 목록 검색
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readPersonSearchList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        groupManageMapper.selectPersonSearchList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 사람(대상자) 추가
     *
     * @param surveyGroupId
     * @param personId
     */
    public void createPerson(String surveyGroupId, String personId) {
        TbSurveyGroupPerson tbSurveyGroupPerson = new TbSurveyGroupPerson();
        tbSurveyGroupPerson.setSurveyGroupId(surveyGroupId);
        tbSurveyGroupPerson.setPersonId(personId);

        groupManageMapper.insertPersonNotExists(tbSurveyGroupPerson);
    }

    /**
     * 사람(대상자) 삭제
     *
     * @param surveyGroupId
     * @param personId
     */
    public void deletePerson(String surveyGroupId, String personId) {
        tbSurveyGroupPersonMapper.delete(surveyGroupId, personId);
    }

    /**
     * 조직 트리 조회
     *
     * @param surveyGroupId
     * @param deptSe
     * @return
     */
    public List<Map<String, Object>> readDeptTree(String surveyGroupId, String deptSe) {
        return groupManageMapper.selectDeptTreeList(surveyGroupId, DeptSeEnums.valueOf(deptSe));
    }

    /**
     * 조직별 사람(대상자) 목록 생성
     *
     * @param surveyGroupId
     * @param tbComDeptList
     */
    public void createDeptPersonList(String surveyGroupId, List<TbComDept> tbComDeptList) {
        for (TbComDept tbComDept : tbComDeptList) {
            groupManageMapper.insertPersonListFromDeptId(surveyGroupId, tbComDept.getDeptId());
        }
    }

}
