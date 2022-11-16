import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class ApiRequest {
    public static void main(String[] args) {
        String url = "{서비스 URL}";
        Map<String, Object> requestData = new HashMap<>();

        requestData.put("apiKey", "{API키}");
        requestData.put("dataFormat", "json");

        Map<String, Object> paging = new HashMap<>();
        paging.put("currentPageNo", "1");
        paging.put("recordCountPerPage", "10");

        requestData.put("paging", paging);

        System.out.println(HttpConnectionUtil.postRequest(url, requestData));
    }
}

class HttpConnectionUtil {
    public static String postRequest(String pURL, Map<String, Object> requestData) {
        String result = "";

        try {
            URL url = new URL(pURL); // URL 설정

            HttpURLConnection http = (HttpURLConnection) url.openConnection(); // 접속
            http.setDefaultUseCaches(false);
            http.setDoInput(true);
            http.setDoOutput(true);
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");

            JSONObject json = new JSONObject(requestData);

            System.out.println(json.toString());
            DataOutputStream dos = null;

            try {
                dos = new DataOutputStream(http.getOutputStream());
                dos.write(json.toString().getBytes("UTF-8"));
                dos.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                builder.append(str + "\n");
            }
            result = builder.toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}