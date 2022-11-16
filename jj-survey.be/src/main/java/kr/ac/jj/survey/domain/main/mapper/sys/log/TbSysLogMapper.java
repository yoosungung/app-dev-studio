package kr.ac.jj.survey.domain.main.mapper.sys.log;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.DataSourceMapperConfig.MainSqlMapper;
import kr.ac.jj.survey.domain.main.model.sys.log.TbSysLog;

/**
 * 시스템 - 로그 Mapper
 */
@MainSqlMapper
public interface TbSysLogMapper extends TbSysLogEntityMapper {
    /**
     * 수정 - 응답 상태 코드, 성공 여부
     *
     * @param tbSysLog
     * @return
     */
    public int updateRspnsSttusCode(@Param("tbSysLog") TbSysLog tbSysLog);
}
