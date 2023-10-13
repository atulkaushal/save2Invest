package com.wellsfargo.hackathon2023.save2invest.service;

import com.wellsfargo.hackathon2023.save2invest.entity.response.TickerResponse;

import java.util.Map;
import java.util.Set;

/**
 * The interface Image service.
 */
public interface ImageService {

    /**
     * Gets brands from image.
     *
     * @param imageData the image data
     * @return the brands from image
     */
    Map<String, Integer> getBrandsFromImage(byte[] imageData);

    /**
     * Gets all info for all brands in image.
     *
     * @param imageData the image data
     * @return the all info for all brands in image
     */
    Set<TickerResponse> getAllInfoForAllBrandsInImage(byte[] imageData);
}
