package com.abel.airtimevtu.service;

import com.abel.airtimevtu.model.AirtimeRequest;
import com.abel.airtimevtu.model.AirtimeResponse;
import com.abel.airtimevtu.model.AppResponse;
import com.abel.airtimevtu.model.Details;
import com.abel.airtimevtu.utility.AppUtility;
import com.abel.airtimevtu.utility.PaymentHashGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AirtimeVtuServiceTest {

    @Mock
    private WebClient webClient;
    @Mock
    private WebClient.RequestBodyUriSpec uriSpec;
    @Mock
    private WebClient.RequestBodyUriSpec headerSpec;

//    @InjectMocks
//    private AirtimeVtuService airtimeVtuService;
//
//    //@InjectMocks
//    //@Mock
//    //PaymentHashGenerator paymentHashGenerator = new PaymentHashGenerator();
//
    @InjectMocks
    AirtimeRequest airtimeRequest;
//
//    private AirtimeResponse airtimeResponse;
//
//    //@Mock
//    WebClient webClient = WebClient.builder().build();;
//
    @Value("${airtimevtu.baseurl}")
    private String baseUrl;
//
//    @Value("${airtimevtu.publickey}")
//    private String publicKey;
//
//    //@Value("${airtimevtu.privatekey}")
//    private String privateKey = "1SOo6tt8oS0xpJrzObHlOc9tPVMPWQKi_CVASPRV";
//
//    private String requestId = AppUtility.generateUniqueId("123456789");
//
//    PaymentHashGenerator paymentHashGenerator = new PaymentHashGenerator();
//
//
//
//    private String refId = AppUtility.generateUniqueId("123456789");
//
//    private String requestBodyString;

    @BeforeEach
    void setUp() {
//
        airtimeRequest = AirtimeRequest.builder()
               // .requestId(requestId)
                .requestId("12345")
                .uniqueCode("WAEC_21565")
                .details(new Details("08067950474",new BigDecimal(100)))
                .build();
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//
//            requestBodyString = mapper.writeValueAsString(airtimeRequest);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        //test code start
//
//        paymentHashGenerator.calculateHMAC512(requestBodyString,privateKey);
//
//        //test code stop
//
//        airtimeVtuService = new AirtimeVtuService(webClient,paymentHashGenerator);
//        airtimeVtuService.setBaseUrl(baseUrl);
//        airtimeVtuService.setPrivateKey(privateKey);
//        airtimeVtuService.setPublicKey(publicKey);

//        airtimeResponse = AirtimeResponse.builder()
//                .amount(new BigDecimal(2900))
//                .channel("api")
//                .phoneNumber("08067950474")
//                .build();

    }

    @Test
    void fulfil() {

        WebClient.RequestBodyUriSpec bodySpec = mock(WebClient.RequestBodyUriSpec.class);
        WebClient.ResponseSpec response = mock(WebClient.ResponseSpec.class);

        when(webClient.post()).thenReturn(uriSpec);
        when(uriSpec.uri(baseUrl)).thenReturn(headerSpec);
        doReturn(bodySpec).when(headerSpec).bodyValue(airtimeRequest);
        when(bodySpec.retrieve()).thenReturn(response);

        //////////////////////////

        // Mock response
//      AppResponse<AirtimeResponse> appResponse = AppResponse.<AirtimeResponse>builder()
//              .referenceId(refId)
//              .requestId(requestId)//is unique per request
//              .responseCode("94")
//              .responseMessage("Biller timeout error")
//              .data(airtimeResponse)
//              .build();
//        // Set up the mock response data here

//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + publicKey);
//        headers.set("PaymentHash", paymentHashGenerator.calculateHMAC512(requestBodyString,privateKey));
//        headers.set("Channel", "api");
//        headers.setContentType(MediaType.APPLICATION_JSON);

        // Call the method under test
//        try {
//            AppResponse<AirtimeResponse> result = airtimeVtuService.fulfil(airtimeRequest);
//
//            assertNotNull(result);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

//        WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock = mock(WebClient.RequestHeadersUriSpec.class);
//        WebClient.RequestHeadersSpec requestHeadersSpecMock = mock(WebClient.RequestHeadersSpec.class);
//        WebClient.ResponseSpec responseSpecMock = mock(WebClient.ResponseSpec.class);


//        when(webClient.post()).thenReturn(requestHeadersUriSpecMock);
//        when(requestHeadersUriSpecMock.uri(eq(baseUrl + "airtime/fulfil"))).thenReturn(requestHeadersSpecMock);
//        when(requestHeadersSpecMock.headers(any())).thenReturn(requestHeadersSpecMock);
//        when(requestHeadersSpecMock.body(any(Mono.class), eq(AppResponse.class))).thenReturn(requestHeadersSpecMock);
//        when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
//        when(responseSpecMock.bodyToMono(eq(AppResponse.class))).thenReturn(Mono.just(mockedAppResponse));
//        when(responseSpecMock.block()).thenReturn(mockedAppResponse);

//        List<CityData> listOfCity = new ArrayList<>();
//        listOfCity.add(cityData);
//
//        // Mock response
//        CityResponse mockCityResponse = CityResponse.builder()
//                .error(false)
//                .message("successful")
//                .data(listOfCity)
//                .build();
//        // Set up the mock response data here
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<?> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<CityResponse> mockResponseEntity = new ResponseEntity<>(mockCityResponse, HttpStatus.OK);
//
//        when(restTemplate.exchange(
//                anyString(),
//                eq(HttpMethod.GET),
//                any(HttpEntity.class),
//                eq(CityResponse.class),
//                anyMap()
//        )).thenReturn(mockResponseEntity);
//
//        // Test input values
//        String country = "Italy";
//        Integer numOfCities = 10;
//
//        // Invoke the method
//        CityResponse result = cityService.getTopCitiesByPopulation(country, numOfCities);
//
//        // Assert the expected result
//        assertEquals(mockCityResponse, result);

    }
}