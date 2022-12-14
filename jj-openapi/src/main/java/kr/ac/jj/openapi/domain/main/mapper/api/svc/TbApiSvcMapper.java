package kr.ac.jj.openapi.domain.main.mapper.api.svc;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.openapi.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.openapi.domain.main.model.api.svc.TbApiSvc;

/**
 * 오픈API_서비스 Mapper
 */
@MainSqlMapper
public interface TbApiSvcMapper extends TbApiSvcEntityMapper {

    /**
     * URL 중복 여부 조회
     *
     * @param tbApiSvc
     * @return
     */
    public int selectUrl(@Param("tbApiSvc") TbApiSvc tbApiSvc);

}
