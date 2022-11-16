package kr.ac.jj.shared.application.bbs.notice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.bbs.notice.mapper.BbsNoticeMapper;
import kr.ac.jj.shared.application.bbs.notice.model.BbsNoticeModel;
import kr.ac.jj.shared.application.common.file.service.FileServiceImpl;
import kr.ac.jj.shared.domain.main.mapper.bbs.notice.TbBbsNoticeMapper;
import kr.ac.jj.shared.domain.main.model.bbs.notice.TbBbsNotice;
import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.web.context.support.MessageUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 게시판(공지사항) Service
 */
@Service
public class BbsNoticeServiceImpl {

    private @Autowired BbsNoticeMapper bbsNoticeMapper;
    private @Autowired TbBbsNoticeMapper tbBbsNoticeMapper;
    private @Autowired FileServiceImpl fileService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        bbsNoticeMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    public BbsNoticeModel read() {
        BbsNoticeModel model = new BbsNoticeModel();

        TbBbsNotice tbBbsNotice = new TbBbsNotice();
        tbBbsNotice.setAtchFileList(new ArrayList<TbComFile>());

        model.setTbBbsNotice(tbBbsNotice);

        return model;
    }

    /**
     * 조회
     *
     * @param bbscttId
     * @param updateRdcnt
     * @return
     */
    public BbsNoticeModel read(String bbscttId, Boolean updateRdcnt) {
        if (BooleanUtils.isTrue(updateRdcnt)) {
            tbBbsNoticeMapper.updateRdcnt(bbscttId);
        }

        BbsNoticeModel model = new BbsNoticeModel();

        TbBbsNotice tbBbsNotice = tbBbsNoticeMapper.select(bbscttId);
        tbBbsNotice.setAtchFileList(fileService.readList(tbBbsNotice.getAtchFileId()));

        model.setTbBbsNotice(tbBbsNotice);

        return model;
    }

    /**
     * 조회
     *
     * @param bbscttId
     * @return
     */
    private BbsNoticeModel read(String bbscttId) {
        return this.read(bbscttId, false);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(BbsNoticeModel model) {
        TbBbsNotice tbBbsNotice = model.getTbBbsNotice();

        tbBbsNotice.newId();
        tbBbsNotice.setRdcnt(0);
        tbBbsNotice.setDeleteYn(false);
        tbBbsNotice.setAtchFileId(fileService.updateList(tbBbsNotice.getAtchFileId(), tbBbsNotice.getAtchFileList()));
        tbBbsNoticeMapper.insert(tbBbsNotice);

        return tbBbsNotice.getBbscttId();
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(BbsNoticeModel model) {
        TbBbsNotice tbBbsNotice = model.getTbBbsNotice();
        String bbscttId = tbBbsNotice.getBbscttId();
        BbsNoticeModel modelDb = this.read(bbscttId);

        if (!modelDb.isEditable()) {
            throw new BizException(MessageUtil.getMessage("common.message.notAvail.update", "수정할 수 없는 데이터입니다."));
        }

        tbBbsNotice.setAtchFileId(fileService.updateList(tbBbsNotice.getAtchFileId(), tbBbsNotice.getAtchFileList()));
        tbBbsNoticeMapper.update(tbBbsNotice);
    }

    /**
     * 삭제
     *
     * @param bbscttId
     */
    public void delete(String bbscttId) {
        BbsNoticeModel modelDb = this.read(bbscttId);

        if (!modelDb.isEditable()) {
            throw new BizException(MessageUtil.getMessage("common.message.notAvail.delete", "삭제할 수 없는 데이터입니다."));
        }

        fileService.updateDelYnAll(modelDb.getTbBbsNotice().getAtchFileId());
        tbBbsNoticeMapper.delete(bbscttId);
    }

}
