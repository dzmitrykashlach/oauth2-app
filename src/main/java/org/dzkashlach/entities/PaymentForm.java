package org.dzkashlach.entities;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PaymentForm {
    private String name;
    private String iban;
    private int amount;
    private String currency;
    private String description;
}