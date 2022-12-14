package kr.ac.jj.openapi.application.apilist.model;

import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

public class ApiRequest extends GridRequest {

    private String apiKey;
    private String dataFormat;

    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getDataFormat() {
        return this.dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

}
