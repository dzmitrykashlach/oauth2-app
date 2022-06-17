package org.dzkashlach.entities;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreditTransferTransaction {
    @SerializedName("instructedAmount")
    private InstructedAmount instructedAmount;
    @SerializedName("remittanceInformation")
    private RemittanceInformation remittanceInformation;
    @SerializedName("requestedExecutionDate")
    private String requestedExecutionDate;
}
