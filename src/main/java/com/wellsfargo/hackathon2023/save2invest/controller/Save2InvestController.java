package com.wellsfargo.hackathon2023.save2invest.controller;

import com.wellsfargo.hackathon2023.save2invest.entity.request.CompanyProfileRequest;
import com.wellsfargo.hackathon2023.save2invest.entity.request.QuoteRequest;
import com.wellsfargo.hackathon2023.save2invest.entity.request.StockNewsRequest;
import com.wellsfargo.hackathon2023.save2invest.entity.response.CompanyProfileResponse;
import com.wellsfargo.hackathon2023.save2invest.entity.response.QuoteResponse;
import com.wellsfargo.hackathon2023.save2invest.entity.response.StockNewsResponse;
import com.wellsfargo.hackathon2023.save2invest.entity.response.TickerResponse;
import com.wellsfargo.hackathon2023.save2invest.service.ImageService;
import com.wellsfargo.hackathon2023.save2invest.service.QRCodeService;
import com.wellsfargo.hackathon2023.save2invest.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Save 2 invest controller.
 */
@RestController
public class Save2InvestController {

    /**
     * The Image service.
     */
    @Autowired
    ImageService imageService;

    /**
     * The Qr code service.
     */
    @Autowired
    QRCodeService qrCodeService;

    /**
     * The Ticker service.
     */
    @Autowired
    TickerService tickerService;

    /**
     * Gets brands from image.
     *
     * @param imageData the image data
     * @return the brands from image
     */
    @RequestMapping(value = "/getBrandsFromImage",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<Map> getBrandsFromImage(@RequestBody byte[] imageData) {
        return new ResponseEntity<Map>(imageService.getBrandsFromImage(imageData), HttpStatus.OK);
    }


    /**
     * Gets brands from qr code.
     *
     * @param qrCode the qr code
     * @return the brands from qr code
     */
    @RequestMapping(value = "/getBrandsFromQRCode",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getBrandsFromQRCode(@RequestBody File qrCode) {
        return new ResponseEntity<String>(qrCodeService.readQRCode(qrCode), HttpStatus.OK);
    }

    /**
     * Gets all info for all brands in image.
     *
     * @param imageData the image data
     * @return the all info for all brands in image
     */
    @RequestMapping(value = "/getAllInfoForBrandsInImage",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<Set<TickerResponse>> getAllInfoForAllBrandsInImage(@RequestBody byte[] imageData) {
        return new ResponseEntity<>((Set<TickerResponse>) imageService.getAllInfoForAllBrandsInImage(imageData), HttpStatus.OK);
    }

    /**
     * Gets company profile by ticker.
     *
     * @param companyProfileRequest the company profile request
     * @return the company profile by ticker
     */
    @RequestMapping(value = "/getCompanyProfileByTicker",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CompanyProfileResponse> getCompanyProfileByTicker(@RequestBody CompanyProfileRequest companyProfileRequest) {
        return new ResponseEntity<CompanyProfileResponse>(tickerService.getCompanyProfileByTicker(companyProfileRequest.getTicker()), HttpStatus.OK);
    }

    /**
     * Gets quotes by ticker.
     *
     * @param quoteRequest the quote request
     * @return the quotes by ticker
     */
    @RequestMapping(value = "/getQuotesByTicker",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<QuoteResponse> getQuotesByTicker(@RequestBody QuoteRequest quoteRequest) {
        return new ResponseEntity<QuoteResponse>(tickerService.getQuoteByTicker(quoteRequest.getTicker()), HttpStatus.OK);
    }

    /**
     * Gets quotes by ticker.
     *
     * @param stockNewsRequest the stock news request
     * @return the quotes by ticker
     */
    @RequestMapping(value = "/getStockNewsByTicker",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StockNewsResponse>> getQuotesByTicker(@RequestBody StockNewsRequest stockNewsRequest) {
        return new ResponseEntity<List<StockNewsResponse>>(tickerService.getStockNewsByTicker(stockNewsRequest.getTicker()), HttpStatus.OK);
    }
}
