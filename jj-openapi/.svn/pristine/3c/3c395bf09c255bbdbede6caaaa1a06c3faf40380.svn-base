package kr.ac.jj.openapi.domain.main.model.api.banner;

import java.util.List;

import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 오픈API - 배너
 */
public class TbApiBanner extends TbApiBannerEntity {

    private static final long serialVersionUID = 337165044136296117L;
    private List<TbComFile> atchFileList;

    public TbApiBanner newId() {
        this.setBannerId(IdGenerationUtil.createUid("TB_API_BANNER"));

        return this;
    }

    public List<TbComFile> getAtchFileList() {
        return this.atchFileList;
    }

    public void setAtchFileList(List<TbComFile> atchFileList) {
        this.atchFileList = atchFileList;
    }

}
