package kr.ac.jj.openapi.application.apilist.handler;

import org.apache.commons.lang3.StringUtils;

import kr.ac.jj.openapi.application.apilist.model.ApiRequest;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataResultHandler;

public class ApiDataResultHandler extends GridDataResultHandler {

    public ApiDataResultHandler(ApiRequest apiRequest) {
        super(apiRequest);
    }

    @Override
    protected void initGridDataWriter() {
        ApiRequest apiRequest = (ApiRequest) this.getGridRequest();
        String dataFormat = apiRequest.getDataFormat();

        if (StringUtils.equalsIgnoreCase(dataFormat, "xml")) {
            this.setGridDataWriter(new ApiDataWriterToXml(apiRequest));
        /*} else if((StringUtils.equalsIgnoreCase(dataFormat, "csv"))) {
            this.setGridDataWriter(new ApiDataWriterToCsv(apiRequest));*/
        } else {
            this.setGridDataWriter(new ApiDataWriterToJson(apiRequest));
        }
    }

}
