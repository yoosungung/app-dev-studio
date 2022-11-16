package kr.ac.jj.survey.application.common.util;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class NaverShortUrlResponse {

    private Result result;
    private String message;
    private String code;

    public Result getResult() {
        return this.result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class Result {

        private String url;
        private String hash;
        private String orgUrl;

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHash() {
            return this.hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getOrgUrl() {
            return this.orgUrl;
        }

        public void setOrgUrl(String orgUrl) {
            this.orgUrl = orgUrl;
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }

    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
