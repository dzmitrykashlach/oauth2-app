package org.dzkashlach.build;


import org.dzkashlach.entities.PaymanentTypeInformation;
import org.dzkashlach.entities.PaymentForm;
import org.dzkashlach.entities.PaymentRequest;

public class PaymentRequestBuilder {

      public static PaymentRequest build(PaymentForm paymentForm){
            return new PaymentRequest(new PaymanentTypeInformation());
      }
}
