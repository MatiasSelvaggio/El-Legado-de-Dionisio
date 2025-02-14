package com.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("detail")
    private String detail;

    public ResponseDto(Integer code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public static ResponseDto getInstanceOK() {return new ResponseDto(200, "Service done correctly");}
}
