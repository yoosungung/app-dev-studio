package kr.ac.jj.shared.domain.main.mapper.com.email.atchfile;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.domain.main.model.com.email.atchfile.TbComEmailAtchFile;

/**
 * 공통 메일 첨부파일 Entity Mapper
 */
abstract interface TbComEmailAtchFileEntityMapper {

    /**
     * 목록 조회 - EMAIL_ID(으)로
     *
     * @param emailId
     * @return
     */
    public List<TbComEmailAtchFile> selectListByEmailId(@Param("emailId") String emailId);

    /**
     * 조회
     *
     * @param emailAtchFileId
     * @return
     */
    public TbComEmailAtchFile select(@Param("emailAtchFileId") String emailAtchFileId);

    /**
     * 생성
     *
     * @param tbComEmailAtchFile
     * @return
     */
    public int insert(@Param("tbComEmailAtchFile") TbComEmailAtchFile tbComEmailAtchFile);

    /**
     * 수정
     *
     * @param tbComEmailAtchFile
     * @return
     */
    public int update(@Param("tbComEmailAtchFile") TbComEmailAtchFile tbComEmailAtchFile);

    /**
     * 삭제
     *
     * @param emailAtchFileId
     * @return
     */
    public int delete(@Param("emailAtchFileId") String emailAtchFileId);

    /**
     * 목록 삭제 - EMAIL_ID(으)로
     *
     * @param emailId
     * @return
     */
    public int deleteListByEmailId(@Param("emailId") String emailId);

}
