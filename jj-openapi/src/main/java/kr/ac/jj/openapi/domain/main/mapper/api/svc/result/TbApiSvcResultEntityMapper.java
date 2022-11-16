package kr.ac.jj.openapi.domain.main.mapper.api.svc.result;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.openapi.domain.main.model.api.svc.result.TbApiSvcResult;

/**
 * 오픈API_서비스_결과_매핑 Entity Mapper
 */
abstract interface TbApiSvcResultEntityMapper
{
    /**
     * 목록 조회 - SVC_ID(으)로
     *
     * @param svcId
     * @return
     */
    public List<TbApiSvcResult> selectListBySvcId(@Param("svcId") String svcId);

    /**
     * 조회
     *
     * @param svcResultId
     * @return
     */
    public TbApiSvcResult select(@Param("svcResultId") String svcResultId);

    /**
     * 생성
     *
     * @param tbApiSvcResult
     * @return
     */
    public int insert(@Param("tbApiSvcResult") TbApiSvcResult tbApiSvcResult);

    /**
     * 수정
     *
     * @param tbApiSvcResult
     * @return
     */
    public int update(@Param("tbApiSvcResult") TbApiSvcResult tbApiSvcResult);

    /**
     * 삭제
     *
     * @param svcResultId
     * @return
     */
    public int delete(@Param("svcResultId") String svcResultId);

    /**
     * 삭제
     *
     * @param svcResultId
     * @return
     */
    public int deleteListBySvcId(@Param("svcId") String svcId);
}
