package kr.ac.jj.survey.application.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
XML 결과 형태
<?xml version="1.0" encoding="UTF-8"?>
    <result>
        <result>
            <hash><![CDATA[5mpMbVA9]]></hash>
            <url><![CDATA[http://me2.do/5mpMbVA9]]></url>
            <orgUrl><![CDATA[http://www.jj.ac.kr/jj/community/notice/gennotice.jsp?mode=view&article_no=358755]]></orgUrl>
        </result>
        <code><![CDATA[200]]></code>
        <message><![CDATA[ok]]></message>
    </result>


https://developers.naver.com/main/

QR코드 이미지 생성: http://me2.do/5mpMbVA9.qr
<img src="http://me2.do/5mpMbVA9.qr"/>
*/
public class NaverShortUrlUtil {

    private static final Logger log = LoggerFactory.getLogger(NaverShortUrlUtil.class);

    public static void main(String[] args) {
        String cacertsPath = "D:/App-DevStudio/bin/java/openjdk-12.0.1-x64/lib/security/cacerts";
        System.setProperty("javax.net.ssl.trustStore", cacertsPath);
        System.setProperty("javax.net.ssl.trustAnchors", cacertsPath);

        String originalURL = "http://www.jj.ac.kr/jj/community/notice/gennotice.jsp?mode=view&article_no=358755";

        NaverShortUrlResponse response = get(originalURL);

        System.out.println(response);
    }

    public static NaverShortUrlResponse get(String originalURL) {
        String apiURL;

        try {
            apiURL = "https://openapi.naver.com/v1/util/shorturl.json?url=" + URLEncoder.encode(originalURL, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        String clientId = "UKD0V1oK_LNoWeJJ4XEH"; // 애플리케이션 클라이언트 아이디값
        String clientSecret = "6LjKIwtdIk"; // 애플리케이션 클라이언트 시크릿값

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);

        HttpURLConnection con = connect(apiURL);

        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                String body = readBody(con.getInputStream());
                ObjectMapper objectMapper = new ObjectMapper();
                NaverShortUrlResponse response = objectMapper.readValue(body, NaverShortUrlResponse.class);
                return response;
            } else { // 에러 발생
                log.error(readBody(con.getErrorStream()));
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

}
