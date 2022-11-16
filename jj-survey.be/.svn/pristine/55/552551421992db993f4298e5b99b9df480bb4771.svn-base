package kr.ac.jj.survey.application.admin.appmanage.bbsmanage.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.survey.application.admin.appmanage.bbsmanage.mapper.BbsManageMapper;
import kr.ac.jj.survey.application.admin.appmanage.bbsmanage.model.BbsManageModel;
import kr.ac.jj.survey.domain.main.mapper.com.bbs.TbComBbsMapper;
import kr.ac.jj.survey.domain.main.mapper.com.bbs.author.TbComBbsAuthorMapper;
import kr.ac.jj.survey.domain.main.model.com.bbs.TbComBbs;
import kr.ac.jj.survey.domain.main.model.com.bbs.author.TbComBbsAuthor;
import kr.ac.jj.survey.domain.main.model.com.bbs.author.TbComBbsAuthorToAuthor;
import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.survey.infrastructure.framework.web.context.support.MessageUtil;
import kr.ac.jj.survey.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;
import kr.ac.jj.survey.infrastructure.title.service.TitleServiceImpl;

/**
 * 게시판 관리 Service
 */
@Service
public class BbsManageServiceImpl {
    private @Autowired BbsManageMapper bbsManageMapper;
    private @Autowired TbComBbsMapper tbComBbsMapper;
    private @Autowired TbComBbsAuthorMapper tbComBbsAuthorMapper;
    private @Autowired TitleServiceImpl titleService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        bbsManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회
     *
     * @param bbsId
     * @return
     */
    public BbsManageModel read(String bbsId) {
        BbsManageModel model = new BbsManageModel();

        if (StringUtils.isEmpty(bbsId)) {
            model.setTbComBbsAuthorToAuthorList(tbComBbsAuthorMapper.selectListByBbsId2());
        } else {
            TbComBbs tbComBbs = tbComBbsMapper.select(bbsId);
            tbComBbs.setBbsNmTitleList(titleService.readList(tbComBbs.getBbsNmTitle()));

            model.setTbComBbs(tbComBbs);
            model.setTbComBbsAuthorToAuthorList(tbComBbsAuthorMapper.selectListByBbsId2(bbsId));
        }

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(BbsManageModel model) {
        TbComBbs tbComBbs = model.getTbComBbs().newId();
        List<TbComBbsAuthorToAuthor> tbComBbsAuthorToAuthorList = model.getTbComBbsAuthorToAuthorList();

        if (tbComBbsMapper.insert(tbComBbs) == 0) {
            throw new BizException(MessageUtil.getMessage("admin.appmanage.bbsmanage.message.bbsCreateFail",
                    "게시판을 생성할 수 없습니다.\n\n이미 추가된 코드인지 확인하시기 바랍니다."));
        }

        titleService.updateList(tbComBbs.getBbsNmTitle(), tbComBbs.getBbsNmTitleList());

        for (TbComBbsAuthorToAuthor tbComBbsAuthorToAuthor : tbComBbsAuthorToAuthorList) {
            TbComBbsAuthor tbComBbsAuthor = new TbComBbsAuthor();
            tbComBbsAuthor.setBbsId(tbComBbs.getBbsId());
            tbComBbsAuthor.setAuthorId(tbComBbsAuthorToAuthor.getAuthorId());
            tbComBbsAuthor.setInqirePosblYn(tbComBbsAuthorToAuthor.getInqirePosblYn());
            tbComBbsAuthor.setWritngPosblYn(tbComBbsAuthorToAuthor.getWritngPosblYn());
            tbComBbsAuthorMapper.insert(tbComBbsAuthor);
        }

        return tbComBbs.getBbsId();
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(BbsManageModel model) {
        TbComBbs tbComBbs = model.getTbComBbs();
        List<TbComBbsAuthorToAuthor> tbComBbsAuthorToAuthorList = model.getTbComBbsAuthorToAuthorList();

        if (tbComBbsMapper.update(tbComBbs) == 0) {
            throw new BizException(MessageUtil.getMessage("admin.appmanage.bbsmanage.message.bbsUpdateFail",
                    "게시판 정보를 수정할 수 없습니다.\n\n이미 존재하는 코드인지 확인하시기 바랍니다."));
        }

        titleService.updateList(tbComBbs.getBbsNmTitle(), tbComBbs.getBbsNmTitleList());

        tbComBbsAuthorMapper.deleteListByBbsId(tbComBbs.getBbsId());

        for (TbComBbsAuthorToAuthor tbComBbsAuthorToAuthor : tbComBbsAuthorToAuthorList) {
            TbComBbsAuthor tbComBbsAuthor = new TbComBbsAuthor();
            tbComBbsAuthor.setBbsId(tbComBbs.getBbsId());
            tbComBbsAuthor.setAuthorId(tbComBbsAuthorToAuthor.getAuthorId());
            tbComBbsAuthor.setInqirePosblYn(tbComBbsAuthorToAuthor.getInqirePosblYn());
            tbComBbsAuthor.setWritngPosblYn(tbComBbsAuthorToAuthor.getWritngPosblYn());
            tbComBbsAuthorMapper.insert(tbComBbsAuthor);
        }
    }

    /**
     * 삭제
     *
     * @param bbsId
     */
    public void delete(String bbsId) {
        TbComBbs tbComBbsDb = tbComBbsMapper.select(bbsId);

        tbComBbsAuthorMapper.deleteListByBbsId(bbsId);

        if (tbComBbsMapper.delete(bbsId) == 0) {
            throw new BizException(MessageUtil.getMessage("admin.appmanage.bbsmanage.message.bbsDeleteFail",
                    "게시판을 삭제할 수 없습니다.\n\n이미 사용중인 코드인지 확인하시기 바랍니다."));
        }

        titleService.deleteList(tbComBbsDb.getBbsNmTitle());
    }
}
