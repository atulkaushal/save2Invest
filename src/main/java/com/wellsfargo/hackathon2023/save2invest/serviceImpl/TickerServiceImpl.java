package com.wellsfargo.hackathon2023.save2invest.serviceImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wellsfargo.hackathon2023.save2invest.entity.response.CompanyProfileResponse;
import com.wellsfargo.hackathon2023.save2invest.entity.response.QuoteResponse;
import com.wellsfargo.hackathon2023.save2invest.entity.response.StockNewsResponse;
import com.wellsfargo.hackathon2023.save2invest.entity.response.TickerResponse;
import com.wellsfargo.hackathon2023.save2invest.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * The type Ticker service.
 */
@Service
public class TickerServiceImpl implements TickerService {

    /**
     * The Ticker service end point.
     */
    @Value("${ticker.service.endpoint}")
    String tickerServiceEndPoint;
    /**
     * The Stock news end point.
     */
    @Value("${stock.news.endpoint}")
    String stockNewsEndPoint;
    /**
     * The Company profile end point.
     */
    @Value("${company.profile.endpoint}")
    String companyProfileEndPoint;

    /**
     * The Quote end point.
     */
    @Value("${quote.endpoint}")
    String quoteEndPoint;

    /**
     * The Rest template.
     */
    @Autowired
    RestTemplate restTemplate;

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<TickerResponse> getTickersByOrganizationName(String orgName) {
        List<TickerResponse> orgList = null;
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(String.format(tickerServiceEndPoint, orgName), List.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode()) && responseEntity.getBody() != null) {
            orgList = responseEntity.getBody();
        }
        return orgList;
    }

    @Override
    public CompanyProfileResponse getCompanyProfileByTicker(String ticker) {
        CompanyProfileResponse details = null;
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(String.format(companyProfileEndPoint, ticker), List.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode()) && responseEntity.getBody() != null) {
            details = mapper.convertValue(responseEntity.getBody().get(0),new TypeReference<CompanyProfileResponse>(){});
        }
        return details;
    }

    @Override
    public List<StockNewsResponse> getStockNewsByTicker(String ticker) {
        List<StockNewsResponse> newsList = null;
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(String.format(stockNewsEndPoint, ticker), Object.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode()) && responseEntity.getBody() != null) {
            LinkedHashMap map = (LinkedHashMap) responseEntity.getBody();
            ArrayList results = (ArrayList) map.get("results");
            newsList = (List<StockNewsResponse>) results;
        }
        return newsList;

    }

    @Override
    public QuoteResponse getQuoteByTicker(String ticker) {
        QuoteResponse details = null;
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(String.format(quoteEndPoint, ticker), List.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode()) && responseEntity.getBody() != null) {
            details = mapper.convertValue(responseEntity.getBody().get(0),new TypeReference<QuoteResponse>(){});
        }
        return details;
    }
}
