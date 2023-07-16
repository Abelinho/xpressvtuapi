package com.abel.airtimevtu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirtimeResponse {

    private String channel;
    private BigDecimal amount;
    private String phoneNumber;
}
