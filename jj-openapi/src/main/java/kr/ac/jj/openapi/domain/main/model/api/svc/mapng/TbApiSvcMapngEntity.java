package kr.ac.jj.openapi.domain.main.model.api.svc.mapng;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 오픈API_서비스_인자_매핑 Entity
 */
abstract class TbApiSvcMapngEntity extends MainEntity {

    private static final long serialVersionUID = 8878303028079643556L;
    protected String svcMapngId;
    protected String svcId;
    protected Boolean essntlYn;
    protected String mapngSe;
    protected String vriablNm;
    protected String vriabl;
    protected String dc;

    public String getSvcMapngId() {
        return this.svcMapngId;
    }

    public void setSvcMapngId(String svcMapngId) {
        this.svcMapngId = svcMapngId;
    }

    public String getSvcId() {
        return this.svcId;
    }

    public void setSvcId(String svcId) {
        this.svcId = svcId;
    }

    public Boolean getEssntlYn() {
        return this.essntlYn;
    }

    public void setEssntlYn(Boolean essntlYn) {
        this.essntlYn = essntlYn;
    }

    public String getMapngSe() {
        return this.mapngSe;
    }

    public void setMapngSe(String mapngSe) {
        this.mapngSe = mapngSe;
    }

    public String getVriablNm() {
        return this.vriablNm;
    }

    public void setVriablNm(String vriablNm) {
        this.vriablNm = vriablNm;
    }

    public String getVriabl() {
        return this.vriabl;
    }

    public void setVriabl(String vriabl) {
        this.vriabl = vriabl;
    }

    public String getDc() {
        return this.dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

}
