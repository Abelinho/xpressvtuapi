package com.abel.airtimevtu.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse<T> implements Serializable {

    private String status;

    private String message;

    private T data;
    private double execTime = 0;
    @Builder.Default
    private Object error = new ArrayList<>();

}
