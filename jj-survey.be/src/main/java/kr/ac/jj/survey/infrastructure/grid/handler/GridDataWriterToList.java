package kr.ac.jj.survey.infrastructure.grid.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;

public class GridDataWriterToList extends GridDataWriter {
    private List<Map<String, Object>> resultList;

    public GridDataWriterToList(GridRequest gridRequest) {
        super(gridRequest);
    }

    @Override
    public void writeStart() {
        this.resultList = new ArrayList<Map<String, Object>>();
    }

    @Override
    public void writeRowData(Map<String, Object> rowData) {
        this.resultList.add(rowData);
    }

    @Override
    public List<Map<String, Object>> getResultList(boolean success) {
        return this.resultList;
    }
}
