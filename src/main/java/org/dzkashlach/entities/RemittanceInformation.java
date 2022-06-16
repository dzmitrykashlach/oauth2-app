package org.dzkashlach.entities;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemittanceInformation {
    @SerializedName("unstructured")
    private String unstructured;
}
