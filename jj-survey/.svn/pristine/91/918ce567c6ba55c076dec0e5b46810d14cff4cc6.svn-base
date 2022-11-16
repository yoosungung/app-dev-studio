package kr.ac.jj.survey.domain.main.mapper.survey.realm;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.survey.realm.TbSurveyRealm;

/**
 * 설문_분야 Entity Mapper
 */
abstract interface TbSurveyRealmEntityMapper {

    /**
     * 조회
     *
     * @param surveyRealmId
     * @return
     */
    public TbSurveyRealm select(@Param("surveyRealmId") String surveyRealmId);

    /**
     * 생성
     *
     * @param tbSurveyRealm
     * @return
     */
    public int insert(@Param("tbSurveyRealm") TbSurveyRealm tbSurveyRealm);

    /**
     * 수정
     *
     * @param tbSurveyRealm
     * @return
     */
    public int update(@Param("tbSurveyRealm") TbSurveyRealm tbSurveyRealm);

    /**
     * 삭제
     *
     * @param surveyRealmId
     * @return
     */
    public int delete(@Param("surveyRealmId") String surveyRealmId);

}
