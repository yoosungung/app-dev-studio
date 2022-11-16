package kr.ac.jj.survey.domain.main.mapper.jd.survey.definition;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.domain.main.model.jd.survey.definition.JdSurveyDefinition;

/**
 * Entity Mapper
 */
abstract interface JdSurveyDefinitionEntityMapper {

    /**
     * 조회
     *
     * @param id
     * @return
     */
    public JdSurveyDefinition select(@Param("id") Long id);

    /**
     * 생성
     *
     * @param jdSurveyDefinition
     * @return
     */
    public int insert(@Param("jdSurveyDefinition") JdSurveyDefinition jdSurveyDefinition);

    /**
     * 수정
     *
     * @param jdSurveyDefinition
     * @return
     */
    public int update(@Param("jdSurveyDefinition") JdSurveyDefinition jdSurveyDefinition);

    /**
     * 삭제
     *
     * @param id
     * @return
     */
    public int delete(@Param("id") Long id);

}
