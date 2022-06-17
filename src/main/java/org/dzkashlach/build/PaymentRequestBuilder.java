package org.dzkashlach.build;

import org.dzkashlach.RandomStringGenerator;
import org.dzkashlach.entities.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentRequestBuilder {

    public static PaymentRequest build(PaymentForm paymentForm) {
        Creditor creditor = new Creditor(paymentForm.getName());
        CreditorAccount creditorAccount = new CreditorAccount(paymentForm.getIban());
        Beneficiary beneficiary = new Beneficiary(creditor, creditorAccount);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime creationDateTime = LocalDateTime.now();
        String now = dtf.format(creationDateTime);

        InstructedAmount instructedAmount = new InstructedAmount(paymentForm.getAmount(), paymentForm.getCurrency());
        RemittanceInformation remittanceInformation = new RemittanceInformation(paymentForm.getDescription());
        CreditTransferTransaction creditTransferTransaction = new CreditTransferTransaction(instructedAmount, remittanceInformation, now);

        int numberOfTransactions = 0;
        String paymentInformationId = new RandomStringGenerator(15).nextString();
        PaymanentTypeInformation paymanentTypeInformation = new PaymanentTypeInformation("","","");
        return new PaymentRequest(beneficiary,now,creditTransferTransaction,numberOfTransactions,paymentInformationId,paymanentTypeInformation);
    }
}
