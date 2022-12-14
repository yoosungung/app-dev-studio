package kr.ac.jj.openapi.application.apilist.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import kr.ac.jj.openapi.application.apilist.model.ApiRequest;
import kr.ac.jj.shared.infrastructure.framework.core.foundation.exception.BaseException;
import kr.ac.jj.shared.infrastructure.framework.core.persistence.dao.paging.model.PagingInfo;
import kr.ac.jj.shared.infrastructure.framework.web.context.request.RequestContextUtil;
import kr.ac.jj.shared.infrastructure.grid.handler.GridDataWriter;

public class ApiDataWriterToXml extends GridDataWriter {

    private final PrintWriter responseWriter;
    private final XmlMapper xmlMapper;

    public ApiDataWriterToXml(ApiRequest apiRequest) {
        super(apiRequest);

        HttpServletResponse response = RequestContextUtil.getResponse();

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/xml");

        try {
            this.responseWriter = response.getWriter();
        } catch (IOException e) {
            throw new BaseException(e);
        }

        this.setDataKey("data");

        this.xmlMapper = new XmlMapper();

        this.responseWriter.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    }

    @Override
    public void writeStart() {
        this.responseWriter.println("<" + this.getKey() + ">");
        this.responseWriter.println("<" + this.getDataKey() + ">");
    }

    @Override
    public void writeRowData(Map<String, Object> rowData) {
        try {
            String xmlString = this.xmlMapper.writeValueAsString(rowData);
            this.responseWriter.println(xmlString);
        } catch (JsonProcessingException e) {
            throw new BaseException(e);
        }
    }

    @Override
    public void writeEnd(boolean success) {
        if (!success) {
            return;
        }

        this.responseWriter.println("</" + this.getDataKey() + ">");

        if (this.gridRequest != null && this.gridRequest.getPaging() != null) {
            PagingInfo paging = this.gridRequest.getPaging();

            PagingMap pagingMap = new PagingMap();
            pagingMap.put("totalRecordCount", paging.getTotalRecordCount());
            pagingMap.put("totalPageCount", paging.getTotalPageCount());
            pagingMap.put("recordCountPerPage", paging.getRecordCountPerPage());
            pagingMap.put("firstPageNoOnPageList", paging.getFirstPageNoOnPageList());
            pagingMap.put("lastPageNoOnPageList", paging.getLastPageNoOnPageList());
            pagingMap.put("currentPageNo", paging.getCurrentPageNo());

            try {
                String xmlString = this.xmlMapper.writeValueAsString(pagingMap);
                this.responseWriter.println(xmlString);
            } catch (JsonProcessingException e) {
                throw new BaseException(e);
            }

        }

        ErrorMap errorMap = new ErrorMap();

        errorMap.put("code", "000");
        errorMap.put("msg", "??????????????? API????????? ?????????.");

        try {
            String xmlString = this.xmlMapper.writeValueAsString(errorMap);
            this.responseWriter.println(xmlString);
        } catch (JsonProcessingException e) {
            throw new BaseException(e);
        }

        this.responseWriter.println("</" + this.getKey() + ">");
    }

    @Override
    public List<Map<String, Object>> getResultList(boolean success) {
        return null;
    }

    @JacksonXmlRootElement(localName = "paging")
    public class PagingMap extends LinkedHashMap<String, Object> {

        private static final long serialVersionUID = 1815691535954920425L;

    }

    @JacksonXmlRootElement(localName = "error")
    public class ErrorMap extends LinkedHashMap<String, Object> {

        private static final long serialVersionUID = -4821562023668401436L;

    }

}
