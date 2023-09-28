package com.commerce.Ecommerce.paystack;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private boolean status;
    private String message;
    private Data data;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class  Data{

        @JsonProperty("authorization_url")
        private String authorizationUrl;
        @JsonProperty("access_code")
        private String accessCode;
        private String reference;

    }

}
