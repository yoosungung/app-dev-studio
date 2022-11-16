package kr.ac.jj.openapi.domain.main.mapper.api.svc.key.hist;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.openapi.domain.main.model.api.svc.key.hist.TbApiSvcKeyHist;

/**
 * 오픈API_서비스_이력 Entity Mapper
 */
abstract interface TbApiSvcKeyHistEntityMapper {

    /**
     * 목록 조회 - SVC_KEY_ID(으)로
     *
     * @param svcKeyId
     * @return
     */
    public List<TbApiSvcKeyHist> selectListBySvcKeyId(@Param("svcKeyId") String svcKeyId);

    /**
     * 조회
     *
     * @param svcHistId
     * @return
     */
    public TbApiSvcKeyHist select(@Param("svcHistId") String svcHistId);

    /**
     * 생성
     *
     * @param tbApiSvcKeyHist
     * @return
     */
    public int insert(@Param("tbApiSvcKeyHist") TbApiSvcKeyHist tbApiSvcKeyHist);

    /**
     * 수정
     *
     * @param tbApiSvcKeyHist
     * @return
     */
    public int update(@Param("tbApiSvcKeyHist") TbApiSvcKeyHist tbApiSvcKeyHist);

    /**
     * 삭제
     *
     * @param svcHistId
     * @return
     */
    public int delete(@Param("svcHistId") String svcHistId);

    /**
     * 목록 삭제 - SVC_KEY_ID(으)로
     *
     * @param svcKeyId
     * @return
     */
    public int deleteListBySvcKeyId(@Param("svcKeyId") String svcKeyId);

}
