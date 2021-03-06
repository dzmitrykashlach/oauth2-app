package org.dzkashlach.entities;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class InstructedAmount {
    @SerializedName("amount")
    private int amount;
    @SerializedName("currency")
    private String currency;
}
