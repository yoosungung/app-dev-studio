package kr.ac.jj.survey.domain.main.model.survey.qestnr.cmmn;

import org.apache.commons.lang3.BooleanUtils;

/**
 * 설문_설문지_공통문항
 */
public class TbSurveyQestnrCmmn extends TbSurveyQestnrCmmnEntity {

    private static final long serialVersionUID = 6928455467630545282L;

    public boolean isIntroEnable() {
        return this.getSexdstn() || this.getArea() || this.getGrade() || this.getCghmy() || this.getSubjct()
                || this.getStsfdg01() || this.getStsfdg02() || this.getStsfdg03();
    }

    public Boolean getSexdstn() {
        return BooleanUtils.isTrue(this.sexdstn);
    }

    @Override
    public Boolean getArea() {
        return BooleanUtils.isTrue(this.area);
    }

    @Override
    public Boolean getGrade() {
        return BooleanUtils.isTrue(this.grade);
    }

    @Override
    public Boolean getCghmy() {
        return BooleanUtils.isTrue(this.cghmy);
    }

    @Override
    public Boolean getSubjct() {
        return BooleanUtils.isTrue(this.subjct);
    }

    @Override
    public Boolean getStsfdg01() {
        return BooleanUtils.isTrue(this.stsfdg01);
    }

    @Override
    public Boolean getStsfdg02() {
        return BooleanUtils.isTrue(this.stsfdg02);
    }

    @Override
    public Boolean getStsfdg03() {
        return BooleanUtils.isTrue(this.stsfdg03);
    }

}
