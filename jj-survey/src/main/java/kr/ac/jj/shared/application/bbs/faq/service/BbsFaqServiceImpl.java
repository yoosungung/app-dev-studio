package kr.ac.jj.shared.application.bbs.faq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.bbs.faq.mapper.BbsFaqMapper;
import kr.ac.jj.shared.application.bbs.faq.model.BbsFaqModel;
import kr.ac.jj.shared.application.common.file.service.FileServiceImpl;
import kr.ac.jj.shared.domain.main.mapper.bbs.faq.TbBbsFaqMapper;
import kr.ac.jj.shared.domain.main.model.bbs.faq.TbBbsFaq;
import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.web.context.support.MessageUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 게시판(FAQ) Service
 */
@Service
public class BbsFaqServiceImpl {

    private @Autowired BbsFaqMapper bbsFaqMapper;
    private @Autowired TbBbsFaqMapper tbBbsFaqMapper;
    private @Autowired FileServiceImpl fileService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        bbsFaqMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    public BbsFaqModel read() {
        BbsFaqModel model = new BbsFaqModel();

        TbBbsFaq tbBbsFaq = new TbBbsFaq();
        tbBbsFaq.setAtchFileList(new ArrayList<TbComFile>());

        model.setTbBbsFaq(tbBbsFaq);

        return model;
    }

    /**
     * 조회
     *
     * @param bbscttId
     * @param updateRdcnt
     * @return
     */
    public BbsFaqModel read(String bbscttId, Boolean updateRdcnt) {
        if (BooleanUtils.isTrue(updateRdcnt)) {
            tbBbsFaqMapper.updateRdcnt(bbscttId);
        }

        BbsFaqModel model = new BbsFaqModel();

        TbBbsFaq tbBbsFaq = tbBbsFaqMapper.select(bbscttId);
        tbBbsFaq.setAtchFileList(fileService.readList(tbBbsFaq.getAtchFileId()));

        model.setTbBbsFaq(tbBbsFaq);

        return model;
    }

    /**
     * 조회
     *
     * @param bbscttId
     * @return
     */
    private BbsFaqModel read(String bbscttId) {
        return this.read(bbscttId, false);
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(BbsFaqModel model) {
        TbBbsFaq tbBbsFaq = model.getTbBbsFaq();

        tbBbsFaq.newId();
        tbBbsFaq.setRdcnt(0);
        tbBbsFaq.setDeleteYn(false);
        tbBbsFaq.setAtchFileId(fileService.updateList(tbBbsFaq.getAtchFileId(), tbBbsFaq.getAtchFileList()));
        tbBbsFaqMapper.insert(tbBbsFaq);

        return tbBbsFaq.getBbscttId();
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(BbsFaqModel model) {
        TbBbsFaq tbBbsFaq = model.getTbBbsFaq();
        String bbscttId = tbBbsFaq.getBbscttId();
        BbsFaqModel modelDb = this.read(bbscttId);

        if (!modelDb.isEditable()) {
            throw new BizException(MessageUtil.getMessage("common.message.notAvail.update", "수정할 수 없는 데이터입니다."));
        }

        tbBbsFaq.setAtchFileId(fileService.updateList(tbBbsFaq.getAtchFileId(), tbBbsFaq.getAtchFileList()));
        tbBbsFaqMapper.update(tbBbsFaq);
    }

    /**
     * 삭제
     *
     * @param BbscttId
     */
    public void delete(String BbscttId) {
        BbsFaqModel modelDb = this.read(BbscttId);

        if (!modelDb.isEditable()) {
            throw new BizException(MessageUtil.getMessage("common.message.notAvail.delete", "삭제할 수 없는 데이터입니다."));
        }

        fileService.updateDelYnAll(modelDb.getTbBbsFaq().getAtchFileId());
        tbBbsFaqMapper.delete(BbscttId);
    }

}
