package com.wellsfargo.hackathon2023.save2invest.serviceImpl;

import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionClient;
import com.microsoft.azure.cognitiveservices.vision.computervision.ComputerVisionManager;
import com.microsoft.azure.cognitiveservices.vision.computervision.models.*;
import com.wellsfargo.hackathon2023.save2invest.entity.response.TickerResponse;
import com.wellsfargo.hackathon2023.save2invest.service.ImageService;
import com.wellsfargo.hackathon2023.save2invest.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * The type Image service.
 */
@Service
public class ImageServiceImpl implements ImageService {

    /**
     * The Key.
     */
    static String key = System.getenv("VISION_KEY");
    /**
     * The Endpoint.
     */
    static String endpoint = System.getenv("VISION_ENDPOINT");

    /**
     * The Ticker service.
     */
    @Autowired
    TickerService tickerService;


    /**
     * The Computer vision client.
     */
    ComputerVisionClient computerVisionClient = ComputerVisionManager.authenticate(key).withEndpoint(endpoint);

    @Override
    public Map<String, Integer> getBrandsFromImage(byte[] imageData) {
        return AnalyzeRemoteImage(computerVisionClient, imageData);
    }

    @Override
    public Set<TickerResponse> getAllInfoForAllBrandsInImage(byte[] imageData) {
        Map<String, Integer> brandMap = AnalyzeRemoteImage(computerVisionClient, imageData);
        Set<TickerResponse> tickerResponses = new HashSet();
        Set<String> brands = brandMap.keySet();
        for (String brand : brands) {
            tickerResponses.addAll(tickerService.getTickersByOrganizationName(brand));
        }
        return tickerResponses;
    }

    private Map<String, Integer> AnalyzeRemoteImage(ComputerVisionClient compVisClient, byte[] imageData) {
        Map<String, Integer> brandMap = new HashMap<>();
        // This list defines the features to be extracted from the image.
        List<VisualFeatureTypes> featuresToExtractFromRemoteImage = new ArrayList<>();
        featuresToExtractFromRemoteImage.add(VisualFeatureTypes.BRANDS);

        System.out.println("\n\nAnalyzing an image...");

        try {
            // Call the Computer Vision service and tell it to analyze the loaded image.
            ImageAnalysis analysis = compVisClient.computerVision().analyzeImageInStream().withImage(imageData)
                    .withVisualFeatures(featuresToExtractFromRemoteImage).withLanguage("en").execute();

            // Display image tags and confidence values.
            System.out.println("\nBrands: ");
            for (DetectedBrand tag : analysis.brands()) {
                System.out.printf("\'%s\' with confidence %f\n", tag.name(), tag.confidence());
                brandMap.put(tag.name(), Double.valueOf(tag.confidence() * 100).intValue());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return brandMap;
    }

   /* @Bean
    private static ComputerVisionClient Authenticate(String key, String endpoint){
        return ComputerVisionManager.authenticate(key).withEndpoint(endpoint);
    }*/
}
