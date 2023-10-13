package com.wellsfargo.hackathon2023.save2invest.service;

import com.wellsfargo.hackathon2023.save2invest.entity.response.CompanyProfileResponse;
import com.wellsfargo.hackathon2023.save2invest.entity.response.QuoteResponse;
import com.wellsfargo.hackathon2023.save2invest.entity.response.StockNewsResponse;
import com.wellsfargo.hackathon2023.save2invest.entity.response.TickerResponse;

import java.util.List;

/**
 * The interface Ticker service.
 */
public interface TickerService {

    /**
     * Gets tickers by organization name.
     *
     * @param orgName the org name
     * @return the tickers by organization name
     */
    public List<TickerResponse> getTickersByOrganizationName(String orgName);

    /**
     * Gets company profile by ticker.
     *
     * @param ticker the ticker
     * @return the company profile by ticker
     */
    public CompanyProfileResponse getCompanyProfileByTicker(String ticker);

    /**
     * Gets stock news by ticker.
     *
     * @param ticker the ticker
     * @return the stock news by ticker
     */
    public List<StockNewsResponse> getStockNewsByTicker(String ticker);

    /**
     * Gets quote by ticker.
     *
     * @param ticker the ticker
     * @return the quote by ticker
     */
    public QuoteResponse getQuoteByTicker(String ticker);
}
