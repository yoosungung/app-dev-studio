package kr.ac.jj.openapi.domain.main.mapper.api.svc.mapng;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.openapi.domain.main.model.api.svc.mapng.TbApiSvcMapng;

/**
 * 오픈API_서비스_인자_매핑 Entity Mapper
 */
abstract interface TbApiSvcMapngEntityMapper {

    /**
     * 목록 조회 - SVC_ID(으)로
     *
     * @param svcId
     * @return
     */
    public List<TbApiSvcMapng> selectListBySvcId(@Param("svcId") String svcId);

    /**
     * 조회
     *
     * @param svcMapngId
     * @return
     */
    public TbApiSvcMapng select(@Param("svcMapngId") String svcMapngId);

    /**
     * 생성
     *
     * @param tbApiSvcMapng
     * @return
     */
    public int insert(@Param("tbApiSvcMapng") TbApiSvcMapng tbApiSvcMapng);

    /**
     * 수정
     *
     * @param tbApiSvcMapng
     * @return
     */
    public int update(@Param("tbApiSvcMapng") TbApiSvcMapng tbApiSvcMapng);

    /**
     * 삭제
     *
     * @param svcMapngId
     * @return
     */
    public int delete(@Param("svcMapngId") String svcMapngId);

    /**
     * 목록 삭제 - SVC_ID(으)로
     *
     * @param svcId
     * @return
     */
    public int deleteListBySvcId(@Param("svcId") String svcId);

}
