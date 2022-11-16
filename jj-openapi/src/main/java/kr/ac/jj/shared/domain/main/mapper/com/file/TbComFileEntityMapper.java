package kr.ac.jj.shared.domain.main.mapper.com.file;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.file.TbComFile;

/**
 * 공통 파일 Entity Mapper
 */
abstract interface TbComFileEntityMapper {

    /**
     * 목록 조회
     *
     * @param fileGroupId
     * @return
     */
    public List<TbComFile> selectList(@Param("fileGroupId") String fileGroupId);

    /**
     * 조회
     *
     * @param fileId
     * @return
     */
    public TbComFile select(@Param("fileId") String fileId);

    /**
     * 생성
     *
     * @param tbComFile
     * @return
     */
    public int insert(@Param("tbComFile") TbComFile tbComFile);

    /**
     * 수정
     *
     * @param tbComFile
     * @return
     */
    public int update(@Param("tbComFile") TbComFile tbComFile);

    /**
     * 삭제
     *
     * @param fileId
     * @return
     */
    public int delete(@Param("fileId") String fileId);

    /**
     * 목록 삭제
     *
     * @param fileGroupId
     * @return
     */
    public int deleteList(@Param("fileGroupId") String fileGroupId);

}
