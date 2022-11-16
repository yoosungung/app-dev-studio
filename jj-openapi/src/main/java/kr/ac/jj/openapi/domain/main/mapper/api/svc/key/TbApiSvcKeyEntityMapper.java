package kr.ac.jj.openapi.domain.main.mapper.api.svc.key;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.openapi.domain.main.model.api.svc.key.TbApiSvcKey;

/**
 * 오픈API_서비스_키 Entity Mapper
 */
abstract interface TbApiSvcKeyEntityMapper {

    /**
     * 목록 조회 - SVC_ID(으)로
     *
     * @param svcId
     * @return
     */
    public List<TbApiSvcKey> selectListBySvcId(@Param("svcId") String svcId);

    /**
     * 조회
     *
     * @param svcKeyId
     * @return
     */
    public TbApiSvcKey select(@Param("svcKeyId") String svcKeyId);

    /**
     * 생성
     *
     * @param tbApiSvcKey
     * @return
     */
    public int insert(@Param("tbApiSvcKey") TbApiSvcKey tbApiSvcKey);

    /**
     * 수정
     *
     * @param tbApiSvcKey
     * @return
     */
    public int update(@Param("tbApiSvcKey") TbApiSvcKey tbApiSvcKey);

    /**
     * 삭제
     *
     * @param svcKeyId
     * @return
     */
    public int delete(@Param("svcKeyId") String svcKeyId);

    /**
     * 목록 삭제 - SVC_ID(으)로
     *
     * @param svcId
     * @return
     */
    public int deleteListBySvcId(@Param("svcId") String svcId);

}
