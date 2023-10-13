package com.wellsfargo.hackathon2023.save2invest.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Quote response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteResponse {
    private String symbol;
    private String name;
    private String price;
    private String changesPercentage;
    private String dayLow;
    private String dayHigh;
    private String yearHigh;
    private String yearLow;
    private String open;
    private String previousClose;
    private String eps;
    private String pe;
    private String earningAnnouncement;
}
