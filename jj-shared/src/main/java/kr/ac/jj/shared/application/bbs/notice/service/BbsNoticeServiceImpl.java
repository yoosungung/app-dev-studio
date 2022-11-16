package kr.ac.jj.shared.application.bbs.notice.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.bbs.notice.mapper.BbsNoticeMapper;
import kr.ac.jj.shared.application.bbs.notice.model.BbsNoticeModel;
import kr.ac.jj.shared.domain.main.mapper.bbs.notice.TbBbsNoticeMapper;
import kr.ac.jj.shared.domain.main.model.bbs.notice.TbBbsNotice;
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
    private @Autowired TbBbsNoticeMapper tbbbsNoticeMapper;

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
         TbBbsNotice tbNoticeFaq = new TbBbsNotice();
         model.setTbBbsNotice(tbNoticeFaq);
         model.isEditable();
         return model;
    }

    /**
     * 조회
     *
     * @param bbscttId
     * @return
     */
    public BbsNoticeModel read(String bbscttId) {
        BbsNoticeModel model =new BbsNoticeModel();
        TbBbsNotice tbBbsNotice =tbbbsNoticeMapper.select(bbscttId);
        model.setTbBbsNotice(tbBbsNotice);
        model.isEditable();
        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(BbsNoticeModel model) {


        TbBbsNotice tbbbsnotice = model.getTbBbsNotice().newId();
        if(tbbbsnotice !=null) {
            tbbbsnotice.setRdcnt(0);
            String bbscttId = tbbbsnotice.getBbscttId();
            tbbbsnotice.setDeleteYn(true);
            tbbbsnotice.setWritngPsnId(bbscttId);
            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
            Date time = new Date();
            String time1 = format1.format(time);
            tbbbsnotice.setWritngDt(time);
            tbbbsNoticeMapper.insert(tbbbsnotice);
        }
        return tbbbsnotice.getBbscttId();
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(BbsNoticeModel model) {
        if (!this.read(model.getTbBbsNotice().getBbscttId()).isEditable()) {
            throw new BizException(MessageUtil.getMessage("common.message.notAvail.update", "수정할 수 없는 데이터입니다."));
        }

        TbBbsNotice tbBbsFaq = model.getTbBbsNotice();

        tbbbsNoticeMapper.update(tbBbsFaq);
    }

    /**
     * 삭제
     *
     * @param bbscttId
     */
    public void delete(String bbscttId) {
         if (!this.read(bbscttId).isEditable()) {
             throw new BizException(MessageUtil.getMessage("common.message.notAvail.delete", "삭제할 수 없는 데이터입니다."));
         }
         tbbbsNoticeMapper.delete(bbscttId);
    }

}
