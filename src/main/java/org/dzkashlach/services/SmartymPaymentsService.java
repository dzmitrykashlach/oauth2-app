package org.dzkashlach.services;

import org.dzkashlach.entities.PaymentRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.Map;

public interface SmartymPaymentsService {
    @POST("/payment-requests")
    Call<Void> requestPayment(@Body PaymentRequest paymentRequest);
}
