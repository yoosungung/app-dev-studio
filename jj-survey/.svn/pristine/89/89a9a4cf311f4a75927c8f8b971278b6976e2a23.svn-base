package kr.ac.jj.survey.domain.main.model.survey.qestnr.person;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.shared.application.common.util.CommonUtil;
import kr.ac.jj.shared.infrastructure.idgen.util.IdGenerationUtil;
import kr.ac.jj.survey.application.common.util.NaverShortUrlResponse;
import kr.ac.jj.survey.application.common.util.NaverShortUrlUtil;
import kr.ac.jj.survey.domain.main.model.survey.qestnr.TbSurveyQestnr;

/**
 * 설문_설문지 - 사람(대상자)
 */
public class TbSurveyQestnrPerson extends TbSurveyQestnrPersonEntity {

    private static final long serialVersionUID = -4218099349119697348L;

    public TbSurveyQestnrPerson newId() {
        this.setSurveyPersonId(IdGenerationUtil.createUid("TB_SURVEY_QESTNR_PERSON"));

        return this;
    }

    public void resetSurveyUrl(TbSurveyQestnr tbSurveyQestnr, Boolean userYn) {
        if (BooleanUtils.isTrue(userYn)) {
            this.setEmailSurveyUrl(tbSurveyQestnr.getEmailSurveyUrl());
            this.setEmailShrtenUrl(tbSurveyQestnr.getEmailShrtenUrl());

            this.setSmsSurveyUrl(tbSurveyQestnr.getSmsSurveyUrl());
            this.setSmsShrtenUrl(tbSurveyQestnr.getSmsShrtenUrl());
        } else {
            this.setEmailSurveyAndShrtenUrl(
                    CommonUtil.addUrlParameter(tbSurveyQestnr.getEmailSurveyUrl(), "surveyPersonId=" + surveyPersonId));

            this.setSmsSurveyAndShrtenUrl(
                    CommonUtil.addUrlParameter(tbSurveyQestnr.getSmsSurveyUrl(), "surveyPersonId=" + surveyPersonId));
        }
    }

    private void setEmailSurveyAndShrtenUrl(String emailSurveyUrl) {
        if (StringUtils.equals(this.emailSurveyUrl, emailSurveyUrl) && StringUtils.isNotEmpty(this.emailShrtenUrl)) {
            return;
        }

        NaverShortUrlResponse naverShortUrlResponse = NaverShortUrlUtil.get(emailSurveyUrl);

        this.emailSurveyUrl = emailSurveyUrl;
        this.emailShrtenUrl = naverShortUrlResponse.getResult().getUrl();
    }

    private void setSmsSurveyAndShrtenUrl(String smsSurveyUrl) {
        if (StringUtils.equals(this.smsSurveyUrl, smsSurveyUrl) && StringUtils.isNotEmpty(this.smsShrtenUrl)) {
            return;
        }

        NaverShortUrlResponse naverShortUrlResponse = NaverShortUrlUtil.get(smsSurveyUrl);

        this.smsSurveyUrl = smsSurveyUrl;
        this.smsShrtenUrl = naverShortUrlResponse.getResult().getUrl();
    }

}
