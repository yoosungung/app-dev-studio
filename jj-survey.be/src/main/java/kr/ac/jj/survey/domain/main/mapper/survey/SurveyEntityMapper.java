package kr.ac.jj.survey.domain.main.mapper.survey;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.survey.Survey;

/**
 * 설문_응답 Entity Mapper
 */
abstract interface SurveyEntityMapper {
    /**
     * 조회
     *
     * @param id
     * @return
     */
    public Survey select(@Param("id") Integer id);

    /**
     * 생성
     *
     * @param survey
     * @return
     */
    public int insert(@Param("survey") Survey survey);

    /**
     * 수정
     *
     * @param survey
     * @return
     */
    public int update(@Param("survey") Survey survey);

    /**
     * 삭제
     *
     * @param id
     * @return
     */
    public int delete(@Param("id") Integer id);
}
