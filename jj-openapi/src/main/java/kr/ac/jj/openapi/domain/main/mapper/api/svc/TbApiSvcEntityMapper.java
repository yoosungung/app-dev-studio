package kr.ac.jj.openapi.domain.main.mapper.api.svc;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.openapi.domain.main.model.api.svc.TbApiSvc;

/**
 * 오픈API_서비스 Entity Mapper
 */
abstract interface TbApiSvcEntityMapper {

    /**
     * 조회
     *
     * @param svcId
     * @return
     */
    public TbApiSvc select(@Param("svcId") String svcId);

    /**
     * 생성
     *
     * @param tbApiSvc
     * @return
     */
    public int insert(@Param("tbApiSvc") TbApiSvc tbApiSvc);

    /**
     * 수정
     *
     * @param tbApiSvc
     * @return
     */
    public int update(@Param("tbApiSvc") TbApiSvc tbApiSvc);

    /**
     * 삭제
     *
     * @param svcId
     * @return
     */
    public int delete(@Param("svcId") String svcId);

}
