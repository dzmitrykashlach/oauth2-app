package org.dzkashlach.entities;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Beneficiary {
    @SerializedName("creditor")
    private Creditor creditor;
    @SerializedName("creditorAccount")
    private CreditorAccount creditorAccount;
}
