package kr.ac.jj.openapi.application.bannermanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.openapi.application.bannermanage.mapper.BannerManageMapper;
import kr.ac.jj.openapi.application.bannermanage.model.BannerManageModel;
import kr.ac.jj.openapi.domain.main.mapper.api.banner.TbApiBannerMapper;
import kr.ac.jj.openapi.domain.main.model.api.banner.TbApiBanner;
import kr.ac.jj.shared.application.common.file.service.FileServiceImpl;

/**
 * 배너관리 Service
 */
@Service
public class BannerManageServiceImpl {

    private @Autowired BannerManageMapper bannerManageMapper;
    private @Autowired TbApiBannerMapper tbApiBannerMapper;
    private @Autowired FileServiceImpl fileService;

    /**
     * 목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<TbApiBanner> readList() {
        return bannerManageMapper.readList();
    }

    /**
     * 사용중 배너목록 조회
     *
     * @param gridRequest
     * @return
     */
    public List<String> readUsingList() {
        return bannerManageMapper.readUsingList();
    }

    /**
     * 배너파일 조회
     *
     * @param gridRequest
     * @return
     */
    public List<TbApiBanner> readAtchFile(List<TbApiBanner> bannerList) {
        for(TbApiBanner banner : bannerList) {
            banner.setAtchFileList(fileService.readList(banner.getAtchFileId()));
        }
        return bannerList;
    }

    /**
     * 사용여부 수정
     *
     * @param model
     */
    public void useUpdate(TbApiBanner tbApiBanner) {

        tbApiBanner.setBannerSttus((tbApiBanner.getBannerSttus().equals("1")) ? "0" : "1");
        tbApiBannerMapper.update(tbApiBanner);
    }

    /**
     * 추가
     *
     * @param model
     */
    public void create(BannerManageModel model) {
        TbApiBanner tbApiBanner = model.getTbApiBanner();

        tbApiBanner.newId();
        tbApiBanner.setBannerSttus("1");
        tbApiBanner.setAtchFileId(fileService.updateList(tbApiBanner.getAtchFileId(), tbApiBanner.getAtchFileList()));
        tbApiBannerMapper.insert(tbApiBanner);
    }

    /**
     * 삭제
     *
     * @param bannerId
     */
    public void delete(String bannerId) {
        TbApiBanner tbApiBannerDb = tbApiBannerMapper.select(bannerId);

        fileService.updateDelYnAll(tbApiBannerDb.getAtchFileId());

        tbApiBannerMapper.delete(bannerId);
    }

    /**
     * 목록 삭제
     *
     * @param bannerIdList
     */
    public void deleteList(List<String> bannerIdList) {

        for (String bannerId : bannerIdList) {
            this.delete(bannerId);
        }
    }

}
