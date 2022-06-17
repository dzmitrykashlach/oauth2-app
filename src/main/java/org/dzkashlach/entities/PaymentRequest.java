package org.dzkashlach.entities;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
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

}