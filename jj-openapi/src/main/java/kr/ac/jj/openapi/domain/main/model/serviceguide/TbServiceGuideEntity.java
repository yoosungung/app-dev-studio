package kr.ac.jj.openapi.domain.main.model.serviceguide;

import java.util.Date;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 서비스 가이드 Entity
 */
abstract class TbServiceGuideEntity extends MainEntity {

    private static final long serialVersionUID = 8671981735980402514L;

    protected String serviceTitle;
    protected String serviceContent;
    protected Date createDt;
    protected Date changeDt;
    protected String createPsnId;
    protected String changePsnId;

    public String getServiceTitle() {
        return this.serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getServiceContent() {
        return this.serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public Date getCreateDt() {
        return this.createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public Date getChangeDt() {
        return this.changeDt;
    }

    public void setChangeDt(Date changeDt) {
        this.changeDt = changeDt;
    }

    public String getCreatePsnId() {
        return this.createPsnId;
    }

    public void setCreatePsnId(String createPsnId) {
        this.createPsnId = createPsnId;
    }

    public String getChangePsnId() {
        return this.changePsnId;
    }

    public void setChangePsnId(String changePsnId) {
        this.changePsnId = changePsnId;
    }

}
