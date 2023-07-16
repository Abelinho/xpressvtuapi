package com.abel.airtimevtu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Details {

    private String phoneNumber;
    private BigDecimal amount;
}
