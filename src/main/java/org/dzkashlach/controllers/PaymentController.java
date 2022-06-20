package org.dzkashlach.controllers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.dzkashlach.build.PaymentRequestBuilder;
import org.dzkashlach.entities.PaymentForm;
import org.dzkashlach.entities.PaymentRequest;
import org.dzkashlach.services.SmartymPaymentsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Controller
@Slf4j
@Scope("session")
public class PaymentController {

    @Value("${smartym.baseUrl}")
    private String SMARTYM_BASE_URL;
    @Value("${spring.security.oauth2.client.registration.smartym.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.client.registration.smartym.client-secret}")
    private String clientSecret;

    private Retrofit retrofit;
    private PaymentForm paymentForm;

    @GetMapping("/index")
    public String viewPaymentPage(Model model) {
        model.addAttribute("payment", new PaymentForm());
        return "index";
    }

    @PostConstruct
    public void init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(SMARTYM_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @PostMapping("/payment-requests")
    public String paymentRequests(@ModelAttribute("payment") PaymentForm paymentForm) {
        this.paymentForm = paymentForm;
        return "redirect:/signin";
    }

    @GetMapping("/signin")
    public void token() {
    }

    @GetMapping("/token")
    public String token(@RequestParam String code) {
        String scope = "pisp";
        SmartymPaymentsService smartymPaymentsService = retrofit.create(SmartymPaymentsService.class);
        Call<okhttp3.ResponseBody> responseBodyCall = smartymPaymentsService.requestToken(clientId, clientSecret, code, scope);
        Response<okhttp3.ResponseBody> response = null;
        try {
            response = responseBodyCall.execute();
        } catch (IOException e) {
            log.error("Failed to request token", e);
        }
        String accessToken = null;
        try {
            String responseBody = response.body().string();
            accessToken = extractAccessToken(responseBody);
        } catch (NullPointerException | IOException e) {
            log.error("Failed to parse access token from response body", e);
        }
        log.info("Received accessToken = " + accessToken);
        PaymentRequest paymentRequest = PaymentRequestBuilder.build(this.paymentForm);
        Call<okhttp3.ResponseBody> requestPaymentCall = smartymPaymentsService.requestPayment(paymentRequest, "Bearer " + accessToken);
        try {
            requestPaymentCall.execute();
        } catch (IOException e) {
            log.error("Failed to make payment request", e);
        }
        return "payment";
    }

    private String extractAccessToken(String responseBody) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(responseBody);
        JsonNode responseBodyJson = mapper.readTree(parser);
        return responseBodyJson.get("accessToken").asText();
    }
}