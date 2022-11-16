package kr.ac.jj.shared.infrastructure.scheduler.mapper;

import kr.ac.jj.shared.config.datasources.SharedDataSourceMainConfig.SharedMainSqlMapper;

/**
 * 스케줄러 Job Mapper
 */
@SharedMainSqlMapper
public interface SchedulerJobMapper {

    /**
     * 스케줄러 목록 전체 미사용 처리
     *
     * @return
     */
    public int updateToNoUseAllList();

    /**
     * 미완료된 실행 목록 비정상 종료 처리
     *
     * @return
     */
    public int updateAbnormalEndList();

}
