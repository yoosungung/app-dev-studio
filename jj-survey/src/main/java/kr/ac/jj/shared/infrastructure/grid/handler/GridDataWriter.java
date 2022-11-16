package kr.ac.jj.shared.infrastructure.grid.handler;

import java.util.List;
import java.util.Map;

import kr.ac.jj.shared.infrastructure.grid.model.GridRequest;

public abstract class GridDataWriter {

    protected final GridRequest gridRequest;

    private String key;
    private String dataKey;

    public GridDataWriter(GridRequest gridRequest) {
        this.gridRequest = gridRequest;

        this.key = "result";
        this.dataKey = "list";
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataKey() {
        return this.dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
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
