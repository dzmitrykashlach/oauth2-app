package org.dzkashlach.entities;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Beneficiary {
    @SerializedName("creditor")
    private Creditor creditor;
    @SerializedName("creditorAccount")
    private CreditorAccount creditorAccount;
}
