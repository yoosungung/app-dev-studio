package kr.ac.jj.survey.application.realmmanage.model;

/**
 * 설문 분야 사용 건수 Model
 */
public class RealmUsedCountModel {

    private int tmplatUsedCo;
    private int qestnrUsedCo;

    public int getTmplatUsedCo() {
        return this.tmplatUsedCo;
    }

    public void setTmplatUsedCo(int tmplatUsedCo) {
        this.tmplatUsedCo = tmplatUsedCo;
    }

    public int getQestnrUsedCo() {
        return this.qestnrUsedCo;
    }

    public void setQestnrUsedCo(int qestnrUsedCo) {
        this.qestnrUsedCo = qestnrUsedCo;
    }

}
