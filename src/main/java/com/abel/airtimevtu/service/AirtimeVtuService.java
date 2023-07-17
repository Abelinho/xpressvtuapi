package com.abel.airtimevtu.service;

import com.abel.airtimevtu.model.AirtimeRequest;
import com.abel.airtimevtu.model.AirtimeResponse;
import com.abel.airtimevtu.model.AppResponse;
import com.abel.airtimevtu.utility.PaymentHashGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Setter
public class AirtimeVtuService {

    private final WebClient webClient;

    private final PaymentHashGenerator paymentHashGenerator;

    @Value("${airtimevtu.baseurl}")
    private String baseUrl;

    @Value("${airtimevtu.publickey}")
    private String publicKey;

    @Value("${airtimevtu.privatekey}")
    private String privateKey;

    public AppResponse<AirtimeResponse> fulfil(AirtimeRequest airtimeRequest) throws JsonProcessingException {

        //get the headers using paymentHashgenerator
        //make a call to xpresspayment airtime VTU endpoint
        //return the response to the controller for onward return to the client

        ObjectMapper mapper = new ObjectMapper();

        String requestBodyString = mapper.writeValueAsString(airtimeRequest);//convert request to JSON String using ObjectMapper

        String url = baseUrl+"airtime/fulfil";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + publicKey);
        headers.set("PaymentHash", paymentHashGenerator.calculateHMAC512(requestBodyString,privateKey));
        headers.set("Channel", "api");
        headers.setContentType(MediaType.APPLICATION_JSON);

        //////////////////////

//        HttpEntity<?> entity = new HttpEntity<>(headers);
//
//        Map<String, String> params = new HashMap<>();
//
//        ResponseEntity<AppResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, AppResponse.class,params);
//
//        return response.getBody();

        return  webClient.post().uri(baseUrl+"airtime/fulfil")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                 .body(Mono.just(airtimeRequest),AppResponse.class)
                .retrieve()
                .bodyToMono(AppResponse.class)
                .block();
    }
}
