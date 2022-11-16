package kr.ac.jj.survey.infrastructure.grid.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.jj.survey.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.survey.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.survey.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;

public class GridDataWriterToJson extends GridDataWriter {
    private final PrintWriter responseWriter;
    private final ObjectMapper objectMapper;

    private String dataSetComma;
    private String dataRowComma;

    public GridDataWriterToJson(GridRequest gridRequest) {
        super(gridRequest);

        HttpServletResponse response = RequestContextUtil.getResponse();

        response.setCharacterEncoding("utf-8");
        response.setContentType(MappingJackson2JsonView.DEFAULT_CONTENT_TYPE);

        try {
            this.responseWriter = response.getWriter();
        } catch (IOException e) {
            throw new BaseException(e);
        }

        this.objectMapper = new ObjectMapper();

        this.responseWriter.print("{");

        this.dataSetComma = "";
    }

    @Override
    public void writeStart() {
        this.responseWriter.println(this.dataSetComma + "\"" + this.getKey() + "\":{\"list\":");
        this.responseWriter.print("[");

        this.dataSetComma = ",";
        this.dataRowComma = "";
    }

    @Override
    public void writeRowData(Map<String, Object> rowData) {
        try {
            String jsonString = this.objectMapper.writeValueAsString(rowData);
            this.responseWriter.println(this.dataRowComma + jsonString);
        } catch (JsonProcessingException e) {
            throw new BaseException(e);
        }

        this.dataRowComma = ",";
    }

    @Override
    public void writeEnd(boolean success) {
        if (success) {
            if (this.gridRequest != null && this.gridRequest.getPaging() != null) {
                PagingInfo paging = this.gridRequest.getPaging();

                this.responseWriter.println("],\"paging\":{\"totalRecordCount\":" + paging.getTotalRecordCount() //
                        + ",\"totalPageCount\":" + paging.getTotalPageCount() //
                        + ",\"recordCountPerPage\":" + paging.getRecordCountPerPage() //
                        + ",\"firstPageNoOnPageList\":" + paging.getFirstPageNoOnPageList() //
                        + ",\"lastPageNoOnPageList\":" + paging.getLastPageNoOnPageList() //
                        + ",\"currentPageNo\":" + paging.getCurrentPageNo() //
                        + "}}");
            } else {
                this.responseWriter.println("]}");
            }
        }
    }

    @Override
    public List<Map<String, Object>> getResultList(boolean success) {
        if (success) {
            this.responseWriter.print("}");
        }

        return null;
    }
}
