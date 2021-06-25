
package com.task.service.constant;

public enum StatusCode {
    OK(0, "ok"), INTERNAL_SERVER_ERROR(-1, "internal server error"), NOT_FOUND(-2, "not found");

    private Integer code;
    private String description;

    private StatusCode(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
    
}
