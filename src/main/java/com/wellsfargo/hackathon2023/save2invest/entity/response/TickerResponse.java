package com.wellsfargo.hackathon2023.save2invest.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Ticker response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TickerResponse {
    private String symbol;
    private String name;
    private String currency;
    private String stockExchange;
    private String exchangeShortName;
}
