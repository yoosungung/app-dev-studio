package kr.ac.jj.shared.application.admin.sysmanage.resrcemanage.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.application.admin.sysmanage.resrcemanage.mapper.ResourceManageMapper;
import kr.ac.jj.shared.application.admin.sysmanage.resrcemanage.model.ResourceManageModel;
import kr.ac.jj.shared.domain.main.mapper.sys.resrce.TbSysResrceMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.resrce.author.TbSysResrceAuthorMapper;
import kr.ac.jj.shared.domain.main.mapper.sys.resrce.author.TbSysResrceAuthorToAuthorMapper;
import kr.ac.jj.shared.domain.main.model.sys.resrce.TbSysResrce;
import kr.ac.jj.shared.domain.main.model.sys.resrce.author.TbSysResrceAuthor;
import kr.ac.jj.shared.domain.main.model.sys.resrce.author.TbSysResrceAuthorToAuthor;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.BaseMapList;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

/**
 * 리소스 관리 Service
 */
@Service
public class ResourceManageServiceImpl {

    private @Autowired ResourceManageMapper resourceManageMapper;
    private @Autowired TbSysResrceMapper tbSysResrceMapper;
    private @Autowired TbSysResrceAuthorMapper tbSysResrceAuthorMapper;
    private @Autowired TbSysResrceAuthorToAuthorMapper tbSysResrceAuthorToAuthorMapper;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<Map<String, Object>> readList(GridRequest gridRequest) {
        GridDataResultHandler resultHandler = new GridDataResultHandler(gridRequest);

        resourceManageMapper.selectList(resultHandler, gridRequest.getPaging(), gridRequest.getSearch());

        return resultHandler.getResultList();
    }

    /**
     * 정렬 순서 목록 조회
     *
     * @return
     */
    public List<TbSysResrce> readSortOrdrList() {
        return tbSysResrceMapper.selectSortOrdrList();
    }

    /**
     * 정렬 순서 목록 저장
     *
     * @param tbSysAuthorList
     */
    public void updateSortOrdrList(List<TbSysResrce> tbSysAuthorList) {
        for (int i = 0; i < tbSysAuthorList.size(); i++) {
            tbSysResrceMapper.updateSortOrdr(tbSysAuthorList.get(i));
        }
    }

    /**
     * 목록 순서 저장
     *
     * @param ordrList
     */
    public void updateOrdrList(BaseMapList ordrList) {
        for (int i = 0; i < ordrList.size(); i++) {
            resourceManageMapper.updateOrdr(ordrList.get(i));
        }
    }

    /**
     * 조회 - 생성용
     *
     * @return
     */
    public ResourceManageModel read() {
        ResourceManageModel model = new ResourceManageModel();

        TbSysResrce tbSysResrce = new TbSysResrce();
        tbSysResrce.setResrceTy("URL");
        tbSysResrce.setSortOrdr(tbSysResrceMapper.selectMaxSortOrdr() + 1);
        tbSysResrce.setUseYn(true);

        model.setTbSysResrce(tbSysResrce);
        model.setTbSysResrceAuthorToAuthorList(
                tbSysResrceAuthorToAuthorMapper.selectListByResrceId(model.getTbSysResrce().getResrceId()));

        return model;
    }

    /**
     * 조회
     *
     * @param resrceId
     * @return
     */
    public ResourceManageModel read(String resrceId) {
        ResourceManageModel model = new ResourceManageModel();

        model.setTbSysResrce(tbSysResrceMapper.select(resrceId));
        model.setTbSysResrceAuthorToAuthorList(
                tbSysResrceAuthorToAuthorMapper.selectListByResrceId(model.getTbSysResrce().getResrceId()));

        return model;
    }

    /**
     * 생성
     *
     * @param model
     * @return
     */
    public String create(ResourceManageModel model) {
        TbSysResrce tbSysResrce = model.getTbSysResrce().newId();
        List<TbSysResrceAuthorToAuthor> tbSysResrceAuthorToAuthorList = model.getTbSysResrceAuthorToAuthorList();
        String resrceId = tbSysResrce.getResrceId();

        tbSysResrceMapper.insert(tbSysResrce);

        this.updateAuthorList(resrceId, tbSysResrceAuthorToAuthorList);

        return resrceId;
    }

    /**
     * 수정
     *
     * @param model
     */
    public void update(ResourceManageModel model) {
        TbSysResrce tbSysResrce = model.getTbSysResrce();
        List<TbSysResrceAuthorToAuthor> tbSysResrceAuthorToAuthorList = model.getTbSysResrceAuthorToAuthorList();
        String resrceId = tbSysResrce.getResrceId();

        tbSysResrceMapper.update(tbSysResrce);

        this.updateAuthorList(resrceId, tbSysResrceAuthorToAuthorList);
    }

    /**
     * 권한 목록 저장
     *
     * @param model
     */
    private void updateAuthorList(String resrceId, List<TbSysResrceAuthorToAuthor> tbSysResrceAuthorToAuthorList) {
        tbSysResrceAuthorMapper.deleteListByResrceId(resrceId);

        for (TbSysResrceAuthorToAuthor tbSysResrceAuthorToAuthor : tbSysResrceAuthorToAuthorList) {
            if (BooleanUtils.isTrue(tbSysResrceAuthorToAuthor.getResrceAuthorYn())) {
                TbSysResrceAuthor tbSysResrceAuthor = new TbSysResrceAuthor();
                tbSysResrceAuthor.setResrceId(resrceId);
                tbSysResrceAuthor.setAuthorId(tbSysResrceAuthorToAuthor.getAuthorId());
                tbSysResrceAuthorMapper.insert(tbSysResrceAuthor);
            }
        }
    }

    /**
     * 삭제
     *
     * @param resrceId
     */
    public void delete(String resrceId) {
        tbSysResrceAuthorMapper.deleteListByResrceId(resrceId);
        tbSysResrceMapper.delete(resrceId);
    }

}
