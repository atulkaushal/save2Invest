package com.wellsfargo.hackathon2023.save2invest.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * The type Company profile request.
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CompanyProfileRequest {
    /**
     * The Ticker.
     */
    @NotNull
    String ticker;
}
