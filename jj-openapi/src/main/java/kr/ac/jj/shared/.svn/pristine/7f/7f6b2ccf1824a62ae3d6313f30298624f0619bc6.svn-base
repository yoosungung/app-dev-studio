package kr.ac.jj.shared.application.bbs.gnrl.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.bbs.gnrl.mapper.BbsGeneralMapper;
import kr.ac.jj.shared.application.bbs.gnrl.model.BbsGeneralModel;
import kr.ac.jj.shared.application.common.file.service.FileServiceImpl;
import kr.ac.jj.shared.domain.main.mapper.bbs.gnrl.TbBbsGnrlMapper;
import kr.ac.jj.shared.domain.main.model.bbs.gnrl.TbBbsGnrl;
import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BizException;
import kr.ac.jj.shared.infrastructure.framework.web.context.support.MessageUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 게시판(일반) Service
 */
@Service
public class BbsGeneralServiceImpl {

    private @Autowired BbsGeneralMapper bbsGeneralMapper;
    private @Autowired TbBbsGnrlMapper tbBbsGnrlMapper;
    private @Autowired FileServiceImpl fileService;

    /**
     * 목록 조회
     *
     * @param bbsCode
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(String bbsCode, GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        bbsGeneralMapper.selectList(bbsCode, resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회 - 생성용
     *
     * @param bbsCode
     * @return
     */
    public BbsGeneralModel read(String bbsCode) {
        BbsGeneralModel model = new BbsGeneralModel();

        TbBbsGnrl tbBbsGnrl = new TbBbsGnrl();
        tbBbsGnrl.setAtchFileList(new ArrayList<TbComFile>());

        model.setTbBbsGnrl(tbBbsGnrl);

        return model;
    }

    /**
     * 조회
     *
     * @param bbsCode
     * @param bbscttId
     * @param updateRdcnt
     * @return
     */
    public BbsGeneralModel read(String bbsCode, String bbscttId, Boolean updateRdcnt) {
        if (BooleanUtils.isTrue(updateRdcnt)) {
            tbBbsGnrlMapper.updateRdcnt(bbscttId);
        }

        BbsGeneralModel model = new BbsGeneralModel();

        TbBbsGnrl tbBbsGnrl = tbBbsGnrlMapper.select(bbscttId);

        if (BooleanUtils.isTrue(tbBbsGnrl.getDeleteYn())) {
            tbBbsGnrl = new TbBbsGnrl();
            tbBbsGnrl.setBbscttSj("(삭제된 글입니다)");
            tbBbsGnrl.setDeleteYn(true);
            tbBbsGnrl.setAtchFileList(new ArrayList<TbComFile>());
        } else {
            tbBbsGnrl.setAtchFileList(fileService.readList(tbBbsGnrl.getAtchFileId()));
        }

        model.setTbBbsGnrl(tbBbsGnrl);

        return model;
    }

    /**
     * 조회
     *
     * @param bbsCode
     * @param bbscttId
     * @return
     */
    private BbsGeneralModel read(String bbsCode, String bbscttId) {
        return this.read(bbsCode, bbscttId, false);
    }

    /**
     * 조회 - 원본글(답글 작성용)
     *
     * @param bbsCode
     * @param bbscttId
     * @return
     */
    public BbsGeneralModel readForReply(String bbsCode, String bbscttId) {
        BbsGeneralModel model = new BbsGeneralModel();

        TbBbsGnrl tbBbsGnrl = tbBbsGnrlMapper.select(bbscttId);
        tbBbsGnrl.setBbscttId(null);
        tbBbsGnrl.setBbscttNo(null);
        tbBbsGnrl.setAtchFileId(null);
        tbBbsGnrl.setAtchFileList(new ArrayList<TbComFile>());

        model.setTbBbsGnrl(tbBbsGnrl);

        return model;
    }

    /**
     * 생성
     *
     * @param bbsCode
     * @param model
     * @return
     */
    public String create(String bbsCode, BbsGeneralModel model) {
        TbBbsGnrl tbBbsGnrl = model.getTbBbsGnrl();

        tbBbsGnrl.newId();
        tbBbsGnrl.setBbsCode(bbsCode);
        tbBbsGnrl.setAtchFileId(fileService.updateList(tbBbsGnrl.getAtchFileId(), tbBbsGnrl.getAtchFileList()));
        tbBbsGnrl.setRdcnt(0);
        tbBbsGnrl.setFrstBbscttId(tbBbsGnrl.getBbscttId());
        tbBbsGnrl.setAnswerLevel(0);
        tbBbsGnrl.setAnswerOrdr(0);
        tbBbsGnrl.setDeleteYn(false);
        tbBbsGnrlMapper.insert(tbBbsGnrl);

        return tbBbsGnrl.getBbscttId();
    }

