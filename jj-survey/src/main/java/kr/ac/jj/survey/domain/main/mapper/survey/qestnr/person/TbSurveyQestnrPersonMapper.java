package kr.ac.jj.survey.domain.main.mapper.survey.qestnr.person;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import kr.ac.jj.survey.config.datasources.DataSourceMainConfig.MainSqlMapper;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.person.TbSurveyQestnrPerson;

/**
 * 설문_설문지 - 사람(대상자) Mapper
 */
@MainSqlMapper
public interface TbSurveyQestnrPersonMapper extends TbSurveyQestnrPersonEntityMapper {

    /**
     * 건수 조회 - SURVEY_QESTNR_ID로
     *
     * @param surveyQestnrId
     * @return
     */
    public int selectCountBySurveyQestnrId(@Param("surveyQestnrId") String surveyQestnrId);

    /**
     * 조회 - SURVEY_QESTNR_ID, PERSON_ID로
     *
     * @param surveyQestnrId
     * @param personId
     * @return
     */
    public TbSurveyQestnrPerson selectBySurveyQestnrIdAndPersonId(@Param("surveyQestnrId") String surveyQestnrId,
            @Param("personId") String personId);

    /**
     * 조회 - SURVEY_ID로
     *
     * @param surveyId
     * @return
     */
    public TbSurveyQestnrPerson selectBySurveyId(@Param("surveyId") Long surveyId);

    /**
     * 수정 - 발송 여부
     *
     * @param surveyPersonId
     * @param sndngYn
     * @return
     */
    public int updateSndngYn(@Param("surveyPersonId") String surveyPersonId, @Param("sndngYn") Boolean sndngYn);

    /**
     * 수정 - 개인정보 제공 동의 일시
     *
     * @param surveyPersonId
     * @param indvdlinfoProvdAgreDt
     * @return
     */
    public int updateIndvdlinfoProvdAgreDt(@Param("surveyPersonId") String surveyPersonId,
            @Param("indvdlinfoProvdAgreDt") Date indvdlinfoProvdAgreDt);

    /**
     * 수정 - 현재 페이지 번호
     *
     * @param surveyPersonId
     * @param currPgeNo
     * @return
     */
    public int updateCurrPgeNo(@Param("surveyPersonId") String surveyPersonId, @Param("currPgeNo") Short currPgeNo);

    /**
     * 수정 - 저장 페이지 번호
     *
     * @param surveyPersonId
     * @param strePgeNo
     * @return
     */
    public int updateStrePgeNo(@Param("surveyPersonId") String surveyPersonId, @Param("strePgeNo") Short strePgeNo);

}
