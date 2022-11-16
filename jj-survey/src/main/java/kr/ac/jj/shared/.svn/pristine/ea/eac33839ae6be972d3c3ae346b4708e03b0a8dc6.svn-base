package kr.ac.jj.shared.application.admin.appmanage.dtymanage.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.admin.appmanage.dtymanage.mapper.DutyManageMapper;
import kr.ac.jj.shared.application.admin.appmanage.dtymanage.model.DutyManageModel;
import kr.ac.jj.shared.domain.main.mapper.com.dty.TbComDtyMapper;
import kr.ac.jj.shared.domain.main.mapper.com.dty.author.TbComDtyAuthorMapper;
import kr.ac.jj.shared.domain.main.mapper.com.dty.author.TbComDtyAuthorToAuthorMapper;
import kr.ac.jj.shared.domain.main.model.com.dty.TbComDty;
import kr.ac.jj.shared.domain.main.model.com.dty.author.TbComDtyAuthor;
import kr.ac.jj.shared.domain.main.model.com.dty.author.TbComDtyAuthorToAuthor;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 직무 관리 Service
 */
@Service
public class DutyManageServiceImpl {

    private @Autowired DutyManageMapper dutyManageMapper;
    private @Autowired TbComDtyMapper tbComDtyMapper;
    private @Autowired TbComDtyAuthorMapper tbComDtyAuthorMapper;
    private @Autowired TbComDtyAuthorToAuthorMapper tbComDtyAuthorToAuthorMapper;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        dutyManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    public DutyManageModel read() {
        DutyManageModel model = new DutyManageModel();

        TbComDty tbComDty = new TbComDty();
        tbComDty.setUseYn(true);

        model.setTbComDty(tbComDty);
        model.setTbComDtyAuthorToAuthorList(tbComDtyAuthorToAuthorMapper.selectListByDtyId(null));

        return model;
    }

    /**
     * 조회
     *
     * @param dtyId
     * @return
     */
    public DutyManageModel read(String dtyId) {
        DutyManageModel model = new DutyManageModel();

        TbComDty tbComDty = tbComDtyMapper.select(dtyId);

        model.setTbComDty(tbComDty);
        model.setTbComDtyAuthorToAuthorList(tbComDtyAuthorToAuthorMapper.selectListByDtyId(dtyId));

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(DutyManageModel model) {
        TbComDty tbComDty = model.getTbComDty().newId();
        List<TbComDtyAuthorToAuthor> tbComDtyAuthorToAuthorList = model.getTbComDtyAuthorToAuthorList();
        String dtyId = tbComDty.getDtyId();

        tbComDtyMapper.insert(tbComDty);

        this.updateAuthorList(tbComDtyAuthorToAuthorList, dtyId);

        return dtyId;
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(DutyManageModel model) {
        TbComDty tbComDty = model.getTbComDty();
        List<TbComDtyAuthorToAuthor> tbComDtyAuthorToAuthorList = model.getTbComDtyAuthorToAuthorList();
        String dtyId = tbComDty.getDtyId();

        tbComDtyMapper.update(tbComDty);

        this.updateAuthorList(tbComDtyAuthorToAuthorList, dtyId);
    }

    /**
     * 권한 목록 저장
     *
     * @param tbComDtyAuthorToAuthorList
     * @param dtyId
     */
    private void updateAuthorList(List<TbComDtyAuthorToAuthor> tbComDtyAuthorToAuthorList, String dtyId) {
        tbComDtyAuthorMapper.deleteListByDtyId(dtyId);

        for (TbComDtyAuthorToAuthor tbComDtyAuthorToAuthor : tbComDtyAuthorToAuthorList) {
            if (BooleanUtils.isTrue(tbComDtyAuthorToAuthor.getDtyAuthorYn())) {
                TbComDtyAuthor tbComDtyAuthor = new TbComDtyAuthor();
                tbComDtyAuthor.setDtyId(dtyId);
                tbComDtyAuthor.setAuthorId(tbComDtyAuthorToAuthor.getAuthorId());
                tbComDtyAuthorMapper.insert(tbComDtyAuthor);
            }
        }
    }

    /**
     * 생성/수정
     *
     * @param tbComDty
     * @return
     */
    public String createOrUpdate(TbComDty tbComDty) {
        if (StringUtils.isEmpty(tbComDty.getDtyId())) {
            tbComDty.newId();
            tbComDtyMapper.insert(tbComDty);
        } else {
            tbComDtyMapper.update(tbComDty);
        }

        return tbComDty.getDtyId();
    }

    /**
     * 삭제
     *
     * @param dtyId
     */
    public void delete(String dtyId) {
        tbComDtyAuthorMapper.deleteListByDtyId(dtyId);
        tbComDtyMapper.delete(dtyId);
    }

}
