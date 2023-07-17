package com.abel.airtimevtu.service;

import com.abel.airtimevtu.model.AirtimeRequest;
import com.abel.airtimevtu.model.AirtimeResponse;
import com.abel.airtimevtu.model.AppResponse;
import com.abel.airtimevtu.utility.PaymentHashGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AirtimeVtuService {

    //to do: implement exception handling, catch bad request exception
    //implement swagger?
    //write unit test create branch make change, merge and push

    private final WebClient webClient;

    private final PaymentHashGenerator paymentHashGenerator;

    @Value("${airtimevtu.baseurl}")
    private String baseUrl;

    @Value("${airtimevtu.publickey}")
    private String publicKey;

    @Value("${airtimevtu.privatekey}")
    private String privateKey;

    public Mono<AppResponse> fulfil(AirtimeRequest airtimeRequest) throws JsonProcessingException {

        //get the headers using paymentHashgenerator
        //make a call to xpresspayment airtime VTU endpoint
        //return the response to the controller for onward return to the client

        ObjectMapper mapper = new ObjectMapper();

        String requestBodyString = mapper.writeValueAsString(airtimeRequest);//convert request to JSON String using ObjectMapper

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + publicKey);
        headers.set("PaymentHash", paymentHashGenerator.calculateHMAC512(requestBodyString,privateKey));
        headers.set("Channel", "api");
        headers.setContentType(MediaType.APPLICATION_JSON);

        return  webClient.post().uri(baseUrl+"airtime/fulfil")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                 .body(Mono.just(airtimeRequest),AppResponse.class)
                .retrieve()
                .bodyToMono(AppResponse.class);
              //  .block();
    }
}