    /**
     * 생성 - 답글
     *
     * @param bbsCode
     * @param orginlBbscttId
     * @param model
     * @return
     */
    public String createReply(String bbsCode, String orginlBbscttId, BbsGeneralModel model) {
        TbBbsGnrl orginlTbBbsGnrl = tbBbsGnrlMapper.select(orginlBbscttId);

        TbBbsGnrl tbBbsGnrl = model.getTbBbsGnrl();

        tbBbsGnrl.newId();
        tbBbsGnrl.setBbsCode(bbsCode);
        tbBbsGnrl.setAtchFileId(fileService.updateList(tbBbsGnrl.getAtchFileId(), tbBbsGnrl.getAtchFileList()));
        tbBbsGnrl.setRdcnt(0);
        tbBbsGnrl.setFrstBbscttId(orginlTbBbsGnrl.getFrstBbscttId());
        tbBbsGnrl.setAnswerLevel(orginlTbBbsGnrl.getAnswerLevel() + 1);
        tbBbsGnrl.setAnswerOrdr(orginlTbBbsGnrl.getAnswerOrdr() + 1);
        tbBbsGnrl.setDeleteYn(false);
        tbBbsGnrlMapper.insert(tbBbsGnrl);

        tbBbsGnrlMapper.updateAnswerOrdrAdd(tbBbsGnrl.getBbscttId());

        return tbBbsGnrl.getBbscttId();
    }

    /**
     * 수정
     *
     * @param bbsCode
     * @param model
     */
    public void update(String bbsCode, BbsGeneralModel model) {
        TbBbsGnrl tbBbsGnrl = model.getTbBbsGnrl();
        String bbscttId = tbBbsGnrl.getBbscttId();
        BbsGeneralModel modelDb = this.read(bbsCode, bbscttId);

        if (!modelDb.isEditable()) {
            throw new BizException(MessageUtil.getMessage("common.message.notAvail.update", "수정할 수 없는 데이터입니다."));
        }

        tbBbsGnrl.setAtchFileId(fileService.updateList(tbBbsGnrl.getAtchFileId(), tbBbsGnrl.getAtchFileList()));
        tbBbsGnrlMapper.update(tbBbsGnrl);
    }

    /**
     * 삭제
     *
     * @param bbsCode
     * @param bbscttId
     */
    public void delete(String bbsCode, String bbscttId) {
        BbsGeneralModel modelDb = this.read(bbsCode, bbscttId);

        if (!modelDb.isEditable()) {
            throw new BizException(MessageUtil.getMessage("common.message.notAvail.delete", "삭제할 수 없는 데이터입니다."));
        }

        TbBbsGnrl tbBbsGnrlDb = modelDb.getTbBbsGnrl();

        fileService.updateDelYnAll(tbBbsGnrlDb.getAtchFileId());

        if (tbBbsGnrlMapper.deleteNotExistsReply(bbscttId) == 0) {
            tbBbsGnrlMapper.updateDeleteYn(bbscttId, true);
        } else {
            this.updateListAnswerOrdr(tbBbsGnrlDb.getFrstBbscttId());
        }

        this.deleteListDeleteYn(tbBbsGnrlDb.getFrstBbscttId());
    }

    /**
     * 목록 삭제 - 삭제 표시된 건들
     *
     * @param frstBbscttId
     */
    private void deleteListDeleteYn(String frstBbscttId) {
        if (tbBbsGnrlMapper.deleteListDeleteYn(frstBbscttId) > 0) {
            this.updateListAnswerOrdr(frstBbscttId);
            this.deleteListDeleteYn(frstBbscttId);
        }
    }

    /**
     * 목록 수정 - 답글 순서 변경
     *
     * @param frstBbscttId
     */
    private void updateListAnswerOrdr(String frstBbscttId) {
        List<String> bbscttIdList = tbBbsGnrlMapper.selectBbscttIdListForAnswerOrdrUpdate(frstBbscttId);

        for (int i = 0; i < bbscttIdList.size(); i++) {
            tbBbsGnrlMapper.updateAnswerOrdr(bbscttIdList.get(i), i + 1);
        }
    }

}
