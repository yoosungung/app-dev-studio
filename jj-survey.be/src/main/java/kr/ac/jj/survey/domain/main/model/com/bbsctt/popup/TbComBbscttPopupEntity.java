package kr.ac.jj.survey.domain.main.model.com.bbsctt.popup;

import java.util.Date;

import kr.ac.jj.survey.domain.main.model.MainEntity;

/**
 * 공통 - 게시글 팝업 Entity
 */
abstract class TbComBbscttPopupEntity extends MainEntity {
    private static final long serialVersionUID = -6304442612812795661L;

    protected String bbscttPopupId;
    protected String bbscttId;
    protected String prhibtPsnId;
    protected Date prhibtEndDe;

    public String getBbscttPopupId() {
        return this.bbscttPopupId;
    }

    public void setBbscttPopupId(String bbscttPopupId) {
        this.bbscttPopupId = bbscttPopupId;
    }

    public String getBbscttId() {
        return this.bbscttId;
    }

    public void setBbscttId(String bbscttId) {
        this.bbscttId = bbscttId;
    }

    public String getPrhibtPsnId() {
        return this.prhibtPsnId;
    }

    public void setPrhibtPsnId(String prhibtPsnId) {
        this.prhibtPsnId = prhibtPsnId;
    }

    public Date getPrhibtEndDe() {
        return this.prhibtEndDe;
    }

    public void setPrhibtEndDe(Date prhibtEndDe) {
        this.prhibtEndDe = prhibtEndDe;
    }
}
