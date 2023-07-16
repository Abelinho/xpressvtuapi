package com.abel.airtimevtu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppResponse<T> implements Serializable {

    private String referenceId;
    private String requestId;
    private String responseCode;
    private String responseMessage;
    private T data;
}
