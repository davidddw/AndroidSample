package org.cloud.demo0baseframework.model.http.exception;

/**
 * @author d05660ddw
 * @version 1.0 2017/7/8
 */

public class ApiException extends Exception {

    private int code;

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
