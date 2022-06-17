package org.dzkashlach.entities;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymanentTypeInformation {
    @SerializedName("categoryPurpose")
    private String categoryPurpose;
    @SerializedName("localInstrument")
    private String localInstrument;
    @SerializedName("serviceLevel")
    private String serviceLevel;
}
