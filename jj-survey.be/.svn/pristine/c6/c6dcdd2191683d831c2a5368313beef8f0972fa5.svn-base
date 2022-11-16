package kr.ac.jj.survey.infrastructure.error.model;

import java.util.Date;
import java.util.Map;

public class ErrorHandleModel {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String trace;
    private String path;
    private Throwable throwable;

    public ErrorHandleModel(Map<String, Object> errorAttributes, Throwable throwable) {
        this.setTimestamp((Date) errorAttributes.get("timestamp"));
        this.setStatus((Integer) errorAttributes.get("status"));
        this.setError((String) errorAttributes.get("error"));
        this.setMessage((String) errorAttributes.get("message"));
        this.setTrace((String) errorAttributes.get("trace"));
        this.setPath((String) errorAttributes.get("path"));
        this.setThrowable(throwable);
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTrace() {
        return this.trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
