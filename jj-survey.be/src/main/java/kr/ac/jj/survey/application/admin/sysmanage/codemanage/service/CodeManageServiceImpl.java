package kr.ac.jj.survey.application.admin.sysmanage.codemanage.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.survey.application.admin.sysmanage.codemanage.mapper.CodeManageMapper;
import kr.ac.jj.survey.application.admin.sysmanage.codemanage.model.CodeManageModel;
import kr.ac.jj.survey.domain.main.mapper.sys.code.group.TbSysCodeGroupMapper;
import kr.ac.jj.survey.domain.main.mapper.sys.code.value.TbSysCodeValueMapper;
import kr.ac.jj.survey.domain.main.model.sys.code.group.TbSysCodeGroup;
import kr.ac.jj.survey.domain.main.model.sys.code.value.TbSysCodeValue;
import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.survey.infrastructure.framework.core.support.collection.DataJobTypes;
import kr.ac.jj.survey.infrastructure.framework.web.context.support.MessageUtil;
import kr.ac.jj.survey.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.infrastructure.title.service.TitleServiceImpl;

/**
 * 코드 관리 Service
 */
@Service
public class CodeManageServiceImpl {
    private @Autowired CodeManageMapper codeManageMapper;
    private @Autowired TbSysCodeGroupMapper tbSysCodeGroupMapper;
    private @Autowired TbSysCodeValueMapper tbSysCodeValueMapper;
    private @Autowired TitleServiceImpl titleService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        codeManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회
     *
     * @param codeGroupId
     * @return
     */
    public CodeManageModel read(String codeGroupId) {
        CodeManageModel model = new CodeManageModel();

        if (StringUtils.isEmpty(codeGroupId)) {
        } else {
            TbSysCodeGroup tbSysCodeGroup = tbSysCodeGroupMapper.select(codeGroupId);
            tbSysCodeGroup.setCodeGroupNmTitleList(titleService.readList(tbSysCodeGroup.getCodeGroupNmTitle()));

            model.setTbSysCodeGroup(tbSysCodeGroup);
            model.setTbSysCodeValueList(tbSysCodeValueMapper.selectListByCodeGroupId(codeGroupId));

            for (TbSysCodeValue tbSysCodeValue : model.getTbSysCodeValueList()) {
                tbSysCodeValue.setCodeValueNmTitleList(titleService.readList(tbSysCodeValue.getCodeValueNmTitle()));
            }
        }

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(CodeManageModel model) {
        TbSysCodeGroup tbSysCodeGroup = model.getTbSysCodeGroup().newId();
        List<TbSysCodeValue> tbSysCodeValueList = model.getTbSysCodeValueList();

        if (tbSysCodeGroupMapper.insert(tbSysCodeGroup) == 0) {
            if (codeManageMapper.selectCodeGroup(tbSysCodeGroup) > 0) {
                throw new BizException(MessageUtil.getMessage("admin.sysmanage.codemanage.message.duplicatedCodeGroup",
                        "중복된 코드그룹 \"{0}\" 입니다.", new String[] { tbSysCodeGroup.getCodeGroup() }));
            }
            throw new BizException(MessageUtil.getMessage("admin.sysmanage.codemanage.message.codeGroupCreateNotAvail",
                    "코드그룹 \"{0}\" 을 생성할 수가 없습니다."));
        }

        titleService.updateList(tbSysCodeGroup.getCodeGroupNmTitle(), tbSysCodeGroup.getCodeGroupNmTitleList());

        for (TbSysCodeValue tbSysCodeValue : tbSysCodeValueList) {
            tbSysCodeValue.setCodeGroupId(tbSysCodeGroup.getCodeGroupId());
            tbSysCodeValueMapper.insert(tbSysCodeValue.newId());
            titleService.updateList(tbSysCodeValue.getCodeValueNmTitle(), tbSysCodeValue.getCodeValueNmTitleList());
        }

        return tbSysCodeGroup.getCodeGroupId();
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(CodeManageModel model) {
        TbSysCodeGroup tbSysCodeGroup = model.getTbSysCodeGroup();
        List<TbSysCodeValue> tbSysCodeValueList = model.getTbSysCodeValueList();

        tbSysCodeGroupMapper.update(tbSysCodeGroup);
        titleService.updateList(tbSysCodeGroup.getCodeGroupNmTitle(), tbSysCodeGroup.getCodeGroupNmTitleList());

        for (TbSysCodeValue tbSysCodeValue : tbSysCodeValueList) {
            DataJobTypes jobType = tbSysCodeValue.get_JOB_TYPES();

            if (jobType == DataJobTypes.CREATE) {
                tbSysCodeValue.setCodeGroupId(tbSysCodeGroup.getCodeGroupId());
                tbSysCodeValueMapper.insert(tbSysCodeValue.newId());
                titleService.updateList(tbSysCodeValue.getCodeValueNmTitle(), tbSysCodeValue.getCodeValueNmTitleList());
            } else if (jobType == DataJobTypes.UPDATE) {
                tbSysCodeValueMapper.update(tbSysCodeValue);
                titleService.updateList(tbSysCodeValue.getCodeValueNmTitle(), tbSysCodeValue.getCodeValueNmTitleList());
            } else if (jobType == DataJobTypes.DELETE) {
                titleService.deleteList(tbSysCodeValue.getCodeValueNmTitle());
                tbSysCodeValueMapper.delete(tbSysCodeValue.getCodeValueId());
            }
        }
    }

    /**
     * 삭제
     *
     * @param codeGroupId
     */
    public void delete(String codeGroupId) {
        TbSysCodeGroup tbSysCodeGroupDb = tbSysCodeGroupMapper.select(codeGroupId);
        List<TbSysCodeValue> tbSysCodeValueDbList = tbSysCodeValueMapper.selectListByCodeGroupId(codeGroupId);

        for (TbSysCodeValue tbSysCodeValueDb : tbSysCodeValueDbList) {
            titleService.deleteList(tbSysCodeValueDb.getCodeValueNmTitle());
        }

        titleService.deleteList(tbSysCodeGroupDb.getCodeGroupNmTitle());

        tbSysCodeValueMapper.deleteListByCodeGroupId(codeGroupId);
        tbSysCodeGroupMapper.delete(codeGroupId);
    }
}
