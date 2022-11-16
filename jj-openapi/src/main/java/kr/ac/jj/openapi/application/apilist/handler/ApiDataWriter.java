package kr.ac.jj.openapi.application.apilist.handler;

import kr.ac.jj.openapi.application.apilist.model.ApiRequest;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataWriter;

public abstract class ApiDataWriter extends GridDataWriter {

    public ApiDataWriter(ApiRequest apiRequest) {
        super(apiRequest);
    }

}
