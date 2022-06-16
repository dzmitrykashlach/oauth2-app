package org.dzkashlach.controllers;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.dzkashlach.build.PaymentRequestBuilder;
import org.dzkashlach.entities.PaymentForm;
import org.dzkashlach.entities.PaymentRequest;
import org.dzkashlach.services.SmartymPaymentsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Controller
public class PaymentController {

    @Value("${smartym.baseUrl}")
    private String SMARTYM_BASE_URL;

    @GetMapping("/index")
    public String viewPaymentPage(Model model) {
        model.addAttribute("payment", new PaymentForm());
        return "index";
    }

    @PostMapping("/payment-requests")
    public String submitPayment(@ModelAttribute("payment") PaymentForm paymentForm) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SMARTYM_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        SmartymPaymentsService smartymPaymentsService = retrofit.create(SmartymPaymentsService.class);
        PaymentRequest paymentRequest = PaymentRequestBuilder.build(paymentForm);
        Call<Void> call =  smartymPaymentsService.requestPayment(paymentRequest);
        return "payment";
    }
}