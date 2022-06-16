package org.dzkashlach.entities;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymanentTypeInformation {
    @SerializedName("beneficiary")
    private String categoryPurpose;
    @SerializedName("beneficiary")
    private String localInstrument;
    @SerializedName("beneficiary")
    private String serviceLevel;
}
