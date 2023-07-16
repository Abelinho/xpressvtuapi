package com.abel.airtimevtu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirtimeRequest {

    private String requestId;
    private String uniqueCode;
    private Details details;

}
