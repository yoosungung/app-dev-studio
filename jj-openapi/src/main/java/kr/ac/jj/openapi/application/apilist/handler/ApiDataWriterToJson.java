package kr.ac.jj.openapi.application.apilist.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.jj.openapi.application.apilist.model.ApiRequest;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataWriterToJson;

public class ApiDataWriterToJson extends GridDataWriterToJson {

    private final PrintWriter responseWriter;
    private final ObjectMapper objectMapper;

    public ApiDataWriterToJson(ApiRequest apiRequest) {
        super(apiRequest);

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
        this.setDataKey("data");
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

        Map<String, Object> errorMap = new LinkedHashMap<String, Object>();

        errorMap.put("code", "000");
        errorMap.put("msg", "정상처리된 API서비스 입니다.");

        try {
            String jsonString = this.objectMapper.writeValueAsString(errorMap);
            this.responseWriter.println(",\"error\":" + jsonString);
        } catch (JsonProcessingException e) {
            throw new BaseException(e);
        }

        this.responseWriter.println("}");
    }

}
