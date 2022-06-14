package org.dzkashlach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class PaymentApp
{
    public static void main(String[] args) {
        SpringApplication.run(PaymentApp.class, args);
    }
}
