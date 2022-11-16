package kr.ac.jj.survey.infrastructure.grid.handler;

import java.util.List;
import java.util.Map;

import kr.ac.jj.survey.infrastructure.grid.model.GridRequest;

public abstract class GridDataWriter {
    protected final GridRequest gridRequest;

    private String key;

    public GridDataWriter(GridRequest gridRequest) {
        this.gridRequest = gridRequest;

        this.setKey("result");
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void writeStart() {
    }

    public void writeRowData(Map<String, Object> rowData) {
    }

    public void writeEnd(boolean success) {
    }

    public List<Map<String, Object>> getResultList(boolean success) {
        return null;
    }
}
