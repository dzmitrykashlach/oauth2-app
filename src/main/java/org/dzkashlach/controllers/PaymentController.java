package org.dzkashlach.controllers;

import org.dzkashlach.entities.Payment;
import org.dzkashlach.services.PaymentOauthServiceSmartymImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {

    @Autowired
    private PaymentOauthServiceSmartymImpl paymentOauthServiceSmartym;

    @GetMapping("/index")
    public String viewPaymentPage(Model model) {
        model.addAttribute("payment", new Payment());
        return "index";
    }

    @PostMapping("/submit")
    public String submitPayment(@ModelAttribute("payment") Payment payment) {
        return "payment";
    }

    @GetMapping("/initPayment")
    public String initPayment(Model model) {
        return "initpayment";
    }
}