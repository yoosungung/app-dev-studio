package kr.ac.jj.openapi.domain.main.mapper.api.svc.key;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.openapi.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.openapi.domain.main.model.api.svc.key.TbApiSvcKey;

/**
 * 오픈API_서비스_키 Mapper
 */
@MainSqlMapper
public interface TbApiSvcKeyMapper extends TbApiSvcKeyEntityMapper {

    /**
     * 승인
     *
     * @param tbApiSvcKey
     * @return
     */
    public int confirm(@Param("tbApiSvcKey") TbApiSvcKey tbApiSvcKey);

    /**
     * 반려
     *
     * @param tbApiSvcKey
     * @return
     */
    public int reject(@Param("tbApiSvcKey") TbApiSvcKey tbApiSvcKey);

}
