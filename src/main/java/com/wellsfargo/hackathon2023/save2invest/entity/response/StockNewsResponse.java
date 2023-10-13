package com.wellsfargo.hackathon2023.save2invest.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Stock news response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockNewsResponse {
    private String title;
    private String author;
    private String published_utc;
    private String article_url;
    private String amp_url;
    private String image_url;
    private String description;
}
