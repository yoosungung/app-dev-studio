package kr.ac.jj.shared.infrastructure.grid.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

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

        if (gridRequest.getOption() != null && StringUtils.isNotEmpty(gridRequest.getOption().getDateFormat())) {
            this.objectMapper.setDateFormat(new SimpleDateFormat(gridRequest.getOption().getDateFormat()));
        }

        this.responseWriter.print("{");

        this.dataSetComma = "";
    }

    @Override
    public void writeStart() {
        this.responseWriter.println(this.dataSetComma + "\"" + this.getKey() + "\":{\"" + this.getDataKey() + "\":");
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
        if (!success) {
            return;
        }

        this.responseWriter.println("]");

        if (this.gridRequest != null && this.gridRequest.getPaging() != null) {
            PagingInfo paging = this.gridRequest.getPaging();

            Map<String, Object> pagingMap = new LinkedHashMap<String, Object>();
            pagingMap.put("totalRecordCount", paging.getTotalRecordCount());
            pagingMap.put("totalPageCount", paging.getTotalPageCount());
            pagingMap.put("recordCountPerPage", paging.getRecordCountPerPage());
            pagingMap.put("firstPageNoOnPageList", paging.getFirstPageNoOnPageList());
            pagingMap.put("lastPageNoOnPageList", paging.getLastPageNoOnPageList());
            pagingMap.put("currentPageNo", paging.getCurrentPageNo());

            try {
                String jsonString = this.objectMapper.writeValueAsString(pagingMap);
                this.responseWriter.println(",\"paging\":" + jsonString);
            } catch (JsonProcessingException e) {
                throw new BaseException(e);
            }
        }

        this.responseWriter.println("}");
    }

    @Override
    public List<Map<String, Object>> getResultList(boolean success) {
        if (success) {
            this.responseWriter.print("}");
        }

        return null;
    }

}
