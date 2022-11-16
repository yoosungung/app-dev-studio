package kr.ac.jj.survey.domain.main.mapper.survey.cmmnrspns;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.survey.cmmnrspns.TbSurveyCmmnRspns;

/**
 * 설문_공통문항 응답 Entity Mapper
 */
abstract interface TbSurveyCmmnRspnsEntityMapper {

    /**
     * 목록 조회 - PERSON_ID(으)로
     *
     * @param personId
     * @return
     */
    public List<TbSurveyCmmnRspns> selectListByPersonId(@Param("personId") String personId);

    /**
     * 조회
     *
     * @param cmmnRspnsId
     * @return
     */
    public TbSurveyCmmnRspns select(@Param("cmmnRspnsId") String cmmnRspnsId);

    /**
     * 생성
     *
     * @param tbSurveyCmmnRspns
     * @return
     */
    public int insert(@Param("tbSurveyCmmnRspns") TbSurveyCmmnRspns tbSurveyCmmnRspns);

    /**
     * 수정
     *
     * @param tbSurveyCmmnRspns
     * @return
     */
    public int update(@Param("tbSurveyCmmnRspns") TbSurveyCmmnRspns tbSurveyCmmnRspns);

    /**
     * 삭제
     *
     * @param cmmnRspnsId
     * @return
     */
    public int delete(@Param("cmmnRspnsId") String cmmnRspnsId);

    /**
     * 목록 삭제 - PERSON_ID(으)로
     *
     * @param personId
     * @return
     */
    public int deleteListByPersonId(@Param("personId") String personId);

}
