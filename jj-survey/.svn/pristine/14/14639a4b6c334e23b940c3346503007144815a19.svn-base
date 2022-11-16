package kr.ac.jj.survey.domain.main.model.survey.qestnr;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.application.common.util.CommonUtil;
import kr.ac.jj.shared.infrastructure.framework.web.support.codedata.CodeDataUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;
import kr.ac.jj.survey.application.common.util.NaverShortUrlResponse;
import kr.ac.jj.survey.application.common.util.NaverShortUrlUtil;

/**
 * 설문_설문지
 */
public class TbSurveyQestnr extends TbSurveyQestnrEntity {

    private static final long serialVersionUID = 7381852266853471430L;

    private String surveyRealmNm;
    private String registDeptNm;

    public TbSurveyQestnr newId() {
        this.setSurveyQestnrId(IdGenerationUtil.createUid("TB_SURVEY_QESTNR"));

        return this;
    }

    public String getSurveyRealmNm() {
        return this.surveyRealmNm;
    }

    public void setSurveyRealmNm(String surveyRealmNm) {
        this.surveyRealmNm = surveyRealmNm;
    }

    public String getRegistDeptNm() {
        return this.registDeptNm;
    }

    public void setRegistDeptNm(String registDeptNm) {
        this.registDeptNm = registDeptNm;
    }

    public String getCnrsYnNm() {
        return CodeDataUtil.getCodeName("[CNRS_YN]", this.cnrsYn);
    }

    public String getSndngYnNm() {
        return CodeDataUtil.getCodeName("[SURVEY_SNDNG_YN]", this.sndngYn);
    }

    public void resetSurveyUrl(String serverUrl) {
        String surveyUrl = serverUrl + "/survey/" + this.getSurveyDefinitionId();

        this.setEmailSurveyAndShrtenUrl(surveyUrl);
        this.setSmsSurveyAndShrtenUrl(surveyUrl);
    }

    private void setEmailSurveyAndShrtenUrl(String surveyUrl) {
        String emailSurveyUrl = CommonUtil.addUrlParameter(surveyUrl, "conectLink=EMAIL");

        if (StringUtils.equals(this.emailSurveyUrl, emailSurveyUrl) && StringUtils.isNotEmpty(this.emailShrtenUrl)) {
            return;
        }

        NaverShortUrlResponse naverShortUrlResponse = NaverShortUrlUtil.get(emailSurveyUrl);

        this.emailSurveyUrl = emailSurveyUrl;
        this.emailShrtenUrl = naverShortUrlResponse.getResult().getUrl();
    }

    private void setSmsSurveyAndShrtenUrl(String surveyUrl) {
        String smsSurveyUrl = CommonUtil.addUrlParameter(surveyUrl, "conectLink=SMS");

        if (StringUtils.equals(this.smsSurveyUrl, smsSurveyUrl) && StringUtils.isNotEmpty(this.smsShrtenUrl)) {
            return;
        }

        NaverShortUrlResponse naverShortUrlResponse = NaverShortUrlUtil.get(smsSurveyUrl);

        this.smsSurveyUrl = smsSurveyUrl;
        this.smsShrtenUrl = naverShortUrlResponse.getResult().getUrl();
    }

}
