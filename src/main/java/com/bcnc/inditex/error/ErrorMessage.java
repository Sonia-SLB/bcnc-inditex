package com.bcnc.inditex.error;

import com.bcnc.inditex.configuration.JacocoAnnotationGenerated;

import java.util.Date;
@JacocoAnnotationGenerated
public class ErrorMessage {

    Integer code;
    String message;
    private Date timestamp;
    public ErrorMessage(Integer code, String message, Date timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
