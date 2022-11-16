package kr.ac.jj.shared.application.common.file.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.jj.shared.domain.main.mapper.com.file.TbComFileMapper;
import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;
import kr.ac.jj.shared.infrastructure.framework.core.support.collection.DataJobTypes;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;

/**
 * 파일 Service
 */
@Service
public class FileServiceImpl {

    private @Autowired TbComFileMapper tbComFileMapper;

    /**
     * 목록 조회
     *
     * @param fileGroupId
     * @return
     */
    public List<TbComFile> readList(String fileGroupId) {
        return tbComFileMapper.selectList(fileGroupId);
    }

    /**
     * 다운로드 정보 조회
     *
     * @param dwldId
     * @return
     */
    public TbComFile readDownloadInfo(String dwldId) {
        List<TbComFile> tbComFileList = tbComFileMapper.selectDownloadList(new String[] { dwldId });

        if (tbComFileList.size() == 1) {
            return tbComFileList.get(0);
        }

        return null;
    }

    /**
     * 다운로드 목록 조회
     *
     * @param dwldIds
     * @return
     */
    public List<TbComFile> readDownloadList(String[] dwldIds) {
        return tbComFileMapper.selectDownloadList(dwldIds);
    }

    /**
     * 목록 저장
     *
     * @param fileGroupId
     * @param tbComFileList
     * @return
     */
    public String updateList(String fileGroupId, List<TbComFile> tbComFileList) {
        if (tbComFileList == null) {
            return fileGroupId;
        }

        if (StringUtils.isEmpty(fileGroupId)) {
            fileGroupId = IdGenerationUtil.createUid("TB_COM_FILE.FILE_GROUP_ID");
        }

        for (TbComFile tbComFile : tbComFileList) {
            tbComFile.setFileGroupId(fileGroupId);

            if (tbComFile.get_JOB_TYPES() == DataJobTypes.CREATE) {
                tbComFile.createDwldId();
                tbComFileMapper.insert(tbComFile.newId());
            } else if (tbComFile.get_JOB_TYPES() == DataJobTypes.DELETE) {
                tbComFileMapper.updateDelYn(tbComFile.getFileId(), true);
            } else {
                tbComFileMapper.update(tbComFile);
            }
        }

        return fileGroupId;
    }

    /**
     * 전체 삭제 처리
     *
     * @param fileGroupId
     * @return
     */
    public int updateDelYnAll(String fileGroupId) {
        return tbComFileMapper.updateDelYnAll(fileGroupId, true);
    }

}
