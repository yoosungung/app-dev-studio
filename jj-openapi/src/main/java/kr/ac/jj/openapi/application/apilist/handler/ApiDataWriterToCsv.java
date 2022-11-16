package kr.ac.jj.openapi.application.apilist.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import kr.ac.jj.openapi.application.apilist.model.ApiRequest;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataWriter;

public class ApiDataWriterToCsv extends GridDataWriter {

    private final PrintWriter responseWriter;

    public ApiDataWriterToCsv(ApiRequest apiRequest) {
        super(apiRequest);

        HttpServletResponse response = RequestContextUtil.getResponse();

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/csv");

        try {
            this.responseWriter = response.getWriter();
        } catch (IOException e) {
            throw new BaseException(e);
        }
    }

    @Override
    public void writeRowData(Map<String, Object> rowData) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();

        map.putAll(rowData);

        int index = 1;
        if((int) map.get("rn") == 1 ) {
            for(Map.Entry<String, Object> elem : map.entrySet()) {

                if(StringUtils.equals(map.keySet().stream().findFirst().get(), elem.getKey())) {
                    responseWriter.print(elem.getKey());
                } else if(index == map.keySet().size()){
                    responseWriter.println("," + elem.getKey());
                } else {
                    responseWriter.print("," + elem.getKey());
                }
                index++;
            }
        }

        index = 1;
        for(Map.Entry<String, Object> elem : map.entrySet()) {
            if(StringUtils.equals(map.keySet().stream().findFirst().get(), elem.getKey())) {
                responseWriter.print(elem.getValue());
            } else if(index == map.keySet().size()){
                responseWriter.println("," + elem.getValue());
            } else {
                responseWriter.print("," + elem.getValue());
            }
            index++;
        }

    }

}
