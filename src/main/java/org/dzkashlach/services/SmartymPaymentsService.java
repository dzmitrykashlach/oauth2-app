package org.dzkashlach.services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.Map;

public interface SmartymPaymentsService {
    @POST("payment-requests")
//    https://www.geeksforgeeks.org/how-to-post-data-to-api-using-retrofit-in-android/
    Call<Void> requestPayment(@Body Map<String, Object> body);
}
