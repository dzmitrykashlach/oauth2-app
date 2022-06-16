package org.dzkashlach.entities;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditTransferTransaction {
    @SerializedName("instructedAmount")
    private InstructedAmount instructedAmount;
    @SerializedName("remittanceInformation")
    private RemittanceInformation remittanceInformation;
    @SerializedName("requestedExecutionDate")
    private String requestedExecutionDate;
    @SerializedName("numberOfTransactions")
    private int numberOfTransactions;
}
