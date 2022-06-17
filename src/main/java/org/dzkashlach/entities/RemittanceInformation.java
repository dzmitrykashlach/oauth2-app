package org.dzkashlach.entities;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class RemittanceInformation {
    @SerializedName("unstructured")
    private String unstructured;
}
