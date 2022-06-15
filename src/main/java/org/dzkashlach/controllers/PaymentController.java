package org.dzkashlach.controllers;

import org.dzkashlach.entities.Payment;
import org.dzkashlach.services.PaymentRequestService;
import org.dzkashlach.services.PaymentRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Controller
public class PaymentController {

    @Autowired
    private PaymentRequestServiceImpl paymentOauthServiceSmartym;

    @GetMapping("/index")
    public String viewPaymentPage(Model model) {
        model.addAttribute("payment", new Payment());
        return "index";
    }

    @GetMapping("/done")
    public String done(Model model) {
        return "payment";
    }

    @PostMapping("/submit")
    public String submitPayment(@ModelAttribute("payment") Payment payment) {
        //wait for authorization and send payment request with retrofit
//        https://square.github.io/retrofit/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://test.devenv.smartym.by/")
                .addConverterFactory(GsonConverterFactory.create()).build();
            PaymentRequestService paymentRequestService = retrofit.create(PaymentRequestService.class);
        paymentRequestService.execute();
            return "payment";
    }

    @GetMapping("/initPayment")
    public String initPayment(Model model) {
        return "initpayment";
    }
}