package kr.ac.jj.openapi.domain.main.model.api.svc.result;

import kr.ac.jj.shared.domain.main.model.MainEntity;

/**
 * 오픈API_서비스_결과_매핑 Entity
 */
abstract class TbApiSvcResultEntity extends MainEntity
{
    protected String          svcResultId;
    protected String          svcId;
    protected String          result;
    protected String          dc;

    public String getSvcResultId()
    {
        return this.svcResultId;
    }

    public void setSvcResultId(String svcResultId)
    {
        this.svcResultId = svcResultId;
    }

    public String getSvcId()
    {
        return this.svcId;
    }

    public void setSvcId(String svcId)
    {
        this.svcId = svcId;
    }

    public String getResult()
    {
        return this.result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getDc()
    {
        return this.dc;
    }

    public void setDc(String dc)
    {
        this.dc = dc;
    }
}
