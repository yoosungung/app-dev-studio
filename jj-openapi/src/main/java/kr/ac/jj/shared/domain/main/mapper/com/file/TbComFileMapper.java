package kr.ac.jj.shared.domain.main.mapper.com.file;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;

/**
 * 공통 파일 Mapper
 */
@SharedMainSqlMapper
public interface TbComFileMapper extends TbComFileEntityMapper {

    /**
     * 다운로드 목록 조회
     *
     * @param dwldIds
     * @return
     */
    public List<TbComFile> selectDownloadList(@Param("dwldIds") String[] dwldIds);

    /**
     * 수정 - 삭제 여부
     *
     * @param fileId
     * @param deleteYn
     * @return
     */
    public int updateDelYn(@Param("fileId") String fileId, @Param("deleteYn") Boolean deleteYn);

    /**
     * 수정 - 전체 삭제 여부
     *
     * @param fileGroupId
     * @param deleteYn
     * @return
     */
    public int updateDelYnAll(@Param("fileGroupId") String fileGroupId, @Param("deleteYn") Boolean deleteYn);

}
