package org.dzkashlach.controllers;

import org.dzkashlach.StateGenerator;
import org.dzkashlach.entities.Payment;
import org.dzkashlach.services.PaymentOauthServiceSmartymImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {
    @Value("${smartym.oauth.sandbox}")
    private String SMARTYM_OAUTH_SANDBOX;
    @Value("${smartym.oauth.client_id}")
    private String SMARTYM_OAUTH_CLIENT_ID;
    @Value("${smartym.oauth.redirect_uri}")
    private String SMARTYM_OAUTH_REDIRECT_URI;
    @Value("${smartym.oauth.response_type}")
    private String SMARTYM_OAUTH_RESPONSE_TYPE;
    @Value("${smartym.oauth.scope}")
    private String SMARTYM_OAUTH_SCOPE;

    @Autowired
    private PaymentOauthServiceSmartymImpl paymentOauthServiceSmartym;

    @GetMapping("/")
    public String viewPaymentPage(Model model) {
        model.addAttribute("payment", new Payment());
        return "index";
    }

    @PostMapping("/submit")
    public String submitPayment(@ModelAttribute("payment") Payment payment) {
        paymentOauthServiceSmartym.authorize();
        String redirect = SMARTYM_OAUTH_SANDBOX +
                "/signin?client_id=" + SMARTYM_OAUTH_CLIENT_ID +
                "&redirect_uri=" + SMARTYM_OAUTH_REDIRECT_URI +
                "&response_type=" + SMARTYM_OAUTH_RESPONSE_TYPE +
                "&scope=" + SMARTYM_OAUTH_SCOPE +
                "&state=" + StateGenerator.generate();
        return "redirect:" + redirect;
    }
}