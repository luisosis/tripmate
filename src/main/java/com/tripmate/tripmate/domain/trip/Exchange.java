package com.tripmate.tripmate.domain.trip;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exchange {
    private String result;
    private String base_code;
    private String target_code;
    private Double conversion_rate;
    private Double conversion_result;
}
