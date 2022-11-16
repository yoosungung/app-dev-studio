package kr.ac.jj.dashboard.application.spread.model;

import java.util.List;
import java.util.Map;

public class SpreadModel {
    private List<String> junhyungNameList;
    private List<Map<String, Object>> resultList;

    public List<String> getJunhyungNameList() {
        return this.junhyungNameList;
    }

    public void setJunhyungNameList(List<String> junhyungNameList) {
        this.junhyungNameList = junhyungNameList;
    }

    public List<Map<String, Object>> getResultList() {
        return this.resultList;
    }

    public void setResultList(List<Map<String, Object>> resultList) {
        this.resultList = resultList;
    }
}
