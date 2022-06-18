package org.dzkashlach.services;

import okhttp3.ResponseBody;
import org.dzkashlach.entities.PaymentRequest;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SmartymPaymentsService {

    @POST("/payment-requests")
    Call<okhttp3.ResponseBody> requestPayment(@Body PaymentRequest paymentRequest, @Header("Authorization") String authorization);

    @POST("/token")
    Call<ResponseBody> requestToken(@Query("client_id") String clientId, @Query("client_secret") String clientSecret, @Query("code") String code, @Query("scope") String scope);
}
