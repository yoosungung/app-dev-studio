package kr.ac.jj.openapi.test;

import java.util.HashMap;
import java.util.Map;

public class OpenApiClientTest {

    public static void main(String[] args) {
        String url = "http://127.0.0.1:48086/jj-openapi/apilist/ApiList/person/dmcybertrackresult3";
        Map<String, Object> requestData = new HashMap<String, Object>();
        requestData.put("apiKey", "JJ75RZR3PCJ55CA67E70");
        requestData.put("dataFormat", "json");

        Map<String, Object> paging = new HashMap<>();
        paging.put("currentPageNo", 1);
        paging.put("recordCountPerPage", 10);

        requestData.put("paging", paging);

//      String json = "{\"apiKey\": \"JJ75S11KD2OC164E5E30\", \"dataFormat\": \"json\", \"paging\": { \"currentPageNo\": \"1\", \"recordCountPerPage\": \"10\" }, \"search\": { \"trackyear\": null, \"trackmonth\": null, \"totalProgress\": null }}\"";
        System.out.println(HttpConnectionUtil.postRequest(url, requestData));
    }

}
