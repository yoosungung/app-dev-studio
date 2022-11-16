package kr.ac.jj.shared.domain.main.mapper.com.email.sndng;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;
import kr.ac.jj.shared.domain.main.model.com.email.sndng.TbComEmailSndng;

/**
 * 공통 메일 발송 Mapper
 */
@SharedMainSqlMapper
public interface TbComEmailSndngMapper extends TbComEmailSndngEntityMapper {

    /**
     * 생성 - 스케줄러로부터
     *
     * @param tbComEmailSndng
     * @return
     */
    public int insertByScheduler(@Param("tbComEmailSndng") TbComEmailSndng tbComEmailSndng);

    /**
     * 수정 - 발송중으로
     *
     * @param tbComEmailSndng
     * @return
     */
    public int updateSndngSttusWorking(@Param("tbComEmailSndng") TbComEmailSndng tbComEmailSndng);

    /**
     * 목록 조회 - 발송중
     *
     * @return
     */
    public List<TbComEmailSndng> selectSndngSttusWorkingList();

    /**
     * 수정 - 발송 결과
     *
     * @param tbComEmailSndng
     * @return
     */
    public int updateSendResult(@Param("tbComEmailSndng") TbComEmailSndng tbComEmailSndng);

}
