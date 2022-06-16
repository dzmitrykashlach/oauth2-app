package org.dzkashlach.entities;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class PaymentRequest {
    @SerializedName("beneficiary")
    private Beneficiary beneficiary;
    @SerializedName("creationDateTime")
    private String creationDateTime;
    @SerializedName("creditTransferTransaction")
    private CreditTransferTransaction creditTransferTransaction;
    @SerializedName("numberOfTransactions")
    private int numberOfTransactions;
    @SerializedName("paymentInformationId")
    private String paymentInformationId;
    @SerializedName("paymentTypeInformation")
    private PaymanentTypeInformation paymentTypeInformation;

    public PaymentRequest(PaymanentTypeInformation paymentInformationId) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.creationDateTime = dtf.format(now);
        numberOfTransactions = 0;
        this.paymentTypeInformation = paymentInformationId;
    }
}