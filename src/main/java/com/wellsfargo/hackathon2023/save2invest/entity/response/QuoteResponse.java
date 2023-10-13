package com.wellsfargo.hackathon2023.save2invest.entity.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class QuoteResponse {
    private String symbol;
    private String name;
    private String price;
    private String changesPercentage;
    private String change;
    private String dayLow;
    private String dayHigh;
    private String yearHigh;
    private String yearLow;
    private String marketCap;
    private String priceAvg50;
    private String priceAvg200;
    private String exchange;
    private String volume;
    private String avgVolume;
    private String open;
    private String previousClose;
    private String eps;
    private String pe;
    private String earningsAnnouncement;
    private String sharesOutstanding;
    private String timestamp;
}
